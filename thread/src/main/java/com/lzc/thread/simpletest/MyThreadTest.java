package com.lzc.thread.simpletest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MyThreadTest {
    public static void main(String[] args) throws InterruptedException {
        // 实现接口会好一点，类可能只要求可执行就行，继承整个 Thread 类开销过大
        Thread myThread = new MyThread();
        myThread.start();

        // 接口，可以使用匿名内部类代替
        MyRunnable myRunnable = new MyRunnable();
        Thread runAbleTest = new Thread(myRunnable);
        runAbleTest.start();

        // 带返回的接口
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(myCallable);
        Thread callAbleTest = new Thread(ft);
        callAbleTest.start();

        // Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。

        // 核心线程数：1 最大线程数：1 队列类型：无界队列 风险：处理效率低
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // 核心线程数：0 最大线程数：极大值 队列类型：同步队列（不存储） 风险：线程数激增耗尽资源
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 核心线程数：10 最大线程数：10 队列类型：无界队列 风险：队列堆积导致oom
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 20; i++) {
//            fixedThreadPool.execute(new MyRunnable());
//        }
//        fixedThreadPool.shutdown();
        // Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
        // 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
//        Future<?> future = fixedThreadPool.submit(() -> {
//            // ..
//        });
//        future.cancel(true);


        // 用户线程 守护线程


        // 休眠当前正在执行的线程，单位为毫秒。
//        Thread.sleep(3000);

        // 声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行。该方法只是对线程调度器的一个建议，而且也只是建议具有相同优先级的其它线程可以运行。
//        Thread.yield();


        Thread myThread2 = new MyThreadInterruptedTest();
        myThread2.start();
        myThread2.interrupt();

    }
}
