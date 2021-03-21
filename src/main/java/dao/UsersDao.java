package dao;

import com.mysql.cj.jdbc.Driver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

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
            stmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();
            return createUsersFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all users.", e);
        }
    }

    public User byId(Long id) {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createUsersFromResults(rs).get(0);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by id.", e);
        }
    }

    public User byUsername(String username){
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return createUsersFromResults(rs).get(0);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by id.", e);
        }
    }

    public boolean exists(String username, String email) {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? OR email = ?");
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            rs.last();    
            int size = rs.getRow(); 
            if(size == 0){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by id.", e);
        }
    }

    public boolean checkPassword(String username, String password) {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? ");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {
                try{
                    rs.next();
                }catch(Exception e){
                    return false;
                }
                return BCrypt.checkpw(password, rs.getString("password"));
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by id.", e);
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
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }
}
