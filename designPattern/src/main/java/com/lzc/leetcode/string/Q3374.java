package com.lzc.leetcode.string;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;
import com.lzc.leetcode.test.args.Args;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.stream.Collectors;

public class Q3374 extends Solution {

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {

        Map<Character, String> mapping = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            Integer integer = nums.get(i);
            char curChar = (char) ('a' + i);
            Character character = curChar;
            String mappingStr = generateMappingStr(integer, i);
            mapping.put(character, mappingStr);
        }

        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            for (int j = 0; j < t; j++) {
                str = mappingStr(str, mapping);
            }
            length += str.length();
        }

        return length;
    }

    private String generateMappingStr(Integer integer, int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= integer; j++) {
            int mod = (i + j) % 26;
            sb.append((char) ('a' + mod));
        }
        return sb.toString();
    }

    private String mappingStr(String str, Map<Character, String> mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String mappingStr = mapping.get(c);
            sb.append(mappingStr);
        }
        return sb.toString();
    }

    @HandleSolution
    public int lengthAfterTransformationsV2(String s, int t, List<Integer> nums) {
        StopWatch watch = new StopWatch();
        watch.start();
        Map<Character, String> mapping = new HashMap<>();
        Map<Character, Long> curMapping = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            Integer integer = nums.get(i);
            char curChar = (char) ('a' + i);
            Character character = curChar;
            String mappingStr = generateMappingStr(integer, i);
            mapping.put(character, mappingStr);
            curMapping.put(character, 1L);
        }

        Map<Character, Long> characterLongMap = iterMapping(1, t, mapping, curMapping);
        Long sumLength = 0L;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sumLength += characterLongMap.get(c);
        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        watch.stop();
        double totalTimeSeconds = watch.getTotalTimeSeconds();
        return (int)(sumLength % (Math.pow(10.0, 9) + 7));

    }

    public Map<Character, Long> iterMapping(int iter, int t, Map<Character, String> mapping, Map<Character, Long> curMapping) {


        Map<Character, Long> oldMapping = curMapping;
        for (int j = 0; j < t; j++) {
            Map<Character, Long> newMapping = new HashMap<>();
            Map<Character, Long> finalOldMapping = oldMapping;
            mapping.forEach((c, v) -> {
                Long l = 0L;
                for (int i = 0; i < v.length(); i++) {
                    char charAt = v.charAt(i);
                    l += finalOldMapping.get(charAt);
                }
                l = l % (long)(Math.pow(10.0, 9) + 7);
                newMapping.put(c, l);
            });
            oldMapping = newMapping;
        }


        return oldMapping;
    }

    @Override
    protected Object[] offerArgs() {
        int[] arr = {23,20,4,11,4,24,13,25,12,21,17,7,6,21,12,11,22,25,22,16,19,8,16,18,19,16};
        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());

        return new Args("bvqbowlhpfhpaddcegzxiawnprkhbvqlmqegsydbykdrxywxvtjlqkdssadafdsafafasdfasdfasu", 4921530, collect).getArgs();
        // 774275204
    }

}
