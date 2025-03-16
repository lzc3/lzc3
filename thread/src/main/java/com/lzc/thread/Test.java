package com.lzc.thread;

/**
 * 1. 保证变量的可见性
 * 在多线程环境中，每个线程都有自己的工作内存，线程在操作变量时，会先将变量从主内存拷贝到自己的工作内存中，然后对工作内存中的变量副本进行操作，最后再将操作结果写回主内存。如果没有 volatile 关键字，一个线程对变量的修改可能不会立即刷新到主内存，其他线程也无法及时看到这个修改。
 * 在这段代码中，initFlag 被声明为 volatile，这意味着当一个线程修改了 initFlag 的值时，这个修改会立即刷新到主内存中，其他线程在读取 initFlag 时，会直接从主内存中读取最新的值，从而保证了 initFlag 在不同线程之间的可见性。
 * 具体来说，prepareData 方法所在的线程将 initFlag 设置为 true 后，等待线程能够立即看到这个修改，从而跳出 while 循环。
 * 2. 禁止指令重排序
 * Java 编译器和处理器为了提高性能，可能会对指令进行重排序。指令重排序可能会导致程序的执行顺序与代码的编写顺序不一致。volatile 关键字可以禁止指令重排序，保证代码的执行顺序与编写顺序一致。
 * 在这段代码中，虽然没有明显的指令重排序问题，但在更复杂的场景中，volatile 关键字可以确保对 initFlag 的读写操作按照代码的顺序执行，避免出现意外的结果。
 */
public class Test {

    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println("waiting data");
            while (!initFlag) {

            }
            System.out.println("end thread");
        }).start();

        Thread.sleep(1000);

        new Thread(Test::prepareData).start();
    }


    public static void prepareData() {
        initFlag = true;
    }

}
