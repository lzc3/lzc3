package com.lzc.sort;

import com.lzc.strategy.cycle.Cycler;
import com.lzc.strategy.cycle.runner.CyclePrintByFormatRunner;
import com.lzc.strategy.cycle.runner.CyclePrintRunner;
import javafx.util.Pair;
import org.lzc.utils.BoxUtils;
import org.lzc.utils.RandomUtils;

import java.util.Arrays;
import java.util.Stack;

public class BlackBoard {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivotIndex = partition(arr, left, right);
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        int i = left, j = right;

        while (i < j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 二分
     */
    public static int search(int[] arr, int value) {
        int length = arr.length;
        int left = 0, right = length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (value < arr[middle]) {
                right = middle - 1;
            } else if (value > arr[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -left - 1;
    }

//    public static void main(String[] args) {
//        int arr[] = {1, 3, 5, 7, 8, 9, 10};
//        for (int i = 0; i < 10; i++) {
//            System.out.println("搜索" + i + ", 结果为：" + search(arr, i));
//        }
//    }




    public static int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        if (row < 1) {
            return -1;
        }

        int col = matrix[0].length;
        if (col < 1) {
            return -1;
        }

        if (k > row * col) {
            return -1;
        }

        int[] rowIndexArr = new int[row];
        Arrays.fill(rowIndexArr, 0);

        int[] integrationArr = new int[row * col];
        int integrationIndex = 0;

        for (int i = 0; i < row; i++) {
            for (int j = rowIndexArr[i]; j < col; j++) {
                Stack<Pair<Integer, Integer>> addStack = new Stack<>();
                int findStartRow = i;
                while (true) {
                    findButtonValue(findStartRow, j, rowIndexArr, matrix, addStack, matrix[i][j]);
                    if (addStack.size() == 0) {
                        while (true) {
                            findButtonValue(++findStartRow, j, rowIndexArr, matrix, addStack, matrix[i][j]);
                            if (addStack.size() == 0) {
                                break;
                            }
                            for (int i1 = 0; i1 < addStack.size(); i1++) {
                                Pair<Integer, Integer> pop = addStack.pop();
                                integrationArr[integrationIndex++] = matrix[pop.getKey()][pop.getValue()];
                            }
                        }
                        integrationArr[integrationIndex++] = matrix[i][j];
                        break;
                    }
                    for (int i1 = 0; i1 < addStack.size(); i1++) {
                        Pair<Integer, Integer> pop = addStack.pop();
                        integrationArr[integrationIndex++] = matrix[pop.getKey()][pop.getValue()];
                    }
                }
            }
        }

        return integrationArr[k - 1];
    }


    public static void findButtonValue(int curRow, int curCol, int[] rowIndexArr, int[][] matrix, Stack<Pair<Integer, Integer>> addStack, int compareValue) {
        int nextRow = curRow + 1;
        if (nextRow < rowIndexArr.length) {
            int startCol = rowIndexArr[nextRow];

            if (startCol < curCol) {
                if (matrix[nextRow][startCol] < compareValue) {
                    addStack.push(new Pair<>(nextRow, startCol));
                    rowIndexArr[nextRow]++;
                    findButtonValue(nextRow, startCol, rowIndexArr, matrix, addStack,  matrix[nextRow][startCol]);
                }
            }
        }
    }

    public static void main(String[] args) {

        // String s1 = s.replaceAll("\\[", "{").replaceAll("]", "}");
//        int[][] martix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//        int findValue = kthSmallest(martix, 5);
//        System.out.println(findValue);

        int[] ints = {17, 37, 25, 17, 35, 29, 16, 33, 36, 20};
        Cycler.cycleByColumnWithHeader(BoxUtils.boxedIntArr(ints), 5, new CyclePrintByFormatRunner("%-9s"));
        System.out.println();
        quickSortLzc(ints, 0, ints.length - 1);
        Cycler.cycleByColumnWithHeader(BoxUtils.boxedIntArr(ints), 5, new CyclePrintByFormatRunner("%-9s"));
    }


    public static void quickSortLzc(int[] arr, int start, int end) {
        if (end >= arr.length || end - start <= 1) {
            return;
        }
        int partionIndex = portionLz(arr, 0, end);
        quickSortLzc(arr, 0, partionIndex - 1);
        quickSortLzc(arr, partionIndex + 1, end);
    }

    public static int portionLz(int[] arr, int start, int end) {
        int partionIndex = end;
        boolean model = true;
        int leftIndex = start, rightIndex = end;
        while (leftIndex < rightIndex) {
            if (model) {
                while (arr[leftIndex] < arr[partionIndex] && leftIndex < partionIndex) leftIndex++;
                if (leftIndex >= partionIndex) {
                    break;
                }
                swap(arr, leftIndex, partionIndex);
                partionIndex = leftIndex;
            } else {
                while (arr[rightIndex] > arr[partionIndex] && rightIndex > partionIndex) rightIndex--;
                if (rightIndex <= partionIndex) {
                    break;
                }
                swap(arr, rightIndex, partionIndex);
                partionIndex = rightIndex;
            }
            model = !model;
        }
        return partionIndex;
    }


}
