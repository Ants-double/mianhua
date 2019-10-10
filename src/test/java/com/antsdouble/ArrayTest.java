package com.antsdouble;

import com.antsdouble.util.AntsArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/10
 */
public class ArrayTest {

    @Test
    public void testOne() {

        Double testDouble=new Double(9);
        System.out.println(testDouble.intValue());
        System.out.println(testDouble.floatValue());
        System.out.println(testDouble.doubleValue());
        List<Double> doubleList = new ArrayList<Double>() {
            {
                add(1.0);
                add(2.0);
                add(3.0);
            }
        };
        System.out.println(Arrays.toString(doubleList.toArray()));
        Double[] doubles = new Double[]{4.0, 5.0, 6.0};
        List<Double> doubleArrayList = AntsArrayUtil.arrayConvertList(doubles);
        System.out.println(Arrays.toString(doubleArrayList.toArray()));

        List<Double[]> listArray = new ArrayList<>();
        listArray.add(doubles);
        listArray.add(AntsArrayUtil.convertArray(doubleList, Double.class));
        Double[] sumArray = AntsArrayUtil.addListArrays(listArray, Double.class, doubles.length);
        System.out.println(Arrays.toString(sumArray));
        Double[] doubleAverage = AntsArrayUtil.averageListArrays(listArray, Double.class, doubles.length);
        System.out.println(Arrays.toString(doubleAverage));


        System.out.println(Arrays.toString(AntsArrayUtil.convertArray(doubleList, Double.class)));

    }
}
