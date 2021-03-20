import dao.DaoFactory;

public class test {
    public static void main(String[] args) {
        System.out.println(DaoFactory.getAdsDao().all());
        
    }
}
