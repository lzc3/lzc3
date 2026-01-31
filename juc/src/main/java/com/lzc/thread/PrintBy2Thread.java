package com.lzc.thread;

import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintBy2Thread {

    public static void main(String[] args) throws InterruptedException {

        StopWatch watch = new StopWatch();
        watch.start();

        final boolean[] flag = {true};
        AtomicInteger shareInteger = new AtomicInteger(1);
        Thread thread1 = new Thread(() -> {
            while (shareInteger.get() <= 100) {
                if (flag[0]) {
                    System.out.println(Thread.currentThread().getName() + ":" + shareInteger.getAndIncrement());
                    flag[0] = false;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "线程1");

        Thread thread2 = new Thread(() -> {
            while (shareInteger.get() <= 100) {
                if (!flag[0]) {
                    System.out.println(Thread.currentThread().getName() + ":" +shareInteger.getAndIncrement());
                    flag[0] = true;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "线程2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        watch.stop();
        long totalTimeMillis = watch.getTotalTimeMillis();
        System.out.println("time:" + totalTimeMillis);
    }


}
