package com.guihaojin.concurrency.future;

import java.util.concurrent.Callable;

public class MyExecutor {

    // pass <V> here!!!
    public <V> MyFuture<V> execute(final Callable<V> task) {
        final Object lock = new Object();
        final ExecutorThread<V> thread = new ExecutorThread<>(task, lock);
        thread.start();

        MyFuture<V> future = new MyFuture<V>() {
            @Override
            public V get() throws Exception {
                synchronized (lock) {
                    while (!thread.isDone()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                    if (thread.getException() != null) {
                        throw thread.getException();
                    }
                    return thread.getResult();
                }
            }
        };
        return future;
    }
}
