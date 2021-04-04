package com.guihaojin.concurrency.waitnotify;

import java.util.Date;

public class AssemblePointExample {

    static class Tourist extends Thread {
        private AssemblePoint assemblePoint;

        public Tourist(AssemblePoint assemblePoint) {
            this.assemblePoint = assemblePoint;
        }

        @Override
        public void run() {
            try {
                  Thread.sleep((int) (Math.random() * 5000));
                System.out.println(new Date() + Thread.currentThread().getName() + " await.");
                this.assemblePoint.await();
                System.out.println(new Date() + Thread.currentThread().getName() + " arrived.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int size = 10;
        AssemblePoint assemblePoint = new AssemblePoint(size);
        Tourist[] tourists = new Tourist[size];
        for (int i = 0; i < size; i++) {
            tourists[i] = new Tourist(assemblePoint);
            tourists[i].start();
        }
    }
}
