package com.qe.example.rxjava_retrofit_mvp.presenter;

import android.text.TextUtils;

import com.qe.example.rxjava_retrofit_mvp.base.BaseApi;
import com.qe.example.rxjava_retrofit_mvp.base.BasePresenter;
import com.qe.example.rxjava_retrofit_mvp.base.BaseRx;
import com.qe.example.rxjava_retrofit_mvp.base.RetrofitHelper;
import com.qe.example.rxjava_retrofit_mvp.bean.Book;
import com.qe.example.rxjava_retrofit_mvp.callback.Callback;
import com.qe.example.rxjava_retrofit_mvp.model.SearchModel;
import com.qe.example.rxjava_retrofit_mvp.view.SearchView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjy on QE.
 */

public class SearchPresenter extends BasePresenter<SearchView, SearchModel> {
    //查找书籍
    public void search(String bookName) {
        if (!TextUtils.isEmpty(bookName)) {
            RetrofitHelper.getBoobApi().searchBook(bookName).compose(BaseRx.<BaseApi<List<Book>>>io4main()).compose(mView.bindLifecycle()).subscribe(mModel.getSearchData(new Callback<Book>() {
                @Override
                public void success(Book book) {
                    //返回给V层
                    mView.searchSusses(book);
                }

                @Override
                public void fail(String msg) {
                    mView.searchFail(msg);
                }
            }));
        }
    }
}
