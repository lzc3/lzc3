package com.lzc.strategy.cycle;

import com.lzc.strategy.Cat;
import com.lzc.strategy.cycle.runner.CycleCatPrintAgeRunner;
import com.lzc.strategy.cycle.runner.CyclePrintByFormatRunner;
import com.lzc.strategy.cycle.runner.CyclePrintRunner;
import com.lzc.strategy.cycle.runner.CycleRunner;
import org.lzc.utils.BoxUtils;

import java.util.stream.IntStream;

public class Cycler{

    private Cycler() {}


    public static void main(String[] args) {
        Cat[] cats = {new Cat(3), new Cat(2), new Cat(4), new Cat(1)};

        Cycler.cycle(cats, CycleCatPrintAgeRunner.getInstance());
        Cycler.cycle(cats, c1 -> {
            System.out.println(c1.getAge());
        });

        Cycler.cycle(cats, CyclePrintRunner.getInstance());

        Cycler.cycleByColumn(null, 10, item -> {
            System.out.printf( "%-8.1f", item);
        });
    }

    /**
     * 循环遍历数组中的每个元素，然后执行对应cycleRunner中的方法
     * @param objects 循环数组
     * @param cycleRunner 执行内容
     */
    public static <T> void cycle(T[] objects, CycleRunner<T> cycleRunner) {
        for (T object : objects) {
            cycleRunner.apply(object);
        }
    }

    public static void cycleByColumnWithHeader(Object[] objects, int column, CycleRunner<Object> cycleRunner) {
        for (int i = 1; i <= column; i++) {
            cycleRunner.apply("column" + i);
        }
        System.out.println();
        cycleByColumn(objects, column, cycleRunner);
    }

    /**
     * 循环遍历数组中的每个元素，然后执行对应cycleRunner中的方法
     *
     * @param objects 循环数组
     * @param column 列数
     * @param cycleRunner 执行内容
     */
    public static <T> void cycleByColumn(T[] objects, int column, CycleRunner<T> cycleRunner) {
        cycleByColumn(objects, column, cycleRunner, false);
    }

    /**
     * 循环遍历数组中的每个元素，然后执行对应cycleRunner中的方法
     *
     * @param objects 循环数组
     * @param column 列数
     * @param cycleRunner 执行内容
     * @param lineBreakFlag 是否换行
     */
    public static <T> void cycleByColumn(T[] objects, int column, CycleRunner<T> cycleRunner, boolean lineBreakFlag) {
        for (int i = 0; i < objects.length; i++) {
            cycleRunner.apply(objects[i]);
            if ((i + 1) % column == 0 && i != objects.length - 1) {
                System.out.println();
            }
        }

        if (lineBreakFlag) {
            System.out.println();
        }
    }

    public static void cycle(int[] intArr) {
        cycle(intArr, CyclePrintRunner.getInstance());
    }

    public static void cycle(int[] intArr, CycleRunner<Object> cycleRunner) {
        for (Object object : intArr) {
            cycleRunner.apply(object);
        }
    }

}
