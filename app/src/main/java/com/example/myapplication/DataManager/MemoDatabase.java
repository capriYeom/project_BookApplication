package com.example.myapplication.DataManager;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Memo.class, SearchKeywordHistory.class}, version = 1, exportSchema = false)
public abstract class MemoDatabase extends RoomDatabase {
    public abstract BookMemoDao memoDao();
    public abstract SearchHistoryDao historyDao();

}
