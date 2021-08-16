package com.yongsui.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String toString(Object obj){
        if(obj == null){
            return null;
        }
        if(obj.getClass() == String.class){
            return (String) obj;
        }
        try{
            return objectMapper.writeValueAsString(obj);
        }catch (JsonProcessingException e){
            logger.error("json序列化出错：" + obj,e);
            return null;
        }
    }

    public static <T> T toBean(String json,Class<T> tClass){
        try{
            return objectMapper.readValue(json,tClass);
        }catch (IOException e){
            logger.error("json解析出错：" + json,e);
            return null;
        }
    }

    public static <E> List<E> toList(String json,Class<E> eClass){
        try{
            return objectMapper.readValue(json,objectMapper.getTypeFactory().constructCollectionType(List.class,eClass));
        }catch (IOException e){
            logger.error("json解析出错：" + json,e);
            return null;
        }
    }

    public static <K,V> Map<K,V> toMap(String json, Class<K> kClass,Class<V> vClass){
        try{
            return objectMapper.readValue(json,objectMapper.getTypeFactory().constructMapType(Map.class,kClass,vClass));
        }catch (IOException e){
            logger.error("json解析出错：" + json,e);
            return null;
        }
    }

    public static <T> T nativeRead(String json, TypeReference<T> type){
        try{
            return objectMapper.readValue(json,type);
        }catch (IOException e){
            logger.error("json解析错误：" + json,e);
            return null;
        }
    }


}
