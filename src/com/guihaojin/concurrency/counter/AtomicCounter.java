package com.guihaojin.concurrency.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private static AtomicInteger counter = new AtomicInteger();

    static class CounterThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                counter.getAndAdd(1);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threads_num = 20;
        Thread[] threads = new Thread[threads_num];
        for (int i = 0; i < threads_num; i++) {
            threads[i] = new CounterThread();
            threads[i].start();
        }
        for (int i = 0; i < threads_num; i++) {
            threads[i].join();
        }
        System.out.println(counter);
    }

}
