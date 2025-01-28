package com.lzc.strategy.cycle.runner;

public class CyclePrintByFormatRunner implements CycleRunner<Object>{

    String format;

    public CyclePrintByFormatRunner(String format) {
        if (format == null) {
            this.format = "%-8.1f";
        } else {
            this.format = format;
        }
    }

    @Override
    public void apply(Object o) {
        System.out.printf(format, o);
    }
}
