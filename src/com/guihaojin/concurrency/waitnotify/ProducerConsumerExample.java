package com.guihaojin.concurrency.waitnotify;

import java.util.Random;

public class ProducerConsumerExample {

    static class Producer extends Thread {
        private MyBlockingQueue<Integer> queue;

        public Producer(MyBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " thread produce: " + i);
                    this.queue.put(i);
                    Thread.sleep((int) (Math.random() * 10));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        private MyBlockingQueue<Integer> queue;

        public Consumer(MyBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int i = this.queue.take();
                    System.out.println(Thread.currentThread().getName() + " thread consume: " + i);
                    Thread.sleep((int) (Math.random() * 1000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue<Integer> q = new MyBlockingQueue<>(3);

        Thread p1 = new Producer(q);
        Thread p2 = new Producer(q);

        Thread c1 = new Consumer(q);
        Thread c2 = new Consumer(q);

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
