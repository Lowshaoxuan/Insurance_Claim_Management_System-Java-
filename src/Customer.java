import java.io.FileWriter;
import java.io.IOException;

public class Customer extends User {

    Vehicle vehicle;
    char roleNo;
    private int count = 000;
    String uId;
    String path = "users.txt";




    public Customer(String name, String email, String password, String phoneNo,char roleNo ){
        super(name, email, password, phoneNo);
        this.roleNo = roleNo;
    }

    public void addVehicle(){

    }

    @Override
    public String setUid(){
        this.uId = this.roleNo + String.valueOf(count);
        count++;
        return uId;
    }

    @Override
    public void saveUser(){
        try(FileWriter writer = new FileWriter(path,true)) {
            writer.write(this.toString());
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        return String.format("%s,%s,%s,%s,%c",this.name,this.email,this.password,this.phoneNo,this.roleNo);
    }




}
