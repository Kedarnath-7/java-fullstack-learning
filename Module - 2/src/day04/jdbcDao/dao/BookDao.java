package day04.jdbcDao.dao;
import day04.jdbcDao.entity.Book2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

interface BookDao {
    public int saveBook(Book2 book);
    public Book2 findBookById(int id);
    public void updateBookById(int id, Book2 book);
    public void deleteBookById(int id);
    public Collection<Book2> findAllBooks();
    public Collection<Book2> findByTitle(String title);
    public Collection<Book2> findByAuthor(String author);
    public Collection<Book2> findByPublisher(String publisher);
    public Book2 mapToBook(ResultSet rs) throws SQLException;
    public void deleteAllBooks();
    public Collection<Book2> sortBookByTitleAsc();
    public Collection<Book2> sortBookByTitleDesc();
    public Collection<Book2> sortBooksByAuthorAsc();
    public Collection<Book2> sortBooksByAuthorDesc();
    public Collection<Book2> sortBooksByPublisherAsc();
    public Collection<Book2> sortBooksByPublisherDesc();
}
