package com.antsdouble;

import com.antsdouble.beans.Person;
import com.antsdouble.util.AntsProtostuffUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/15
 */
public class ProtoTest {
    @Test
    public void  test(){
        Person user = new Person(1, "lyy");
        byte[] serializer = AntsProtostuffUtil.serializer(user);
        System.out.println(Arrays.toString(serializer));
        Person deserialize = AntsProtostuffUtil.deserialize(serializer, Person.class);
        System.out.println(deserialize);
    }
}
