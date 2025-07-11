package com.lzc.train;


import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

public class Week3 {

    public static void main(String[] args) throws InterruptedException {
        Week3 week3 = new Week3();
//        week3.testList();
        week3.testSet();

        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            LockSupport.unpark(mainThread);
        });
        thread.start();

        Thread.sleep(3000);
        System.out.println();
        LockSupport.park("我锁住了");

        System.out.println("===");
//        LockSupport.park("我锁住了");
        System.out.println("第二次park将会阻塞");

        byte[] a = new byte[1024];
        a[0] = 1;
        System.out.println(a[0]);
        System.out.println(a[1]);

        Semaphore semaphore = new Semaphore(3);
    }


    public void testList() {
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        List<String> vector = new Vector<>();

//        testFailFast();

        testSet();

        testQueue();

        Map<String, String> testMap = new HashMap<>();
        for (Map.Entry<String, String> entry : testMap.entrySet()) {

        }

        System.out.println();
    }

    private void testQueue() {
        // 无界队列（LinkedList）：add() 和 offer() 行为相同
        Queue<Integer> linkedListQueue = new LinkedList<>();
        System.out.println("LinkedListQueue (无界队列):");
        linkedListQueue.add(1);       // 添加成功
        linkedListQueue.offer(2);     // 添加成功
        System.out.println("队列内容: " + linkedListQueue); // [1, 2]

        // 有界队列（ArrayBlockingQueue，容量为 2）
        Queue<Integer> boundedQueue = new ArrayBlockingQueue<>(2);
        System.out.println("\nArrayBlockingQueue (容量=2):");

        // add() 方法：队列已满时抛出异常
        boundedQueue.add(3);
        boundedQueue.add(4);
        try {
            boundedQueue.add(5); // 队列已满，抛出 IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("add() 失败: " + e.getMessage()); // 队列已满
        }

        // offer() 方法：队列已满时返回 false
        System.out.println("offer(5): " + boundedQueue.offer(5)); // false
        System.out.println("队列内容: " + boundedQueue); // [3, 4]
    }


    @SneakyThrows
    public void testFailFast() {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }

        Thread thread1 = new Thread(() -> {
            for (String s : list) {
                System.out.println(s);
            }
            countDownLatch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            list.add("101");
            countDownLatch.countDown();
        });

        thread1.start();
        thread2.start();
        countDownLatch.await();
    }

    public void testSet() {
        // 1. HashSet: 无序
        System.out.println("=== HashSet ===");
        Set<String> hashSet = new HashSet<>();
        hashSet.add("apple");
        hashSet.add("banana");
        hashSet.add("cherry");
        hashSet.add(null); // 允许 null
        hashSet.add("apple"); // 重复元素会被忽略
        System.out.println("HashSet 顺序: " + hashSet); // 输出顺序不确定

        // 2. LinkedHashSet: 保持插入顺序
        System.out.println("\n=== LinkedHashSet ===");
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("apple");
        linkedHashSet.add("cherry");
        linkedHashSet.add("banana");

        linkedHashSet.add(null); // 允许 null
        linkedHashSet.add("apple"); // 重复元素会被忽略
        System.out.println("LinkedHashSet 顺序: " + linkedHashSet); // 输出顺序与插入顺序一致

        // 3. TreeSet: 自然排序（元素需实现 Comparable）
        System.out.println("\n=== TreeSet ===");
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("apple");
        treeSet.add("banana");
        treeSet.add("cherry");
        // treeSet.add(null); // 报错：NullPointerException
        treeSet.add("apple"); // 重复元素会被忽略
        System.out.println("TreeSet 顺序: " + treeSet); // 输出按字典序排序

        // 4. TreeSet: 自定义排序（通过 Comparator）
        System.out.println("\n=== TreeSet with Custom Comparator ===");
        Set<Integer> reverseTreeSet = new TreeSet<>(Comparator.reverseOrder());
        reverseTreeSet.add(3);
        reverseTreeSet.add(1);
        reverseTreeSet.add(2);
        System.out.println("自定义排序 TreeSet: " + reverseTreeSet); // 输出降序排列 [3, 2, 1]

        // 5. TreeSet 特有的范围查询功能
        System.out.println("\n=== TreeSet Range Query ===");
        TreeSet<Integer> numberSet = new TreeSet<>();
        for (int i = 1; i <= 10; i++) {
            numberSet.add(i);
        }
        System.out.println("大于等于 5 的元素: " + numberSet.tailSet(5)); // [5, 6, 7, 8, 9, 10]
        System.out.println("小于 8 的元素: " + numberSet.headSet(8)); // [1, 2, 3, 4, 5, 6, 7]
        System.out.println("范围在 [3, 7) 的元素: " + numberSet.subSet(3, 7)); // [3, 4, 5, 6]

    }



}
