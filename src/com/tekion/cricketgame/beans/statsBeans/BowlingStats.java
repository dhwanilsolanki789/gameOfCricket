package src.com.tekion.cricketgame.beans.statsBeans;

public class BowlingStats {
    private int wicketsTaken;
    private int runsConceded;
    private int ballsBowled;

    public BowlingStats(){
        this.wicketsTaken = this.runsConceded = 0;
        this.ballsBowled = 0;
    }

    public int getRunsConceded() {
        return this.runsConceded;
    }

    public void addRunsConceded(int runs) {
        this.runsConceded += runs;
    }

    public void addBallsBowled(){
        this.ballsBowled += 1;
    }

    public int getWicketsTaken() {
        return this.wicketsTaken;
    }

    public void addWicketsTaken(){
        this.wicketsTaken += 1;
    }

    @Override
    public String toString() {
        return wicketsTaken + "/" + runsConceded + " (" + (ballsBowled/6) + "."
                + (ballsBowled%6)+ " Ovs)";
    }
}
