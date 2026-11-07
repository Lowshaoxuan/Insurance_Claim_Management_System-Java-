import java.io.*;

public class Customer extends User {

    Vehicle vehicle;
    char roleNo;
    static int count = Integer.parseInt(getCurrentid());


    String uId;
    String path = "users.txt";
    static final String idpath = "customerid.txt";

    public Customer(String name, String email, String password, String phoneNo,char roleNo ){
        super(name, email, password, phoneNo);
        this.roleNo = roleNo;
    }

    public void addVehicle(){

    }

    @Override
    public String setUid(){
        this.uId = this.roleNo + String.format("%04d",count);
        count++;
        writeid(count);
        return uId;
    }

    private void writeid(int count){
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
