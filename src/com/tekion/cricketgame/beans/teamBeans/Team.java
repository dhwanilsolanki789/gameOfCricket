package src.com.tekion.cricketgame.beans.teamBeans;

import src.com.tekion.cricketgame.launcher.Utility;

import java.util.Arrays;

public class Team {
    private final String teamName;
    private final static int PLAYER_COUNT = 11;
    private int battersCount;
    private int bowlersCount;
    private Player[] players;

    public Team(String name) {
        this.teamName = name;
        createTeam();
    }

    public void createTeam(){
        players = new Player[this.PLAYER_COUNT];
        this.battersCount = 6;
        this.bowlersCount = 5;
        System.out.println("Team " + this.teamName);
        for(int i=1; i<=battersCount; i++){
            System.out.print("Enter batter " + i + " name: ");
            players[i-1] = new Batter(Utility.getNameInput());
        }
        for(int i=1; i<=bowlersCount; i++){
            System.out.print("Enter bowler " + i + " name: ");
            players[(battersCount-1)+i] = new Bowler(Utility.getNameInput());
        }
    }


    public Player[] assignOpeners(){
        return new Player[]{ this.players[0], this.players[1] };
    }

    public Player assignBatter(int wickets){
        return this.players[wickets+1];
    }
    public Bowler assignBowler(int over){
        over = (over)%bowlersCount;
        return (Bowler) players[battersCount+over];
    }

    public Player[] getPlayers(){
        return this.players;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayerCount() {
        return PLAYER_COUNT;
    }

    @Override
    public String toString() {
        return "Team{\n" +
                "teamName='" + teamName + '\'' +
                ",\nplayerCount=" + PLAYER_COUNT +
                ",\nplayers=" + Arrays.toString(players) +
                '}';
    }

}
