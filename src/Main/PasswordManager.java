package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PasswordManager {

    private Scanner scanner = new Scanner(System.in);
    private Map<String, String> map = new HashMap<>();
    PasswordManager() {

        askQuestion();

    }
    private void askQuestion() {
        while (true){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("What would you like to do?");
            System.out.println("(A)dd Password, (V)iew Password, (Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response) {
                case 'A' -> addPassword();
                case 'V' -> viewPassword();
                case 'Q' -> quit();
                default -> System.out.println("not a valid response.");
            }

        }
    }
    private void addPassword() {
        System.out.print("enter website: ");
                String website = scanner.nextLine().toLowerCase();
                System.out.print("enter username: ");
                String userName = scanner.nextLine();
                System.out.print("enter password: ");
                String password = scanner.nextLine();
                map.put(website, "user name: " + userName + " Password: " + password);
                System.out.println("password stored!");

    }
    private void viewPassword(){
        System.out.print("enter website: ");
        String siteReader = scanner.nextLine();
        System.out.println(map.getOrDefault(siteReader, "No data for that website exists"));

    }
    private void quit(){
        System.out.println("Have a nice day!");
        System.out.println("~~~~~~~~~~~~~~~~");
        System.exit(0);

    }
}