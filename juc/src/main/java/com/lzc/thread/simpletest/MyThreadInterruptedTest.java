package com.lzc.thread.simpletest;

public class MyThreadInterruptedTest extends Thread{
    @Override
    public void run() {
        while (!interrupted()) {
            // ..
        }
        System.out.println("MyThreadInterruptedTest end");
    }
}
