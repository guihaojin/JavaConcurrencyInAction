package com.guihaojin.concurrency;

public class DeadLockExample {

    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                try {
                    Thread.sleep(1000);
                    synchronized (lockB) {
                        System.out.println("acquired lockB in t1");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                try {
                    Thread.sleep(1000);
                    synchronized (lockA) {
                        System.out.println("acquired lockA in t2");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
