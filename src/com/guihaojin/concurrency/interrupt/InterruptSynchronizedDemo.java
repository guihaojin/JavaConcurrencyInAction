package com.guihaojin.concurrency.interrupt;

public class InterruptSynchronizedDemo {
    private static final Object lock = new Object();

    static class A extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                while (!Thread.currentThread().isInterrupted()) {
                    // do nothing
                }
                System.out.println(Thread.currentThread().getName() + " exit");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            A a = new A();
            a.start();
            Thread.sleep(100);
            // interrupt a BLOCKED thread doesn't work
            a.interrupt();
            a.join();
        }
    }
}
