package src.com.tekion.cricketgame.launcher;

import src.com.tekion.cricketgame.beans.teamBeans.Team;
import src.com.tekion.cricketgame.controller.SeriesController;

public class GameLauncher {
    public static void main(String[] args) {
        System.out.print("Enter team 1 name: ");
        String team1Name = Utility.getNameInput();
        Team team1 = new Team(team1Name);
        System.out.print("Enter team 2 name: ");
        String team2Name = Utility.getNameInput();
        Team team2 = new Team(team2Name);
        System.out.print("Enter the number of matches you want in the series: ");
        int matchCount = Utility.getNumberInput();
        SeriesController series = new SeriesController(team1, team2 , matchCount);
        series.playSeries();
    }
}