package com.lzc.sort;

import com.lzc.strategy.cycle.Cycler;
import com.lzc.strategy.cycle.runner.CyclePrintByFormatRunner;
import com.lzc.strategy.cycle.runner.CyclePrintRunner;
import org.lzc.utils.BoxUtils;

public class Sort_Test {

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};
//        bubbleSort(arr);
//        selectionSort(arr);
        insertionSort(arr);
        Cycler.cycleByColumnWithHeader(BoxUtils.boxedIntArr(arr), 5, new CyclePrintByFormatRunner("%-9s"));
        System.out.println();
    }

    /**
     * 冒泡排序
     *
     * @param arr 数组
     */
    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i <= length - 2; i++) {
            for (int j = 1; j < length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr 数组
     */
    public static void selectionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int maxIndex = 0;
            for (int j = 1; j < length - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(arr, maxIndex, length - i - 1);
        }
    }

    /**
     * 插入排序
     *
     * @param arr 数组
     */
    public static void insertionSort(int[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int replaceIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[replaceIndex] < arr[j]) {
                    swap(arr, replaceIndex, j);
                    replaceIndex = j;
                }
            }
        }
    }


    /**
     * 使用异或的方式交换数组中的元素
     *
     * @param arr 数组
     * @param index1 索引1
     * @param index2 索引2
     */
    public static void swap(int[] arr, int index1, int index2) {
        if (index1 == index2
                || index1 < 0 || index1 >= arr.length
                || index2 < 0 || index2 >= arr.length) {
            return;
        }
        arr[index1] = arr[index1] ^ arr[index2];
        arr[index2] = arr[index1] ^ arr[index2];
        arr[index1] = arr[index1] ^ arr[index2];
    }

}
