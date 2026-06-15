package day04.jdbcDao.dao;

import day04.jdbcDao.connection.DBManager;
import day04.jdbcDao.entity.Todo;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class TodoDaoImpl implements TodoDao {

    @Override
    public int saveTodo(Todo todo) {
        try(Connection con = DBManager.getConnection()){
            String sql = "insert into todo(task, completed) values (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

        }catch (SQLException e){
            System.out.println("Issue in DB connectivity...." + e.getMessage());
        }
        return 0;
    }

    @Override
    public Todo findTodoById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo where id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToTodo(rs);
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity...." + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateTodoById(int id, Todo todo) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update todo set task=?, completed=? where id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, todo.getTask());
            stmt.setBoolean(2, todo.isCompleted());
            stmt.setInt(3, id);
            stmt.executeQuery();
            System.out.println("Update successful....");
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public void deleteTodoById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from todo where id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeQuery();
            System.out.println("Deleted successfully...");
        }catch (SQLException e){
            System.out.println("Issue in db connectivity.... "+ e.getMessage());
        }
    }

    @Override
    public Collection<Todo> findAllTodos() {
        Collection<Todo> todos = new LinkedList<Todo>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from todo;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                todos.add(mapToTodo(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
        return todos;
    }

    @Override
    public Collection<Todo> findByTask(String task) {
        return List.of();
    }

    @Override
    public Collection<Todo> findByCompleted(boolean completed) {
        return List.of();
    }

    @Override
    public Todo mapToTodo(ResultSet rs) throws SQLException {
        return new Todo(rs.getInt("id"), rs.getString("task"), rs.getBoolean("completed"));
    }

    @Override
    public void deleteAllTasks() {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from todos;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
            System.out.println("Delete successful....");
        }catch(SQLException e){
            System.out.println("Issue in db connectivity...." + e.getMessage());
        }
    }

    @Override
    public Collection<Todo> sortTodosByTaskAsc() {
        return List.of();
    }

    @Override
    public Collection<Todo> sortTodosByTaskDesc() {
        return List.of();
    }

    @Override
    public Collection<Todo> sortTodosByCompletedAsc() {
        return List.of();
    }

    @Override
    public Collection<Todo> sortTodosByCompletedDesc() {
        return List.of();
    }
}
