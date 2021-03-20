package dao;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Category;

public class CategoriesDao implements Categories{
    private Connection connection = null;
    
    public CategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getURL(),
                config.getUsername(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Category> all() {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT * from categories;");
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    public List<Category> byId(Long id) {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT * FROM categories WHERE id = ?;");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ad by id.", e);
        }
    }

    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> catagories = new ArrayList<>();
        while(rs.next()){
            catagories.add(extractCategory(rs));
        }
        return catagories;
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
            rs.getLong("id"),
            rs.getString("category")
        );
    }

    @Override
    public Long insert(Category category) {
        // TODO Auto-generated method stub
        return null;
    }
}
