package io.itjun.week4;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 使用Semaphore方式,相当于是一个景区的望远镜,只有望远镜有空位了才可以进去看风景
 */
public class Demo5Semaphore extends Fibo {
    private static volatile int value = 0;

    private final static Semaphore semaphore = new Semaphore(1);

    public Demo5Semaphore() throws InterruptedException {
        semaphore.acquire();
    }

    private void sum() {
        value = fibo(36);
        semaphore.release();
    }

    private Integer get() throws InterruptedException {
        //在此处进行了抢占
        semaphore.acquire();
        semaphore.release();
        return value;
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Demo5Semaphore demo = new Demo5Semaphore();

        new Thread(() -> {
            demo.sum();
        }).start();

        System.out.println("异步计算结果为：" + demo.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }
}
