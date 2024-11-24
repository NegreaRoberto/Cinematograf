package view;

import java.util.Scanner;

public class UserOperations {
    private UserOperations(){}

    public static void userOperations(){
        System.out.println("Ce doriti sa faceti? ");
        System.out.println("Logare");
        System.out.println("Exit");
        Scanner scanner = new Scanner(System.in);
        String comanda = scanner.nextLine();
        switch (comanda){
            case "Logare":
                System.out.println("User: ");
                String user = scanner.nextLine();
                System.out.println("Parola: ");
                String parola = scanner.nextLine();

                if(user.equals("personalCinematograf") & parola.equals("Cinem@123")){
                    CinemaOperations.cinemaOperations();
                }else {
                    System.out.println("Username sau parola gresita!");
                }
                break;
            case "Exit":
                System.out.println("Programul se inchide");
                System.exit(0);
                break;
        }
    }
}

