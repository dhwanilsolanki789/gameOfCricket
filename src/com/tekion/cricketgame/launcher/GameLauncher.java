package src.com.tekion.cricketgame.launcher;

import src.com.tekion.cricketgame.controller.MatchController;

import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        System.out.print("Enter team 1 name: ");
        String team1 = getNameInput();
        System.out.print("Enter team 2 name: ");
        String team2 = getNameInput();
        MatchController newMatch = new MatchController(5, team1, team2);
        newMatch.startGame();
    }

    public static String getNameInput() {
        Scanner s = new Scanner(System.in);
        return s.next();
    }
}
