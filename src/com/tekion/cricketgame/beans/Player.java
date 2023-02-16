package src.com.tekion.cricketgame.beans;

public class Player {
    private final String playerName;
    private int runsScored;
    private int ballsPlayed;
    private double[] hitProbability;

    public Player(String name){
        this.playerName = name;
        this.ballsPlayed = 0;
        this.runsScored = 0;
    }

    public int playBall(){
        double probability = Math.random();
        int ballScore;

        //Run scoring probability
        if (probability <= this.hitProbability[0]) {
            System.out.println("Good delivery! Dot ball.");
            ballScore = 0;
        } else if (probability <= this.hitProbability[1]) {
            System.out.println(this.getName() + " takes a single, 1 run.");
            ballScore = 1;
        } else if (probability <= this.hitProbability[2]) {
            System.out.println(this.getName() + " takes a quick double, 2 runs.");
            ballScore = 2;
        } else if (probability <= this.hitProbability[3]) {
            System.out.println(this.getName() + " takes 3 runs.");
            ballScore = 3;
        } else if (probability <= this.hitProbability[4]) {
            System.out.println(this.getName() + " hits a Boundary! 4 runs.");
            ballScore = 4;
        } else if (probability <= this.hitProbability[5]) {
            System.out.println(this.getName() + " hits a Maximum! 6 runs.");
            ballScore = 6;
        } else {
            System.out.println("Wicket! " + this.getName() + " has to go back after making " + this.getRunsScored() + " runs!");
            this.addBallsPlayed();
            return 7;
        }

        //Update batter's score
        this.addRunsScored(ballScore);
        this.addBallsPlayed();
        return ballScore;
    }

    public String getName(){
        return playerName;
    }
    public int getRunsScored(){
        return runsScored;
    }
    public void addRunsScored(int runs){
        this.runsScored += runs;
    }
    public int getBallsPlayed(){
        return ballsPlayed;
    }
    public void addBallsPlayed(){
        this.ballsPlayed += 1;
    }
    public void setHitProbability(double[] hitProbability) {
        this.hitProbability = hitProbability;
    }
}