package io.itjun.week4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo6ReentrantLock extends Fibo {

    private static volatile Integer value = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    private Integer get() throws InterruptedException {
        lock.lock();
        try {
            while (value == null) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
        return value;
    }

    private void sum() {
        lock.lock();
        try {
            value = fibo(36);
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Demo6ReentrantLock demo = new Demo6ReentrantLock();

        new Thread(() -> {
            demo.sum();
        }).start();

        int result = demo.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
