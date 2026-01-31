package com.lzc.leetcode.doublepoint;

import com.lzc.Main;
import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

import java.util.Stack;

public class Q42 extends Solution {

    @HandleSolution
    public int trap(int[] height) {
        int len = height.length;
        int[] dpLeft = new int[len], dpRight = new int[len];

        dpLeft[0] = 0;
        for (int i = 1; i < len; i++) {
            dpLeft[i] = Math.max(dpLeft[i - 1], height[i - 1]);
        }

        dpRight[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            dpRight[i] = Math.max(dpRight[i + 1], height[i + 1]);
        }

        int ans = 0;
        for (int i = 1; i < len -1; i++) {
            int min = Math.min(dpLeft[i], dpRight[i]);
            if (height[i] < min) {
                ans += min - height[i];
            }
        }
        return ans;
    }

    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[1];
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        objects[0] = arr;
        return objects;
    }


    public int trapTrain(int[] height) {
        int ans = 0;

        Stack<Integer> sinStack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int curNum = height[i];
            while (!sinStack.isEmpty() && curNum > height[sinStack.peek()]) {
                int popIndex = sinStack.pop();
                if (!sinStack.isEmpty()) {
                    int peekValue = height[sinStack.peek()];
                    int theta = Math.min(peekValue, curNum);
                    ans += (theta - height[popIndex]) * (i - sinStack.peek() - 1);
                }
            }
            sinStack.push(i);
        }

        return ans;
    }
}
