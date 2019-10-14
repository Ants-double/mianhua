package com.antsdouble;

import com.antsdouble.beans.Person;
import com.antsdouble.util.AntsMapUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/14
 */
public class MapTest {


    @Test
    public void  test() throws Exception {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "d");
        map.put(4, "b");
        map.put(2, "a");
        map.put(33, "c");

        Person person=new Person();
        person.setId(3l);
        person.setName("test");
        Map map1 = AntsMapUtil.java2Map(person);
        System.out.println(map1);
        System.out.println(AntsMapUtil.mapToObject(map1,Person.class));
        Person person2=new Person();
        System.out.println(AntsMapUtil.map2Java(person2,map1));
       // String test = AntsMapUtil.getObject(map1, "id");
        System.out.println(map);
        Map<Integer, String> orderByKey = AntsMapUtil.orderByKey(map, -1);
        System.out.println(orderByKey);
        Map<Integer, String> orderByKey1 = AntsMapUtil.orderByKey(map, 1);
        System.out.println(orderByKey1);
    }
}
