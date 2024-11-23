package com.lzc.singleton;

public class Lzc03 {

    /**
     * 这里不能加final，因为其必须初始化
     */
    public static Lzc03 INSTANCE;

    private Lzc03() {}

    public static Lzc03 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Lzc03();
        }
        return INSTANCE;
    }

}
