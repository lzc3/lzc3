package com.lzc.singleton;

/**
 * 饿汉式单例
 * 类加载到内存后就实例化一个实例，JVM保证线程安全（JVM保证每个class只会落到内存一次）
 * 简单实用
 * 缺点：不管用到与否，类装载时就完成实例化
 */
public class Lzc01 {

    private static final Lzc01 INSTANCE = new Lzc01();

    /**
     * 构造方法设置成私有
     */
    private Lzc01() {}

    public static Lzc01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Lzc01 lzc01 = Lzc01.getInstance();
        Lzc01 lzc02 = Lzc01.getInstance();
    }
}
