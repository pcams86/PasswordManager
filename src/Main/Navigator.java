package Main;

import java.util.Scanner;

public class Navigator {
    private boolean close = false;
    private boolean modified = false;

    Scanner scanner = new Scanner(System.in);
    PasswordManager pm = new PasswordManager("/tmp/entries.json");
    Console console = new Console();

    public void homeScreen() {
        while (!close) {
            if (pm.map.isEmpty()) {
                switch (console.askQuestion(
                        "what would you like to do?",
                        "(A)dd Password, (Q)uit")) {
                    case 'A' -> add();
                    case 'Q' -> quit();
                    default -> console.output("not a valid response\n");
                }
            } else {
                switch (console.askQuestion(
                        "what would you like to do?",
                        "(A)dd Password, (V)iew Password, (F)ull List,(D)elete, (Q)uit")) {
                    case 'A' -> add();
                    case 'V' -> pm.view(console.getLowerCaseAnswer("enter website: "));
                    case 'F' -> pm.fullList();
                    case 'D' -> delete();
                    case 'Q' -> quit();
                    default -> console.output("not a valid response\n");
                }
            }
        }
    }

    private void quit() {
        if (modified) {
            switch (console.askQuestion(
                    "Would you like to save the changes?",
                    "(Y)es, (N)o, (C)ancel")) {
                case 'Y' -> {
                    pm.save();
                    console.goodbye();
                    close = true;
                }
                case 'N' -> {
                    console.goodbye();
                    close = true;
                }
                case 'C' -> close = false;
                default -> quit();
            }
        } else {
            console.goodbye();
            close = true;
        }
    }

    private void add() {
        String readWebsite = console.getLowerCaseAnswer("enter website: ");
        String readUserName = console.getAnswer("enter password: ");
        String readPassword = console.getAnswer("enter username: ");
        pm.add(readWebsite, readUserName, readPassword);
        console.output("password stored!\n");
        modified = true;
    }

    private void delete() {
        String readWebsite = console.getLowerCaseAnswer("Which entry would you like to delete? ");
        if (!pm.map.containsKey(readWebsite)) console.output("No data for that website exists\n");
        else {
            pm.delete(readWebsite);
            modified = true;
            console.output("Entry has been removed\n");
        }
    }
}
