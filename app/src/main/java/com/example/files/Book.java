package com.example.files;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
    private String title;
    private String author;
    private int year;
    private int pages;

    public Book() {}
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

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        year = in.readInt();
        pages = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(year);
        dest.writeInt(pages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


}
