package Main;

import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);

    public char askQuestion(String question, String options){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(question + "\n" + options);
        return Character.toUpperCase(scanner.nextLine().charAt(0));
    }

    public void goodbye(){
        System.out.println("Have a nice day!");
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    public void output(String message){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print(message);
    }

    public String getAnswer(String message){
        output(message);
        return scanner.nextLine();
    }

    public String getLowerCaseAnswer(String message){
        output(message);
        return scanner.nextLine().toLowerCase();
    }
}