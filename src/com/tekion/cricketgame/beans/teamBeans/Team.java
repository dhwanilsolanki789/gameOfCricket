package src.com.tekion.cricketgame.beans.teamBeans;

import src.com.tekion.cricketgame.launcher.Utility;

public class Team {
    private final String teamName;
    private final int playerCount;
    private Player[] players;

    public Team(String name, int playerCount) {
        this.teamName = name;
        this.playerCount = playerCount;
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


    public Player[] assignOpeners(){
        return new Player[]{ this.players[0], this.players[1] };
    }

    public Player assignBatter(int wickets){
        return this.players[wickets+1];
    }
    public Bowler assignBowler(int over){
        over = (over)%3;
        return (Bowler) players[4+over];
    }

    public Player[] getPlayers(){
        return this.players;
    }

    public String getTeamName() {
        return teamName;
    }

}
