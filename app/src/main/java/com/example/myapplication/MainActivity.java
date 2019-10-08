package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

        initRetrofit();
        callNewList();
    }

    private void callNewList() {
        mResponse = mService.getList();
        mResponse.enqueue(mRetrofitCallback);
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.itbook.store/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = mRetrofit.create(RetrofitService.class);
    }

    private Callback<RetrofitResponse> mRetrofitCallback = new Callback<RetrofitResponse>() {
        @Override
        public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
            assert response.body() != null;
            String result = response.body().getBooks().get(1).getTitle();
            String error = String.valueOf(response.body().getError());
            Log.d("CONNECTIONEVENT", "onResponse: " + error + result);

            callDetailBook(response.body().getBooks().get(1).getIsbn13());
        }

        @Override
        public void onFailure(Call<RetrofitResponse> call, Throwable t) {
            t.printStackTrace();
            Log.d("CONNECTIONEVENT", "onFailure: " + t);
        }
    };

    private void callDetailBook(String isbn13) {
        mBookResponse = mService.getBookDetail(isbn13);
        mBookResponse.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                String result = response.body().getAuthors();
                Log.d("CONNECTIONEVENT", "onResponse: " + result);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
