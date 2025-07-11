package com.lzc.leetcode;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;

import java.util.Map;
import java.util.Stack;

public class Q2566 extends Solution {


    @HandleSolution
    public int minMaxDifference(int num) {

        int copyNum = num;
        Stack<Integer> stack = new Stack<>();
        do {
            int curSit = copyNum % 10;
            stack.push(curSit);
            copyNum = copyNum / 10;
        } while (copyNum != 0);

        int[] valueArr = transStackToArr(stack);
        int max = calculateMaxInt(valueArr);
        int min = calculateMinInt(valueArr);
        return max - min;
    }

    private int calculateMinInt(int[] sitArr) {
        int oriValue = -1;
        int num = 0;
        for (int i = 0; i < sitArr.length; i++) {
            if (oriValue == -1) {
                oriValue = sitArr[i];
            } else if (sitArr[i] != oriValue) {
                num += sitArr[i] * (int) Math.pow(10, sitArr.length - i -1);
            }
        }
        return num;
    }

    private int calculateMaxInt(int[] sitArr) {
        int oriValue = -1;
        int num = 0;
        for (int i = 0; i < sitArr.length; i++) {
            if (oriValue == -1 && sitArr[i] != 9) {
                oriValue = sitArr[i];
                num += 9 * (int) Math.pow(10, sitArr.length - i -1);
            } else if (sitArr[i] == oriValue) {
                num += 9 * (int) Math.pow(10, sitArr.length - i -1);
            } else {
                num += sitArr[i] * (int) Math.pow(10, sitArr.length - i -1);
            }
        }
        return num;
    }

    private int[] transStackToArr(Stack<Integer> stack) {

        int size = stack.size();
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = stack.pop();
        }

        return arr;
    }

    @Override
    protected Object[] offerArgs() {
        Object num = 999;
        return new Args(num).getArgs();
    }
}
