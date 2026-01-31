package com.lzc.sort;

import com.lzc.strategy.cycle.Cycler;
import com.lzc.strategy.cycle.runner.CyclePrintByFormatRunner;
import com.lzc.strategy.cycle.runner.CyclePrintRunner;
import org.lzc.utils.ArrayUtils;
import org.lzc.utils.BoxUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Sort_Test {
    public static void bucketSort(int[] arr) {
        int bucketNum = 3;
        int min = ArrayUtils.min(arr);
        int max = ArrayUtils.max(arr);
//        int bucketCapacity = (max - min) / bucketNum;

        List<List<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new ArrayList<>());
        }
        for (int item : arr) {
//            int bucketIndex = Math.min((item - min) / bucketCapacity, bucketNum - 1);
            int bucketIndex = (item - min) * bucketNum / (max - min + 1);
            bucketList.get(bucketIndex).add(item);
        }

        int indexGap = 0;
        for (int i = 0; i < bucketNum; i++) {
            List<Integer> bucket = bucketList.get(i);
            int[] array = bucket.stream().mapToInt(Integer::intValue).toArray();
            selectionSort(array);
            for (int j = 0; j < array.length; j++) {
                arr[j + indexGap] = array[j];
            }
            indexGap += array.length;
        }

    }


    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[]{5,4,3,2,1};
//        bubbleSort(arr);
//        selectionSort(arr);
//        insertionSort(arr);
//        int[] ints = mergeSort(arr);
//        bucketSort(arr);
//        Cycler.cycleByColumnWithHeader(BoxUtils.boxedIntArr(arr), 5, new CyclePrintByFormatRunner("%-9s"));
//        System.out.println();
//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);
//        blockingQueue.take();

        shellSort(arr);
        Cycler.cycleByColumnWithHeader(BoxUtils.boxedIntArr(arr), 5, new CyclePrintByFormatRunner("%-9s"));


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
     * 归并排序
     *
     */
    public static int[] mergeSort(int[] arr) {
        int length = arr.length;
        if (length == 1) {
            return arr;
        } else {
            int[] leftArr = new int[length/2];
            int[] rightArr = new int[length - length/2];
            System.arraycopy(arr, 0, leftArr, 0, length/2);
            System.arraycopy(arr, length/2, rightArr, 0, length - length/2);
            return sortArr(mergeSort(leftArr), mergeSort(rightArr));
        }
    }


    public static int[] sortArr(int[] arrLeft, int[] arrRight) {

        if (arrLeft == null && arrRight != null) {
            return arrRight;
        }
        if (arrLeft != null && arrRight == null) {
            return arrLeft;
        }

        int leftLength = arrLeft.length;
        int rightLength = arrRight.length;

        int[] resultArr = new int[leftLength + rightLength];
        int curL = 0, curR = 0;
        while (curL < leftLength && curR < rightLength) {
            if (arrLeft[curL] < arrRight[curR]) {
                resultArr[curL + curR] = arrLeft[curL];
                curL++;
            } else {
                resultArr[curL + curR] = arrRight[curR];
                curR++;
            }
        }

        if (leftLength - curL >= 0) System.arraycopy(arrLeft, curL, resultArr, curL + curR, leftLength - curL);
        if (rightLength - curR >= 0) System.arraycopy(arrRight, curR, resultArr, curL + curR, rightLength - curR);

        return resultArr;
    }


    /**
     * 希尔排序
     */
    public static void shellSort(int arr[]) {
        int length = arr.length;
        int gap = length;
        while (gap > 1) {
            gap = gap / 2;
            for (int i = 0; i < gap; i++) {
                // i i+gap i+2*gap ...
                for (int j = i + gap; j < length; j = j + gap) {
                    for (int k = j - gap; k >= i; k = k - gap) {
                        if (arr[k+gap] < arr[k]) {
                            swap(arr, k+gap, k);
                        } else {
                            break;
                        }
                    }
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
