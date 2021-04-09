package com.guihaojin.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    private volatile int counter;
    private final Lock lock = new ReentrantLock();

    public void incr() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        return counter;
    }

    static class CounterThread extends Thread {
        private LockCounter lockCounter;

        public CounterThread(LockCounter lockCounter) {
            this.lockCounter = lockCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                lockCounter.incr();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        LockCounter lockCounter = new LockCounter();
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new CounterThread(lockCounter);
            threads[i].start();
        }
        for (int i = 0; i < n; i++) {
            threads[i].join();
        }
        System.out.println(lockCounter.get());
    }
}
