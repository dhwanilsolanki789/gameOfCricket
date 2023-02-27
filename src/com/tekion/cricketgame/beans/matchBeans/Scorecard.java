package src.com.tekion.cricketgame.beans.matchBeans;

import src.com.tekion.cricketgame.beans.statsBeans.MatchStats;
import src.com.tekion.cricketgame.beans.teamBeans.Team;
import src.com.tekion.cricketgame.launcher.Utility;


public class Scorecard {
    private final MatchStats currentMatchStats;
    private final Inning inning1;
    private final Inning inning2;
    private final int maxWickets;
    private int matchTarget;

    private Team winner;

    public Scorecard(Team battingTeamAfterToss, Team bowlingTeamAfterToss, int maxWickets) {
        this.currentMatchStats = new MatchStats(this, battingTeamAfterToss, bowlingTeamAfterToss);
        inning1 = new Inning(battingTeamAfterToss, bowlingTeamAfterToss);
        inning2 = new Inning(bowlingTeamAfterToss, battingTeamAfterToss);
        this.maxWickets = maxWickets;
    }
    public void printResults(Team battingTeam, Team bowlingTeam){
        if(inning2.getRunsScored() >= this.matchTarget) {
            winner = battingTeam;
            System.out.println(battingTeam.getTeamName() + " won by "
                    + (this.maxWickets - inning2  .getWicketsFell()) + " wickets.");
        }
        if(inning2.getRunsScored() < this.matchTarget -1) {
            winner = bowlingTeam;
            System.out.println(bowlingTeam.getTeamName() + " won by "
                    + (this.matchTarget - inning2.getRunsScored() - 1) + " runs.");
        }
        // Check if match tied or print game results
        if(inning2.getRunsScored() == this.matchTarget -1) {
            winner = null;
            System.out.println("Match tied!");
        }
        Utility.printDottedLine();
    }

    public void print(){
        inning1.printInningStats();
        inning2.printInningStats();
        Utility.printBlankLine();
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

    public Team getWinner() {
        return this.winner;
    }
}