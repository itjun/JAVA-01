package io.itjun.week4;

/**
 * 通过自循环判断进行获取value
 */
public class Demo1While extends Fibo {

    public volatile static Integer value = 0;

    private void sum() {
        value = fibo(36);
    }

    private Integer get() {
        while (true) {
            if (value != null) {
                return value;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Demo1While obj = new Demo1While();
        new Thread(() -> {
            obj.sum();
        }).start();
        int result = obj.get();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

}
