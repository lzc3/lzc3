package com.lzc.leetcode;

import java.util.*;

/**
 * N皇后问题
 * 小结：拆分问题，一步一步解决，不要急
 * todo 目前为暴力解法，学习优化后的算法
 */
public class Nqueen {

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);
        System.out.println(lists);
    }

    public static List<List<String>> solveNQueens(int n) {
        // 初始化路径，将第一行加入到路径
        List<List<Integer>> path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            path.add(Collections.singletonList(i));
        }

        // 遍历剩余n-1行
        for (int i = 1; i < n; i++) {
            List<List<Integer>> iterPath = new ArrayList<>();
            for (List<Integer> integers : path) {
                for (int j = 0; j < n; j++) {
                    if (integers.contains(j)) {
                        continue;
                    }
                    List<Integer> mergeList = new ArrayList<>(integers);
                    mergeList.add(j);
                    if (checkList(mergeList)) {
                        iterPath.add(mergeList);
                    }
                }
            }
            path = iterPath;
        }

        return transPathToResult(path, n);
    }

    public static boolean checkList(List<Integer> mergeList) {
//        // 校验是否不同列
//        Set<Integer> distinctSet = new HashSet<>();
//        for (Integer integer : mergeList) {
//            if (distinctSet.contains(integer)) {
//                return false;
//            }
//            distinctSet.add(integer);
//        }

        // 校验是否在对角线
        int curIndex = mergeList.size() - 1;
        for (int i = 0; i < curIndex; i++) {
            if (Math.abs(mergeList.get(curIndex) - mergeList.get(i)) == (curIndex - i)) {
                return false;
            }
        }

        return true;
    }

    public static List<List<String>> transPathToResult(List<List<Integer>> path, int n) {
        char normal = '.';
        char sit = 'Q';

        List<List<String>> result = new ArrayList<>();
        for (List<Integer> iterPath : path) {
            List<String> everySolution = new ArrayList<>();
            for (Integer rowSit : iterPath) {
                char[] str = new char[n];
                Arrays.fill(str, normal);
                str[rowSit] = sit;
                everySolution.add(new String(str));
            }
            result.add(everySolution);
        }
        return result;
    }
}
