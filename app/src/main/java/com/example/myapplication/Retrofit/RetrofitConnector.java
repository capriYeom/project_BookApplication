package com.example.myapplication.Retrofit;

import android.util.Log;

import com.example.myapplication.Model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnector {

    private static RetrofitConnector sInstance;

    private Retrofit mRetrofit;
    private Call<RetrofitResponse> mResponse;
    private RetrofitService mService;
    private Call<Book> mBookResponse;

    private BookListListener mListListener;
    private BookListener mListener;

    private RetrofitConnector() {
        initRetrofit();
    }

    public static RetrofitConnector getInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitConnector();
        }
        return sInstance;
    }

    public void callNewList() {
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

            if (mListListener != null) {
                mListListener.onResult(response.body().getBooks());
            }
        }

        @Override
        public void onFailure(Call<RetrofitResponse> call, Throwable t) {
            t.printStackTrace();
            Log.d("CONNECTIONEVENT", "onFailure: " + t);
        }
    };

    private Callback<Book> mRetroBookCallback = new Callback<Book>() {
        @Override
        public void onResponse(Call<Book> call, Response<Book> response) {
            if (mListener != null) {
                mListener.onResult(response.body());
            }
        }

        @Override
        public void onFailure(Call<Book> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public void callDetailBook(String isbn13) {
        mBookResponse = mService.getBookDetail(isbn13);
        mBookResponse.enqueue(mRetroBookCallback);
    }

    public void callSearchList(String query) {
        mResponse = mService.getSearchList(query);
        mResponse.enqueue(mRetrofitCallback);
    }

    public void callSearchListWithPage(String query, Integer pageNum) {
        mResponse = mService.getSearchListWithPage(query, pageNum);
        mResponse.enqueue(mRetrofitCallback);
    }

    public void setBookListListener(BookListListener listener) {
        mListListener = listener;
    }

    public void setBookListener(BookListener listener) {
        mListener = listener;
    }

    public interface BookListListener {
        void onResult(List<Book> bookList);
    }

    public interface BookListener {
        void onResult(Book book);
    }
}
