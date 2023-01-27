package src;

public class Team {
     private String teamName;
     private int teamScore;
     private int wicketsFell;

     public Team(String id){
          teamName = id;
          teamScore =0;
          wicketsFell =0;
     }

     public String getName() {
          return teamName;
     }
     public void setTeamScore(int score) {
          teamScore = score;
     }
     public void setWicketsFell(int wickets){
          wicketsFell = wickets;
     }
     public int getTeamScore() {
          return teamScore;
     }
     public int getWicketsFell() {
          return wicketsFell;
     }
}
