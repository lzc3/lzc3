package com.lzc.train;


import com.lzc.sort.Sort_Test;
import org.lzc.utils.ArrayUtils;
import org.lzc.utils.RandomUtils;

import java.util.Arrays;

/**
 * 对数器
 * 1.最优解
 * 2.暴力解
 * 3.随机样本生成器（长度、值随机）
 * 4.最优解、暴力解跑相同的样本，对比结果
 * 5.结果不一样，打印样本，人工干预
 * 6.样本数目达到一定程度，依旧没问题，可以确定正确
 */
public class Video_005_Validator {

    public static void main(String[] args) {

        // 随机数组最大长度
        int N = 100;
        // 随机数组每个值，在1~V之间
        int V = 1000;
        // 测试次数
        int t = 1000;
        System.out.println("开始测试");
        for (int i = 0; i < t; i++) {
            // 随机获取数组长度
            int n = RandomUtils.random(N);
            int[] randomArray1 = RandomUtils.randomArray(n, V);
            int[] randomArray2 = ArrayUtils.copyArray(randomArray1);
            int[] randomArray3 = ArrayUtils.copyArray(randomArray1);

            Sort_Test.bubbleSort(randomArray1);
            Sort_Test.selectionSort(randomArray2);
            Sort_Test.insertionSort(randomArray3);

            if (!ArrayUtils.sameArray(randomArray1, randomArray2) || !ArrayUtils.sameArray(randomArray2, randomArray3)) {
                System.out.println("出错了");
            }
        }
    }

}
