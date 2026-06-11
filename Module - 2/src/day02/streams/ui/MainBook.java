package day02.streams.ui;

import day02.streams.dao.BookDao;
import day02.streams.dao.BookDaoImpl;
import day02.streams.entity.Book;

public class MainBook {
    public static void main(String[] args) {
        BookDao bookDao = new BookDaoImpl();
        bookDao.save(new Book("Parallel world", "Michio Kaku", 1));
        bookDao.save(new Book("History of time", "Stephen Hawking", 2));
        bookDao.save(new Book("Atomic Habits", "Someone", 3));
        bookDao.save(new Book("Java", "James Gosling", 5));
        bookDao.save(new Book("Python", "Van russom", 4));
        bookDao.save(new Book("C", "Denis Richie", 7));
        for(Book book: bookDao.findAll()){
            System.out.println(book);
        }
        bookDao.deleteById(3);
        for(Book book: bookDao.findAll()){
            System.out.println(book);
        }
        System.out.println(bookDao.findByAuthor("Stephen Hawking"));
        System.out.println(bookDao.findByTitle("Parallel World"));

        System.out.println("======================");
        System.out.println("Sorting by title: ");
        System.out.println(bookDao.sortByTitle());
        System.out.println("============================");
        System.out.println("Sorting by author: ");
        System.out.println(bookDao.sortByAuthor());

    }
}
