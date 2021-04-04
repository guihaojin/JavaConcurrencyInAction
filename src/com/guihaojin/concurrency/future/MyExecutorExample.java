package com.guihaojin.concurrency.future;

import java.util.concurrent.Callable;

public class MyExecutorExample {

    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();

        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int mills = (int) (Math.random() * 3000);
                Thread.sleep(mills);
                return mills;
            }
        };

        MyFuture<Integer> future = executor.execute(task);
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
