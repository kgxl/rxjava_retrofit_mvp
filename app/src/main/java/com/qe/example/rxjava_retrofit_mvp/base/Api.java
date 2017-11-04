package com.qe.example.rxjava_retrofit_mvp.base;

import com.qe.example.rxjava_retrofit_mvp.bean.Book;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zjy on QE.
 */

public interface Api {
    @GET("v2/book/search")
    Observable<BaseApi<List<Book>>> searchBook(@Query("q") String bookName);
}
