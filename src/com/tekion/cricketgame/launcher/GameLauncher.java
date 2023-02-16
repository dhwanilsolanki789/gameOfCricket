package src.com.tekion.cricketgame.launcher;

import src.com.tekion.cricketgame.controller.*;

import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        System.out.print("Enter team 1 name: ");
        String team1Name = Utility.getNameInput();
        System.out.print("Enter team 2 name: ");
        String team2Name = Utility.getNameInput();
        MatchController newMatch = new MatchController(6, team1Name, team2Name);
        newMatch.startGame();
    }
}