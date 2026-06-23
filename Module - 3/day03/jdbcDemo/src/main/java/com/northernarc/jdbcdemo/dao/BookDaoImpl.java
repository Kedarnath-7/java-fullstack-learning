package com.northernarc.jdbcdemo.dao;

import com.northernArc.restapiDemo.model.Book;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookDaoImpl implements BookDao {

    private Map<Integer, Book> books;

    @PostConstruct
    public void init() {
        System.out.println("Initializing Books Data...");
        books = new HashMap<>();
        books.put(10, new Book(10, "Parallel Worlds", "Michio Kaku", "Unknown"));
        books.put(20, new Book(20, "History of time", "Stephen Hawking", "SK Publications"));
        books.put(30, new Book(30, "Java", "James Gosling", "Java Publications"));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Books Data...");
        books.clear();
    }

    @Override
    public Book addBook(Book book) {
        books.put(book.getId(), book);
        return books.get(book.getId());
    }

    @Override
    public Book findBookById(int id) {
        return books.get(id);
    }

    @Override
    public void updateById(int id, Book book) {
        books.put(id, book);
    }

    @Override
    public void deleteById(int id) {
        books.remove(id);
    }

    @Override
    public Collection<Book> findAllBooks() {
        return books.values();
    }

    @Override
    public void deleteAllBooks() {
        books.clear();
    }
}
