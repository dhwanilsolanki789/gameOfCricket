package src.com.tekion.cricketgame.beans;

import src.com.tekion.cricketgame.controller.Utility;

import java.security.PublicKey;


public class Scorecard {
    private final Inning inning1;
    private final Inning inning2;
    private final int maxWickets;
    private int matchTarget;

    public Scorecard(Team battingTeamAfterToss, Team bowlingTeamAfterToss, int maxWickets) {
        inning1 = new Inning(battingTeamAfterToss, bowlingTeamAfterToss);
        inning2 = new Inning(bowlingTeamAfterToss, battingTeamAfterToss);
        this.maxWickets = maxWickets;
    }
    public void printResults(Team battingTeam, Team bowlingTeam){
        if(inning2.getRunsScored() >= this.matchTarget) {
            System.out.println(battingTeam.getTeamName() + " won by "
                    + (this.maxWickets - inning2  .getWicketsFell()) + " wickets.");
        }
        if(inning2.getRunsScored() < this.matchTarget -1) {
            System.out.println(bowlingTeam.getTeamName() + " won by "
                    + (this.matchTarget - inning2.getRunsScored() - 1) + " runs.");
        }
        // Check if match tied or print game results
        if(inning2.getRunsScored() == this.matchTarget -1) {
            System.out.println("Match tied!");
        }
        Utility.printDottedLine();
    }

    public void print(){
        inning1.printBattingStats();
        inning1.printBowlingStats();
        inning2.printBattingStats();
        inning2.printBowlingStats();
    }

    public Inning getInning(int inningNo){
        if(inningNo == 1) {
            return inning1;
        } else {
            return inning2;
        }
    }
    public Inning getInning1() {
        return inning1;
    }

    public Inning getInning2() {
        return inning2;
    }

    public void setMatchTarget(int matchTarget) {
        this.matchTarget = matchTarget;
    }
}