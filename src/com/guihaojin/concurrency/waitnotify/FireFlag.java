package com.guihaojin.concurrency.waitnotify;

public class FireFlag {
    private volatile boolean fire;

    public synchronized void waitForFire() throws InterruptedException {
        while (!fire) {
            wait();
        }
    }

    public synchronized void fire() {
        this.fire = true;
        notifyAll();
    }
}
