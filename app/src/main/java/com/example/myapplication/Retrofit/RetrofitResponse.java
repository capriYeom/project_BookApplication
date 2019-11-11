package com.example.myapplication.Retrofit;

import com.example.myapplication.Model.Book;

import java.util.List;

class RetrofitResponse {
    private Integer error;
    private Integer total;
    private Integer page;
    private List<Book> books;

    Integer getError() {
        return error;
    }

    void setError(Integer error) {
        this.error = error;
    }

    Integer getTotal() {
        return total;
    }

    void setTotal(Integer total) {
        this.total = total;
    }

    Integer getPage() {
        return page;
    }

    void setPage(Integer page) {
        this.page = page;
    }

    List<Book> getBooks() {
        return books;
    }

    void setBooks(List<Book> books) {
        this.books = books;
    }
}
