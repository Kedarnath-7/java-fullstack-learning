package com.northernArc.springDao.dao;

import com.northernArc.springDao.entity.Todo;

import java.util.*;

public class TodoDaoImplCollection implements TodoDao {
    private Map<Integer, Todo> todos = new HashMap<>();
    @Override
    public void save(Todo todo) {
        todos.put(todo.getId(), todo);
    }

    @Override
    public void deleteById(int id) {
        todos.remove(id);
    }

    @Override
    public void updateById(int id, Todo todo) {
        Todo todo1 = todos.get(id);
        if(todo1 != null){
            todo1.setTask(todo.getTask());
            todo1.setCompleted(todo.isCompleted());
        }
    }

    @Override
    public Todo findById(int id) {
        return todos.get(id);
    }

    @Override
    public Collection<Todo> findAllTodos() {
        return todos.values();
    }

    @Override
    public Collection<Todo> findAllCompletedTodos() {
        return todos.values().stream().filter(Todo::isCompleted).toList();
    }

    @Override
    public Collection<Todo> findAllIncompleteTodos() {
        return todos.values().stream().filter(todo -> !todo.isCompleted()).toList();
    }

    @Override
    public Collection<Todo> sortByCompleted() {
        return todos.values().stream().sorted(Comparator.comparing(Todo::isCompleted).reversed()).toList();
    }

    @Override
    public Collection<Todo> sortByIncompleted() {
        return todos.values().stream().sorted(Comparator.comparing(Todo::isCompleted)).toList();
    }

    @Override
    public void deleteAll() {
        todos.clear();
    }
}
