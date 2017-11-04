package com.qe.example.rxjava_retrofit_mvp.callback;

/**
 * Created by zjy on QE.
 */

public interface Callback<T> {
    void success(T t);

    void fail(String msg);
}
