package src.com.tekion.cricketgame.launcher;

import java.util.Scanner;

public class Utility {
    private final static Scanner s = new Scanner(System.in);

    public static String getNameInput(){
        return s.next();
    }

    public static int getNumberInput() {
        return s.nextInt();
    }

    public void logOutput(String message){
        System.out.println(message);
    }

    public static void printBlankLine(){
        System.out.println(" ");
    }

    public static void printDottedLine(){
        System.out.println("!----------------------------------------!");
    }
}