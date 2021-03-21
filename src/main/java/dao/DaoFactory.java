package dao;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static Categories categoriesDao;
    private static Config config = new Config();

    public static Users getUsersDao(){
        if(usersDao == null){
            usersDao = new UsersDao(config);
        }
        return usersDao;
    }

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
