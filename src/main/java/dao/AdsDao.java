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

import models.Ad;
import models.Category;

public class AdsDao implements Ads{
    private Connection connection = null;
    
    public AdsDao(Config config) {
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT posts.user_id as user_id , posts.title as title, posts.id as post_id, posts.price as price, posts.content as content, users.username as username FROM posts JOIN users on users.id = posts.user_id;");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    public List<Ad> byId(Long id) {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT posts.user_id as user_id , posts.title as title, posts.id as post_id, posts.price as price, posts.content as content, users.username as username FROM posts JOIN users on users.id = posts.user_id WHERE posts.id = ?;");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ad by id.", e);
        }
    }

    public List<Ad> byUserId(Long id) {
        PreparedStatement stmt = null;
        try {
            //gets all the information from the tables we need
            stmt = connection.prepareStatement("SELECT posts.user_id as user_id , posts.title as title, posts.id as post_id, posts.price as price, posts.content as content, users.username as username FROM posts JOIN users on users.id = posts.user_id WHERE posts.user_id = ?;");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ad by id.", e);
        }
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            Ad advert = extractAd(rs);
            //we make a new statement for our second query to get images from that table using the post_id
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("SELECT * FROM images WHERE post_id = ?;");
            stmt.setLong(1, advert.getId());
            ResultSet innerRs = stmt.executeQuery();
            //with those results we get an list of urls for the images
            List<String> images = new ArrayList<>();
            while(innerRs.next()){
                images.add(innerRs.getString("url"));
            }
            //and finally set them to the advert itself
            advert.setImages(images);

            //clear the connection objects
            stmt = null;
            innerRs = null;
            //Next we are grabbing the categories needed
            
            stmt = connection.prepareStatement("SELECT ad_categories.category_id as category_id , categories.category as name, ad_categories.post_id as post_id FROM categories INNER JOIN ad_categories ON ad_categories.category_id = categories.id WHERE post_id = ?;");
            stmt.setLong(1, advert.getId());
            innerRs = stmt.executeQuery();
            //with those results we get an list of urls for the images
            List<Category> categories = new ArrayList<>();
            while(innerRs.next()){
                categories.add(extractCategory(innerRs));
            }

            ads.add(advert);
        }
        return ads;
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("post_id"),
            rs.getLong("user_id"),
            rs.getString("username"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getDouble("price")
        );
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
            rs.getLong("category_id"),
            rs.getString("name")
        );
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO posts(user_id, title, content, price) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.setDouble(4, ad.getPrice());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            long postId = rs.getLong(1);

            //clear the connection objects
            //inserts only first image uploaded
            stmt = null;
            insertQuery = "INSERT INTO INSERT images(post_id, url, description) VALUES (?, ?, ?, ?)";
            stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, postId);
            stmt.setString(2, ad.getImages().get(0));
            stmt.setString(3, "Placeholder value");
            
            return postId;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }
}
