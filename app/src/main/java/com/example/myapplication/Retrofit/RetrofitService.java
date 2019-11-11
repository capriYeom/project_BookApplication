package com.example.myapplication.Retrofit;

import com.example.myapplication.Model.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("new")
    Call<RetrofitResponse> getList();

    @GET("books/{isbn13}")
    Call<Book> getBookDetail(@Path("isbn13") String isbnNum);

    @GET("search/{query}")
    Call<RetrofitResponse> getSearchList(@Path("query") String query);

    @GET("search/{query}/{page}")
    Call<RetrofitResponse> getSearchListWithPage(@Path("query") String query, @Path("page") Integer page);
}
