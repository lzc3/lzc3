package com.lzc.leetcode.window;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

public class Q3 extends Solution {

    @HandleSolution
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int ans = 0;

        int index = 0;
        int start = 0;
        StringBuilder sb = new StringBuilder();
        while (index < len) {
            char c = s.charAt(index);
            int charAt = sb.toString().lastIndexOf(c);
            if (charAt != -1) {
                ans = Math.max(ans, sb.length());
                start = start + charAt + 1;
                index = start;
                sb = new StringBuilder();
            } else {
                index++;
                sb.append(c);
            }
        }

        return Math.max(index, ans);
    }

    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[1];
        objects[0] = "abcabcbb";
        return objects;
    }

    public int lengthOfLongestSubstrin1g(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        int ans = 0;
        int left = 0;
        int[] cnt = new int[128];
        for (int right = 0; right < n; right++) {
            char c = charArray[right];
            cnt[c]++;
            while (cnt[c] > 1) {
                cnt[charArray[left]]--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

}
