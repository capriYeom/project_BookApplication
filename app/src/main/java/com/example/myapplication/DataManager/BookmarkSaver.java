package com.example.myapplication.DataManager;

import com.example.myapplication.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookmarkSaver {
    private static BookmarkSaver sInstance;
    private List<Book> mBookList;

    private BookmarkSaver() {
        mBookList = new ArrayList<>();
    }

    public static BookmarkSaver getInstance() {
        if (sInstance == null) {
            sInstance = new BookmarkSaver();
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
