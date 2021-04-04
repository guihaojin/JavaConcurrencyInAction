package com.guihaojin.concurrency.waitnotify;

public class WaitThread extends Thread {

    private volatile boolean fire;

    @Override
    public void run() {
        synchronized (this) {
            while (!fire) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Fired");
        }
    }

    public synchronized void setFire() {
        fire = true;
        // notify threads in condition waiting queue!!!
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread t = new WaitThread();
        t.start();

        Thread.sleep(1000);
        System.out.println("fire");
        t.setFire();
    }
}
