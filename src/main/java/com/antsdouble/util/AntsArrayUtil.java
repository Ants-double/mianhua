package com.antsdouble.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/9
 */
public class AntsArrayUtil {

    public static double[] convertValueType(Double[] doubles) {
        return ArrayUtils.toPrimitive(doubles);
    }

    public static Double[] convertArray(List<Double> data) {

        Double[] temp = new Double[data.size()];
        Double[] doubles = data.toArray(temp);
        return doubles;
    }

    public static Double[] addListArrays(List<Double[]> list, int length) {
        Double[] sumArray = new Double[length];
        for (int i = 0; i < length; i++) {
            for (Double[] item : list) {
                sumArray[i] += item[i];
            }
        }
        return sumArray;
    }

    public static Double[] averageListArrays(List<Double[]> list, int length) {
        Double[] result = new Double[length];
        Double[] doubles = addListArrays(list, length);
        for (int i = 0; i < length; i++) {
            result[i] = doubles[i] / list.size();
        }
        return result;
    }

}
