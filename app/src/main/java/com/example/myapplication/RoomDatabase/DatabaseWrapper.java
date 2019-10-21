package com.example.myapplication.RoomDatabase;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseWrapper {
    private static DatabaseWrapper sInstance;
    private static final String DATABASE_NAME = "app_database";
    private static ExecutorService executorService;


    private MemoDatabase mDatabase;

    private DatabaseWrapper(Context context) {
        mDatabase = Room.databaseBuilder(context, MemoDatabase.class, DATABASE_NAME).build();
        executorService = Executors.newSingleThreadExecutor();
    }

    public static DatabaseWrapper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseWrapper(context);
        }
        return sInstance;
    }

    public void getBookMemo(final Long bookNum, final GetMemoHandler handler) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (handler != null) {
                    handler.onResult(bookMemoDao().getMemo(bookNum));
                }
            }
        });
    }

    public void addBookMemo(final Memo memo) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                bookMemoDao().insert(memo);
            }
        });
    }

    public void deleteBookmemo(final Memo memo) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                bookMemoDao().delete(memo);
            }
        });
    }

    public void updateBookMemo(final Long bookNum, final String memo) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                bookMemoDao().updateMemo(bookNum, memo);
            }
        });
    }

    private BookMemoDao bookMemoDao() {
        return mDatabase.memoDao();
    }

    public interface GetMemoHandler {
        void onResult(Memo memo);
    }
}
