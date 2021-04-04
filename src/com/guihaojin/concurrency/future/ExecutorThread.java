package com.guihaojin.concurrency.future;

import java.util.concurrent.Callable;

public class ExecutorThread<V> extends Thread {
    private V result;
    private Callable<V> task;
    private Object lock;
    private Exception exception;
    private boolean done;

    public ExecutorThread(Callable<V> task, Object lock) {
        this.task = task;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            result = task.call();
        } catch (Exception e) {
            exception = e;
        } finally {
            synchronized (lock) {
                done = true;
                lock.notifyAll();
            }
        }
    }

    public V getResult() {
        return result;
    }

    public boolean isDone() {
        return done;
    }

    public Exception getException() {
        return exception;
    }
}
