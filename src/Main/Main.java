package Main;

public class Main {
    public static void main(String[] args) {
        PasswordManager pm = new PasswordManager("/tmp/entries.json");
        pm.askQuestion();
    }
}