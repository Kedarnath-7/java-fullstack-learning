package day04.jdbcDao.dao;

import day04.jdbcDao.connection.DBManager;
import day04.jdbcDao.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public int saveProduct(Product product) {
        try(Connection con = DBManager.getConnection()){
            String sql = "insert into product(name, brand, category) values(?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getBrand());
            stmt.setString(3, product.getCategory());
            return stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return 0;
    }

    @Override
    public Product findProductById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product where id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToProduct(rs);
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateProductById(int id, Product book) {
        try(Connection con = DBManager.getConnection()){
            String sql = "update product set name=?, brand=?, category=? where id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getBrand());
            stmt.setString(3, book.getCategory());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
    }

    @Override
    public void deleteProductById(int id) {
        try(Connection con = DBManager.getConnection()){
            String  sql = "delete from product where id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
    }

    @Override
    public Collection<Product> findAllProducts() {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> findByName(String name) {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product where name=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> findByBrand(String brand) {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select* from product where brand=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, brand);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> findByCategory(String category) {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product where category=?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Product mapToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("brand"),
                rs.getString("category"));
    }

    @Override
    public void deleteAllProducts() {
        try(Connection con = DBManager.getConnection()){
            String sql = "delete from product;";
            PreparedStatement stmt = con.prepareStatement(sql);
            int rows = stmt.executeUpdate();
            System.out.println("Deleted all " + rows + " products...");
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
    }

    @Override
    public Collection<Product> sortProductsByNameAsc() {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product order by name;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> sortProductsByNameDesc() {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product order by name desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> sortProductsByBrandAsc() {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product order by brand;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> sortProductsByBrandDesc() {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product order by brand desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> sortProductsByCategoryAsc() {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product order by category;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }

    @Override
    public Collection<Product> sortProductsByCategoryDesc() {
        Collection<Product> products = new LinkedList<Product>();
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product order by category desc;";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                products.add(mapToProduct(rs));
            }
        }catch (SQLException e){
            System.out.println("Issue in DB connectivity.." + e.getMessage());
        }
        return products;
    }
}
