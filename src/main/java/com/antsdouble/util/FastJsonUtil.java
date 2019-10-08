package com.antsdouble.util;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author lyy
 * @description
 * @date 2019/10/8
 */
public class FastJsonUtil {
    public static  final SerializerFeature[] FEATURES={
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteDateUseDateFormat
    };
}
