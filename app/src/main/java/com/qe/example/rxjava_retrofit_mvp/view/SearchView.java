package com.qe.example.rxjava_retrofit_mvp.view;

import com.qe.example.rxjava_retrofit_mvp.base.BaseView;
import com.qe.example.rxjava_retrofit_mvp.bean.Book;

/**
 * Created by zjy on QE.
 */

public interface SearchView extends BaseView{
    void searchSusses(Book book);

    void searchFail(String errorMsg);
}
