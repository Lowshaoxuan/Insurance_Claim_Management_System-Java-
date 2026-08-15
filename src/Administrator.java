import java.io.FileWriter;
import java.io.IOException;

public class Administrator extends User {



    char roleNo;
    private int count = 1;
    String uId;
    String path = "users.txt";


    public Administrator(String name,String email,String password,String phoneNo,char roleNo){
        super(name, email, password, phoneNo);
        this.roleNo = roleNo;

    }

    @Override
    public String setUid(){
        this.uId = this.roleNo + String.format("%04d",count);
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
