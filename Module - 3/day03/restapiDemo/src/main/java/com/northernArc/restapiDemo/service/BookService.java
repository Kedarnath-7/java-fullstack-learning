package com.northernArc.restapiDemo.service;

import com.northernArc.restapiDemo.model.Book;

import java.util.Collection;
import java.util.List;

public interface BookService {
    Book addBook(Book book);
    Book getBook(int id);
    void deleteBook(int id);
    void updateBook(int id, Book book);
    Collection<Book> getAll();
}
