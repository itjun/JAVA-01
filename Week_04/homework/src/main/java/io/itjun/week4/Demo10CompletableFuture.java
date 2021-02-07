package io.itjun.week4;

import java.util.concurrent.CompletableFuture;

public class Demo10CompletableFuture extends Fibo {

    private static int sum() {
        return fibo(36);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Integer result = CompletableFuture.supplyAsync(() -> sum()).join();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
