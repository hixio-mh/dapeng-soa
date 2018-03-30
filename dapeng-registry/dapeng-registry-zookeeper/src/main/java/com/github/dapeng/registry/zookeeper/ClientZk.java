package com.github.dapeng.registry.zookeeper;

import com.github.dapeng.core.version.Version;
import com.github.dapeng.registry.ConfigKey;
import com.github.dapeng.registry.RuntimeInstance;
import com.github.dapeng.registry.ServiceInfo;
import com.github.dapeng.route.Route;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * zookeeperClient 主要作用是 供 客户端 从zk获取服务实例
 *
 * @author tangliu
 * @date 2016/2/29
 */
public class ClientZk extends CommonZk {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientZk.class);

    private final Map<String, List<ServiceInfo>> caches = new ConcurrentHashMap<>();
    /**
     * 其他配置信息
     */
    private final Map<String, Map<ConfigKey, Object>> config = new ConcurrentHashMap<>();
    /**
     * 路由配置信息
     */
    private final List<Route> routes = new ArrayList<>();


    public ClientZk(String zkHost) {
        this.zkHost = zkHost;
    }

    public void init() {
        connect();
    }

    /**
     * 连接zookeeper
     */
    private void connect() {
        try {
            CountDownLatch semaphore = new CountDownLatch(1);

            // Fixme zk连接状态变化不应该引起本地runtime缓存的清除, 尤其是zk挂了之后, 不至于影响业务(本地缓存还存在于每个SoaConnectionPool中?)
            zk = new ZooKeeper(zkHost, 15000, e -> {
                switch (e.getState()) {
                    case Expired:
                        LOGGER.info("Client's host: {} 到zookeeper Server的session过期，重连", zkHost);

                        destroy();
                        init();
                        break;
                    case SyncConnected:
                        semaphore.countDown();
                        LOGGER.info("Client's host: {}  已连接 zookeeper Server", zkHost);
                        caches.clear();
                        config.clear();
                        zkConfigMap.clear();
                        break;
                    case Disconnected:
                        LOGGER.error("Client's host: {} 到zookeeper的连接被断开", zkHost);
                        destroy();
                        init();
                        break;
                    case AuthFailed:
                        LOGGER.info("Zookeeper connection auth failed ...");
                        destroy();
                        break;
                    default:
                        break;
                }
            });
            semaphore.await(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    public void destroy() {
        if (zk != null) {
            try {
                LOGGER.info("Client's host: {} 关闭到zookeeper的连接", zkHost);
                zk.close();
                zk = null;
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * route 目前暂未实现
     *
     * @return
     */
    public List<Route> getRoutes() {
        return this.routes;
    }


    public List<ServiceInfo> getServiceInfoCached(String serviceName, String versionName, boolean compatible) {

        List<ServiceInfo> serverList;

        if (caches.containsKey(serviceName)) {
            serverList = caches.get(serviceName);
        } else {
            //get service Info and set up Watcher
            getServiceInfoByServiceName(serviceName);
            serverList = caches.get(serviceName);
        }

        List<ServiceInfo> usableList = new ArrayList<>();

        if (serverList != null && serverList.size() > 0) {

            if (!compatible) {
                usableList.addAll(serverList.stream().filter(server -> server.versionName.equals(versionName)).collect(Collectors.toList()));
            } else {
                usableList.addAll(serverList.stream().filter(server -> Version.toVersion(versionName).compatibleTo(Version.toVersion(server.versionName))).collect(Collectors.toList()));
            }
        }
        return usableList;
    }

    public ZkServiceInfo syncServiceZkInfo(String serviceName, Map<String, ZkServiceInfo> zkInfos) {
        String servicePath = SERVICE_PATH + "/" + serviceName;
        try {
            if (zk == null) {
                LOGGER.info(getClass().getSimpleName() + "::syncServiceZkInfo:zk is null, now init()");
                init();
            }

            List<String> childrens = zk.getChildren(servicePath, watchedEvent -> {
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                    if (zkInfos.containsKey(serviceName)) {
                        LOGGER.info(getClass().getSimpleName() + "::syncServiceZkInfo:{}子节点发生变化，重新获取信息", watchedEvent.getPath());
                        syncServiceZkInfo(serviceName, zkInfos);
                    } else {
                        LOGGER.info(getClass().getSimpleName() + "::syncServiceZkInfo:service:" + serviceName + " has been removed");
                    }
                }
            });

            if (childrens.size() == 0) {
                LOGGER.info(getClass().getSimpleName() + "::syncServiceZkInfo:no service instances found");
                return null;
            }
            List<RuntimeInstance> runtimeInstanceList = new ArrayList<>();
            LOGGER.info(getClass().getSimpleName() + "::syncServiceZkInfo, 获取{}的子节点成功", servicePath);
            //child = 10.168.13.96:9085:1.0.0
            for (String children : childrens) {
                String[] infos = children.split(":");
                RuntimeInstance instance = new RuntimeInstance(serviceName, infos[0], Integer.valueOf(infos[1]), infos[2]);
                runtimeInstanceList.add(instance);
            }
            ZkServiceInfo zkInfo = new ZkServiceInfo(serviceName, runtimeInstanceList);

            zkInfos.put(serviceName, zkInfo);
            return zkInfo;

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 根据serviceName节点的路径，获取下面的子节点，并监听子节点变化
     *
     * @param serviceName
     */
    private void getServiceInfoByServiceName(String serviceName) {

        String servicePath = SERVICE_PATH + "/" + serviceName;
        try {

            if (zk == null) {
                init();
            }

            List<String> children = zk.getChildren(servicePath, watchedEvent -> {
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                    LOGGER.info("{}子节点发生变化，重新获取信息", watchedEvent.getPath());
                    getServiceInfoByServiceName(serviceName);
                }
            });

            LOGGER.info("获取{}的子节点成功", servicePath);
            WatcherUtils.resetServiceInfoByName(serviceName, servicePath, children, caches);

        } catch (KeeperException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


}
