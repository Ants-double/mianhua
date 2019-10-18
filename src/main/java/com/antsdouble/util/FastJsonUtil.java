package com.antsdouble.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author lyy
 * @Deprecated test
 * @date 2019/10/8
 */
public class FastJsonUtil {
    private static Logger logger = LoggerFactory.getLogger(FastJsonUtil.class);
    public static final SerializerFeature[] FEATURES = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteDateUseDateFormat
    };

    public static boolean isJson(String content) {
        if (null == content || "".equals(content)) {
            return false;
        }
        try {
            JSON.parse(content);
            logger.debug("test");
            return true;
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }

        return false;
    }

    public static String toJson(Object obj, boolean flag) {
        return JSON.toJSONString(obj, flag);
    }

    public static <T> T toBean(String json, Class<T> c) {
        return JSON.parseObject(json, c);
    }

    public static <T> List<T> toList(String json, TypeReference<List<T>> jsonTypeReference) {

        return JSON.parseObject(json, jsonTypeReference);

    }

    public static <U, V> Map<U, V> toMap(String json) {
        TypeReference<Map<U, V>> typeReference = new TypeReference<Map<U, V>>() {
        };
        return JSON.parseObject(json, typeReference);
    }

}
