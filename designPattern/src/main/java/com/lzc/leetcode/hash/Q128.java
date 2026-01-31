package com.lzc.leetcode.hash;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

import java.util.*;

public class Q128 extends Solution {

    @HandleSolution
    public int longestConsecutive(int[] nums) {
        Set<String> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(String.valueOf(num));
        }

        int max = 0;
        for (int num : nums) {
            if (!numSet.contains(String.valueOf(num-1))) {
                int curNum = num + 1;
                int index = 1;
                while (numSet.contains(String.valueOf(curNum))) {
                    index++;
                    curNum = curNum + 1;
                }
                max = Math.max(max, index);
            }
        }

        return max;
    }

    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[1];
        int[] arr = {100,4,200,1,3,2};
        objects[0] = arr;
        return objects;
    }


    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> indexMap = new HashMap<>();

        for (String str : strs) {
            String sortStr = sortStr(str);
            List<String> stringList = indexMap.computeIfAbsent(sortStr, key -> new ArrayList<>());
            stringList.add(str);
        }

        return new ArrayList<>(indexMap.values());
    }
    public static String sortStr(String str) {
        StringBuilder sb = new StringBuilder();
        int[] nums = new int[str.length()];
        for (int i = 0; i < str.length() ; i++) {
            char s = str.charAt(i);
            nums[i] = (int)s;
        }

        Arrays.sort(nums);

        for (int i : nums) {
            char c = (char)i;
            sb.append(c);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        String[] arrs = new String[2];
        arrs[0] = "abc";
        arrs[1] = "cba";
        groupAnagrams(arrs);
    }
}
