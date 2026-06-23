package com.northernArc.springDao.ui;
import com.northernArc.springDao.dao.TodoDao;
import com.northernArc.springDao.dao.TodoDaoJdbc;
import com.northernArc.springDao.entity.Todo;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TodoConsoleController {
    private Scanner scanner;
    private TodoDao todoDao;
    private TodoDaoJdbc todoDaoJdbc;
    public TodoConsoleController(Scanner scanner, TodoDao todoDao){
        this.scanner=scanner;
        this.todoDao=todoDao;
    }
    public TodoConsoleController(Scanner scanner, TodoDaoJdbc todoDao){
        this.scanner=scanner;
        this.todoDaoJdbc=todoDao;
    }
    public void printWelcomeMessage(){
        System.out.println("Welcome to Todo Dao service..");
    }
    public void showMenu(){
        while(true) {
            System.out.println("1:Add");
            System.out.println("2:Update");
            System.out.println("3:Delete");
            System.out.println("4:List All");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            redirectChoice(choice);
        }
    }
    private void redirectChoice(int choice) {
        switch(choice){
            case 1:
                add();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
            case 4:
                listAll();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    private void listAll() {
        System.out.println(todoDao.findAllTodos());
    }
    private void delete() {
        System.out.println("Enter id of task to be deleted:");
        int id=scanner.nextInt();
        todoDao.deleteById(id);
        todoDaoJdbc.deleteTodoById(id);
    }
    private void update() {
        System.out.println("Enter id of task to be updated:");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new content:");
        String content=scanner.nextLine();
        System.out.println("Enter new done status:");
        boolean done=scanner.nextBoolean();
        todoDao.updateById(id,new Todo(content,done));
        todoDaoJdbc.updateTodoById(id, new Todo(content,done));
    }
    private void add() {
        System.out.println("Enter id:");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter content:");
        String content=scanner.nextLine();
        System.out.println("Enter done:");
        boolean done=scanner.nextBoolean();
        todoDao.save(new Todo(id,content,done));
        todoDaoJdbc.saveTodo((new Todo(content,done)));
    }
}