package com.qe.example.rxjava_retrofit_mvp.model;

import com.qe.example.rxjava_retrofit_mvp.base.BaseApi;
import com.qe.example.rxjava_retrofit_mvp.base.BaseModel;
import com.qe.example.rxjava_retrofit_mvp.base.RetrofitHelper;
import com.qe.example.rxjava_retrofit_mvp.bean.Book;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by zjy on QE.
 */

public class SearchModel implements BaseModel {
    //搜索书籍
    public Observable<BaseApi<List<Book>>> getSearchData(String bookName) {
        return RetrofitHelper.getBoobApi().searchBook(bookName);
    }
}
