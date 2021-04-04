package com.guihaojin.concurrency.waitnotify;

public class Worker extends Thread {
    MyLatch latch;

    public Worker(MyLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + " count down");
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int workerNum = 10;
        MyLatch latch = new MyLatch(workerNum);
        Thread[] workers = new Thread[workerNum];
        for (int i = 0; i < 10; i++) {
            workers[i] = new Worker(latch);
            workers[i].start();
        }
        // wait for all worker threads to finish
        latch.await();
        System.out.println("collect worker results");
    }
}
