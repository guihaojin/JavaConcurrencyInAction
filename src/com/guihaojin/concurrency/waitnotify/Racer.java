package com.guihaojin.concurrency.waitnotify;

public class Racer extends Thread {
    private FireFlag fireFlag;

    public Racer(FireFlag flag) {
        this.fireFlag = flag;
    }

    @Override
    public void run() {
        try {
            this.fireFlag.waitForFire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " race run.");
    }

    public static void main(String[] args) throws InterruptedException {
        FireFlag fireFlag = new FireFlag();
        int n = 8;
        Thread[] racers = new Thread[n];
        for (int i = 0; i < n; i++) {
            racers[i] = new Racer(fireFlag);
            racers[i].start();
        }
        for (int i = 10; i >= 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("Fire!");
        fireFlag.fire();
    }
}
