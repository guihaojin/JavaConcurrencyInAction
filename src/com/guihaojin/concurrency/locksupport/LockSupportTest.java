package com.guihaojin.concurrency.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " park");
            LockSupport.park(); // 放弃CPU
            System.out.println(Thread.currentThread().getName() + " continue");
        });
        t.start();
        System.out.println(t.getName() + " started.");

        System.out.println("Main thread sleeping...");
        Thread.sleep(3000);
        System.out.println("Un-park thread " + t.getName());
        LockSupport.unpark(t);
    }
}
