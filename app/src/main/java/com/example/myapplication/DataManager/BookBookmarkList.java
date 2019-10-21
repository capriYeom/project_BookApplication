package com.example.myapplication.DataManager;

import com.example.myapplication.Model.Book;
import com.example.myapplication.Model.BookSortType;
import com.example.myapplication.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookBookmarkList {

    private static BookBookmarkList sInstance;
    private List<Book> mBookList;

    private BookBookmarkList() {
        mBookList = new ArrayList<>();
    }

    public static BookBookmarkList getInstance() {
        if (sInstance == null) {
            sInstance = new BookBookmarkList();
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
        if (PreferenceUtils.getSortType() != null) {
            DataSorter.sortBookmark(PreferenceUtils.getSortType(), mBookList);
        }
        return mBookList;
    }
}
