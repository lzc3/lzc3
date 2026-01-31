package com.lzc.leetcode.string;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

public class Q8 extends Solution {

    @HandleSolution
    public int myAtoi(String s) {
        int len = s.length();

        char addChar = '+';
        char subChar = '-';

        boolean negFlag = false;
        int startIdx = 0;
        for (; startIdx<len; startIdx++) {
            char c = s.charAt(startIdx);
            if (subChar == c) {
                negFlag = true;
                startIdx++;
                break;
            }

            if (addChar == c) {
                startIdx++;
                break;
            }

            if (' ' == c) {
                continue;
            }

            if (isNum(c)) {
                break;
            } else {
                return 0;
            }
        }


        char[] numChars = new char[len];
        int numCharsIdx = 0;
        for (int i = startIdx; i < len; i++) {
            char c = s.charAt(i);
            if (isNum(c)) {
                numChars[numCharsIdx++] = c;
            } else{
                break;
            }
        }

        long finNum = 0;
        for (int i = numCharsIdx - 1; i >= 0; i--) {
            finNum += Math.pow(10, i) * transChar(numChars[numCharsIdx - 1 - i]);
        }
        finNum = negFlag ? Math.max(Integer.MIN_VALUE, -finNum) : Math.min(Integer.MAX_VALUE, finNum);
        return (int)finNum;
    }

    public int transChar(char c) {
        int start = (int)'9';
        return 9 - start + (int)c;

    }

    public boolean isNum(char c) {
        return '9' == c
                || '8' == c
                || '7' == c
                || '6' == c
                || '5' == c
                || '4' == c
                || '3' == c
                || '2' == c
                || '1' == c
                || '0' == c;
    }

    @Override
    protected Object[] offerArgs() {
        Object[] objects = new Object[1];
        objects[0] = "-91283472332";
        return objects;
    }
}
