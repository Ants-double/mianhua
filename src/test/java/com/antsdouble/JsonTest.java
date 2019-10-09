package com.antsdouble;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.antsdouble.beans.Person;
import com.antsdouble.util.FastJsonUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/8
 */
public class JsonTest {
    @Test
    public void testJson() {

        //测试空
        Person person = new Person();
        Object json = JSON.toJSONString(person, SerializerFeature.WriteNullStringAsEmpty);
        System.out.println(json);
        //测试对象
        Person person1 = new Person(1, "lyy");
        System.out.println(JSON.toJSONString(person1, SerializerFeature.WriteClassName));
        // System.out.println(JSON.toJSONBytes(person1, SerializerFeature.config(0,)));
        //时间
        String dateJsonNoFeature = JSON.toJSONString(new Date());
        System.out.println(dateJsonNoFeature);
        String dateJson = JSON.toJSONString(new Date(), FastJsonUtil.FEATURES);
        System.out.println(dateJson);
        //list
        List<Person> list = new ArrayList<Person>();
        list.add(person);
        list.add(person1);
        System.out.println(JSON.toJSON(list));
        String jsonList = JSON.toJSONString(list, FastJsonUtil.FEATURES);
        System.out.println(jsonList);
        //map
        HashMap<Integer, Person> map = new HashMap<Integer, Person>();
        map.put(0, person);
        map.put(1, person1);
        System.out.println(JSON.toJSON(map));
        String mapJson = JSON.toJSONString(map, FastJsonUtil.FEATURES);
        System.out.println(mapJson);
        //byte
        byte[] bytes = JSON.toJSONBytes(map, FastJsonUtil.FEATURES);
        System.out.println(bytes.toString());


        //反序
        System.out.println("反序");
        System.out.println(JSON.parse(dateJson));
        //list
        System.out.println(JSON.parse(jsonList));
        // 把JSON文本parse成JSONArray
        System.out.println(JSON.parseArray(jsonList, Person.class));
        //map
        System.out.println(JSON.parse(mapJson));
        // 把JSON文本parse成JSONObject
        Map<Integer, Person> map1 = JSON.parseObject(mapJson, new TypeReference<Map<Integer, Person>>() {
        });
        System.out.println(map1);
        System.out.println(map1.get(1));

        //bytes
        Object parse = JSON.parse(bytes, Feature.AllowArbitraryCommas);
        System.out.println(parse);
    }
}
