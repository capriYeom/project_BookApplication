package com.example.myapplication.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Model.Book;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    private static BookDatabase sInstance;

    public abstract BookDao postDao();

    private static final Object sLock = new Object();

    public static BookDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), BookDatabase.class, "BOOK.db").allowMainThreadQueries().build();
            }
            return sInstance;
        }
    }
}
