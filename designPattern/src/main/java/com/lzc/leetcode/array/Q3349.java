package com.lzc.leetcode.array;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import org.lzc.utils.BoxUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Q3349 extends Solution {

    @HandleSolution
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int size = nums.size();
        int cnt = 1, preCnt = 0, ans = 0;
        for (int i = 1; i < size; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                cnt++;
            } else {
                preCnt = cnt;
                cnt = 1;
            }
            ans = Math.max(ans, Math.min(cnt, preCnt));
            ans = Math.max(ans, cnt / 2);
        }

        return ans >= k;
    }


    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[2];
        int[] arr = {2,5,7,8,9,2,3,4,3,1};
        objects[0] = Arrays.stream(BoxUtils.boxedIntArr(arr)).collect(Collectors.toList());
        objects[1] = 3;
        return objects;
    }
}
