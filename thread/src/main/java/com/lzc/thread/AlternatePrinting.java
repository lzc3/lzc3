package com.lzc.thread;

import java.util.Objects;

public class AlternatePrinting {

    public static void main(String[] args) {

        final int[] shareInt = {1};
        final boolean[] flag = {true};
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (shareInt[0] > 100) break;
                    if (flag[0]) {
                        System.out.println(Thread.currentThread().getName() + ":" + shareInt[0]++);
                        flag[0] = !flag[0];
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (shareInt[0] > 100) break;
                    if (!flag[0]) {
                        System.out.println(Thread.currentThread().getName() + ":" + shareInt[0]++);
                        flag[0] = !flag[0];
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }, "Thread2");

        thread1.start();
        thread2.start();
    }
}