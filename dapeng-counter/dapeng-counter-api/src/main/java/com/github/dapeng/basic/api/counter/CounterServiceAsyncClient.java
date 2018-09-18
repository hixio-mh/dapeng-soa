package com.github.dapeng.basic.api.counter;

      import com.github.dapeng.core.*;
      import com.github.dapeng.org.apache.thrift.*;
      import java.util.concurrent.CompletableFuture;
      import java.util.concurrent.Future;
      import java.util.ServiceLoader;
      import com.github.dapeng.basic.api.counter.CounterServiceAsyncCodec.*;
      import com.github.dapeng.basic.api.counter.CounterServiceSuperCodec.*;
      import com.github.dapeng.basic.api.counter.service.CounterServiceAsync;

      /**
       * Autogenerated by Dapeng-Code-Generator (2.0.5)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

      **/
      public class CounterServiceAsyncClient implements CounterServiceAsync{
      private final String serviceName;
      private final String version;

      private SoaConnectionPool pool;
      private final SoaConnectionPool.ClientInfo clientInfo;

      public CounterServiceAsyncClient() {
        this.serviceName = "com.github.dapeng.basic.api.counter.service.CounterService";
        this.version = "1.0.0";

        ServiceLoader<SoaConnectionPoolFactory> factories = ServiceLoader.load(SoaConnectionPoolFactory.class,getClass().getClassLoader());
        this.pool = factories.iterator().next().getPool();
        this.clientInfo = this.pool.registerClientInfo(serviceName,version);
      }

      
          
            /**
            * 
            **/
            
              public CompletableFuture<Void> submitPoint(com.github.dapeng.basic.api.counter.domain.DataPoint dataPoint) throws SoaException{

              String methodName = "submitPoint";
              submitPoint_args submitPoint_args = new submitPoint_args();
              submitPoint_args.setDataPoint(dataPoint);
                

              CompletableFuture<submitPoint_result> response = (CompletableFuture<submitPoint_result>) pool.sendAsync(serviceName,version,"submitPoint",submitPoint_args, new SubmitPoint_argsSerializer(), new SubmitPoint_resultSerializer());

              
                  return response.thenApply((submitPoint_result result )->  null);
                
            }
            
          

        
          
            /**
            * 
            **/
            
              public CompletableFuture<Void> submitPoints(java.util.List<com.github.dapeng.basic.api.counter.domain.DataPoint> dataPoints) throws SoaException{

              String methodName = "submitPoints";
              submitPoints_args submitPoints_args = new submitPoints_args();
              submitPoints_args.setDataPoints(dataPoints);
                

              CompletableFuture<submitPoints_result> response = (CompletableFuture<submitPoints_result>) pool.sendAsync(serviceName,version,"submitPoints",submitPoints_args, new SubmitPoints_argsSerializer(), new SubmitPoints_resultSerializer());

              
                  return response.thenApply((submitPoints_result result )->  null);
                
            }
            
          

        
          
            /**
            * 
            **/
            
              public CompletableFuture<java.util.List<com.github.dapeng.basic.api.counter.domain.DataPoint>> queryPoints(com.github.dapeng.basic.api.counter.domain.DataPoint condition,String beginTimeStamp,String endTimeStamp) throws SoaException{

              String methodName = "queryPoints";
              queryPoints_args queryPoints_args = new queryPoints_args();
              queryPoints_args.setCondition(condition);
                queryPoints_args.setBeginTimeStamp(beginTimeStamp);
                queryPoints_args.setEndTimeStamp(endTimeStamp);
                

              CompletableFuture<queryPoints_result> response = (CompletableFuture<queryPoints_result>) pool.sendAsync(serviceName,version,"queryPoints",queryPoints_args, new QueryPoints_argsSerializer(), new QueryPoints_resultSerializer());

              
                  
                      return response.thenApply((queryPoints_result result )->  result.getSuccess());
                    
                
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
    