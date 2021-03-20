package dao;

import java.util.List;

import models.Category;

public interface Categories {
    List<Category> all();
    // insert a new ad and return the new ad's id
    Long insert(Category category);
}
