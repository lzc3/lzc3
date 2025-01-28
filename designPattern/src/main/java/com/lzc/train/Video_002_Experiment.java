package com.lzc.train;

import com.lzc.strategy.cycle.Cycler;
import com.lzc.strategy.cycle.runner.CyclePrintByFormatRunner;

import java.util.Arrays;

public class Video_002_Experiment {

    public static void main(String[] args) {
        int n = 100;
        int startValue = 100;
        int t = 100;
        System.out.printf("进行迭代，人数：%s，初始财富：%s，迭代次数 %s\n", n, startValue, t);
        experiment(n, startValue, t);
    }


    public static void experiment(int n, double startValue, int t) {

        Double[] money = new Double[n];
        Arrays.fill(money, startValue);
        for (int i = 0; i < t; i++) {
            boolean[] ifGiveMoney = new boolean[n];
            Arrays.fill(ifGiveMoney, true);
            for (int j = 0; j < money.length; j++) {
                if (money[j] <= 0) {
                    ifGiveMoney[j] = false;
                }
            }

            // 每次迭代中，每个人给出1个财富给其他人
            for (int k = 0; k < ifGiveMoney.length; k++) {
                if (ifGiveMoney[k]) {
                    int giveNum = k;
                    do {
                        giveNum = (int) (Math.random() * n);
                    } while (giveNum == k);
                    money[giveNum] += 1;
                    money[k] -= 1;
                }
            }
        }
        System.out.println("财富迭代完成");

        double gini = calculateGini(money);
        System.out.printf("--基尼系数：%s \n", gini);

        System.out.println("--现阶段财富值如下");
        Arrays.sort(money);
        Cycler.cycleByColumn(money, 10, new CyclePrintByFormatRunner("%-8.1f"));
        System.out.println();
    }


    /**
     * 计算基尼系数
     *
     * @param wealth 浮点数数组
     * @return 基尼系数结果
     */
    private static double calculateGini(Double[] wealth) {
        int length = wealth.length;
        double wealthSum = 0;
        double absSum = 0;

        for (double value : wealth) {
            wealthSum += value;
            for (double v : wealth) {
                absSum += Math.abs(value - v);
            }
        }
        return absSum / (2 * length * wealthSum);
    }


}
