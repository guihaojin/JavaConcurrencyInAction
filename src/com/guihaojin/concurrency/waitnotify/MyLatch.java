package com.guihaojin.concurrency.waitnotify;

public class MyLatch {
    private volatile int counter;

    public MyLatch(int counter) {
        this.counter = counter;
    }

    public synchronized void await() throws InterruptedException {
        while (counter > 0) {
            wait();
        }
    }

    public synchronized void countDown() {
        counter--;
        if (counter <= 0) {
            notifyAll();
        }
    }
}
