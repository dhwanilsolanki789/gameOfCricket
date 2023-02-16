package src.com.tekion.cricketgame.controller;

import src.com.tekion.cricketgame.beans.Match;
import src.com.tekion.cricketgame.beans.Team;

public class MatchController {
    private final Match match;
    private Team battingTeam;
    private Team bowlingTeam;

    public MatchController(int overs, String team1Name, String team2Name) {
        this.match = new Match(overs, team1Name, team2Name);
    }

    public void startGame() {
        setupGame();
        int inningScore = match.playInning(1, battingTeam, bowlingTeam);
        changeInning(inningScore);
        inningScore = match.playInning(2, battingTeam, bowlingTeam);
        match.printScoreboard(battingTeam,bowlingTeam);
    }

    public void setupGame() {
        System.out.println("Game Starts!");
        Team[] toss = match.flipCoin();
        System.out.println("Coin flipped!");
        // batting & bowling team assigned
        battingTeam = toss[0];
        bowlingTeam = toss[1];
        match.createScorecard(battingTeam,bowlingTeam);
        //Inning commencement
        System.out.println(battingTeam.getTeamName() + " bats first!");
        Utility.printDottedLine();
    }

    public void changeInning(int inningScore) {
        //Set match target and second inning setup
        match.setMatchTarget(inningScore + 1);
        Team temp = battingTeam;     //bowling & batting team reassigned
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
        System.out.println((inningScore + 1) + " is the target for " + battingTeam.getTeamName());
        Utility.printDottedLine();
    }
}