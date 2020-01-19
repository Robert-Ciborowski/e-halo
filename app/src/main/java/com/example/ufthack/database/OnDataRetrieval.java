package com.example.ufthack.database;

public abstract class OnDataRetrieval<T> {
    public T data;
    public abstract void onRetrieval();
}
