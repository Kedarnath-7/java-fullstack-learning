package day02.streams.entity;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int id;
    private List<Book> books;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.books = new ArrayList<>();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "id=" + id + ", title=" + title + ", author=" + author;
    }

}
