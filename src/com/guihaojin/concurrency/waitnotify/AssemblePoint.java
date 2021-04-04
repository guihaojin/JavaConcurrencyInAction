package com.guihaojin.concurrency.waitnotify;

public class AssemblePoint {
    private volatile int n;

    public AssemblePoint(int n) {
        this.n = n;
    }

    public synchronized void await() throws InterruptedException {
        if (n > 0) {
            n--;
        }
        while (n > 0) {
            wait();
        }
        notifyAll();
    }
}
