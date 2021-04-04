package com.guihaojin.concurrency.visibility;

public class MemoryVisibilityExample {
    private static boolean flag;
    // volatile fixes memory visibility issue
//    private static volatile boolean flag;

    static class WaitThread extends Thread {
        @Override
        public void run() {
            while (!flag) {
                // do nothing
            }
            System.out.println("Flag is true.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new WaitThread();
        t.start();
        Thread.sleep(1000);
        flag = true;
        System.out.println("End of main");
    }
}
