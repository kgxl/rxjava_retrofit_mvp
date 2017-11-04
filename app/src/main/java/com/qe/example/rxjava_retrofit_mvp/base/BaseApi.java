package com.qe.example.rxjava_retrofit_mvp.base;

/**
 * Created by zjy on LT
 */

public class BaseApi<T> {

    private int count;
    private T books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getBooks() {
        return books;
    }

    public void setBooks(T books) {
        this.books = books;
    }
}
