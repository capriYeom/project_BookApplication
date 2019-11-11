package com.example.myapplication.DataManager;

import com.example.myapplication.Model.Book;
import com.example.myapplication.Model.BookSortType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataSorter {
    private DataSorter() {}

    public static void sortBookmark(BookSortType type, List<Book> bookList) {
        switch (type) {
            case NAME:
                sortByName(bookList);
                break;
            case RATING:
                sortByRating(bookList);
                break;
            case PRICE:
                sortByPrice(bookList);
                break;
            case ISBN:
                sortByISBN(bookList);
                break;
            default:
                break;
        }
    }

    private static void sortByName(List<Book> bookList) {
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    private static void sortByRating(List<Book> bookList) {
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getRating().compareTo(o2.getRating());
            }
        });
    }

    private static void sortByISBN(List<Book> bookList) {
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getIsbn13().compareTo(o2.getIsbn13());
            }
        });
    }

    private static void sortByPrice(List<Book> bookList) {
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                float o1Price = Float.parseFloat(o1.getPrice().substring(1));
                float o2Price = Float.parseFloat(o2.getPrice().substring(1));
                return Float.compare(o1Price, o2Price);
            }
        });
    }
}
