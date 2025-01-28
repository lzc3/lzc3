package com.lzc.train;

import com.lzc.strategy.cycle.Cycler;

import java.util.StringJoiner;

public class Video_003_Binary {

    public static String split = " ";

    public static void main(String[] args) {
        Cycler.cycle(new int[]{1,2,3,4,5}, item -> {
            printNumBinaryValue((int) item, split);
        });
    }

    /**
     * 打印num的二进制格式
     *
     * @param num 十进制数
     * @param split 分隔符
     */
    public static void printNumBinaryValue(int num, String split) {
        System.out.printf("将%s转换为二进制结果如下:\n", num);
        StringJoiner sj = new StringJoiner(split);
        for (int i = 31; i >= 0; i--) {
            sj.add((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println(sj);
    }

}
