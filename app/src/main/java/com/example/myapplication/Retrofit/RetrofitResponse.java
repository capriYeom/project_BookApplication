package com.example.myapplication.Retrofit;

import com.example.myapplication.Model.Book;

import java.util.List;

public class RetrofitResponse {
    private Integer error;
    private Integer total;
    private Integer page;
    private List<Book> books;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
