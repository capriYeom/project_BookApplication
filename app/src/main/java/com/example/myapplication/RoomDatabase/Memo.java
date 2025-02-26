package com.example.myapplication.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_memo")
public class Memo {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_num")
    private Long bookNum;

    @ColumnInfo(name = "book_memo_string")
    private String memo;

    public Memo(@NonNull Long bookNum, String memo) {
        this.bookNum = bookNum;
        this.memo = memo;
    }

    Long getBookNum() {
        return bookNum;
    }

    public String getMemo() {
        return memo;
    }
}
