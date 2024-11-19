package com.lzc.thread;

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
