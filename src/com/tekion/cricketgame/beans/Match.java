package src.com.tekion.cricketgame.beans;

public class Match {
    private final Team t1;
    private final Team t2;
    private final int matchOvers;
    private final int maxWickets;
    private int oversCompleted;
    private int ballsBowled;
    private int matchTarget;

    public Match(int overs, String team1Name, String team2Name) {
        t1 = new Team(team1Name);
        t2 = new Team(team2Name);
        this.maxWickets = 5;
        matchOvers = overs;
    }

    public Team getT1() {
        return t1;
    }

    public Team getT2() {
        return t2;
    }

    public int getMatchOvers() {
        return matchOvers;
    }

    public int playInning(int inningNo,Team battingTeam, Team bowlingTeam){
        String team1 = battingTeam.getTeamName();
        String team2 = bowlingTeam.getTeamName();

        //Inning setup
        int overs = this.matchOvers;
        int currScore = 0;
        int currWickets = 0;
        boolean inningEnded = false;

        int currOver, currBall = 0;
        for (currOver = 1; currOver <= overs; currOver++) {
            currBall = 0;
            for (; currBall < 6; currBall++) {
                //Storing ball outcome
                char ballScore = this.bowlBall();
                //Check for wicket or runs
                if (ballScore != 'W') {
                    currScore += (ballScore - '0');
                    //Second inning check for target reach
                    if(inningNo == 2){
                        if(currScore >= matchTarget) {
                            System.out.println("Innings ended!");
                            inningEnded = true;
                            break;
                        }
                    }
                } else {
                    //Wicket logged
                    System.out.println(team1 + " are " + (++currWickets) + " down.");

                    //End inning if all wickets down
                    if (currWickets == maxWickets) {
                        System.out.println("Innings ended!");
                        inningEnded = true;
                        break;
                    }
                }
            }
            if (inningEnded) {
                break;
            }
            //Over logged
            System.out.println(currOver + " over ends!");
        }
        //Store inning stats
        this.oversCompleted = currOver;
        this.ballsBowled = currBall;
        battingTeam.setTeamScore(currScore);
        battingTeam.setWicketsFell(currWickets);
        //Print inning stats
        System.out.println(team1 + " scores " + currScore + " runs.");
        System.out.println("!----------------------------------------!");
        return currScore;
    }

    public void printResults(int inningScore, Team battingTeam, Team bowlingTeam) {
        //Print match results
        if (inningScore >=matchTarget) {
            System.out.println(battingTeam.getTeamName() + " won by " + (5 - battingTeam.getWicketsFell()) + " wickets.");
        }
        if (inningScore <matchTarget - 1){
            System.out.println(bowlingTeam.getTeamName() + " won by " + (matchTarget - inningScore - 1) + " runs.");
        }
        // Check if match tied or print game results
        if (inningScore == matchTarget - 1) {
            System.out.println("Match tied!");
        }
    }

    public Team[] flipCoin() {
        int toss = (int) (Math.random() * 2);
        Team[] teamOrder = new Team[2];

        // Shuffled random team order for toss
        if(toss == 1) {
            teamOrder[0] = t1;
            teamOrder[1] = t2;
        } else {
            teamOrder[0] = t2;
            teamOrder[1] = t1;
        }

        return teamOrder;
    }

    public char bowlBall() {
        //Random score generation
        int ballScore = (int) (Math.random() * 8);

        // 7 -> wicket
        if(ballScore != 7) {
            if(ballScore == 4) {
                System.out.println("Boundary! 4 runs.");
            } else if(ballScore == 6) {
                System.out.println("Maximum! 6 runs.");
            } else if(ballScore == 0) {
                System.out.println("Good delivery! Dot ball.");
            } else {
                System.out.println(ballScore + " runs.");
            }

            return (char)(ballScore + '0');
        } else {
            System.out.print("Wicket! ");
            return 'W';
        }
    }

    public int getMatchTarget() {
        return matchTarget;
    }

    public void setMatchTarget(int matchTarget) {
        this.matchTarget = matchTarget;
    }

}
