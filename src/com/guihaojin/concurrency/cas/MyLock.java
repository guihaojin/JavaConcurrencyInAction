package com.guihaojin.concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * implement permissive blocking lock with CAS/AtomicInteger
 */
public class MyLock {

    AtomicInteger status = new AtomicInteger(0);

    public void lock() {
        while (!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }

    public void unlock() {
        status.compareAndSet(1, 0);
    }
}
