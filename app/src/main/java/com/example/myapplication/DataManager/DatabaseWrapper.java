package com.example.myapplication.DataManager;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

public class DatabaseWrapper {
    private static DatabaseWrapper sInstance;
    private static final String DATABASE_NAME = "app_database";

    private MemoDatabase mDatabase;

    private DatabaseWrapper(Context context) {
        mDatabase = Room.databaseBuilder(context, MemoDatabase.class, DATABASE_NAME).build();
    }

    public static DatabaseWrapper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseWrapper(context);
        }
        return sInstance;
    }

    public void getBookMemo(final Long bookNum, final GetMemoHandler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (handler != null) {
                    Log.d("BOOK", "run: getBOOKMEMO");
                   handler.onResult(bookMemoDao().getMemo(bookNum));
                }
            }
        }).start();
    }

    public void addBookMemo(final Memo memo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("BOOK", "run: addBOOKMEMO");
                bookMemoDao().insert(memo);
            }
        }).start();
    }

    public void deleteBookmemo(final Memo memo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bookMemoDao().delete(memo);
            }
        }).start();
    }

    public void updateBookMemo(final Long bookNum, final String memo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bookMemoDao().updateMemo(bookNum, memo);
            }
        }).start();
    }

    private BookMemoDao bookMemoDao() {
        return mDatabase.memoDao();
    }

    public interface GetMemoHandler {
        void onResult(Memo memo);
    }
}
