package com.lzc.train;

/**
 * 1.有序数组中判断num存不存在
 * 2.在有序数组中找到>=num最左边的位置
 * 3.在有序数组中找到<=num最右边的位置
 * 4.二分搜索不一定发生在有序数组上（比如寻找峰值问题）
 * 5.“二分答案法”
 */
public class Video_006 {
    public static void main(String[] args) {

        int[] arr = new int[] {1,3,5,7,9,10};
        for (int i = 0; i < 10; i++) {
            System.out.println("当前值" + i + " 在数组中的状态为：" + isExistInArray(arr,i));
        }


    }

    public static boolean isExistInArray(int[] arr, int value) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int middle = (left + right) / 2;
            int middleValue = arr[middle];
            if (middleValue == value) {
                return true;
            } else if (middleValue < value) {
                left = middle + 1;
            } else  {
                right = middle;
            }
        }
        return false;
    }

}
