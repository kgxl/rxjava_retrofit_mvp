package com.qe.example.rxjava_retrofit_mvp.model;

import com.qe.example.rxjava_retrofit_mvp.base.BaseApi;
import com.qe.example.rxjava_retrofit_mvp.base.BaseModel;
import com.qe.example.rxjava_retrofit_mvp.base.BaseObserver;
import com.qe.example.rxjava_retrofit_mvp.bean.Book;
import com.qe.example.rxjava_retrofit_mvp.callback.Callback;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by zjy on QE.
 */

public class SearchModel implements BaseModel {
    //搜索书籍
    public Observer<BaseApi<List<Book>>> getSearchData(final Callback<Book> callback) {
        return new BaseObserver<List<Book>>() {
            @Override
            public void handleData(List<Book> book) {
                for (Book b : book) {
                    //接口回掉给P层
                    callback.success(b);
                }
            }

            @Override
            public void errorMsg(String msg) {
                callback.fail(msg);
            }
        };
    }
}
