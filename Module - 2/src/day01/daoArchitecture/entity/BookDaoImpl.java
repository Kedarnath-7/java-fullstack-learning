package day01.daoArchitecture.entity;


import daoArchitecture.dao.BookDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private final List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        this.books.add(book);
    }

    @Override
    public void update(Book book) {
        for(Book b : books){
            if(book.getId() == b.getId()){
                b.setTitle(b.getTitle());
                b.setAuthor(b.getAuthor());
                return;
            }
        }
    }

    @Override
    public void deleteById(int id) {
        //for(Book b : books){  // concurrent error
        Iterator<Book> itr = books.iterator();
        while(itr.hasNext()){
            Book book = itr.next();
            if(book.getId() == id){
                itr.remove();
                return;
            }
        }
    }

    @Override
    public Book findById(int id) {
        for(Book b : books){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    @Override
    public Iterable<Book> findAll() {
        return books;
    }

    @Override
    public Iterable<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for(Book b : books){
            if(b.getAuthor().equalsIgnoreCase(author)){
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public void deleteAll() {
        books.clear();
    }

    @Override
    public Iterable<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for(Book b : books){
            if(b.getTitle().equalsIgnoreCase(title)){
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public Iterable<Book> sortByTitleAsc() {
        return null;
    }

    @Override
    public Iterable<Book> sortByTitleDesc() {
        return null;
    }
}
