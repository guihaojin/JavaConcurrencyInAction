package com.guihaojin.concurrency.interrupt;

import java.util.Date;

public class InterruptExample extends Thread {

    @Override
    public void run() {
        // without this check, interrupt() only set interrupt flag to true
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(new Date());
        }
        System.out.println("Done");
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptExample t = new InterruptExample();
        t.start();
        Thread.sleep(20);
        System.out.println(Thread.currentThread().getName() + ": interrupt thread t");
        // interrupt RUNNABLE thread
        t.interrupt();

        // interrupt WAITING/TIMED_WAITING
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(isInterrupted());
                }
            }
        };
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }
}
