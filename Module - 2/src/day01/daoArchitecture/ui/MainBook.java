package day01.daoArchitecture.ui;

import daoArchitecture.dao.BookDao;
import daoArchitecture.entity.Book;
import daoArchitecture.entity.BookDaoImpl;

public class MainBook {
    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();
        bookDao.save(new Book("Parallel world", "Michio Kaku", 1));
        bookDao.save(new Book("History of time", "Stephen Hawking", 2));
        bookDao.save(new Book("Atomic Habits", "Someone", 3));
        for(Book book: bookDao.findAll()){
            System.out.println(book);
        }
        bookDao.deleteById(3);
        for(Book book: bookDao.findAll()){
            System.out.println(book);
        }
        System.out.println(bookDao.findByAuthor("Stephen Hawking"));
        System.out.println(bookDao.findByTitle("Parallel World"));

    }
}
