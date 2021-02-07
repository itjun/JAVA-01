package io.itjun.week4;

public class Demo3Synchronized extends Fibo {

    private volatile static int value = 0;

    private synchronized void sum() {
        value = fibo(36);
        this.notifyAll();
    }

    private synchronized Integer get() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Demo3Synchronized demo = new Demo3Synchronized();
        new Thread(() -> {
            demo.sum();
        }).start();
        int result = demo.get();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
}