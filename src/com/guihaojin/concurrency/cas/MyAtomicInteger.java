package com.guihaojin.concurrency.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/* DOESN'T WORK... */
public class MyAtomicInteger {
    private volatile int value;
    private static final Unsafe unsafe;
    private static final long valueOffset;

    static {
        try {
            // can't get unsafe from Unsafe directly!!!
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            System.out.println("Initialized unsafe: " + unsafe);

            // get memory address of the offset field
            valueOffset = unsafe.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
            System.out.println("valueOffset: " + valueOffset);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public MyAtomicInteger(int value) {
        this.value = value;
    }

    public final int get() {
        return this.value;
    }

    public final int incrementAndGet() {
        // ERROR: doesn't work???!!!
        for (; ;) {
            int current = get();
            int next = current += 1;
            if (compareAndSet(current, next)) {
                return next;
            }
        }
        // this is what AtomicInteger use
//        return unsafe.getAndAddInt(this, valueOffset, 1);
    }

    private boolean compareAndSet(int current, int next) {
        return unsafe.compareAndSwapInt(this, valueOffset, current, next);
    }
}
