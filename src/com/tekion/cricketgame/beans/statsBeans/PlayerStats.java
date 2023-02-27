package src.com.tekion.cricketgame.beans.statsBeans;

import src.com.tekion.cricketgame.beans.teamBeans.Bowler;
import src.com.tekion.cricketgame.beans.teamBeans.Player;

public class PlayerStats {
    private final Player player;
    private final BattingStats battingStats;
    private BowlingStats bowlingStats;

    public PlayerStats(Player player){
        this.player = player;
        this.battingStats = new BattingStats();
        if(player instanceof Bowler){
            this.bowlingStats = new BowlingStats();
        }
    }

    public void updateBattingStats(int runsHit){
        battingStats.addRunsScored(runsHit);
        battingStats.addBallsPlayed();
        if(runsHit == 4){
            battingStats.addFour();
        }
        if(runsHit == 6){
            battingStats.addSix();
        }
    }

    public void updateBowlingStats(int runsConceded, boolean wicketFell){
        bowlingStats.addRunsConceded(runsConceded);
        bowlingStats.addBallsBowled();
        if(wicketFell){
            bowlingStats.addWicketsTaken();
        }
    }

    public String getBattingStats(){
        return player.getName() + "  " + battingStats.toString();
    }

    public String getBowlingStats(){
        return player.getName() + "  " + bowlingStats.toString();
    }

    public int getRuns(){
        return battingStats.getRunsScored();
    }

    public int getWickets(){
        return bowlingStats.getWicketsTaken();
    }

    public int getRunsConceded(){
        return bowlingStats.getRunsConceded();
    }

    @Override
    public String toString() {
        String toString =  "PlayerStats{" + "battingStats = \n" + battingStats.toString();
        if(this.player instanceof Bowler){
            toString += ", \n bowlingStats = \n" + bowlingStats.toString() +
                    '}';
        } else {
            toString += '}';
        }
        return toString;
    }
}
