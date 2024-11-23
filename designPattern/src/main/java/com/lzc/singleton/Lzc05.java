package com.lzc.singleton;

public class Lzc05 {

    public static Lzc05 INSTANCE;

    private Lzc05() {}

    /**
     * 通过减小同步代码块的方式提高效率
     * 不可行
     */
    public static Lzc05 getInstance() {
        if (INSTANCE == null) {
            synchronized (Lzc05.class) {
                INSTANCE = new Lzc05();
            }
        }
        return INSTANCE;
    }
}
