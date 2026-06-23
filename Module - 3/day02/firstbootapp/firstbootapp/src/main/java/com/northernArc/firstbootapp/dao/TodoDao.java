package com.northernArc.firstbootapp.dao;

import com.northernArc.firstbootapp.model.Todo;

import java.util.Map;

public interface TodoDao {
    public void save(Todo todo);
    public void deleteById(int id);
    public void updateById(int id, Todo todo);
    public Todo findById(int id);
    public Map<Integer, Todo> findAll();

}
