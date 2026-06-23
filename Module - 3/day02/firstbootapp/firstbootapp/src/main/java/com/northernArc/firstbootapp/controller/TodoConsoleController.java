package com.northernArc.firstbootapp.controller;

import com.northernArc.firstbootapp.dao.TodoDao;
import com.northernArc.firstbootapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TodoConsoleController {
    @Autowired
    private Scanner scanner;

    @Autowired
    private TodoDao todoDao;

    public void showMenu() {
        while(true) {
            System.out.println("Enter 1 to add");
            System.out.println("Enter 2 to delete");
            System.out.println("Enter 3 to update");
            System.out.println("Enter 4 to find all");
            System.out.println("Enter 5 to exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 5) {
                break;
            }
            redirect(choice);
        }
    }

    private void redirect(int choice){
        switch(choice){
            case 1:
                add();
                break;
            case 2:
                delete();
                break;
            case 3:
                update();
                break;
            case 4:
                listAll();
                break;
            default:
                System.out.println("Invalid choice..");
        }

    }

    private void add() {
        System.out.println("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter content: ");
        String content = scanner.nextLine();
        System.out.println("Enter done: ");
        boolean done = scanner.nextBoolean();
        scanner.nextLine();
        todoDao.save(new Todo(id, content, done));
    }

    private void delete() {
        System.out.println("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        todoDao.deleteById(id);
    }

    private void update() {
        System.out.println("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter content: ");
        String content = scanner.nextLine();
        System.out.println("Enter done: ");
        boolean done = scanner.nextBoolean();
        scanner.nextLine();
        todoDao.updateById(id, new Todo(id, content, done));
    }

    private void listAll() {
        todoDao.findAll().forEach((k, v)->{
            System.out.println(k+": "+v);
        });
    }


}
