package Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PasswordManager {
    private final Scanner scanner;
    private Map<String, Entry> map;
    private boolean close = false;
    private boolean modified = false;

    public void askQuestion() {
        while (!close) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("What would you like to do?");
            System.out.println("(A)dd Password, (V)iew Password, (F)ull List,(D)elete, (Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response) {
                case 'A' -> addPassword();
                case 'V' -> viewPassword();
                case 'F' -> fullList();
                case 'D' -> delete();
                case 'Q' -> quit();
                default -> System.out.println("not a valid response.");
            }

        }
    }

    private void addPassword() {
        System.out.print("enter website: ");
        String readWebsite = scanner.nextLine().toLowerCase();
        System.out.print("enter username: ");
        String readUserName = scanner.nextLine();
        System.out.print("enter password: ");
        String readPassword = scanner.nextLine();
        Entry entry = new Entry(readWebsite, readUserName, readPassword);
        map.put(entry.website, entry);
        System.out.println("password stored!");
        modified = true;

    }

    private void viewPassword() {
        System.out.print("enter website: ");
        String website = scanner.nextLine().toLowerCase();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        if (!map.containsKey(website)) {
            System.out.println("No data for that website exists");
        } else {
            var entry = map.get(website);
            System.out.println(MessageFormat.format("website:  {0}\nusername: {1}\npassword: {2}",
                    entry.website,
                    entry.userName,
                    entry.password));
        }
    }

    private void fullList() {
        for (var entry : map.values()) {
            System.out.println(MessageFormat.format("website:  {0}\nusername: {1}\npassword: {2}\n",
                    entry.website,
                    entry.userName,
                    entry.password));
        }
    }
    private void delete() {
        System.out.println("Which entry would you like to delete?");
        String readWebsite = scanner.nextLine().toLowerCase();
        if (map.containsKey(readWebsite)){
            map.remove(readWebsite);
            modified = true;
            System.out.println("Entry has been removed");
        }else{
            System.out.println("No data for that website exists");
        }

    }

    private void quit() {
        if (modified) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Would you like to save the changes?");
            System.out.println("(Y)es, (N)o, (C)ancel");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response) {
                case 'Y' -> {
                    Save();
                    System.out.println("Have a nice day!");
                    System.out.println("~~~~~~~~~~~~~~~~");
                    close = true;
                }
                case 'N' -> {
                    System.out.println("Have a nice day!");
                    System.out.println("~~~~~~~~~~~~~~~~");
                    close = true;
                }
                case 'C' -> close = false;
                default -> quit();
            }
        } else {
            System.out.println("Have a nice day!");
            System.out.println("~~~~~~~~~~~~~~~~");
            close = true;
        }
    }

    private void Save() {
        try {
            File theFile = new File("/tmp/entries.json");
            FileWriter fileWriter = new FileWriter(theFile);
            Gson gson = new Gson();
            gson.toJson(this.map, fileWriter);
            fileWriter.close();
            modified = false;
            System.out.println("Changes saved");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PasswordManager(String path) {
        scanner = new Scanner(System.in);
        File theFile = new File(path);
        if (theFile.exists()) {
            try {
                FileReader fileReader = new FileReader(theFile);
                Gson gson = new Gson();
                TypeToken<Map<String, Entry>> mapType = new TypeToken<>() {
                };
                map = gson.fromJson(fileReader, mapType.getType());
            } catch (Exception e) {
                map = new HashMap<>();
                System.out.println(e);
            }

        } else {
            map = new HashMap<>();
        }
    }
}