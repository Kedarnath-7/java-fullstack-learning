package day01.daoArchitecture.ui;

import day01.daoArchitecture.dao.TodoDao;
import day01.daoArchitecture.entity.Todo;
import day01.daoArchitecture.dao.TodoDaoImpl;

public class TodoMain {
    public static void main(String[] args) {
        TodoDao todoDao = new TodoDaoImpl();
        todoDao.save(new Todo(12, "Check mail", false));
        todoDao.save(new Todo(15, "revise today notes", true));
        todoDao.save(new Todo(19, "Exercise", false));
        todoDao.save(new Todo(23, "practice java", true));

        for(Todo task: todoDao.findAll()) {
            System.out.println(task);
        }
        System.out.println("=====================");
        todoDao.deleteById(19);
        for(Todo task: todoDao.findAll()) {
            System.out.println(task);
        }
        System.out.println("=========================");
        System.out.println(todoDao.findById(15));
        System.out.println(todoDao.findById(23));
    }
}
