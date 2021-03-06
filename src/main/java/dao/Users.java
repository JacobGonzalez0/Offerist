package dao;

import java.util.List;

import models.User;

public interface Users {
    // get a list of all the ads
    List<User> all();
    // insert a new ad and return the new ad's id
    Long insert(User user);

    User byId(Long id);

    boolean exists(String username, String email);

    boolean checkPassword(String username, String password);

    User byUsername(String username);
}
