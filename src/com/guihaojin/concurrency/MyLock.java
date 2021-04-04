package com.guihaojin.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class MyLock {

    AtomicInteger status = new AtomicInteger(0);

    void lock() {
        while (!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }

    void unlock() {
        status.compareAndSet(1, 0);
    }
}
