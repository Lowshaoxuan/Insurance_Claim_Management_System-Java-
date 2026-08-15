import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main{


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

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
            System.out.println("Login Successfully. Navigate to Home Page");
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

            System.out.println("Welcome to Home Page");
            System.out.println("====Customer Menu====\n");
            System.out.println("1. View Profile");
            System.out.println("2. Manage Vehicle");
            System.out.println("3. View Policy");
            System.out.println("4. Submit Claims");
            System.out.println("5. View Claims");
            System.out.println("6. Logout\n");

            System.out.println("Enter your option here: ");
            option = scanner.nextLine().charAt(0);

            switch (option){
                case '1' -> showUserInfo(email);
            }
        }

    }

    private static void showUserInfo(String email) {

        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String [] data = line.split(",");

                if(data[1].equals(email)){
                    System.out.println("Name: " + data[0]);
                    System.out.println("Email: " + data[1]);
                    System.out.println("Password: " + data[2]);
                    System.out.println("Phone Number: " + data[3]);
                    System.out.println("Customer ID: " + data[5]);
                }
            }
        } catch (IOException e) {
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

    /*
    public static User getUser(String email){
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line = reader.readLine(); ;

            while(line != null ){

                String [] data = line.split(",");

                if(data[1].equals(email)){
                    char role = data[4].charAt(0);
                    if (data[4].equals('C')) {
                        return new Customer(data[0], data[1], data[2], data[3],role);
                    } else if () {
                        
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }*/

    public static char getUserRole(String email){
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))){
            String line = reader.readLine();

            while(line != null){

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