package day02.streams.dao;

import day02.streams.entity.Book;

import java.util.List;

public interface BookDao {
    public void save(Book book);
    public void update(Book book);
    public void deleteById(int id);
    public Book findById(int id);
    public Iterable<Book> findAll();
    public Iterable<Book> findByAuthor(String author);
    public void deleteAll();
    public Iterable<Book> findByTitle(String title);
    public Iterable<Book> sortByTitleAsc();
    public Iterable<Book> sortByTitleDesc();
    public Iterable<Book> findAllByAuthor(String author);

    public List<Book> sortByTitle();
    public List<Book> sortByAuthor();
}
