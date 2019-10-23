package com.antsdouble.util;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/9
 */
public class AntsArrayUtil {


    public static boolean isEmpty(final Object array)
    {
        return array == null || Array.getLength(array) == 0;
    }
    public static int size(final Object array)
    {
        return array == null ? 0 : Array.getLength(array);
    }
    public static <T> T[] shallowCopy(final T[] array)
    {
        if (array == null)
        {
            return null;
        }
        return array.clone();
    }
    public static <T> T[] addAll(final T[] array1, final T[] array2)
    {
        if (array1 == null)
        {
            return shallowCopy(array2);
        }
        else if (array2 == null)
        {
            return shallowCopy(array1);
        }
        final T[] newArray = (T[]) Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);
        System.arraycopy(array1, 0, newArray, 0, array1.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);
        return newArray;
    }

    public static <T> T[] removeItem(T[] array, int pos)
    {
        int length = Array.getLength(array);
        T[] dest = (T[]) Array.newInstance(array.getClass().getComponentType(), length - 1);

        System.arraycopy(array, 0, dest, 0, pos);
        System.arraycopy(array, pos + 1, dest, pos, length - pos - 1);
        return dest;
    }

    public static <T> T[] getArraySubset(T[] array, int start, int end)
    {
        return Arrays.copyOfRange(array, start, end);
    }
    public static <T> T[] toArray(Class<T> classToCastTo, Collection c)
    {
        T[] array = (T[]) c.toArray((T[]) Array.newInstance(classToCastTo, c.size()));
        Iterator i = c.iterator();
        int idx = 0;
        while (i.hasNext())
        {
            Array.set(array, idx++, i.next());
        }
        return array;
    }
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

    public static <T extends Number> T sum(T[] data)
    {
        Double sum = 0.0;
        for (int i = 0; i < data.length; i++)
        {
            sum += data[i].doubleValue();
        }

        if(data[0].getClass().getTypeName().equals("java.lang.Integer"))
        {
            Integer result = (int)((double)sum);
            return (T)result;
        }

        if(data[0].getClass().getTypeName().equals("java.lang.Double"))
        {
            Double result = sum.doubleValue();
            return (T)result;
        }

        if(data[0].getClass().getTypeName().equals("java.lang.Float"))
        {
            Float result = sum.floatValue();
            return (T)result;
        }

        return (T)sum;
    }

}
