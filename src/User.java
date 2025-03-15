import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String id;
/*
User: This class represents a user of the application. It encapsulates user information like username, password, and ID.

Constructor: User(String username, String password, String id):
 This constructor initializes a new User object with the provided username, password, and ID.


 */
    public User(String username, String password, String id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
//
//    public String getID() {
//
//        throw new UnsupportedOperationException("Unimplemented method 'getID'");
//    }
}