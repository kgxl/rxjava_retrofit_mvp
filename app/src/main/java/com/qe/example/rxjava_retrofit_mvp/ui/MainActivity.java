package com.qe.example.rxjava_retrofit_mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qe.example.rxjava_retrofit_mvp.R;
import com.qe.example.rxjava_retrofit_mvp.bean.Book;
import com.qe.example.rxjava_retrofit_mvp.model.SearchModel;
import com.qe.example.rxjava_retrofit_mvp.presenter.SearchPresenter;
import com.qe.example.rxjava_retrofit_mvp.view.SearchView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends RxAppCompatActivity implements SearchView {

    private SearchPresenter mPresenter;
    private EditText mEtBook;
    private TextView mBook;
    private StringBuilder mStringBuilder;
    private String bookNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtBook = (EditText) findViewById(R.id.et_book);
        mBook = (TextView) findViewById(R.id.book);
        mPresenter = new SearchPresenter();
        SearchModel searchModel = new SearchModel();
        mPresenter.setPresenter(this, searchModel);
        mStringBuilder = new StringBuilder();
    }

    public void search(View view) {
        mBook.setText("");
        bookNames = "";
        mPresenter.search(mEtBook.getText().toString().trim());
    }


    @Override
    public void searchSusses(Book book) {
        bookNames=mStringBuilder.append(book.getPublisher() + "\n").toString();
        mBook.setText(bookNames);
    }

    @Override
    public void searchFail(String errorMsg) {
        mBook.setText(errorMsg);
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }
}
