package src.com.tekion.cricketgame.beans;

import src.com.tekion.cricketgame.controller.Utility;

public class Team {
    private final String teamName;
    private final int playerCount;
    private int teamScore;
    private int wicketsFell;
    private int oversCompleted;
    private int ballsPlayed;
    private Player players[];

    public Team(String name, int playerCount) {
        this.teamName = name;
        this.playerCount =playerCount;
        this.teamScore = 0;
        this.wicketsFell = 0;
        createTeam();
    }

    public void createTeam(){
        players = new Player[this.playerCount];
        System.out.println("Team " + this.teamName);
        for(int i=1; i<=4; i++){
            System.out.print("Enter batter " + i + " name: ");
            players[i-1] = new Batter(Utility.getNameInput());
        }

        for(int i=1; i<=3; i++){
            System.out.print("Enter bowler " + i + " name: ");
            players[3+i] = new Bowler(Utility.getNameInput());
        }
    }

    public void printBattingStats(){
        System.out.println(players[0].getName() + " - " + players[0].getRunsScored()
                + "(" + players[0].getBallsPlayed() + ")");
        System.out.println(players[1].getName() + " - " + players[1].getRunsScored()
                + "(" + players[1].getBallsPlayed() + ")");
        for(int i=2; i<this.playerCount; i++){
            //In case batter did not bat
            if(players[i].getBallsPlayed() == 0){
                System.out.println(players[i].getName() + " (Did not bat)");
                continue;
            }
            System.out.println(players[i].getName() + " - " + players[i].getRunsScored() +
                    "(" + players[i].getBallsPlayed() + ")");
        }
        System.out.println("Total - " + this.teamScore + " (" + this.getWicketsFell() + " wickets)");
    }

    public void printBowlingStats(){
        for(int i=4; i<7; i++){
            Bowler bowler = (Bowler) players[i];
            System.out.print(bowler.getName() + " - " + bowler.getWicketsTaken() + "/" + bowler.getRunsConceded());
            System.out.println(" (" + (bowler.getBallsBowled()/6) + "." +  (bowler.getBallsBowled()%6) + ")");
        }
    }

    public Player[] assignOpeners(){
        return new Player[]{this.players[0], this.players[1]};
    }

    public Player assignBatter(int wickets){
        return this.players[wickets+1];
    }
    public Bowler assignBowler(int over){
        over = (over)%3;
        return (Bowler) players[4+over];
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamScore(int score) {
        teamScore = score;
    }

    public void setWicketsFell(int wickets) {
        wicketsFell = wickets;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getWicketsFell() {
        return wicketsFell;
    }

    public int getOversCompleted() {
        return oversCompleted;
    }

    public void setOversCompleted(int oversCompleted) {
        this.oversCompleted = oversCompleted;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }
}
