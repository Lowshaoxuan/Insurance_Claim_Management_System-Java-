import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main{


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        StringBuffer sb = new StringBuffer("Hello");
        sb.setLength(2);
        System.out.println(sb);
        landing();
    }
    public static void landing(){

        char landingIn;
        boolean isLanding = true;

        System.out.println("---------------------------------------------------------");
        System.out.println("Welcome to Shao Xuan's Insurance Claim Management System");
        System.out.println("---------------------------------------------------------");
        System.out.println();

        while(isLanding){
            System.out.println("Enter the number to choose to Login or Register acc");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("Press 'Q' to leave\n");

            System.out.print("Enter your option here: ");
            landingIn = Character.toUpperCase(scanner.next().charAt(0));
            scanner.nextLine();

            switch (landingIn){
                case 'Q' -> {
                    isLanding = false;
                    System.out.println("See You Next Time!!!");
                }
                case '1' -> loginPage();
                case '2' -> registerPage();
                default -> System.out.println("Invalid Option\n");
            }
        }
    }

    public static void loginPage(){

        String lEmail;
        String lPass;

        System.out.println();
        System.out.println("Enter your login credential here: ");
        System.out.print("Email: ");
        lEmail = scanner.nextLine();

        if (!checkemail(lEmail)){
            System.out.println("This email haven't register pls register first");
            System.out.println("Navigate to Main Menu ...\n");
            try {
                Thread.sleep(2700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            landing();
        }

        System.out.print("Password: ");
        lPass = scanner.nextLine();

        if (checkpass(lEmail, lPass)) {
            System.out.println("Login Successfully. Navigate to Home Page\n");
            try {
                Thread.sleep(1400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            char role = getUserRole(lEmail);
            homepage(role,lEmail);
        } else {
            System.out.println("Wrong password pls try again. Navigate back to main menu ... \n");
            try {
                Thread.sleep(2700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            landing();
        }
    }

    private static void homepage(char role,String email) {

        switch (role){
            case 'C' -> customerhome(email);
            case 'A' -> adminhome(email);
            case 'O' -> officerhome(email);
        }
    }

    private static void officerhome(String email) {
    }

    private static void adminhome(String email) {
    }

    private static void customerhome(String email) {

        char option = '!';

        while(option != '6') {
            System.out.println("====Customer Menu====");
            System.out.println("1. View Profile");
            System.out.println("2. Manage Vehicle");
            System.out.println("3. View Policy");
            System.out.println("4. Submit Claims");
            System.out.println("5. View Claims");
            System.out.println("6. Logout\n");

            System.out.print("Enter your option here: ");
            option = scanner.nextLine().charAt(0);
            switch (option){
                case '1' -> showUserInfo(email);
                case '2' -> showVehicle(getUserId(email));
                case '3' -> showPolicy(getUserId(email));
            }
        }

    }

    private static void showPolicy(String userId) {

        try(BufferedReader reader = new BufferedReader(new FileReader("policy.txt"))){

            String line;

            while((line = reader.readLine()) != null){

                String [] data = line.split(",");


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getUserId(String email) {

        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String data [] = line.split(",");

                if(data[1].equals(email)){
                    return data[5];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static void showVehicle(String uId) {

        try(BufferedReader reader = new BufferedReader(new FileReader("vehicles.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String [] data = line.split(",");

                if(data[5].equals(uId)){
                    System.out.println();
                    System.out.println("-----------MY VEHICLE-----------");
                    System.out.println("Vehicle ID: " + data[0]);
                    System.out.println("Registration: " + data[1]);
                    System.out.println("Manufacturer: " + data[2]);
                    System.out.println("Model: " + data[3]);
                    System.out.println("Year: " + data[4]+"\n");
                }else {
                    char option;
                    boolean stay = true;

                    while(stay) {
                        System.out.println();
                        System.out.println("There's no vehicle registered under you. " +
                                "Would you like to register now? Click 'Y' to register or 'B' to back to menu");
                        System.out.print("Enter your option here: ");
                        option = scanner.nextLine().toUpperCase().charAt(0);
                        System.out.println();
                        if (option == 'B'){
                            stay = false;
                            customerhome(getUserEmail(uId));
                        } else if (option == 'Y') {
                            registerVehicle(getUserEmail(uId));
                        }
                    }
                }
            }
            boolean stay = true;
            char option;
            while(stay) {
                System.out.println("Enter 'Y' if you want to register a new vehicle or 'B' to back to Customer Menu");
                System.out.print("Option: ");
                option = scanner.nextLine().toUpperCase().charAt(0);

                switch(option){
                    case 'Y' -> {
                        System.out.println();
                        registerVehicle(getUserEmail(uId));
                    }
                    case 'B' -> {
                        System.out.println("Navigate to Customer Home Page...");
                        Thread.sleep(2000);
                        customerhome(getUserEmail(uId));
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void registerVehicle(String email) {
        String [] tempinfo = new String [4];
        String registerNo;
        String manufacturer;
        String model;
        int year;
        boolean empty = true;
        String uId = getUserId(email);

        while (empty) {
            System.out.println("Please Fill in your vehicle's info at bellow and can't fill with blank or space.");
            System.out.print("Register Number: ");
            registerNo = scanner.nextLine();
            if (checkRegisterNo(registerNo)) {
                System.out.println("Register Number must be unique!\n");
                break;
            }
            tempinfo[0] = registerNo;

            System.out.print("Manufacturer: ");
            manufacturer = scanner.nextLine();
            tempinfo[1] = manufacturer;

            System.out.print("Model: ");
            model = scanner.nextLine();
            tempinfo[2] = model;

            System.out.print("Year: ");
            year = scanner.nextInt();
            scanner.nextLine();
            tempinfo[3] = String.valueOf(year);

            if (!Arrays.asList(tempinfo).contains(" ")) {
                empty = false;
                Vehicle vehicle = new Vehicle(registerNo, manufacturer, model, year, uId);
                vehicle.setUid();
                vehicle.saveVehicle();
                System.out.println("Register Successfully");
                System.out.println("Navigate back to Customer Home Page");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                customerhome(getUserEmail(uId));
            }
        }
    }

    private static boolean checkRegisterNo(String registerNo) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("vehicles.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data[1].equals(registerNo)) {
                    return true;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private static String getUserEmail(String uId) {

        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String data [] = line.split(",");

                if(data[5].equals(uId)){
                    return data[1];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static void showUserInfo(String email) {

        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String [] data = line.split(",");

                if(data[1].equals(email)){
                    System.out.println();
                    System.out.println("Name: " + data[0]);
                    System.out.println("Email: " + data[1]);
                    System.out.println("Password: " + data[2]);
                    System.out.println("Phone Number: " + data[3]);
                    System.out.println("Customer ID: " + data[5]+"\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerPage(){

        char registerOp;
        boolean isReg = true;

        while(isReg) {
            System.out.println("Choose to Register");
            System.out.println("1. Customer");
            System.out.println("2. Claims Officer");
            System.out.println("Press 'B' to back to Main Menu\n");

            System.out.print("Enter Your Option Here: ");
            registerOp = Character.toUpperCase(scanner.next().charAt(0));
            scanner.nextLine();

            switch (registerOp) {
                case 'B' -> {
                    isReg = false;
                    landing();
                }
                case '1' -> {
                    isReg = false;
                    registerInfo(1);
                }
                case '2' -> {
                    isReg = false;
                    registerInfo(2);
                }
                default -> System.out.println("Invalid Option");
            }
        }
    }

    public static void registerInfo(int registerRole){

        String [] tempinfo = new String[4];
        boolean empty = true;

        while(empty) {
            String name;
            String email;
            String password;
            String phoneNo;

            System.out.println("Please enter your info follow the order and all field need to be filled.");
            System.out.print("Name: ");
            name = scanner.nextLine();
            tempinfo[0] = name;

            System.out.print("Email: ");
            email = scanner.nextLine();
            tempinfo[1] = email;

            if (checkemail(email)) {
                System.out.println("This Email already exist,please use another email for registration");
                System.out.println("Navigating to Register Page\n");
                try {
                    Thread.sleep(2700);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                registerPage();
            }

            System.out.print("Password: ");
            password = scanner.nextLine();
            tempinfo[2] = password;

            System.out.print("Phone Number: ");
            phoneNo = scanner.nextLine();
            tempinfo[3] = phoneNo;

            if (!Arrays.asList(tempinfo).contains(" ")){
                empty = false;
                if (registerRole == 1) {
                    Customer customer = new Customer(name, email, password, phoneNo, 'C');
                    customer.saveUser();
                } else {
                    ClaimsOfficer claimsOfficer = new ClaimsOfficer(name, email, password, phoneNo, 'O');
                    claimsOfficer.saveUser();
                }
            }
        }


        System.out.println("Register Succefully Navigate to Main Menu...\n");
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        landing();

    }

    public static String getUser() {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line).append(System.lineSeparator());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    public static boolean checkemail(String email){
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){

            String line;

            while((line = reader.readLine()) != null){
                String [] data = line.split(",");

                if(data[1].equals(email)){
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean checkpass(String email,String pass){

        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){

            String line;

            while((line = reader.readLine()) != null){

                String [] data = line.split(",");

                if(data[1].equals(email)) {
                    if (data[2].equals(pass)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static char getUserRole(String email){
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String [] data = line.split(",");

                if(data[1].equals(email)){
                    return data[4].charAt(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}