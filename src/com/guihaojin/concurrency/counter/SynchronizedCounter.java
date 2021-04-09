package com.guihaojin.concurrency.counter;

public class SynchronizedCounter {

    public static class SyncCounter {
        private int counter;

        public synchronized void incr() {
            counter++;
        }

        public synchronized int get() {
            return counter;
        }
    }

    public static class CounterThread extends Thread {
        private SyncCounter counter;

        public CounterThread(SyncCounter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incr();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncCounter counter = new SyncCounter();
        int threads_num = 20;
        Thread[] threads = new Thread[threads_num];
        for (int i = 0; i < threads_num; i++) {
            threads[i] = new CounterThread(counter);
            threads[i].start();
        }
        for (int i = 0; i < threads_num; i++) {
            threads[i].join();
        }
        System.out.println(counter.get());
    }

}
