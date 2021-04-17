package com.guihaojin.concurrency.cow;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class MyCopyOnWriteList<E> {
    private final ReentrantLock lock = new ReentrantLock();

    private volatile Object[] array;

    public MyCopyOnWriteList() {
        this.array = new Object[0];
    }

    public Object[] getArray() {
        return this.array;
    }

    public boolean add(E e) {
        lock.lock();
        try {
            Object[] current = getArray();
            int size = current.length;
            Object[] newElements = Arrays.copyOf(current, size + 1);
            newElements[size] = e;
            this.array = newElements;
            return true;
        } finally {
            lock.unlock();
        }
    }
}
