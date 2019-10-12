package com.antsdouble.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/10
 */
public class AntsMathUtil {
    private static Logger logger = LoggerFactory.getLogger(AntsMathUtil.class);

    public static <T extends Number> T add(T t1, T t2) {
        // int i = t1 + t2;
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Integer resultInt;
        Float resultFloat;
        Double resultDouble;

        resultInt = t1.intValue() + t2.intValue();
        resultFloat = t1.floatValue() + t2.floatValue();
        resultDouble = t1.doubleValue() + t2.doubleValue();
        return getT(t1, t2, resultInt, resultFloat, resultDouble);

    }


    public static <T extends Number> T subtract(T t1, T t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        int resultInt;
        float resultFloat;
        double resultDouble;
        resultInt = t1.intValue() - t2.intValue();
        resultFloat = t1.floatValue() - t2.floatValue();
        resultDouble = t1.doubleValue() - t2.doubleValue();
        return getT(t1, t2, resultInt, resultFloat, resultDouble);
    }

    public static <T extends Number> T multiply(T t1, T t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        int resultInt;
        float resultFloat;
        double resultDouble;
        resultInt = t1.intValue() * t2.intValue();
        resultFloat = t1.floatValue() * t2.floatValue();
        resultDouble = t1.doubleValue() * t2.doubleValue();
        return getT(t1, t2, resultInt, resultFloat, resultDouble);
    }

    public static <T extends Number> T division(T t1, T t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        int resultInt;
        float resultFloat;
        double resultDouble;
        resultInt = t1.intValue() / t2.intValue();
        resultFloat = t1.floatValue() / t2.floatValue();
        resultDouble = t1.doubleValue() / t2.doubleValue();
        return getT(t1, t2, resultInt, resultFloat, resultDouble);
    }

    /**
     * 功能描述
     * double 向量夹角公式 ，也可以用余玄定理
     *
     * @param xCenter, yCenter, firstX, firstY, secondX, secondY
     * @return double
     * @author lyy
     * @date 2019/10/12
     */
    public static double threePointAngle(double xCenter, double yCenter, double firstX, double firstY, double secondX, double secondY) {
        double dx1 = firstX - xCenter;
        double dx2 = secondX - xCenter;
        double dy1 = firstY - yCenter;
        double dy2 = secondY - yCenter;
        double c = Math.sqrt(dx1 * dx1 + dy1 * dy1) * Math.sqrt(dx2 * dx2 + dy2 * dy2);
        if (c == 0) {
            return -1;
        }
        return new BigDecimal((Math.acos((dx1 * dx2 + dy1 * dy2) / c) * 180 / Math.PI)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 功能描述  https://github.com/Ants-double/huanhuncao/blob/master/books/%E5%B9%B3%E5%9D%87%E9%A3%8E%E5%90%91%E7%9A%84%E8%AE%A1%E7%AE%97%E6%96%B9%E6%B3%95%E5%8F%8A%E5%85%B6%E6%AF%94%E8%BE%83.pdf
     *
     * @param list
     * @return java.lang.Double
     * @author lyy
     * @date 2019/10/12
     */
    public static Double windAngleAverage(List<Double> list) {
        Double result = Double.NaN;
        if (list.size() > 0) {
            List<Double> listU = new ArrayList<>();
            List<Double> listV = new ArrayList<>();
            for (Double item : list) {
                if (item >= 0 && item < 90) {
                    listV.add(Math.cos(item * Math.PI / 180));
                    listU.add(Math.sin(item * Math.PI / 180));
                } else if (item >= 90 && item < 180) {
                    listV.add(-Math.sin((item - 90) * Math.PI / 180));
                    listU.add(Math.cos((item - 90) * Math.PI / 180));
                } else if (item >= 180 && item < 270) {
                    listV.add(-Math.cos((item - 180) * Math.PI / 180));
                    listU.add(-Math.sin((item - 180) * Math.PI / 180));
                } else if (item >= 270 && item < 360) {
                    listV.add(Math.sin((item - 270) * Math.PI / 180));
                    listU.add(-Math.cos((item - 270) * Math.PI / 180));
                }
            }
            result = (Math.atan2(listU.stream().mapToDouble(Double::doubleValue).sum(), listV.stream().mapToDouble(Double::doubleValue).sum()) / Math.PI * 180 + 360) % 360;
        }
        return result;
    }


    private static <T extends Number> T getT(T t1, T t2, Integer resultInt, Float resultFloat, Double resultDouble) {
        if (t1.getClass().isInstance(resultInt) && t2.getClass().isInstance(resultInt)) {
            logger.debug(String.valueOf(resultInt));
            return (T) resultInt;
        } else if (t1.getClass().isInstance(resultInt) && t2.getClass().isInstance(resultFloat)) {
            logger.debug(String.valueOf(resultFloat));
            return (T) resultFloat;
        } else if (t1.getClass().isInstance(resultDouble) || t2.getClass().isInstance(resultDouble)) {
            logger.debug(String.valueOf(resultDouble));
            return (T) resultDouble;
        } else if (t1.getClass().isInstance(resultFloat) || t2.getClass().isInstance(resultFloat)) {
            logger.debug(String.valueOf(resultFloat));
            return (T) resultFloat;
        } else {
            logger.error("type error");
            return t1;
        }
    }

}
