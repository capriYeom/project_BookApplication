package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("new")
    Call<RetrofitResponse> getList();

    @GET("books/{isbn13}")
    Call<Book> getBookDetail(@Path("isbn13") String isbnNum);
}
