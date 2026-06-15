package day04.jdbcDao.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class Todo {
    private int id;
    private String task;
    private boolean completed;

    public Todo() {

    }
    public Todo(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }
    public Todo(int id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;

    }
    public  int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public Boolean isCompleted() {
        return this.completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toString() {
        return "id: " + id + ", task: " + task + ", completed: " + completed;
    }
}
