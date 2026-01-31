package com.lzc.leetcode.doublepoint;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q15 extends Solution {

    @HandleSolution
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i != 0 && (nums[i] == nums[i - 1])) {
                continue;
            }

            int target = -nums[i];
            int third = len - 1;

            for (int second = i + 1; second < len; second++) {
                if (second != (i + 1) && nums[second] == nums[second -1]) {
                    continue;
                }
                while (second < third) {
                    if ((nums[second] + nums[third]) <= target) {
                        break;
                    }
                    third--;
                }

                if (second < third && (nums[second] + nums[third]) == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[1];
        int[] arr = {-1,0,1,2,-1,-4};
        objects[0] = arr;
        return objects;
    }

    public List<List<Integer>> solutionTrain(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int firstNum = nums[i];
            int target = -firstNum;

            int right = len - 1;
            for (int left = i + 1; left < nums.length; left++) {
                if (left > i + 1 && nums[left] == nums[left-1]) {
                    continue;
                }
                while (left < right) {
                    int leftNum = nums[left];
                    int rightNum = nums[right];
                    int curTarget = leftNum + rightNum;
                    if (leftNum + rightNum == target) {
                        List<Integer> ansItem = new ArrayList<>();
                        ansItem.add(firstNum);ansItem.add(leftNum);ansItem.add(rightNum);
                        ans.add(ansItem);
                        break;
                    } else if (curTarget > target) {
                        right--;
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
