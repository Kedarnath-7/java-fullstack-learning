package com.northernArc.springDao.dao;

import com.northernArc.springDao.entity.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface TodoDaoJdbc {
    public int saveTodo(Todo todo);
    public Todo findTodoById(int id);
    public void updateTodoById(int id, Todo todo);
    public void deleteTodoById(int id);
    public Collection<Todo> findAllTodos();
    public Collection<Todo> findByTask(String task);
    public Collection<Todo> findByCompleted(boolean completed);
    public Todo mapToTodo(ResultSet rs) throws SQLException;
    public void deleteAllTasks();
    public Collection<Todo> sortTodosByTaskAsc();
    public Collection<Todo> sortTodosByTaskDesc();
    public Collection<Todo> sortTodosByCompletedAsc();
    public Collection<Todo> sortTodosByCompletedDesc();
}

