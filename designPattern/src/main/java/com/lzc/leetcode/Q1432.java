package com.lzc.leetcode;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.Test;
import com.lzc.leetcode.test.args.Args;

import java.util.Map;
import java.util.Stack;

public class Q1432 extends Solution {


    @HandleSolution
    public int maxDiff(int num) {

        int copyNum = num;
        Stack<Integer> stack = new Stack<>();
        do {
            int curSit = copyNum % 10;
            stack.push(curSit);
            copyNum = copyNum / 10;
        } while (copyNum != 0);

        int[] valueArr = transStackToArr(stack);
        int max = calculateInt(valueArr, 9);
        int min = calculateInt(valueArr, 1);
        min = min == 0 ? calculateInt(valueArr, 1, false) : min;
        return max - min;
    }
    private int calculateInt(int[] sitArr, int compareNum) {
        return calculateInt(sitArr, compareNum, true);
    }
    private int calculateInt(int[] sitArr, int compareNum, boolean moveFlag) {
        int oriValue = -1;
        int num = 0;
        int waitSub = 0;
        int headNum = sitArr[0];
        for (int i = 0; i < sitArr.length; i++) {
            if (oriValue == -1 && (sitArr[i] != compareNum)) {
                oriValue = sitArr[i];
                num += compareNum * (int) Math.pow(10, sitArr.length - i -1);
                if (oriValue == headNum) {
                    num -= waitSub;
                }
            } else if (sitArr[i] == oriValue) {
                num += compareNum * (int) Math.pow(10, sitArr.length - i -1);
            } else {
                num += sitArr[i] * (int) Math.pow(10, sitArr.length - i -1);
                if (moveFlag && i == 0 && compareNum == 1) {
                    waitSub -= compareNum * (int) Math.pow(10, sitArr.length - i -1);
                    compareNum = 0;
                }
            }
        }
        return num;
    }
    private int[] transStackToArr(Stack<Integer> stack) {

        int size = stack.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = stack.pop();
        }

        return arr;
    }

    @Override
    protected Object[] offerArgs() {
        Object num = 123456;
        return new Args(num).getArgs();
    }
}
