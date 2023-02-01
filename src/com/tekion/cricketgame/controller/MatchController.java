package src.com.tekion.cricketgame.controller;

import src.com.tekion.cricketgame.beans.Match;
import src.com.tekion.cricketgame.beans.Team;

public class MatchController {
    private final Match match;
    private final int maxWickets;
    private int inningScore;
    private Team battingTeam;
    private Team bowlingTeam;

    public MatchController(int overs, String team1Name, String team2Name) {
        this.match = new Match(overs,team1Name,team2Name);
        this.maxWickets = 5;
    }


    public void startGame() {
        setupGame();
        inningScore = match.playInning(1,battingTeam,bowlingTeam);
        changeInning(inningScore);
        inningScore = match.playInning(2,battingTeam, bowlingTeam);
        match.printResults(inningScore,battingTeam,bowlingTeam);
    }
    public void setupGame() {
        System.out.println("Game Starts!");
        // Toss
        Team[] toss = match.flipCoin();
        System.out.println("Coin flipped!");
        // batting & bowling team assigned
        battingTeam = toss[0];
        bowlingTeam = toss[1];
        //Inning commencement
        System.out.println(battingTeam.getTeamName() + " bats first!");
        System.out.println("!----------------------------------------!");
    }

    public void changeInning(int inningScore) {
        match.setMatchTarget(inningScore+1);
        Team temp = battingTeam;     //bowling & batting team reassigned
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
        System.out.println((inningScore+1) + " is the target for " + battingTeam.getTeamName());
        System.out.println("!----------------------------------------!");
    }
}
