package com.example.myapplication.RoomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.Model.Memo;

@Database(entities = {Memo.class}, version = 1, exportSchema = false)
public abstract class MemoDatabase extends RoomDatabase {
    public abstract BookMemoDao memoDao();

}
