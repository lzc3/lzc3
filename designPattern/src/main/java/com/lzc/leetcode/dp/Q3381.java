package com.lzc.leetcode.dp;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

import java.util.Arrays;
import java.util.Map;


public class Q3381 extends Solution {

    // 最小前缀和
    // [i, j)

    @HandleSolution
    public long maxSubarraySum(int[] nums, int k) {
        int len = nums.length;

        long[] preSum = new long[len + 1];
        for (int i = 1; i < len + 1; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        long[] modArr = new long[k];
        System.arraycopy(preSum, 0, modArr, 0, k);
        long max = Long.MIN_VALUE;
        for (int i = k; i < len + 1; i++) {
            int mod = i % k;
            long ans = preSum[i] - modArr[mod];
            max = Math.max(ans, max);
            modArr[mod] = Math.min(modArr[mod], preSum[i]);
        }

        return max;
    }

    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[2];
        int[] arr = {3,-11,8};
        objects[0] = arr;
        objects[1] = 1;
        return objects;
    }
}
