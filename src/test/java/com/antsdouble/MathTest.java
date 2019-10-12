package com.antsdouble;

import com.antsdouble.util.AntsMathUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/12
 */
public class MathTest {

    @Test
    public void testMath(){
        List<Double> doubles = Arrays.asList(new Double(275), new Double(1));
        Double average = AntsMathUtil.windAngleAverage(doubles);
        System.out.println(average);

        double v = AntsMathUtil.threePointAngle(0, 0, 8,8 , 0, 8);
        System.out.println(v);
    }
}
