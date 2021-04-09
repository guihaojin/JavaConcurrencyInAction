package com.guihaojin.concurrency.cas;

public class MyLockTest implements Runnable {

    public static class Counter {
//        private volatile int counter = 0;
        private int counter = 0;
        private MyLock lock = new MyLock();

        public void incr() {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }

        public int getCounter() {
            return counter;
        }
    }

//    public MyLock lock = new MyLock();
//    Lock lock = new ReentrantLock();
//    public static volatile int counter = 0;
//    public static AtomicInteger counter = new AtomicInteger(0);
    public static Counter counter = new Counter();

    public static void main(String[] args) throws InterruptedException {
        int threadSize = 20;
        Thread[] threads = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            threads[i] = new Thread(new MyLockTest());
            threads[i].start();
        }
        for (int i = 0; i < 20; i++) {
            threads[i].join();
        }
        System.out.println(counter.getCounter());
//        System.out.println(counter);
    }

//    public synchronized int getCounter() {
//        return counter;
//    }
//
//    public synchronized void incr() {
//        counter++;
//    }

    @Override
    public void run() {
        for(int i = 0; i < 10000; i++) {
//            lock.lock();
//            try {
//                counter++;
//            } finally {
//                lock.unlock();
//            }
            counter.incr();
//            counter.getAndAdd(1);
        }
    }
}
