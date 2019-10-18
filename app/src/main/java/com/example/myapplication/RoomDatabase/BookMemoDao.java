package com.example.myapplication.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BookMemoDao {
    @Query("SELECT * FROM book_memo WHERE book_num =:bookISBN")
    Memo getMemo(Long bookISBN);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Memo memo);

    @Query("update book_memo set book_memo_string =:bookMEMO WHERE book_num =:bookISBN")
    void updateMemo(Long bookISBN, String bookMEMO);

    @Delete
    void delete(Memo memo);

    @Query("DELETE FROM book_memo")
    void clear();
}
