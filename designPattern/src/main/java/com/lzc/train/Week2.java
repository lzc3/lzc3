package com.lzc.train;

import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Week2 {

    public static void main(String[] args) {
        testL();
    }

    public static void testDate() {

        LocalDate of = LocalDate.of(2025, 5, 21);


        // 定义格式和时区（例如：上海时区）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));

        String formattedTime = now.format(formatter);
        System.out.println("上海时区当前时间: " + formattedTime);
        String s = "---\n" +
                "title: oracle篇\n" +
                "date: " + formattedTime + "\n" +
                "categories:\n" +
                "  - [Java, 数据库]\n" +
                "tags:\n" +
                "  - oracle\n" +
                "---";

        System.out.println(s);
    }


    @SneakyThrows
    public static void testL() {
        // n m k
        // x -> len1 + len2  cost = len1 * len2

        ThreadLocal<String> session = new ThreadLocal<>();
        session.set("1378925436");

        session.get();
        session.remove();

        final byte b1=1;
        final byte b2=3;
        byte b3=b1+b2;
        System.out.println();



        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            System.out.println("excute-123");
        });
        Future<?> submit = executorService.submit(() -> {
            System.out.println("submit-123");
        });
        Object o = submit.get();

        // T1
        CompletableFuture<Void> futureT1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1 is executing. Current time：" + LocalDate.now());
            // 模拟耗时操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executorService);
// T2
        CompletableFuture<Void> futureT2 = CompletableFuture.runAsync(() -> {
            System.out.println("T2 is executing. Current time：" + LocalDate.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 使用allOf()方法合并T1和T2的CompletableFuture，等待它们都完成
        CompletableFuture<Void> bothCompleted = CompletableFuture.allOf(futureT1, futureT2);

        System.out.println("123");

    }




}
