package com.lzc.strategy;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

@Data
public class Cat implements Comparable<Cat>{

    private int age;


    public Cat(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Cat cat) {
        if (age < cat.age) {
            return -1;
        } else if (age == cat.age) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                '}';
    }
}
