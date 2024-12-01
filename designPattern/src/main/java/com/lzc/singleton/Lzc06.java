package com.lzc.singleton;

public class Lzc06 {

    // JIT
    // 语句重排 volatile
    public static volatile Lzc06 INSTANCE;

    private Lzc06() {}

    /**
     * 双重检查
     */
    public static Lzc06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Lzc06.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Lzc06();
                }
            }
        }
        return INSTANCE;
    }
    
}
