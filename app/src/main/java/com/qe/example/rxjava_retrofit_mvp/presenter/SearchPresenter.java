package com.qe.example.rxjava_retrofit_mvp.presenter;

import android.text.TextUtils;

import com.qe.example.rxjava_retrofit_mvp.base.BaseApi;
import com.qe.example.rxjava_retrofit_mvp.base.BaseObserver;
import com.qe.example.rxjava_retrofit_mvp.base.BasePresenter;
import com.qe.example.rxjava_retrofit_mvp.base.BaseRx;
import com.qe.example.rxjava_retrofit_mvp.bean.Book;
import com.qe.example.rxjava_retrofit_mvp.model.SearchModel;
import com.qe.example.rxjava_retrofit_mvp.view.SearchView;

import java.util.List;

/**
 * Created by zjy on QE.
 */

public class SearchPresenter extends BasePresenter<SearchView, SearchModel> {
    //查找书籍
    public void search(String bookName) {
        if (!TextUtils.isEmpty(bookName)) {
            mModel.getSearchData(bookName).compose(BaseRx.<BaseApi<List<Book>>>io4main()).compose(mView.bindLifecycle()).subscribe((new BaseObserver<Book>() {

                @Override
                public void handleData(Book book) {
                    //返回给V层
                    mView.searchSusses(book);
                }

                @Override
                public void errorMsg(String msg) {
                    mView.searchFail(msg);
                }
            }));
        }
    }
}
