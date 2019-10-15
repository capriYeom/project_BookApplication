package com.example.myapplication.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Model.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void addAll(List<Book> bookList);

    @Insert
    void all(Book book);

    @Delete
    void delete(Book book);

    @Query("DELETE from book")
    void deleteAll();

    @Query("SELECT * FROM book")
    LiveData<List<Book>> getAll();

    @Query("SELECT * FROM book WHERE isBookmarked")
    LiveData<List<Book>> getBoomarked();
    
    @Query("update book set isBookmarked =:isBookmarked where isbn13 =:isbn13")
    void updateBookmark(Long isbn13, boolean isBookmarked);
}
