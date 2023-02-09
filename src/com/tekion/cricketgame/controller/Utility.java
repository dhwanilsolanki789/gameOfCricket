package src.com.tekion.cricketgame.controller;

import java.util.Scanner;

public class Utility {
    public static String getNameInput(){
        Scanner s = new Scanner(System.in);
        return s.next();
    }

    public static void printBlankLine(){
        System.out.println(" ");
    }

    public static void printDottedLine(){
        System.out.println("!----------------------------------------!");
    }
}
