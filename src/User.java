import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class User {

    String name;
    String email;
    String password;
    String phoneNo;
    private final String path = "users.txt";


    public User(String name, String email, String password, String phoneNo){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    abstract public String setUid();

    abstract public void saveUser();

    abstract public String toString();



}
