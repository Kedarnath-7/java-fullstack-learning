package day01.daoArchitecture.dao;

import day01.daoArchitecture.entity.Todo;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class TodoDaoImpl implements TodoDao {

    private final Set<Todo> tasks = new LinkedHashSet<Todo>();
    @Override
    public void save(Todo task) {
        this.tasks.add(task);
    }

    @Override
    public Todo findById(int id) {
        for(Todo task : this.tasks) {
            if(task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        //for(Todo t : tasks){  // concurrent error
        Iterator<Todo> itr = tasks.iterator();
        while(itr.hasNext()){
            Todo task = itr.next();
            if(task.getId() == id){
                itr.remove();
                return;
            }
        }
    }

    @Override
    public void update(Todo task) {
        for(Todo t : tasks){
            if(task.getId() == t.getId()){
                t.setTask(t.getTask());
                t.setCompleted(t.isCompleted());
                return;
            }
        }
    }

    @Override
    public Iterable<Todo> findAll() {
        return tasks;
    }

    @Override
    public void deleteAll() {
        tasks.clear();
    }
}
