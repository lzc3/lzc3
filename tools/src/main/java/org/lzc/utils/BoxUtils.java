package org.lzc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BoxUtils {

    /**
     * 包装int数组成Integer
     *
     * @param arr 整数数组
     * @return Integer数组
     */
    public static Integer[] boxedIntArr(int[] arr) {
         return Arrays.stream(arr).boxed().toArray(Integer[]::new);
    }

    /**
     * 包装String数组成Integer
     *
     * @param arr 整数数组
     * @return Integer数组
     */
    public static Integer[] boxedIntArrWithNull(String[] arr) {
        if (arr == null) return new Integer[0];
        return Arrays.stream(arr)
                .map(item -> {
                    if (item == null) {
                        return null;
                    }
                    return Integer.valueOf(item);
                })
                .toArray(Integer[]::new);
    }


    /**
     * 创建从1到column的数组
     *
     * @param column 整数
     * @return Integer数组
     */
    public static Integer[] createIntegerArray(int column) {
        return IntStream.rangeClosed(1, column) // 生成 1 到 column 的流
                .boxed()               // 将 int 流转换为 Integer 流
                .toArray(Integer[]::new); // 转换为 Integer[]
    }
}
