package com.antsdouble.util;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/9
 */
public class AntsArrayUtil {

    /**
     * 功能描述 引用转值
     *
     * @param [doubles]
     * @return double[]
     * @author lyy
     * @date 2019/10/9
     */
    public static double[] convertValueType(Double[] doubles) {
        return ArrayUtils.toPrimitive(doubles);
    }

    public static float[] convertValueType(Float[] doubles) {
        return ArrayUtils.toPrimitive(doubles);
    }

    public static int[] convertValueType(Integer[] doubles) {
        return ArrayUtils.toPrimitive(doubles);
    }


    public static <T> T[] convertArray(List<T> list, Class<T> type) {
        T[] temp = (T[]) Array.newInstance(type, list.size());
        T[] doubles = list.toArray(temp);
        return doubles;
    }

    public static <T> List<T> arrayConvertList(T[] doubles) {
        return new ArrayList<T>(Arrays.asList(doubles));
    }

    public static <T extends Number> T[] addListArrays(List<T[]> list, Class<T> type, int length) {
        T[] sumArray = (T[]) Array.newInstance(type, length);
        for (int i = 0; i < length; i++) {
            for (T[] item : list) {
                sumArray[i] = AntsMathUtil.add(sumArray[i], item[i]);
            }
        }
        return sumArray;
    }

    public static <T extends Number> T[] averageListArrays(List<T[]> list, Class<T> type, int length) {
        T[] result = (T[]) Array.newInstance(type, length);
        T[] doubles = addListArrays(list, type, length);
        for (int i = 0; i < length; i++) {
            result[i] = (T) AntsMathUtil.division(doubles[i], list.size());
        }
        return result;
    }


}
