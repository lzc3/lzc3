package com.lzc.strategy.cycle.runner;

/**
 * 打印toString内容
 */
public class CyclePrintRunner implements CycleRunner<Object>{

    private static final CyclePrintRunner INSTANCE = new CyclePrintRunner();

    private CyclePrintRunner() {}

    public static CyclePrintRunner getInstance() {
        return INSTANCE;
    }

    @Override
    public void apply(Object o) {
        System.out.println(o.toString());
    }
}
