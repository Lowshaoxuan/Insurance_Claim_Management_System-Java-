import java.io.*;

public class Vehicle {

    String vId;
    String vRegisNo;
    String manufacturer;
    String model;
    int year;
    String ownerId;
    String path = "vehicles.txt";
    int count = getid();

    private int getid() {
        String lastid = null;
        try(BufferedReader reader =  new BufferedReader(new FileReader(path))){
            String line;

            while((line = reader.readLine()) != null){
                String [] data = line.split(",");

                System.out.println(data[0]);

                lastid = data[0].substring(1,5);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(lastid);
    }

    public Vehicle(String vRegisNo, String manufacturer, String model, int year, String ownerId) {
        this.vRegisNo = vRegisNo;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.ownerId = ownerId;
    }

    public String setUid(){
        this.vId = "V" + String.format("%04d",++count);
        return vId;
    }


    public void saveVehicle(){
        try(FileWriter writer = new FileWriter(path,true)) {
            writer.write(this.toString());
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%d,%s",this.vId,this.vRegisNo,this.manufacturer,this.model,this.year,this.ownerId);
    }
}
