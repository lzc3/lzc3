package com.lzc.train;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class Week1 {


    public static void main(String[] args) {

//        testBox(); // 包装类测试

//        testProxy(); // 测试动态代理

//        testQueueAndStack();


        testOp();
    }



    /**
     * 享元模式（Flyweight Pattern）是一种结构型设计模式，
     * 通过共享对象来减少内存占用、提升性能，特别适用于处理大量相似对象的场景。
     * 其核心思想是将对象的内部状态（可共享）和外部状态（不可共享）分离，
     * 通过复用内部状态来避免重复创建对象。
     */
    public static void testBox() {
        // Integer 的自动装箱在 -128 到 127 范围内会使用缓存对象（通过 Integer.valueOf()），而 超出该范围 时会创建新的对象。
        // Java 的 Integer 缓存上限可以通过 JVM 参数调整， jvm的参数-Djava.lang.Integer.IntegerCache.high
        Integer a2 = 1000;
        Integer b2 = 1000;
        System.out.println(a2 == b2); // false

        // 当 Integer 和 int 用 == 比较时，
        // Java会自动将 Integer 拆箱为 int，最终比较的是两个基本类型的值，结果为 true
        Integer a1 = 1;
        int b1 = 1;
        System.out.println(a1 == b1); // true

        Integer a3 = 1000;
        int b3 = 1000;
        System.out.println(a3 == b3); // true
        // 建议直接使用.equals()
    }

    /**
     * List<Integer>在get的时候是取下标还是这个值
     */
    public static void testListGet() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(3);
        Integer integer = integerList.get(1);
    }

    interface DoSomething {
        void doSomething();
    }

    static class Car implements DoSomething {

        @Override
        public void doSomething() {
            System.out.println("drive drive");
        }
    }

    public static void testProxy() {

        Car car = new Car();
        DoSomething doSomething = (DoSomething)Proxy.newProxyInstance(car.getClass().getClassLoader(), car.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 在调用方法前可以添加额外的逻辑
                System.out.println("Before method call.");

                // 调用实际对象的方法
                Object result = method.invoke(car, args);

                // 在调用方法后可以添加额外的逻辑
                System.out.println("After method call.");
                return result;
            }
        });

        doSomething.doSomething();
    }

    public static void testQueueAndStack() {
        Queue<String> queue = new ArrayDeque();
        queue.add("lzc");
        queue.add("lzs");
        queue.offer("aa"); // 队列满了会抛异常

        String element = queue.element(); // 和peek的区别在与队列为空会抛异常
        String peek = queue.peek();

        String poll = queue.poll();
        System.out.println(poll);

        Stack<String> stack = new Stack<>();

        stack.add("2");
        stack.push("3");
        stack.add("1");

        String peek1 = stack.peek();
        String pop = stack.pop();
        System.out.println();
    }


    public static void testThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 核心线程数
        executor.setMaxPoolSize(10); // 最大线程数
        executor.setQueueCapacity(100); // 队列容量
        executor.setKeepAliveSeconds(100); // 非核心线程数空闲后存活时间
        executor.setAllowCoreThreadTimeOut(false); // 若设置为 true，核心线程在空闲时间超过 keepAliveSeconds 后也会被销毁
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // ThreadPoolExecutor#DiscardPolicy：这个策略将会直接丢弃任务
        // ThreadPoolExecutor#CallerRunsPolicy：不在新线程中执行任务，而是有调用者所在的线程来执行策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("excutor"); // 名称前缀
        executor.initialize();
    }

    static class Father {
        String name = "father";

        public String getName() {
            return name;
        }
    }

    static class Son extends Father{
        String name;

        public Son(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void testOp() {
        int a = 9;
        int b = a++;
        int c = ++a;
        int d = c--;
        int e = --d;

        System.out.println(42 == 42.0);

        BigDecimal bigDecimal1 = new BigDecimal("52.73");
        BigDecimal bigDecimal2 = new BigDecimal("32.5");
        BigDecimal bigDecimalAdd = bigDecimal1.add(bigDecimal2);


        BigInteger bigInteger1 = new BigInteger("20181120169");
        System.out.println();

        Father father = new Son("son");
        String name1 = father.getName();
        String name2 = father.name;
        System.out.println(name1);
        System.out.println(name2);
    }







}
