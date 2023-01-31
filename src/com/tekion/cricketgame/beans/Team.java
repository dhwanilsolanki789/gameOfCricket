package src.com.tekion.cricketgame.beans;

public class Team {
    private final String teamName;
    private int teamScore;
    private int wicketsFell;

    public Team(String name) {
        this.teamName = name;
        this.teamScore = 0;
        this.wicketsFell = 0;
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
}
