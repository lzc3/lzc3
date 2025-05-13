package com.lzc.leetcode.array;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

import java.util.Arrays;

public class Q645 extends Solution {

    @HandleSolution
    public int[] findErrorNums(int[] nums) {
        int length = nums.length;
        int[] tempNums = new int[length];
        Arrays.fill(tempNums, 0);

        int[] result = new int[2];
        for (int curNum : nums) {
            if (tempNums[curNum - 1] == 1) {
                tempNums[curNum - 1] = -1;
            } else {
                tempNums[curNum - 1] = 1;
            }

        }

        for (int i = 0; i < tempNums.length; i++) {
            if (tempNums[i] == 0) {
                result[1] = i + 1;
            }
            if (tempNums[i] == -1) {
                result[0] = i + 1;
            }
        }

        return result;
    }

    @Override
    protected Object[] offerArgs() {
        int[] arr = {3,2,2};

        Object[] objects = new Object[1];
        objects[0] = arr;
        return objects;
    }
}
