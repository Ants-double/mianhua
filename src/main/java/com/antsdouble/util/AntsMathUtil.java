package com.antsdouble.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
