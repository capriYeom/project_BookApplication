package com.example.myapplication.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.Database.BookDao;
import com.example.myapplication.Database.BookDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookViewModel extends AndroidViewModel {

    private BookDao bookDao;
    private ExecutorService executorService;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookDao = BookDatabase.getInstance(application).postDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Book>> getAllBooks() {
        return bookDao.getAll();
    }

    public void addBook(final Book book) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.add(book);
            }
        });
    }

    public void deleteBook(final Book book) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.delete(book);
            }
        });
    }

}
