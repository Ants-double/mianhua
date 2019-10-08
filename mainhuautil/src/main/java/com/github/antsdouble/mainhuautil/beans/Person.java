package com.github.antsdouble.mainhuautil.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lyy
 * @description
 * @date 2019/10/8
 */
public class Person {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;

    public Person() {
    }

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
