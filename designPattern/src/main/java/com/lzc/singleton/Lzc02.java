package com.lzc.singleton;

public class Lzc02 {
    private static final Lzc02 INSTANCE;

    static {
        INSTANCE = new Lzc02();
    }

    private Lzc02() {}

    public static Lzc02 getInstance() {
        return INSTANCE;
    }

}
