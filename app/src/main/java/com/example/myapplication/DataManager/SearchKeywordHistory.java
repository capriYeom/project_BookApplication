package com.example.myapplication.DataManager;

import androidx.room.Entity;

@Entity(tableName = "search_keyword")
public class SearchKeywordHistory {
    public String keyword;

    public SearchKeywordHistory(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
