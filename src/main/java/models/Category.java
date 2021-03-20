package models;

public class Category {
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;

    public Category(long id, String name){
        this.id = id;
        this.name = name;
    }
}
