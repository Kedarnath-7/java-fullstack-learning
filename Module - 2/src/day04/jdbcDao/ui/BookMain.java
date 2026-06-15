package day04.jdbcDao.ui;

import day04.jdbcDao.dao.BookDaoImpl;
import day04.jdbcDao.entity.Book2;

import java.util.Scanner;

public class BookMain {
    private static Scanner sc = new Scanner(System.in);
    private static BookDaoImpl bookDao = new BookDaoImpl();
    public static void main(String[] args) {

        System.out.println("Choose option: 1. Add Book\2. Get Book by id\n3. Find All:");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                addBook();
                break;
            case 2:
                get();
                break;
            case 4:
                findAll();
                break;
            default:
                System.out.println("Wrong choice...");
        }


    }

    static int addBook(){
        System.out.println("Enter title: ");
        String title = sc.nextLine();
        System.out.println("Enter author: ");
        String author = sc.nextLine();
        System.out.println("Enter publisher: ");
        String publisher = sc.nextLine();

        Book2 book = new Book2(title, author, publisher);
        return bookDao.saveBook(book);

    }
    static Book2 get(){
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        return bookDao.findBookById(id);
    }

    static void findAll(){
        bookDao.findAllBooks().forEach(System.out::println);
    }
}
