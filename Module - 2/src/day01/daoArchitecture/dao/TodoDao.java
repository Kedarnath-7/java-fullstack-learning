package day01.daoArchitecture.dao;
import day01.daoArchitecture.entity.Todo;

public interface TodoDao {
    public void save(Todo task);
    public Todo findById(int id);
    public void deleteById(int id);
    public void update(Todo task);
    public Iterable<Todo> findAll();
    public void deleteAll();

}
