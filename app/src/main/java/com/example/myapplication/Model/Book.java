package com.example.myapplication.Model;

public class Book {

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

    public Book(Long isbn13, String title, String subtitle, String price, String image, String url) {
        this.isbn13 = isbn13;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.image = image;
        this.url = url;
    }

    public Book(Long isbn13, String title, String subtitle, String authors, String publisher, String language, Integer isbn10, Integer pages, Integer year, Integer rating, String description, String price, String image, String url) {
        this.isbn13 = isbn13;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.language = language;
        this.isbn10 = isbn10;
        this.pages = pages;
        this.year = year;
        this.rating = rating;
        this.desc = description;
        this.price = price;
        this.image = image;
        this.url =  url;
    }

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
