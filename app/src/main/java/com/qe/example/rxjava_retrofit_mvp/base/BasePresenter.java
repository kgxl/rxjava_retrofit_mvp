package com.qe.example.rxjava_retrofit_mvp.base;

/**
 * Created by zjy on QE.
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {
    //持有M层和V层，必须是要实现BaseView和BaseModel
    public V mView;
    public M mModel;

    public void setPresenter(V view, M model) {
        mView = view;
        mModel = model;
    }

    public void setPresenter(V view) {
        mView = view;
    }

}
