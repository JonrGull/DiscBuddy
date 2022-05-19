package discbuddy.commands.utils;

public class User {

    private String id;
    private String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public User(String id, String user) {
        this.id = id;
        this.user = user;
    }

}
