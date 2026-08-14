import java.awt.color.ICC_ColorSpace;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
            homepage();
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

    private static void homepage() {
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

        String name;
        String email;
        String password;
        String phoneNo;

        System.out.println("Please enter your info follow the order");
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Email: ");
        email = scanner.nextLine();

        if(checkemail(email)){
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
        System.out.print("Phone Number: ");
        phoneNo = scanner.nextLine();

        if(registerRole == 1) {
            Customer customer = new Customer(name, email, password, phoneNo,'C');
            customer.saveUser();
        }else {
            ClaimsOfficer claimsOfficer = new ClaimsOfficer(name, email, password, phoneNo,'O');
            claimsOfficer.saveUser();
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
}