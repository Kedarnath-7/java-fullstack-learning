package com.northernArc.springDao.entity;

public class Book {
    private String title;
    private String author;
    private int id;
    private String publisher;

    public Book(){

    }
    public Book(String title, String author, String publisher){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public Book(int id, String title, String author, String publisher){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }
    public String getPublisher(){
        return this.publisher;
    }
    public int getId(){
        return this.id;
    }

    public void setTitle(String title){
        this.title =title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    @Override
    public String toString(){
        return "{Title: " + this.title + ", Author: " + this.author + ", Publisher: " + this.publisher + "}";
    }
}
