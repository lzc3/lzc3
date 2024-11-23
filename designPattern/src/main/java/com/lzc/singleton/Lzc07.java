package com.lzc.singleton;

/**
 * 静态内部类实现
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 */
public class Lzc07 {

    private static Lzc07 INSTANCE;

    private Lzc07() {}

    private static class Lzc07Holder {
        private static final Lzc07 INSTANCE = new Lzc07();
    }


    public static Lzc07 getInstance() {
        return Lzc07Holder.INSTANCE;
    }
}
