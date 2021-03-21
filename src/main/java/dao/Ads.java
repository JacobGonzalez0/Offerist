package dao;

import java.util.List;

import models.Ad;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> byId(Long id);

    List<Ad> byUserId(Long id);
}
