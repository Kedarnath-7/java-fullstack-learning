package com.northernArc.springDao.dao;
import com.northernArc.springDao.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface BookDao {
    public int saveBook(Book book);
    public Book findBookById(int id);
    public void updateBookById(int id, Book book);
    public void deleteBookById(int id);
    public Collection<Book> findAllBooks();
    public Collection<Book> findByTitle(String title);
    public Collection<Book> findByAuthor(String author);
    public Collection<Book> findByPublisher(String publisher);
    public Book mapToBook(ResultSet rs) throws SQLException;
    public void deleteAllBooks();
    public Collection<Book> sortBookByTitleAsc();
    public Collection<Book> sortBookByTitleDesc();
    public Collection<Book> sortBooksByAuthorAsc();
    public Collection<Book> sortBooksByAuthorDesc();
    public Collection<Book> sortBooksByPublisherAsc();
    public Collection<Book> sortBooksByPublisherDesc();
}
