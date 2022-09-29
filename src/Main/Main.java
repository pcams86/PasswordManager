package Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("quit")) {
            System.out.print("Do you want to ADD or VIEW a password? ");
            input = scanner.nextLine();

            if (input.equals("view")) {
                System.out.print("enter website: ");
                String siteReader = scanner.nextLine();
                System.out.println(map.get(siteReader));
            }
            if (input.equals("add")) {
                System.out.print("enter website: ");
                String website = scanner.nextLine().toLowerCase();
                System.out.print("enter username: ");
                String userName = scanner.nextLine();
                System.out.print("enter password: ");
                String password = scanner.nextLine();
                map.put(website, "user name: " + userName + " Password: " + password);
                System.out.println("password stored!");
            }


        }
    }
}
