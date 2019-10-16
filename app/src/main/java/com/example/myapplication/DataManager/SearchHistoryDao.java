package com.example.myapplication.DataManager;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SearchHistoryDao {
    @Query("SELECT * FROM search_keyword")
    List<String> getAllHistory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(SearchKeywordHistory keyword);

    @Query("delete FROM search_keyword")
    void clearHistory();
}
