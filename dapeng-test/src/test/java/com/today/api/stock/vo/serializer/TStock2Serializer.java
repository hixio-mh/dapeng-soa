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
package com.today.api.stock.vo.serializer;
        import com.today.api.purchase.request.serializer.*;import com.today.api.common.serializer.*;import com.today.api.purchase.response.serializer.*;import com.today.api.stock.response.serializer.*;import com.today.api.stock.request.serializer.*;import com.today.api.stock.events.serializer.*;import com.today.api.stock.vo.serializer.*;

        import com.github.dapeng.core.*;
        import com.github.dapeng.org.apache.thrift.*;
        import com.github.dapeng.org.apache.thrift.protocol.*;

        import java.util.Optional;
        import java.util.concurrent.CompletableFuture;
        import java.util.concurrent.Future;

        /**
        * Autogenerated by Dapeng-Code-Generator (2.1.1)
        *
        * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
        *
        **/
        public class TStock2Serializer implements BeanSerializer<com.today.api.stock.vo.TStock2>{
        
      @Override
      public com.today.api.stock.vo.TStock2 read(TProtocol iprot) throws TException{

      com.today.api.stock.vo.TStock2 bean = new com.today.api.stock.vo.TStock2();
      TField schemeField;
      iprot.readStructBegin();

      while(true){
        schemeField = iprot.readFieldBegin();
        if(schemeField.type == TType.STOP){ break;}

        switch(schemeField.id){
          
              case 1:
              if(schemeField.type == TType.I64){
              long elem0 = iprot.readI64();
       bean.setId(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 2:
              if(schemeField.type == TType.STRING){
              String elem0 = iprot.readString();
       bean.setOwnerId(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 3:
              if(schemeField.type == TType.I32){
              com.today.api.stock.enums.StockOwnerTypeEnum elem0 = com.today.api.stock.enums.StockOwnerTypeEnum.findByValue(iprot.readI32());
       bean.setOwnerType(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 4:
              if(schemeField.type == TType.STRING){
              String elem0 = iprot.readString();
       bean.setSkuNo(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 5:
              if(schemeField.type == TType.STRING){
              java.math.BigDecimal elem0 = new java.math.BigDecimal(iprot.readString());
       bean.setStockNum(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 6:
              if(schemeField.type == TType.I32){
              com.today.api.stock.enums.StockIsDeletedEnum elem0 = com.today.api.stock.enums.StockIsDeletedEnum.findByValue(iprot.readI32());
       bean.setIsDeleted(Optional.of(elem0));
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 7:
              if(schemeField.type == TType.I64){
              long elem0 = iprot.readI64();
       bean.setCreatedAt(Optional.of(elem0));
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 8:
              if(schemeField.type == TType.I32){
               int elem0 = iprot.readI32();
       bean.setCreatedBy(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 9:
              if(schemeField.type == TType.I64){
              long elem0 = iprot.readI64();
       bean.setUpdatedAt(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 10:
              if(schemeField.type == TType.I32){
               int elem0 = iprot.readI32();
       bean.setUpdatedBy(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 11:
              if(schemeField.type == TType.STRING){
              String elem0 = iprot.readString();
       bean.setRemark(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
          
            default:
            TProtocolUtil.skip(iprot, schemeField.type);
          
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      validate(bean);
      return bean;
    }
    
      @Override
      public void write(com.today.api.stock.vo.TStock2 bean, TProtocol oprot) throws TException{

      validate(bean);
      oprot.writeStructBegin(new TStruct("TStock2"));

      
            oprot.writeFieldBegin(new TField("id", TType.I64, (short) 1));
            Long elem0 = bean.getId();
            oprot.writeI64(elem0);
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("ownerId", TType.STRING, (short) 2));
            String elem1 = bean.getOwnerId();
            oprot.writeString(elem1);
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("ownerType", TType.I32, (short) 3));
            com.today.api.stock.enums.StockOwnerTypeEnum elem2 = bean.getOwnerType();
            oprot.writeI32(elem2.getValue());
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("skuNo", TType.STRING, (short) 4));
            String elem3 = bean.getSkuNo();
            oprot.writeString(elem3);
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("stockNum", TType.STRING, (short) 5));
            java.math.BigDecimal elem4 = bean.getStockNum();
            oprot.writeString(elem4.toString());
            
            oprot.writeFieldEnd();
          if(bean.getIsDeleted().isPresent()){
              oprot.writeFieldBegin(new TField("isDeleted", TType.I32, (short) 6));
              com.today.api.stock.enums.StockIsDeletedEnum elem5 = bean.getIsDeleted().get();
              oprot.writeI32(elem5.getValue());
              
            }
            if(bean.getCreatedAt().isPresent()){
              oprot.writeFieldBegin(new TField("createdAt", TType.I64, (short) 7));
              Long elem6 = bean.getCreatedAt().get();
              oprot.writeI64(elem6);
              
            }
            
            oprot.writeFieldBegin(new TField("createdBy", TType.I32, (short) 8));
            Integer elem7 = bean.getCreatedBy();
            oprot.writeI32(elem7);
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("updatedAt", TType.I64, (short) 9));
            Long elem8 = bean.getUpdatedAt();
            oprot.writeI64(elem8);
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("updatedBy", TType.I32, (short) 10));
            Integer elem9 = bean.getUpdatedBy();
            oprot.writeI32(elem9);
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("remark", TType.STRING, (short) 11));
            String elem10 = bean.getRemark();
            oprot.writeString(elem10);
            
            oprot.writeFieldEnd();
          
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }
    
      public void validate(com.today.api.stock.vo.TStock2 bean) throws TException{
      
              if(bean.getOwnerId() == null)
              throw new SoaException(SoaCode.StructFieldNull, "ownerId字段不允许为空");
            
              if(bean.getOwnerType() == null)
              throw new SoaException(SoaCode.StructFieldNull, "ownerType字段不允许为空");
            
              if(bean.getSkuNo() == null)
              throw new SoaException(SoaCode.StructFieldNull, "skuNo字段不允许为空");
            
              if(bean.getStockNum() == null)
              throw new SoaException(SoaCode.StructFieldNull, "stockNum字段不允许为空");
            
              if(bean.getRemark() == null)
              throw new SoaException(SoaCode.StructFieldNull, "remark字段不允许为空");
            
    }
    
        @Override
        public String toString(com.today.api.stock.vo.TStock2 bean)
        {return bean == null ? "null" : bean.toString();}
      }
      

      