package io.itjun.week4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Demo9CyclicBarrier extends Fibo {

    private static volatile Integer value = null;

    CyclicBarrier barrier = new CyclicBarrier(1, new SumRunnable());

    private Integer get() throws BrokenBarrierException, InterruptedException {
        barrier.await();
        return value;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Demo9CyclicBarrier demo = new Demo9CyclicBarrier();

        new Thread(new SumRunnable()).start();

        int result = 0;
        try {
            result = demo.get();
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    static class SumRunnable extends Fibo implements Runnable {
        @Override
        public void run() {
            value = fibo(36);
        }
    }

}
