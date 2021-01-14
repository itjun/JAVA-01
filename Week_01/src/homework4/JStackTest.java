package homework4;

import java.util.concurrent.locks.ReentrantLock;

public class JStackTest extends Thread {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public JStackTest(String name) {
        setName(name);
    }

    @Override
    public void run() {
        System.out.println("this is " + Thread.currentThread().getName());
        doSomething();
    }

    public void doSomething() {
        if ("t1".equals(Thread.currentThread().getName())) {
            try {
                lock1.lock();
                System.out.println("t1 get lock1, wait for lock2");
                sleep(100);
                lock2.lock();
                System.out.println("t1 is do something, " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock2.lock();
                lock1.unlock();
            }
        } else {
            try {
                lock2.lock();
                System.out.println("t2 get lock2, wait for lock1");
                sleep(300);
                lock1.lock();
                System.out.println("t2 is do something, " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        JStackTest jStackTest1 = new JStackTest("t1");
        JStackTest jStackTest2 = new JStackTest("t2");
        jStackTest1.start();
        jStackTest2.start();
    }
}
