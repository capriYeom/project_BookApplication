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

    private void setIsbn13(Long isbn13) {
        this.isbn13 = isbn13;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    private void setAuthors(String authors) {
        this.authors = authors;
    }

    private void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    private void setLanguage(String language) {
        this.language = language;
    }

    private void setIsbn10(Integer isbn10) {
        this.isbn10 = isbn10;
    }

    private void setPages(Integer pages) {
        this.pages = pages;
    }

    private void setYear(Integer year) {
        this.year = year;
    }

    private void setRating(Integer rating) {
        this.rating = rating;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    private void setPrice(String price) {
        this.price = price;
    }

    private void setImage(String image) {
        this.image = image;
    }

    private void setUrl(String url) {
        this.url = url;
    }
}
