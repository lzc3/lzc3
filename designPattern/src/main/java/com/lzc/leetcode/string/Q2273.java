package com.lzc.leetcode.string;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.stream.Collectors;

public class Q2273 extends Solution {

    @HandleSolution
    public String[] removeAnagrams(String[] words) {

        List<String> resultStr = new ArrayList<>();
        int length = words.length;
        int curIndex = length - 1;
        int preIndex = curIndex -1;
        while (preIndex >= 0 && curIndex > 0) {
            String curStr = words[curIndex];
            String preStr = words[preIndex];
            if (compareStr(curStr,preStr)) {
                preIndex--;
            } else {
                resultStr.add(0, words[preIndex + 1]);
                curIndex = preIndex;
                preIndex = curIndex - 1;
            }
        }

        resultStr.add(0, words[0]);

        int size = resultStr.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = resultStr.get(i);
        }

        return result;
    }

    public boolean compareStr(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();

        if (length1 != length2) {
            return false;
        }

        int sum1 = 0, sum2 = 0;
        int pow1 = 1, pow2 = 1;
        for (int i = 0; i < length1; i++) {
            int c1 = str1.charAt(i) -60;
            int c2 = str2.charAt(i) -60;

            sum1 += c1;
            sum2 += c2;
            pow1 *= c1;
            pow2 *= c2;
        }

        return sum1 == sum2 && pow1 == pow2;
    }

    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[1];
        objects[0] = new String[]{"a","b","c","d","e"};
        return objects;
    }

}
