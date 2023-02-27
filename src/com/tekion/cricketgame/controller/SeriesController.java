package src.com.tekion.cricketgame.controller;

import src.com.tekion.cricketgame.beans.teamBeans.Team;

public class SeriesController {
    private final Team team1, team2;
    private int matchCount;
    private final int totalMatches;
    private int team1Wins, team2Wins;
    private Team winner;

    public SeriesController(String team1Name, String team2Name, int matchCount){
        team1 = new Team(team1Name,7);
        team2 = new Team(team2Name,7);
        this.team1Wins = this.team2Wins = 0;
        this.matchCount = 0;
        this.totalMatches = matchCount;
    }

    public void playSeries(){
        for( ;matchCount < totalMatches; matchCount++){
            playMatch();
            printPostMatchStats();
            if(seriesEndCheck()){
                break;
            }
        }
    }

    private void printPostMatchStats() {
        System.out.println(team1.getTeamName() + " " + team1Wins + " - "
                + team2.getTeamName() + " " + team2Wins);
    }

    public void playMatch(){
        MatchController newMatch = new MatchController(6, team1, team2);
        Team winner = newMatch.playGame();
        incrementTeamWins(winner);
    }

    private void incrementTeamWins(Team winner) {
        if(winner == team1){
            team1Wins++;
        } else {
            team2Wins++;
        }
    }

    public boolean seriesEndCheck(){
        if(team1Wins > totalMatches/2 || team2Wins > totalMatches/2){
            if(team1Wins >= totalMatches/2){
                this.winner = team1;
            } else {
                this.winner = team2;
            }
            return true;
        }
        return false;
    }

}
