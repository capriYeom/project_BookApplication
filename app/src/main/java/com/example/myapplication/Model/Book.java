package com.example.myapplication.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    private Long isbn13;
    private String title;
    private String subtitle;
    private String authors;
    private String publisher;
    private String language;
    private Integer isbn10;
    private Integer pages;
    private Integer year;
    private Integer rating;
    private String desc;
    private String price;
    private String image;
    private String url;

    public Book() {}

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getIsbn10() {
        return isbn10;
    }

    public Long getIsbn13() {
        return isbn13;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }
}
