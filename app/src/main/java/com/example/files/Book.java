package com.example.files;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Book implements Serializable {
    public String title;
    public String author;
    public int year;
    public int pages;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

//    Геттеры
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getYear() { return year; }
    public int getPages() { return pages; }


}
