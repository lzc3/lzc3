package com.lzc.thread.simpletest;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("my callable run");
        return 1;
    }
}
