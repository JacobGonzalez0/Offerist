package dao;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UsersDao implements Users{
    private Connection connection = null;
    
    public UsersDao(Config config) {
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
    public List<User> all() {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT posts.user_id as user_id , posts.title as title, posts.id as post_id, posts.price as price, posts.content as content, users.username as username FROM posts JOIN users on users.id = posts.user_id;");
            ResultSet rs = stmt.executeQuery();
            return createUsersFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    public User byId(Long id) {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT posts.user_id as user_id , posts.title as title, posts.id as post_id, posts.price as price, posts.content as content, users.username as username FROM posts JOIN users on users.id = posts.user_id WHERE posts.id = ?;");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createUsersFromResults(rs).get(0);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ad by id.", e);
        }
    }

    private List<User> createUsersFromResults(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = extractUser(rs);
            users.add(user);
        }
        return users;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email")
        );
    }

    @Override
    public Long insert(User user) {
        
        return null;
    }
}
