package com.guihaojin.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitThread extends Thread {
    private volatile boolean fire;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Override
    public void run() {
        lock.lock();
        try {
            while (!fire) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ": fired.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void setFire() {
        lock.lock();
        try {
            fire = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();

        System.out.println(Thread.currentThread().getName() + ": sleeping...");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + ": fire");
        waitThread.setFire();
    }
}
