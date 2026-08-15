import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClaimsOfficer extends User {


    char roleNo;
    static int count = Integer.parseInt(getCurrentid());
    String uId;
    final String path = "users.txt";
    static final String idpath = "officerid.txt";


    public ClaimsOfficer(String name,String email,String password,String phoneNo,char roleNo) {
        super(name, email, password, phoneNo);
        this.roleNo = roleNo;

    }

    @Override
    public String setUid(){
        this.uId = this.roleNo + String.format("%04d",count);
        count++;
        writeid(count);
        return uId;
    }

    public void writeid(int count){

        try(FileWriter writer = new FileWriter(idpath,true)){
            writer.write(String.valueOf(count));
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(){
        try(FileWriter writer = new FileWriter(path,true)) {
            writer.write(this.toString());
            writer.write(',');
            writer.write(this.setUid());
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        return String.format("%s,%s,%s,%s,%c",this.name,this.email,this.password,this.phoneNo,this.roleNo);
    }

    public static String getCurrentid() {
        String lastline =  null;
        try(BufferedReader reader = new BufferedReader(new FileReader(idpath))){
            String line;

            while((line= reader.readLine())  != null) {
                lastline = line;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return lastline;
    }
}
