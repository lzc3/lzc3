package com.lzc.strategy;

import com.lzc.strategy.cycle.Cycler;
import com.lzc.strategy.cycle.runner.CyclePrintRunner;

public class SorterByComparator {

    public static void main(String[] args) {
        Cat[] cats = {new Cat(3), new Cat(2), new Cat(4), new Cat(1)};

        // 匿名内部类
        SorterByComparator.sort(cats, new Comparator<Cat>() {
            @Override
            public int compare(Cat t1, Cat t2) {
                if (t1.getAge() < t2.getAge()) return -1;
                else if (t1.getAge() == t2.getAge()) return 0;
                else return 0;
            }
        });

        // lambda
        SorterByComparator.sort(cats, (o1, o2) -> {
            return 0;
        });

        // 传入类
        Cycler.cycleByObjects(cats, CyclePrintRunner.getInstance());
    }

    public static void sort(Object[] objects, Comparator comparator) {
        for (int i = 0; i < objects.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < objects.length; j++) {
                minPos = comparator.compare(objects[j], objects[minPos]) == -1 ? j : minPos;
            }
            swap(objects, i, minPos);
        }

    }


    public static void swap(Object[] objects, int i, int j) {
        Object temp = objects[i];
        objects[i] = objects[j];
        objects[j] = temp;
    }

}
