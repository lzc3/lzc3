package com.lzc.leetcode.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.locks.LockSupport;

public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InterruptedException {

//        String classPath = "com.lzc.leetcode.array." + "Q240";
        String classPath = "com.lzc.leetcode." + "Q1432";
        test(classPath);
    }

    public static void test(String classPath) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Class<?> aClass = Class.forName(classPath);
        Solution solution = (Solution) aClass.newInstance();
        // Method[] methods = aClass.getMethods();
        // 私有方法无法获取, aClass.getDeclaredMethods()可以获取所有的方法,method.setAccessible(true)
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(HandleSolution.class)) {
                // 假如此注解加在父类上无法判断成功, Java默认不会将接口或父类方法上的注解继承到实现类或子类的方法上
                // 同时，Java的元注解 @Inherited 仅对 类级别的注解 生效，无法作用于方法或接口方法。
                Object invoke = method.invoke(solution, solution.offerArgs());
                System.out.println(invoke);
            }
        }
    }
}
