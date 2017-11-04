package com.qe.example.rxjava_retrofit_mvp.base;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjy on LT
 */

public abstract class BaseObserver<T> implements Observer<BaseApi<T>> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull BaseApi<T> tBaseApi) {
        if (tBaseApi.getCount() > 0) {
            handleData(tBaseApi.getBooks());
        } else {
            errorMsg("未搜索到");
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof ConnectException)
            errorMsg("无网络");
        errorMsg(e.getMessage());
    }

    @Override
    public void onComplete() {
    }

    public abstract void handleData(T t);

    public abstract void errorMsg(String msg);
}
