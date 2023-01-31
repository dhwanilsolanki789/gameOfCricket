package src.com.tekion.cricketgame.controller;

import src.com.tekion.cricketgame.beans.Match;
import src.com.tekion.cricketgame.beans.Team;

import java.util.Scanner;

public class MatchController {
    private final Match match;
    private final int maxWickets;

    public MatchController(int overs, String team1Name, String team2Name) {
        this.match = new Match(overs,team1Name,team2Name);
        this.maxWickets = 5;
    }


    public void startGame() {

        System.out.println("Game Started!");
        // Toss
        Team[] toss = match.flipCoin();
        System.out.println("Coin flipped!");

        // batting & bowling team assigned
        Team battingTeam = toss[0];
        Team bowlingTeam = toss[1];
        String team1 = battingTeam.getTeamName();
        String team2 = bowlingTeam.getTeamName();
        System.out.println(team1 + " bats first!");

        //Match & first inning setup
        int overs = match.getMatchOvers();
        int currScore = 0;
        int currWickets = 0;
        boolean inningEnded = false;

        //First Inning starts
        for (int over = 1; over <= overs; over++) {
            int ball = 0;
            for (; ball < 6; ball++) {
                //Storing ball outcome
                char ballScore = match.bowlBall();

                //Check for wicket or runs
                if (ballScore != 'W') {
                    currScore += (ballScore - '0');
                } else {
                    //Wicket logged
                    System.out.println(team1 + " are " + (++currWickets) + " down.");

                    //End inning if all wickets down
                    if (currWickets == maxWickets) {
                        System.out.println("First innings ended!");
                        inningEnded = true;
                        break;
                    }
                }
            }
            if (inningEnded) {
                break;
            }

            //Over logged
            System.out.println(over + " over ends!");
        }

        //Storing first inning stats
        battingTeam.setTeamScore(currScore);
        battingTeam.setWicketsFell(currWickets);
        //Printing inning stats
        System.out.println(team1 + " scores " + currScore + " runs.");

        //Second inning setup
        int target = currScore + 1;
        Team temp = battingTeam;     //bowling & batting team reassigned
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
        System.out.println(target + " is the target for " + team2);
        currScore = 0;
        currWickets = 0;
        inningEnded = false;
        int over, ball = 0;

        // Second inning starts
        for (over = 1; over <= overs; over++) {
            ball = 0;
            for (; ball < 6; ball++) {
                //Storing ball outcome
                char ballScore = match.bowlBall();

                //Check for wicket or runs
                if (ballScore != 'W') {
                    currScore += (ballScore - '0');

                    // Check for target achieved
                    if (currScore >= target) {

                        //Printing game results
                        System.out.println(team2 + " scores " + currScore + " runs.");
                        System.out.println("Second innings ended!");
                        System.out.println(team2 + " won by " + (5 - battingTeam.getWicketsFell()) + " wickets.");
                        inningEnded = true;
                        break;
                    }
                } else {
                    //Wicket logged
                    System.out.println(team2 + " are " + (++currWickets) + " down.");

                    //End inning if all wickets down
                    if (currWickets == maxWickets) {
                        //Printing inning stats
                        System.out.println(team2 + " scores " + currScore + " runs.");
                        System.out.println("Second innings ended!");
                        if (currScore < target - 1) {
                            //Printing game results
                            System.out.println(team1 + " won by " + (target - currScore - 1) + " runs.");
                        }
                        inningEnded = true;
                        break;
                    }
                }
            }
            if (inningEnded) {
                break;
            }
            //Over logged
            System.out.println(over + " over ends!");
        }

        // Storing second innings stats
        battingTeam.setTeamScore(currScore);
        battingTeam.setWicketsFell(currWickets);

//      System.out.println(i + " overs and " + j + " balls");

        // Check if match tied or print game results
        if (currScore == target - 1) {
            System.out.println("Match tied!");
        } else if (currScore < target && ((over == overs + 1) && (ball == 6))) {
            System.out.println(team2 + " scores " + currScore + " runs.");
            System.out.println(team1 + " won by " + (target - currScore - 1) + " runs.");
        }
    }
}
