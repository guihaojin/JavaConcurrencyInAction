package com.guihaojin.concurrency.counter;

import com.guihaojin.concurrency.cas.MyAtomicInteger;

public class MyAtomicCounter {
    private static MyAtomicInteger myAtomicInteger = new MyAtomicInteger(0);
    private static int counter = 0;

    static class CounterThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                myAtomicInteger.incrementAndGet();
//                counter++;
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

        System.out.println(myAtomicInteger.get());
//        System.out.println(counter);

    }
}
