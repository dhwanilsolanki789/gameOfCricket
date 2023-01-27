package src;

public class MatchController {
    Match game;
    private int wicketsToWin;

    public MatchController(int overs){
        game = new Match(overs);
        wicketsToWin = 5;
    }

    public void startGame(){
        System.out.println("Game Started!");
        // Toss
        boolean toss = game.flipCoin();
        System.out.println("Coin flipped!");

        // batting & bowling team assigned
        Team battingTeam = (toss)?(game.t1):(game.t2);
        Team bowlingTeam = (toss)?(game.t2):(game.t1);
        String team1 = battingTeam.getName();
        String team2 = bowlingTeam.getName();
        System.out.println(team1 + " bats first!");

        //Match & first inning setup
        int overs = game.getMatchOvers();
        int currScore =0;
        int currWickets =0;
        boolean inningEnded = false;

        //First Inning starts
        for(int over=1; over<=overs; over++){
            int ball=0;
            for(; ball<6; ball++) {
                //Storing ball outcome
                int ballScore = game.bowlBall();

                //Check for wicket or runs
                if (ballScore != 7) {
                    currScore += ballScore;
                    System.out.println(ballScore + "runs");
                } else {
                    //Wicket logged
                    System.out.println(team1 + " are " + (++currWickets) + " down!");

                    //End inning if all wickets down
                    if (currWickets == wicketsToWin) {
                        System.out.println("First innings ended!");
                        inningEnded = true;
                        break;
                    }
                }
            }
            if(inningEnded) {
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
        int target = currScore+1;
        Team temp = battingTeam;     //bowling & batting team reassigned
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
        System.out.println(target + " is the target for " + team2);
        currScore =0;
        currWickets =0;
        inningEnded = false;
        int over,ball=0;

        // Second inning starts
        for(over=1; over<=overs; over++) {
            ball=0;
            for (; ball < 6; ball++) {
                //Storing ball outcome
                int ballScore = game.bowlBall();

                //Check for wicket or runs
                if (ballScore != 7) {
                    currScore += ballScore;
                    System.out.println(ballScore + " runs");

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
                    System.out.println(team2 + " are " + (++currWickets) + " down!");

                    //End inning if all wickets down
                    if (currWickets == 5) {
                        //Printing inning stats
                        System.out.println(team2 + " scores " + currScore + " runs.");
                        System.out.println("Second innings ended!");
                        if(currScore < target-1){
                            //Printing game results
                            System.out.println(team1 + " won by " + (target - currScore - 1) + " runs.");
                        }
                        inningEnded = true;
                        break;
                    }
                }
            }
            if(inningEnded) {
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
        if(currScore == target - 1){
            System.out.println("Match tied!");
        } else if(currScore < target && ((over == overs + 1) && (ball == 6))) {
            System.out.println(team2 + " scores " + currScore + " runs.");
            System.out.println(team1 + " won by " + (target - currScore -1) + " runs.");
        }
    }
}
