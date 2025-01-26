package com.lzc.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Q3250 {

    public static void main(String[] args) {

//        Arrays.fill(maxQueueOneMapNum, 1);
//        Cycler.cycle(maxQueueOneMapNum);

        int arr[] = {16,5};
        Q3250 q3250 = new Q3250();
        q3250.countOfPairs(arr);

    }

    public int countOfPairs(int[] nums) {

        // init
        Map<Integer, Integer> statusMap = new HashMap<>();
        for (int i = 0; i <= nums[0]; i++) {
            statusMap.put(i, 1);
        }


        for (int i = 1; i < nums.length; i++) {
            statusMap = updateStatusMap(statusMap, nums[i], nums[i-1]);
            if (statusMap.keySet().size() == 0) {
                break;
            }
        }

        int limit = (int) (Math.pow(10.0, 9.0) + 7);
        AtomicLong result = new AtomicLong();
        statusMap.forEach((k,v) -> {
            result.addAndGet(v);
            if (result.get() > limit) {
                result.set(((int)result.get()) % limit);
            }
        });

        return (int)result.get();
    }

    public Map<Integer, Integer> updateStatusMap(Map<Integer, Integer> oldStatusMap, int ci, int preCi) {
        Map<Integer, Integer> newStatusMap = new HashMap<>();
        oldStatusMap.forEach((ai, v) -> {
            int bi = ci - ai;
            int theta = ci - preCi;
            int deStart = theta < 0 ? theta : 0;

            int upTheta = preCi - ci;
            int upStart = upTheta < 0 ? upTheta : 0;
            for (int de = bi + deStart; de >= 0 - upStart; de--) {
                int newAi = ci - de;
                Integer orDefault = newStatusMap.getOrDefault(newAi, 0);
                newStatusMap.put(newAi, orDefault + v);
            }



        });
        return newStatusMap;
    }


}

/*
给你一个长度为 n 的 正 整数数组 nums 。

如果两个 非负 整数数组 (arr1, arr2) 满足以下条件，我们称它们是 单调 数组对：

两个数组的长度都是 n 。
arr1 是单调 非递减 的，换句话说 arr1[0] <= arr1[1] <= ... <= arr1[n - 1] 。
arr2 是单调 非递增 的，换句话说 arr2[0] >= arr2[1] >= ... >= arr2[n - 1] 。
对于所有的 0 <= i <= n - 1 都有 arr1[i] + arr2[i] == nums[i] 。
请你返回所有 单调 数组对的数目。

由于答案可能很大，请你将它对 109 + 7 取余 后返回。


// ai + bi = ci     0 + 2 = 2
        // ci+1 - ci = theta  3 - 2 = 1
        // theta1 - theta2 = theta
        // 0 <= theta2 <= bi


        // 一串数字
        // 每个数字对应的数组个数
        // 计算每个数字对应当前num[i]符合条件的数值个数，更新map
*/


