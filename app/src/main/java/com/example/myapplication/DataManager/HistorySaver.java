package com.example.myapplication.DataManager;

import com.example.myapplication.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class HistorySaver {
    private List<Book> mBookList;
    private static HistorySaver sInstance;

    private HistorySaver() {
        mBookList = new ArrayList<>();
    }

    public static HistorySaver getInstance() {
        if (sInstance == null) {
            sInstance = new HistorySaver();
        }

        return sInstance;
    }

    public void addBookToList(Book book) {
        for (Book targetBook : mBookList) {
            if (targetBook.getTitle().equals(book.getTitle())) {
                return;
            }
        }
        mBookList.add(book);
    }

    public void removeBookFromList(Book book) {
        for (Book targetBook : mBookList) {
            if (targetBook.getTitle().equals(book.getTitle())) {
                mBookList.remove(targetBook);
            }
        }
    }

    public List<Book> getBookList() {
        return mBookList;
    }
}
