package com.northernArc.springDao.ui;

import com.northernArc.springDao.dao.BookDao;
import com.northernArc.springDao.entity.Book;
import java.util.Scanner;

public class BookConsoleController {
    private Scanner scanner;
    private BookDao bookDao;

    public BookConsoleController(Scanner scanner, BookDao bookDao){
        this.scanner = scanner;
        this.bookDao = bookDao;
    }

    public void showWelcomeMessage(){
        System.out.println("Welcome to the Book Console Application....");
        System.out.println("Explore wide range of operations you can perform...");
    }

    public void showMenu(){
        System.out.println("1. Add book");
        System.out.println("2. View all books");
        System.out.println("3. Update book");
        System.out.println("4. Delete book");
        System.out.println("5. Find by id");
        System.out.println("6. Find by author");
        System.out.println("7. Find by publisher");
        System.out.println("8. Sort by title asc");
        System.out.println("9. Sort by title desc");
        System.out.println("10. Sort by author asc");
        System.out.println("11. Sort by author desc");
        System.out.println("12. Sort by publisher asc");
        System.out.println("13. Sort by publisher desc");
        System.out.println("14. Delete all books");
        System.out.println("15. Find by title");
        int choice = scanner.nextInt();
        redirectChoice(choice);
    }

    private void redirectChoice(int choice){
        switch (choice){
            case 1:
                add();
                break;
            case 2:
                findAll();
                break;
            case 3:
                updateById();
                break;
            case 4:
                deleteById();
                break;
            case 5:
                findById();
                break;
            case 6:
                findByAuthor();
                break;
            case 7:
                findByPublisher();
                break;
            case 8:
                bookDao.sortBookByTitleAsc();
                break;
            case 9:
                bookDao.sortBookByTitleDesc();
                break;
            case 10:
                bookDao.sortBooksByAuthorAsc();
                break;
            case 11:
                bookDao.sortBooksByAuthorDesc();
                break;
            case 12:
                bookDao.sortBooksByPublisherAsc();
                break;
            case 13:
                bookDao.sortBooksByPublisherDesc();
                break;
            case 14:
                findByTitle();
                break;
            default:
                System.out.println("Invalid choice...");

        }
    }

    private void add(){
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();
        bookDao.saveBook(new Book(title, author,publisher));
    }

    private void updateById(){
        System.out.println("Enter book id: ");
        int id = scanner.nextInt();
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();
        bookDao.updateBookById(id, new Book(title, author,publisher));
    }

    private void deleteById(){
        System.out.println("Enter book id: ");
        int id = scanner.nextInt();
        bookDao.deleteBookById(id);
    }
    private void findById(){
        System.out.println("Enter book id: ");
        int id = scanner.nextInt();
        bookDao.findBookById(id);
    }
    private void findAll(){
        bookDao.findAllBooks();
    }

    private void findByAuthor(){
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        bookDao.findByAuthor(author);
    }
    private void findByTitle(){
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        bookDao.findByTitle(title);
    }
    private void findByPublisher(){
        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();
        bookDao.findByPublisher(publisher);
    }


}
