package com.lzc.test;

public class ExtendClassTest {



    static class Person {
        public void say() {
            System.out.println(generateSay());
        }

        public String generateSay() {
            return "I am xxx";
        }

    }

    static class Gamer extends Person {

        @Override
        public void say() {
            super.say();
        }
        @Override
        public String generateSay() {
            return "play xxx";
        }

    }

    public static void main(String[] args) {
        ExtendClassTest extendClassTest = new ExtendClassTest();
        Gamer gamer = new Gamer();
        gamer.say();
    }

}
