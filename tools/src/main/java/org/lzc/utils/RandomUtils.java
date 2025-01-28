package org.lzc.utils;

public class RandomUtils {

    /**
     * 获取一个随机数，范围为1~v
     *
     * @param v 值
     * @return int
     */
    public static int random(int v) {
        return (int) (Math.random() * v) + 1;
    }


    /**
     * 获取一个随机的int数组，每个元素值为 1 ~ v
     *
     * @param n 长度
     * @param v 值
     * @return int[]
     */
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random(v);
        }
        return arr;
    }

}
