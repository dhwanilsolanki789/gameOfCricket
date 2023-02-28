package src.com.tekion.cricketgame.beans.teamBeans;

public class Bowler extends Player {
    public Bowler(String name) {
        super(name);
        this.setHitProbability(new double[]{0.376, 0.625, 0.672, 0.673, 0.816, 0.884});
    }
}