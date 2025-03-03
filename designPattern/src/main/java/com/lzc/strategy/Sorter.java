package com.lzc.strategy;

import com.lzc.strategy.cycle.Cycler;
import com.lzc.strategy.cycle.runner.CyclePrintRunner;

public class Sorter {


    public static void main(String[] args) {
        Cat[] cats = {new Cat(3), new Cat(2), new Cat(4), new Cat(1)};
        Sorter.sort(cats);
        Cycler.cycle(cats, CyclePrintRunner.getInstance());
    }


     public static void sort(Comparable[] comparables) {
         for (int i = 0; i < comparables.length - 1; i++) {
             int minPos = i;
             for (int j = i + 1; j < comparables.length; j++) {
                 minPos = comparables[j].compareTo(comparables[minPos]) == -1 ? j : minPos;
             }
             swap(comparables, i, minPos);
         }

     }


     public static void swap(Comparable[] comparables, int i, int j) {
         Comparable temp = comparables[i];
         comparables[i] = comparables[j];
         comparables[j] = temp;
     }

}
