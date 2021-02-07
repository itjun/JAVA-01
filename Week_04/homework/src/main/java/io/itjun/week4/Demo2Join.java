package io.itjun.week4;

/**
 * 使用Join等待的方式进行获取Value
 */
public class Demo2Join extends Fibo {

    private static volatile Integer value = null;

    private void sum() {
        value = fibo(36);
    }

    private Integer get() {
        return value;
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        Demo2Join demo1 = new Demo2Join();

        Thread t = new Thread(() -> {
            demo1.sum();
        });
        t.start();
        t.join();
        int result = demo1.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }
}
