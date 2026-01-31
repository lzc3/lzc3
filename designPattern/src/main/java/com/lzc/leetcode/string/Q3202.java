package com.lzc.leetcode.string;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;

import java.util.*;
import java.util.stream.Collectors;

public class Q3202 extends Solution {

    @HandleSolution
    public int maximumLength(int[] nums, int k) {
        int[][] dp = new int[k][k];
        int res = 0;
        for (int num : nums) {
            num %= k;
            for (int prev = 0; prev < k; prev++) {
                dp[num][prev] = dp[prev][num] + 1;
                res = Math.max(res, dp[num][prev]);
            }
        }
        return res;
    }
    @Override
    protected Object[] offerArgs() {
        int[] ints = {3,8,10,7,6};
        Object[] objects = new Object[2];
        objects[0] = ints;
        objects[1] = 2;
        return objects;
    }
}
