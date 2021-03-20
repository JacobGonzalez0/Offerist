package dao;

import org.apache.taglibs.standard.tag.common.core.CatchTag;

public class DaoFactory {
    private static Ads adsDao;
    private static Categories categoriesDao;
    private static Config config = new Config();

    public static Ads getAdsDao(){
        if(adsDao == null){
            adsDao = new AdsDao(config);
        }
        return adsDao;
    }

    public static Categories getCategoriesDao(){
        if(categoriesDao == null){
            categoriesDao = new CategoriesDao(config);
        }
        return categoriesDao;
    }
}
