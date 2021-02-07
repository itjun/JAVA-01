package io.itjun.week4;

/**
 * 通过Sleep获取数据
 */
public class Demo4Sleep {

    private static volatile Integer value = null;

    private void sum() {
        value = fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    private Integer get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        Demo4Sleep demo = new Demo4Sleep();

        new Thread(() -> {
            demo.sum();
        }).start();
        int result = demo.get();

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

}
