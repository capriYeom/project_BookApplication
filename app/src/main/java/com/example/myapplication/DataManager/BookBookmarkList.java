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

    private void sortBookmark(BookSortType type) {
        switch (type) {
            case NAME:
                sortByName();
                break;
            case RATING:
                sortByRating();
                break;
            case PRICE:
                sortByPrice();
                break;
            case ISBN:
                sortByISBN();
                break;
            default:
                break;
        }
    }

    private void sortByName() {
        Collections.sort(mBookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    private void sortByRating() {
        Collections.sort(mBookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getRating().compareTo(o2.getRating());
            }
        });
    }

    private void sortByISBN() {
        Collections.sort(mBookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getIsbn13().compareTo(o2.getIsbn13());
            }
        });
    }

    private void sortByPrice() {
        Collections.sort(mBookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                float o1Price = Float.parseFloat(o1.getPrice().substring(1));
                float o2Price = Float.parseFloat(o2.getPrice().substring(1));
                return Float.compare(o1Price, o2Price);
            }
        });
    }

    public List<Book> getBookList() {
        if (PreferenceUtils.getSortType() != null) {
            sortBookmark(PreferenceUtils.getSortType());
        }
        return mBookList;
    }
}
