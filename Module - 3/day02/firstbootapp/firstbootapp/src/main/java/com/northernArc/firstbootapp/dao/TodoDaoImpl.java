package com.northernArc.firstbootapp.dao;

import com.northernArc.firstbootapp.model.Todo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TodoDaoImpl implements TodoDao {

    private Map<Integer, Todo> todos;

    @PostConstruct
    public void init() {
        System.out.println("TodoDaoImpl init");
        todos = new HashMap<>();
    }

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
        todos.put(id, todo);
    }

    @Override
    public Todo findById(int id) {
        return todos.get(id);
    }

    @Override
    public Map<Integer, Todo> findAll() {
        return todos;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying todos...");
        todos.clear();
    }
}
