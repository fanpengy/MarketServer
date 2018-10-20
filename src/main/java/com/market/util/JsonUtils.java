package com.market.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.*;

public class JsonUtils {


    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());

        // null的字段不输出,减少数据量,也避免.NET系统用基本类型反序列化本系统的包装类型字段,导致出错
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(module);
    }

    public static ObjectMapper getObjectMapperInstance() {
        return objectMapper;
    }

    public static void decodeJSONObject(String prefix, Object obj, Map<String, Object> map) {
        if (obj instanceof JSONObject) {
            JSONObject json = ((JSONObject) obj);
            Iterator<String> keys = ((JSONObject) obj).keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                Object o = json.get(key);
                String pre = String.join(".", new String[]{prefix, key});
                if (o instanceof JSONObject || o instanceof JSONArray) {
                    decodeJSONObject(pre, o, map);
                } else {
                    map.put(pre, o);
                }
            }
        } else if (obj instanceof JSONArray) {
            Iterator iterator = ((JSONArray) obj).iterator();
            while (iterator.hasNext()) {
                Object ob = iterator.next();
                if (ob instanceof JSONObject || ob instanceof JSONArray) {
                    decodeJSONObject(prefix, ob, map);
                } else {
                    map.put(prefix, ob);
                }
            }
        }
    }

    /**
     * 将object序列化为string
     *
     * @param obj
     * @return String
     */
    public static String jsonSerialize(Object obj) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            //logger.error("JsonUtils.jsonSerialize():%s", ex);
        }
        return str;
    }

    /**
     * 将string反序列化为object
     *
     * @param str
     * @param tClass
     * @return T
     */
    public static <T> T jsonDeserialize(String str, Class<T> tClass) {
        T instance = null;
        try {
            instance = objectMapper.readValue(str, tClass);
        } catch (Exception ex) {
            //logger.error("JsonUtils.jsonDeserialize()异常 value:{}, toType:{}", str, tClass.getName(), ex);
        }
        return instance;
    }

    public static <T> T jsonDeserialize(String str, TypeReference<T> tType) {
        T instance = null;
        try {
            instance = objectMapper.readValue(str, tType);
        } catch (Exception ex) {
            //logger.error("JsonUtils.jsonDeserialize():%s", ex);
        }
        return instance;
    }

    /**
     * 反序列化成list
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonDeserialize2List(String json, Class<T> clazz) {
        if (json == null || json.equals("") || Objects.isNull(clazz)) {
            return null;
        }

        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            //logger.error("json jsonDeserialize2List fail,json={},classType={}", json, clazz.getName(), e);
            return null;
        }
    }
}
