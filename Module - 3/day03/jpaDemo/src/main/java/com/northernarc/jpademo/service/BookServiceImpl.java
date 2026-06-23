package com.northernarc.jpademo.service;

import com.northernarc.jpademo.model.Book;
import com.northernarc.jpademo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        // validation logic
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(int id) {
        // validation logic
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteBook(int id) {
        // validation logic
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBook(int id, Book book) {
        // validation logic
        Book book1 = bookRepository.findById(id).get();
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setPublisher(book.getPublisher());
        bookRepository.save(book1);
    }

    @Override
    public Collection<Book> getAll() {
        // validation logic
        return bookRepository.findAll();
    }


}
