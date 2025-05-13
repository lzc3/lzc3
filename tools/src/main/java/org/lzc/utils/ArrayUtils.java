package org.lzc.utils;

public class ArrayUtils {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,3,4};
        int[] arr2 = new int[]{1,5,4};
        System.out.println(sameArray(arr1, arr2));
        System.out.println(arr1 == copyArray(arr1));
        System.out.println(sameArray(arr1, copyArray(arr1)));
    }


    /**
     * 复制一份新的数组
     *
     * @param arr 原数组
     * @return 新数组
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int length = arr.length;
        int[] copyArr = new int[length];
        System.arraycopy(arr, 0, copyArr, 0, length);
        return copyArr;
    }

    /**
     * 判断两个数组元素是否都一致
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return true 一致 false 不一致
     */
    public static boolean sameArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        if (arr1 == arr2) {
            return true;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回int数组中最小的值
     *
     * @param arr 数组
     * @return min
     */
    public static int min(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    /**
     * 返回int数组中最大的值
     *
     * @param arr 数组
     * @return max
     */
    public static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}
