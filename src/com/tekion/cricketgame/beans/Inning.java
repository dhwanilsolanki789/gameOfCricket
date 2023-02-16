package src.com.tekion.cricketgame.beans;

import src.com.tekion.cricketgame.controller.Utility;

import java.util.Arrays;

public class Inning {
    private int runsScored;
    private int wicketsFell;
    private final Team battingTeam;
    private final Team bowlingTeam;
    private int oversBowled;
    private int ballsBowled;
    private boolean inningEnded;

    public Inning(Team battingTeam, Team bowlingTeam){
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.runsScored = 0;
        this.wicketsFell =0;
        this.oversBowled = this.ballsBowled = 0;
        this.inningEnded = false;
    }

    public void printInningScore(){
        System.out.println(battingTeam.getTeamName() + " - " + this.runsScored + "/" + this.wicketsFell + " ("
                + this.oversBowled + "." + this.ballsBowled + " Overs)");
    }

    public void printPostOverStats(Bowler currBowler,Player currBatterOnStrike, Player currBatterOffStrike){
        System.out.print(currBowler.getName() + " - " +
                currBowler.getWicketsTaken() + "/" + currBowler.getRunsConceded());
        System.out.print(" | " + battingTeam.getTeamName() + " " + this.runsScored + "/" + this.wicketsFell);
        System.out.print(" ( " + currBatterOnStrike.getName() + " - " + currBatterOnStrike.getRunsScored() + "*");
        System.out.println("  " + currBatterOffStrike.getName() + " - " + currBatterOffStrike.getRunsScored() + " )");
        Utility.printBlankLine();
    }

    public void printBattingStats(){
        System.out.println(battingTeam.getTeamName() + " Innings - " + this.runsScored + "/" + this.wicketsFell + " ("
                + this.oversBowled + "." + this.ballsBowled + " Overs)");
        Utility.printBlankLine();
        Player[] batters = battingTeam.getPlayers();
        for(Player batter : batters){
            //In case batter did not bat
            if(batter.getBallsPlayed() == 0){
                System.out.println(batter.getName() + " (Did not bat)");
                continue;
            }
            System.out.println(batter.getName() + " - " + batter.getRunsScored() +
                    "(" + batter.getBallsPlayed() + ")");
        }
        System.out.println("Total - " + this.runsScored + " (" + this.wicketsFell + " wickets)");
        Utility.printBlankLine();
    }

    public void printBowlingStats(){
        Player[] bowlers = Arrays.copyOfRange(bowlingTeam.getPlayers(),4,7);
        for (Player player : bowlers) {
            Bowler bowler = (Bowler) player;
            System.out.print(bowler.getName() + " - " + bowler.getWicketsTaken() + "/" + bowler.getRunsConceded());
            System.out.println(" (" + (bowler.getBallsBowled() / 6) + "." + (bowler.getBallsBowled() % 6) + ")");
        }
        Utility.printDottedLine();
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketsFell() {
        return wicketsFell;
    }

    public void setWicketsFell(int wicketsFell) {
        this.wicketsFell = wicketsFell;
    }

    public int getOversBowled() {
        return oversBowled;
    }

    public void setOversBowled(int oversBowled) {
        this.oversBowled = oversBowled;
    }

    public boolean checkInningEnded() {
        return this.inningEnded;
    }

    public void setInningEnded(){
        this.inningEnded = true;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }
}