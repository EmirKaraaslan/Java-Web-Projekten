package src.main.webapp.usermanagment.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String country;
    private  int Deleted;

    public User(int id, String name, String email, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public User() {}

    public User(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public User(int id, String name, String email, String country ,int Deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.Deleted = Deleted;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
