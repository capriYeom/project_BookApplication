package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit mRetrofit;
    private Call<RetrofitResponse> mResponse;
    private RetrofitService mService;
    private Call<Book> mBookResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitConnector.getInstance().setBookListListener(new RetrofitConnector.BookListListener() {
            @Override
            public void onResult(List<Book> bookList) {
                Log.d("CONNECTIONEVENT", "onResult: " + bookList.get(0).getTitle());

                RetrofitConnector.getInstance().callDetailBook(bookList.get(0).getIsbn13());
            }
        });

        RetrofitConnector.getInstance().setBookListener(new RetrofitConnector.BookListener() {
            @Override
            public void onResult(Book book) {
                Log.d("CONNECTIONEVENT", "onResult: " + book.getTitle());
            }
        });

        RetrofitConnector.getInstance().callNewList();
        RetrofitConnector.getInstance().callSearchList("Learning");
        RetrofitConnector.getInstance().callSearchListWithPage("Learning", 2);

    }


}
