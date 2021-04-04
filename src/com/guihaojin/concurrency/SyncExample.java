package com.guihaojin.concurrency;

public class SyncExample {

    public static void doSomethingA() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--doSomethingA--");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--doSomethingB--");
    }

    public static void main(String[] args) throws InterruptedException {
//        // fully synchronous
//        long start = System.currentTimeMillis();
//        doSomethingA();
//        doSomethingB();
//        System.out.println(System.currentTimeMillis() - start);

        Thread t = new Thread();
        t.start();
        // async
//        long start1 = System.currentTimeMillis();
//        Thread thread = new Thread(() -> {
//            try {
//                doSomethingA();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        thread.start();
//        thread.join();
//        System.out.println("main finished here");
//        doSomethingB();
//        thread.join(); // wait for thread to finish
//        System.out.println(System.currentTimeMillis() - start1);
    }
}
