package models;

public class User {
    private long id;
    private String username;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String email;

    public User(long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;

    }
}
