package day01.daoArchitecture.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Todo {
    private int id;
    private String task;
    private boolean completed;
    private Set<Todo> tasks;

    public Todo(int id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
        this.tasks = new LinkedHashSet<>();
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
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toString() {
        return "id: " + id + ", task: " + task + ", completed: " + completed;
    }
}
