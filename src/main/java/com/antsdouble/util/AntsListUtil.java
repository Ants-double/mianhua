package com.antsdouble.util;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/10
 */
public class AntsListUtil {


    public static <T extends Number> T listAverage(List<T> list) {

        if (Double.class.isInstance(list.get(0))) {
            BigDecimal bigDecimal = new BigDecimal(list.stream().mapToDouble(num -> {
                return (Double) num;
            }).average().getAsDouble());
            return (T) Double.valueOf(bigDecimal.doubleValue());
        } else if (Integer.class.isInstance(list.get(0))) {
            BigDecimal bigDecimal = new BigDecimal(list.stream().mapToInt(num -> {
                return (Integer) num;
            }).average().getAsDouble()).setScale(0, BigDecimal.ROUND_HALF_UP);
            return (T) Integer.valueOf(bigDecimal.intValue());
        } else if (Float.class.isInstance(list.get(0))) {
            BigDecimal bigDecimal = new BigDecimal(list.stream().mapToDouble(num -> {
                return Double.valueOf(String.valueOf(num));
            }).average().getAsDouble());
            return (T) Float.valueOf(bigDecimal.floatValue());
        } else {
            return null;
        }

    }

}
