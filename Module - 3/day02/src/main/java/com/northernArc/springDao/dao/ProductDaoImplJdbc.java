package com.northernArc.springDao.dao;

import com.northernArc.springDao.connection.DBManager;
import com.northernArc.springDao.entity.Product;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
public class ProductDaoImplJdbc implements ProductDao{

    @Override
    public int saveProduct(Product product) {
        try(Connection con = DBManager.getConnection()){
            String sql = "insert into product_spring(name, brand, category) values(?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getBrand());
            stmt.setString(3, product.getCategory());
            return stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Issue in connecting to database.." +e.getMessage());
        }
        return 0;
    }

    @Override
    public Product findProductById(int id) {
        try(Connection con = DBManager.getConnection()){
            String sql = "select * from product where id =?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapToProduct(rs);
            }
        }catch (SQLException e){
            System.out.println("Issue in connecting to database.." +e.getMessage());
        }
        return null;
    }

    @Override
    public void updateProductById(int id, Product product) {

    }

    @Override
    public void deleteProductById(int id) {

    }

    @Override
    public Collection<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public Collection<Product> findByName(String name) {
        return List.of();
    }

    @Override
    public Collection<Product> findByBrand(String brand) {
        return List.of();
    }

    @Override
    public Collection<Product> findByCategory(String category) {
        return List.of();
    }

    @Override
    public Product mapToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("brand"),
                rs.getString("category")
        );
    }

    @Override
    public void deleteAllProducts() {

    }

    @Override
    public Collection<Product> sortProductsByNameAsc() {
        return List.of();
    }

    @Override
    public Collection<Product> sortProductsByNameDesc() {
        return List.of();
    }

    @Override
    public Collection<Product> sortProductsByBrandAsc() {
        return List.of();
    }

    @Override
    public Collection<Product> sortProductsByBrandDesc() {
        return List.of();
    }

    @Override
    public Collection<Product> sortProductsByCategoryAsc() {
        return List.of();
    }

    @Override
    public Collection<Product> sortProductsByCategoryDesc() {
        return List.of();
    }
}
