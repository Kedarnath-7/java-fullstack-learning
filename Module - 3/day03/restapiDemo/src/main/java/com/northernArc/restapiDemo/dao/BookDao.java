package com.northernArc.restapiDemo.dao;

import com.northernArc.restapiDemo.model.Book;

import java.util.Collection;

public interface BookDao {
    Book addBook(Book book);
    Book findBookById(int id);
    void updateById(int id, Book book);
    void deleteById(int id);
    Collection<Book> findAllBooks();
    void deleteAllBooks();
}
