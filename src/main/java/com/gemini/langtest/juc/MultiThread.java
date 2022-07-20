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

    public static void main(String[] args) throws InterruptedException {
//        MultiThread multiThread = new MultiThread();
//
//        Thread t1 = new Thread(() -> {
//            multiThread.print(1);
//        });
//        t1.start();
//
//        Thread t2 = new Thread(() -> {
//            multiThread.print(2);
//        });
//        t2.start();
//
//        Thread t3 = new Thread(() -> {
//            multiThread.print(0);
//        });
//        t3.start();

        CountDownLatch latch = new CountDownLatch(3);
        latch.countDown();
        System.out.println("countDown");
        latch.countDown();
        System.out.println("countDown");
//        latch.countDown();
//        System.out.println("countDown");
        latch.await();
        System.out.println("await");
        latch.await();
        System.out.println("await");
        latch.await();
        System.out.println("await");
        latch.await();
        System.out.println("await");
    }

}
