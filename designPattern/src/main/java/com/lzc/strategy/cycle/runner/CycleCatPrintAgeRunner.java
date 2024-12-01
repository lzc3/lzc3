package com.lzc.strategy.cycle.runner;

import com.lzc.strategy.Cat;

public class CycleCatPrintAgeRunner implements CycleRunner<Cat>{


    private static final CycleCatPrintAgeRunner INSTANCE = new CycleCatPrintAgeRunner();

    private CycleCatPrintAgeRunner() {}

    public static CycleCatPrintAgeRunner getInstance() {
        return INSTANCE;
    }

    @Override
    public void apply(Cat cat) {
        System.out.println(cat.getAge());
    }
}
