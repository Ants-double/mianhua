package com.antsdouble.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
