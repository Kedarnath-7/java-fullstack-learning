package com.northernArc.springDao.dao;

import com.northernArc.springDao.connection.DBManager;
import com.northernArc.springDao.entity.Todo;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


@Component
public class TodoDaoImplJdbc implements TodoDaoJdbc{
    @Override
    public int saveTodo(Todo todo) {
        try(Connection con = DBManager.getConnection()){
            String sql = "insert into todo_spring(task, completed) values(?, ?);";
            PreparedStatement stmt =  con.prepareStatement(sql);
            stmt.setString(1, todo.getTask());
            stmt.setBoolean(2, todo.isCompleted());
            return stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return 0;
    }

    @Override
    public Todo findTodoById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring where id = ?;";
            PreparedStatement stmt =  con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToTodo(rs);
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateTodoById(int id, Todo todo) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update todo_spring set task = ?, completed = ? where id = ?;";
            PreparedStatement stmt =  con.prepareStatement(sql);
            stmt.setString(1, todo.getTask());
            stmt.setBoolean(2, todo.isCompleted());
            stmt.executeUpdate();
            System.out.println("Updated successfully....");
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public void deleteTodoById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from todo_spring where id = ?;";
            PreparedStatement stmt =  con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Deleted successfully....");
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public Collection<Todo> findAllTodos() {
        Collection<Todo> todos = new LinkedList();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return todos;
    }

    @Override
    public Collection<Todo> findByTask(String task) {
        Collection<Todo> todos = new LinkedList();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring where task = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, task);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return todos;
    }

    @Override
    public Collection<Todo> findByCompleted(boolean completed) {
        Collection<Todo> todos = new LinkedList();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring where completed = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, completed);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return todos;
    }

    @Override
    public Todo mapToTodo(ResultSet rs) throws SQLException {
        return new Todo(
                rs.getInt("id"),
                rs.getString("task"),
                rs.getBoolean("completed")
        );
    }

    @Override
    public void deleteAllTasks() {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from todo_spring;";
            PreparedStatement stmt =  con.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Deleted successfully....");
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public Collection<Todo> sortTodosByTaskAsc() {
        Collection<Todo> todos = new LinkedList();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring order by task asc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return todos;
    }

    @Override
    public Collection<Todo> sortTodosByTaskDesc() {
        Collection<Todo> todos = new LinkedList();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring order by task desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return todos;
    }

    @Override
    public Collection<Todo> sortTodosByCompletedAsc() {
        Collection<Todo> todos = new LinkedList();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring order by completed asc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return todos;
    }

    @Override
    public Collection<Todo> sortTodosByCompletedDesc() {
        Collection<Todo> todos = new LinkedList();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo_spring order by completed desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return todos;
    }
}
