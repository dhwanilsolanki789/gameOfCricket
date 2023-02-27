package src.com.tekion.cricketgame.beans.statsBeans;

public class BattingStats {
    private int runs;
    private int ballsPlayed;
    private int foursHit;
    private int sixesHit;


    public BattingStats(){
        this.runs = this.ballsPlayed = 0;
        this.foursHit = this.sixesHit = 0;
    }

    public void getBattingStats(){

    }

    public void addRunsScored(int runsHit) {
        this.runs += runsHit;
    }

    public int getRunsScored() {
        return this.runs;
    }

    public void addBallsPlayed() {
        this.ballsPlayed += 1;
    }

    public void addFour() {
        this.foursHit += 1;
    }

    public void addSix() {
        this.sixesHit += 1;
    }

    @Override
    public String toString() {
        return runs + "(" + ballsPlayed + "), " + foursHit + " Fours, " + sixesHit + " Sixes";
    }
}
