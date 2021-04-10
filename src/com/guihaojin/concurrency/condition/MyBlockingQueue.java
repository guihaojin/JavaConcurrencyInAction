package com.guihaojin.concurrency.condition;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * implement blocking queue with 2 conditions
 */
public class MyBlockingQueue<E> {
    private Queue<E> queue;
    private int limit;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        queue = new ArrayDeque<>(limit);
    }

    public void put(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (this.queue.size() >= this.limit) {
                notFull.await();
            }
            queue.add(e);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (this.queue.isEmpty()) {
                notEmpty.await();
            }
            E e = this.queue.poll();
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
