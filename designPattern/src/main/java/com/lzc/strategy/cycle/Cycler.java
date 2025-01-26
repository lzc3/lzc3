package com.lzc.strategy.cycle;

import com.lzc.strategy.Cat;
import com.lzc.strategy.cycle.runner.CycleCatPrintAgeRunner;
import com.lzc.strategy.cycle.runner.CyclePrintRunner;
import com.lzc.strategy.cycle.runner.CycleRunner;

public class Cycler <T>{


    private static final Cycler INSTANCE = new Cycler();

    private Cycler() {}

    public static <R> Cycler<R> getInstance() {
        return INSTANCE;
    }


    public static void main(String[] args) {
        Cat[] cats = {new Cat(3), new Cat(2), new Cat(4), new Cat(1)};

        Cycler<Cat> instanceCat = Cycler.getInstance();
        instanceCat.cycle(cats, CycleCatPrintAgeRunner.getInstance());
        instanceCat.cycle(cats, c1 -> {
            System.out.println(c1.getAge());
        });

        Cycler.cycleByObjects(cats, CyclePrintRunner.getInstance());
    }

    /**
     * 循环遍历数组中的每个元素，然后执行对应cycleRunner中的方法
     * @param objects 循环数组
     * @param cycleRunner 执行内容
     */
    public void cycle(T[] objects, CycleRunner<T> cycleRunner) {
        for (T object : objects) {
            cycleRunner.apply(object);
        }
    }

    public static void cycleByObjects(Object[] objects, CycleRunner<Object> cycleRunner) {
        for (Object object : objects) {
            cycleRunner.apply(object);
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
