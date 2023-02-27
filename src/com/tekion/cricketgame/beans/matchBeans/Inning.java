package src.com.tekion.cricketgame.beans.matchBeans;

import src.com.tekion.cricketgame.beans.statsBeans.BattingStats;
import src.com.tekion.cricketgame.beans.statsBeans.BowlingStats;
import src.com.tekion.cricketgame.beans.statsBeans.PlayerStats;
import src.com.tekion.cricketgame.beans.teamBeans.Bowler;
import src.com.tekion.cricketgame.beans.teamBeans.Player;
import src.com.tekion.cricketgame.beans.teamBeans.Team;
import src.com.tekion.cricketgame.launcher.Utility;

import java.util.LinkedHashMap;
import java.util.Set;

public class Inning {
    private int runsScored;
    private int wicketsFell;
    private final Team battingTeam;
    private final Team bowlingTeam;
    private final LinkedHashMap<Player,PlayerStats> battingTeamStats;
    private final LinkedHashMap<Player, BattingStats> battingStats;
    private final LinkedHashMap<Player, BowlingStats> bowlingStats;
    private final LinkedHashMap<Player,PlayerStats> bowlingTeamStats;
    private int oversBowled;
    private int ExcessBallsBowled;
    private boolean inningEnded;

    public Inning(Team battingTeam, Team bowlingTeam){
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.runsScored = 0;
        this.wicketsFell =0;
        this.oversBowled = this.ExcessBallsBowled = 0;
        this.inningEnded = false;
        battingTeamStats = new LinkedHashMap<>();
        this.battingStats = new LinkedHashMap<>();
        this.bowlingStats = new LinkedHashMap<>();
        bowlingTeamStats = new LinkedHashMap<>();
    }

    public void setupBatters(Player currBatterOnStrike, Player currBatterOffStrike) {
//        battingStats.put(currBatterOffStrike,)
        battingTeamStats.put(currBatterOnStrike, new PlayerStats(currBatterOnStrike));
        battingTeamStats.put(currBatterOffStrike, new PlayerStats(currBatterOffStrike));
    }

    public void printInningScore(){
        System.out.println(battingTeam.getTeamName() + " - " + this.runsScored + "/" + this.wicketsFell + " ("
                + this.oversBowled + "." + this.ExcessBallsBowled + " Overs)");
    }

    public void printPostOverStats(Bowler currBowler, Player currBatterOnStrike, Player currBatterOffStrike){
        PlayerStats[] activeBatterStats = { battingTeamStats.get(currBatterOnStrike),
                battingTeamStats.get(currBatterOffStrike)};
        PlayerStats activeBowlerStats = bowlingTeamStats.get(currBowler);
        System.out.print(currBowler.getName() + " - " +
                activeBowlerStats.getWickets() + "/" + activeBowlerStats.getRunsConceded());
        System.out.print(" | " + battingTeam.getTeamName() + " " + this.runsScored + "/" + this.wicketsFell);
        System.out.print(" ( " + currBatterOnStrike.getName() + " - " + activeBatterStats[0].getRuns() + "*");
        System.out.println("  " + currBatterOffStrike.getName() + " - " + activeBatterStats[1].getRuns() + " )");
        Utility.printBlankLine();
    }

    public void updateBatter(Player currBatterOnStrike) {
        battingTeamStats.put(currBatterOnStrike,new PlayerStats(currBatterOnStrike));
    }

    public void updateBowler(Bowler currBowler){
        if(!bowlingTeamStats.containsKey(currBowler)){
            bowlingTeamStats.put(currBowler,new PlayerStats(currBowler));
        }
    }

    public void updateRuns(int runsHit, Player batter, Bowler bowler){
        addRunsScored(runsHit);
        updatePlayers(batter,bowler,runsHit,false);
    }

    public void updateWickets(Player batter,Bowler bowler){
        System.out.println("after making " + battingTeamStats.get(batter).getRuns() + " runs!");
        addWicketsFell();
        updatePlayers(batter,bowler,0,true);
    }

    public void printInningStats(){
        //Print Inning Summary
        System.out.println(battingTeam.getTeamName() + " Innings - " + this.runsScored + "/" + this.wicketsFell + " ("
                + this.oversBowled + "." + this.ExcessBallsBowled + " Overs)");
        Utility.printBlankLine();
        printBattingStats();
        //Print Team Total
        System.out.println("Total - " + this.runsScored + " (" + this.wicketsFell + " wickets)");
        Utility.printBlankLine();
        printBowlingStats();
        Utility.printDottedLine();
    }
    public void printBattingStats(){
        //Print each player batting stats
        Set<Player> batters = battingTeamStats.keySet();
        for(Player batter : batters){
            PlayerStats playerStats = battingTeamStats.get(batter);
            System.out.println(playerStats.getBattingStats());
        }
    }

    public void printBowlingStats(){
        Set<Player> bowlers = bowlingTeamStats.keySet();
        for(Player bowler : bowlers){
            PlayerStats playerStats = bowlingTeamStats.get(bowler);
            System.out.println(playerStats.getBowlingStats());
        }
    }

    public void updatePlayers(Player batter, Bowler bowler, int runs, boolean wicketFell){
        PlayerStats batterStats = battingTeamStats.get(batter);
        batterStats.updateBattingStats(runs);
        PlayerStats bowlerStats = bowlingTeamStats.get(bowler);
        bowlerStats.updateBowlingStats(runs,wicketFell);
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void addRunsScored(int runsHit) {
        this.runsScored += runsHit;
    }

    public int getWicketsFell() {
        return wicketsFell;
    }

    public void addWicketsFell() {
        this.wicketsFell += 1;
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

    public int getExcessBallsBowled() {
        return ExcessBallsBowled;
    }

    public void setExcessBallsBowled(int excessBallsBowled) {
        this.ExcessBallsBowled = excessBallsBowled;
    }

    @Override
    public String toString() {
        return "Inning {" +
                "runsScored =" + runsScored +
                ", wicketsFell =" + wicketsFell +
                ", battingTeamStats =" + battingTeamStats.toString() +
                ", bowlingTeamStats =" + bowlingTeamStats.toString() +
                ", oversBowled =" + oversBowled +
                ", ExcessBallsBowled =" + ExcessBallsBowled +
                '}';
    }
}