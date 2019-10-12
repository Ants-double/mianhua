package com.antsdouble;

import com.antsdouble.util.AntsArrayUtil;
import com.antsdouble.util.AntsListUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/12
 */
public class ListTest {

    @Test
    public void testAverage() {

        List<Double> doubles = Arrays.asList(new Double(3.3), new Double(3.3), new Double(3.3));
        Double aDouble = AntsListUtil.listAverage(doubles);
        System.out.println(aDouble);
        List<Integer> integerList = Arrays.asList(new Integer(12), new Integer(1), new Integer(4));
        Integer integer = AntsListUtil.listAverage(integerList);
        System.out.println(integer);
        List<Float> floatList = Arrays.asList(new Float(12.3), new Float(1.5), new Float(4));
        Float aFloat = AntsListUtil.listAverage(floatList);
        System.out.println(aFloat);
    }
}
