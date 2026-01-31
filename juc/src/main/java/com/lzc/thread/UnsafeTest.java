package com.lzc.thread;

import sun.misc.Unsafe;

public class UnsafeTest {

    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        unsafe.loadFence();

        unsafe.storeFence();

        unsafe.fullFence();;
    }

}
