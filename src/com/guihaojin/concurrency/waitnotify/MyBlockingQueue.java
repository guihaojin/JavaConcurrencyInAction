package com.guihaojin.concurrency.waitnotify;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBlockingQueue<E> {

    private int limit;
    private Queue<E> queue;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        this.queue = new ArrayDeque<>(limit);
    }

    public synchronized void put(E element) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        E e = queue.poll();
        notifyAll();
        return e;
    }
}
