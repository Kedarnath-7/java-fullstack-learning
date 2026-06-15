package day04.jdbcDao.dao;
import day04.jdbcDao.entity.Book2;
import day04.jdbcDao.connection.DBManager;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BookDaoImpl implements BookDao{
    //private List<Book2> bookList;

    @Override
    public int saveBook(Book2 book) {
        try{
            Connection con = DBManager.getConnection();
            String sql = "insert into book(title, author, publisher) values(?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            int rows = stmt.executeUpdate();
            DBManager.closeConnection(con);
            return rows;
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return 0;
    }


    @Override
    public Book2 findBookById(int id) {
        try {
            Connection con = DBManager.getConnection();
            String sql = "select * from book where id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToBook(rs);
            }
        } catch (SQLException e) {
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return null;
    }



    @Override
    public void updateBookById(int id, Book2 book) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update book set title = ?, author = ?, publisher = ? where id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            System.out.println("Updated book successfully...");
            DBManager.closeConnection(con);
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public void deleteBookById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql =  "delete from book where id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Book has been deleted successfully..");
            DBManager.closeConnection(con);
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }


    @Override
    public Collection<Book2> findAllBooks() {
        Collection<Book2> books = new LinkedList<>();
        try{
            Connection con = DBManager.getConnection();
            String sql = "select * from book;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
            DBManager.closeConnection(con);
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }

    @Override
    public Collection<Book2> findByTitle(String title) {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book where title = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
            DBManager.closeConnection(con);
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }

        return books;
    }

    @Override
    public Collection<Book2> findByAuthor(String author) {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book where author = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, author);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
            DBManager.closeConnection(con);
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());

        }
        return books;
    }

    @Override
    public Collection<Book2> findByPublisher(String publisher) {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book where publisher = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, publisher);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
            DBManager.closeConnection(con);
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }



    @Override
    public Book2 mapToBook(ResultSet rs) throws SQLException {
        return new Book2(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("publisher")
        );
    }

    @Override
    public void deleteAllBooks() {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from book;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
            System.out.println("Books has been deleted successfully..");
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
    }

    @Override
    public Collection<Book2> sortBookByTitleAsc() {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book order by title;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }

    @Override
    public Collection<Book2> sortBookByTitleDesc() {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book order by title desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
        } catch (SQLException e) {
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }

    @Override
    public Collection<Book2> sortBooksByAuthorAsc() {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book order by author;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }

    @Override
    public Collection<Book2> sortBooksByAuthorDesc() {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book order by author desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }

    @Override
    public Collection<Book2> sortBooksByPublisherAsc() {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book order by publisher;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }

    @Override
    public Collection<Book2> sortBooksByPublisherDesc() {
        Collection<Book2> books = new LinkedList<>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from book order by publisher desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                books.add(mapToBook(rs));
            }
        }catch(SQLException e){
            System.out.println("Issue in db connectivity..." + e.getMessage());
        }
        return books;
    }
}
