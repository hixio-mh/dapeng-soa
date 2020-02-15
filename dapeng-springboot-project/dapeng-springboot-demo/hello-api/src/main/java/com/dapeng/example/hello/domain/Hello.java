package com.dapeng.example.hello.domain;

        import java.util.Optional;
        import com.github.dapeng.org.apache.thrift.TException;
        import com.github.dapeng.org.apache.thrift.protocol.TCompactProtocol;
        import com.github.dapeng.util.TCommonTransport;

        /**
         * Autogenerated by Dapeng-Code-Generator (2.1.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

        *

 hello

        **/
        public class Hello{
        
            /**
            *

 name

            **/
            public String name ;
            public String getName(){ return this.name; }
            public void setName(String name){ this.name = name; }

            public String name(){ return this.name; }
            public Hello name(String name){ this.name = name; return this; }
          
            /**
            *

 message

            **/
            public Optional<String> message = Optional.empty();
            public Optional<String> getMessage(){ return this.message; }
            public void setMessage(Optional<String> message){ this.message = message; }

            public Optional<String> message(){ return this.message; }
            public Hello message(Optional<String> message){ this.message = message; return this; }
          

        public static byte[] getBytesFromBean(Hello bean) throws TException {
          byte[] bytes = new byte[]{};
          TCommonTransport transport = new TCommonTransport(bytes, TCommonTransport.Type.Write);
          TCompactProtocol protocol = new TCompactProtocol(transport);

          new com.dapeng.example.hello.domain.serializer.HelloSerializer().write(bean, protocol);
          transport.flush();
          return transport.getByteBuf();
        }

        public static Hello getBeanFromBytes(byte[] bytes) throws TException {
          TCommonTransport transport = new TCommonTransport(bytes, TCommonTransport.Type.Read);
          TCompactProtocol protocol = new TCompactProtocol(transport);
          return new com.dapeng.example.hello.domain.serializer.HelloSerializer().read(protocol);
        }

        public String toString(){
          StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"").append("name").append("\":\"").append(this.name).append("\",");
    stringBuilder.append("\"").append("message").append("\":\"").append(this.message.isPresent()?this.message.get():null).append("\",");
    
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
            stringBuilder.append("}");

          return stringBuilder.toString();
        }
      }
      