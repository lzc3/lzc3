package com.lzc.singleton;

public class Lzc04 {

    public static Lzc04 INSTANCE;

    private Lzc04() {}

    /**
     * synchronized锁住了当前对象
     */
    public static synchronized Lzc04 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Lzc04();
        }
        return INSTANCE;
    }
}
