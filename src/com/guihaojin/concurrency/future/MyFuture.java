package com.guihaojin.concurrency.future;

public interface MyFuture<V> {
    V get() throws Exception;
}
