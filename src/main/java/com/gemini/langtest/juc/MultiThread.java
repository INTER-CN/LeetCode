package com.gemini.langtest.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 天何
 * @date 2022/7/18
 */
public class MultiThread {

    private Lock lock = new ReentrantLock();

    private CountDownLatch latch = new CountDownLatch(2);

    private int num = 1;

    public void print(int i) {
        while (num < 100) {
            lock.lock();

            if (num < 100 && num % 3 == i) {
                System.out.println("===>" + Thread.currentThread().getName() + " | " + num);
                num++;
            }

            lock.unlock();
        }
    }

    public void testWaitCountDown(int n) {
        try {
            latch.await();
            System.out.println("CountDownLatch await " + n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testCountDown(int n) {
        try {
            Thread.sleep(n * 1000L);
            latch.countDown();
            System.out.println("CountDownLatch countDown " + n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void f() {
        System.out.println("execute f()");
        new Thread(() -> {
            System.out.println("new thread");
            f();
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        f();
    }

}
