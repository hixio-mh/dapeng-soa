/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.today.api.stock;

      import com.github.dapeng.core.*;
      import com.github.dapeng.org.apache.thrift.*;
      import java.util.ServiceLoader;
      import com.today.api.stock.StockService2Codec.*;
      import com.today.api.stock.StockService2SuperCodec.*;
      import com.today.api.stock.service.StockService2;

      /**
       * Autogenerated by Dapeng-Code-Generator (2.1.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

      **/
      public class StockService2Client implements StockService2{
      private final String serviceName;
      private final String version;

      private SoaConnectionPool pool;
      private final SoaConnectionPool.ClientInfo clientInfo;

      public StockService2Client() {
        this.serviceName = "com.today.api.stock.service.StockService2";
        this.version = "1.0.0";

        ServiceLoader<SoaConnectionPoolFactory> factories = ServiceLoader.load(SoaConnectionPoolFactory.class,getClass().getClassLoader());
        this.pool = factories.iterator().next().getPool();
        this.clientInfo = this.pool.registerClientInfo(serviceName,version);
      }

      public StockService2Client(String serviceVersion) {
        this.serviceName = "com.today.api.stock.service.StockService2";
        this.version = serviceVersion;

        ServiceLoader<SoaConnectionPoolFactory> factories = ServiceLoader.load(SoaConnectionPoolFactory.class,getClass().getClassLoader());
        this.pool = factories.iterator().next().getPool();
        this.clientInfo = this.pool.registerClientInfo(serviceName,version);
      }

      
          
            /**
            * 

# 实时库存处理接口
## 业务描述
	处理实时库存
## 接口依赖
	无
## 边界异常说明
	无
## 输入
	1. list<stock_request.AtomProcessStockItem> 库存处理请求头
## 前置检查
	1. 一次最多处理1000个 sku 的库存, 如果超过1000个sku 请下游调用方自己做分页处理
	2. sourceTime 必须是今天内的时间, 不能处理过去的. 有过去时间的记录 error 日志
	3. 用消息处理库存的请求必须有kafkaId字段
## 逻辑处理
	1. 查询库存主体(ownerId, ownerType, skuNo), 不存在插入库存主体
	2. 插入库存流水, 对于事件处理的库存需要通过 kafkaId 查询库存流水是否已经处理过了, 如果已经处理过则不再重复处理
	3. 更新库存信息
## 数据库变更
	1. insert stock_journal
	2. update stock
## 后置检查
	无
## 事务处理
	默认事务处理
## 输出
	无

            **/
            
              public java.util.List<com.today.api.purchase.response.ProcessStockResponseItem> processStock(com.today.api.stock.events.StockEvent event) throws SoaException{

              String methodName = "processStock";

              processStock_args processStock_args = new processStock_args();
              processStock_args.setEvent(event);
                

              processStock_result response = pool.send(serviceName,version,"processStock",processStock_args, new ProcessStock_argsSerializer(), new ProcessStock_resultSerializer());

              
                  
                      return response.getSuccess();
                    
                
            }
            
          

        
          
            /**
            * 

# 支持处理零散商品实时库存处理接口
## 业务描述
	处理实时库存: 暂时只有盘点业务调用
## 接口依赖
	无
## 边界异常说明
	无
## 输入
	1. list<stock_request.ProcessStockItem> 库存处理请求头
## 前置检查
	1. 一次最多处理1000个 sku 的库存, 如果超过1000个sku 请下游调用方自己做分页处理
	2. sourceTime 必须是今天内的时间, 不能处理过去的. 有过去时间的记录 error 日志
	3. 用消息处理库存的请求必须有kafkaId字段
## 逻辑处理
	1. 查询库存主体(ownerId, ownerType, skuNo), 不存在插入库存主体
	2. 插入库存流水, 对于事件处理的库存需要通过 kafkaId 查询库存流水是否已经处理过了, 如果已经处理过则不再重复处理
	3. 更新库存信息
## 数据库变更
	1. insert stock_journal
	2. update stock
## 后置检查
	无
## 事务处理
	默认事务处理
## 输出
	无

            **/
            
              public java.util.List<com.today.api.purchase.response.ProcessStockResponseItem> processStockByPiecemeal(java.util.List<com.today.api.purchase.request.ProcessStockItem> request) throws SoaException{

              String methodName = "processStockByPiecemeal";

              processStockByPiecemeal_args processStockByPiecemeal_args = new processStockByPiecemeal_args();
              processStockByPiecemeal_args.setRequest(request);
                

              processStockByPiecemeal_result response = pool.send(serviceName,version,"processStockByPiecemeal",processStockByPiecemeal_args, new ProcessStockByPiecemeal_argsSerializer(), new ProcessStockByPiecemeal_resultSerializer());

              
                  
                      return response.getSuccess();
                    
                
            }
            
          

        

      /**
      * getServiceMetadata
      **/
      public String getServiceMetadata() throws SoaException {
        String methodName = "getServiceMetadata";
        getServiceMetadata_args getServiceMetadata_args = new getServiceMetadata_args();
        getServiceMetadata_result response = pool.send(serviceName,version,methodName,getServiceMetadata_args, new GetServiceMetadata_argsSerializer(), new GetServiceMetadata_resultSerializer());
        return response.getSuccess();
      }

      /**
      * echo
      **/
      public String echo() throws SoaException {
        String methodName = "echo";
        echo_args echo_args = new echo_args();
        echo_result response = pool.send(serviceName,version,methodName,echo_args, new echo_argsSerializer(), new echo_resultSerializer());
        return response.getSuccess();
      }


    }
    