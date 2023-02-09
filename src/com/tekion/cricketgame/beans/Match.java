package src.com.tekion.cricketgame.beans;

import src.com.tekion.cricketgame.controller.Utility;

public class Match {
    private final Team team1;
    private final Team team2;
    private final int matchOvers;
    private final int maxWickets;
    private int currScore;
    private int currWickets;
    private Bowler currBowler;
    private Player currBatterOnStrike;
    private Player currBatterOffStrike;
    private int matchTarget;

    public Match(int overs, String team1Name, String team2Name) {
        this.maxWickets = 6;
        team1 = new Team(team1Name, maxWickets + 1);
        team2 = new Team(team2Name, maxWickets + 1);
        matchOvers = overs;
    }

    public int playInning(int inningNo, Team battingTeam, Team bowlingTeam) {
        innitiateInning(battingTeam);
        implementInning(inningNo,battingTeam,bowlingTeam);
        battingTeam.setTeamScore(currScore);
        battingTeam.setWicketsFell(currWickets);
        //Print inning stats
        System.out.println(battingTeam.getTeamName() + " scores " + currScore + " runs.");
        Utility.printDottedLine();
        return currScore;
    }

    public void innitiateInning(Team battingTeam){
        //Inning setup
        this.currScore = 0;
        this.currWickets = 0;
        Player[] openers = battingTeam.assignOpeners();
        this.currBatterOnStrike = openers[0];
        this.currBatterOffStrike = openers[1];
    }

    public void implementInning( int inningNo,Team battingTeam, Team bowlingTeam){
        boolean inningEnded = false;
        int currOver, currBall = 0;
        for (currOver = 0; currOver < this.matchOvers; currOver++) {
            currBall = 1;
            this.currBowler = bowlingTeam.assignBowler(currOver);
            for (; currBall <= 6; currBall++) {
                // Bowl ball & inning end checked
                System.out.print("(" + currOver + "." + currBall + ") ");
                int ballOutcome = currBatterOnStrike.playBall();
                updateMatch(ballOutcome, battingTeam, bowlingTeam);
                inningEnded = inningEndCheck(inningNo);
                if (inningEnded) {
                    currBowler.addBallsBowled(currBall);
                    break;
                }
            }
            if (inningEnded) {
                break;
            } else {
                changeOver();
            }
        }
        //Store inning stats
        battingTeam.setOversCompleted(currOver);
        battingTeam.setBallsPlayed(currBall);
    }

    public void updateMatch(int ballOutcome, Team battingTeam, Team bowlingTeam) {
        // 7 -> wicket
        if (ballOutcome != 7) {
            //update score and log ball result
            this.currScore += ballOutcome;
            currBowler.addRunsConceded(ballOutcome);
            //Switch Strike when needed
            if (ballOutcome % 2 == 1) {
                changeStrike();
            }
        } else {
            //update wickets and log ball result
            this.currWickets++;
            currBowler.incrementWicketsTaken();
            System.out.println(battingTeam.getTeamName() + " are " + (this.currWickets) + " down as " + currBowler.getName() + " strikes!");
            if (currWickets < 6) {
                this.currBatterOnStrike = battingTeam.assignBatter(this.currWickets);
            }
        }
    }

    private boolean inningEndCheck(int inningNo) {
        //End inning if all wickets down
        if (this.currWickets == maxWickets) {
            currBatterOnStrike = null;
            System.out.println("Innings ended!");
            return true;
        }
        // End inning if target reached
        if (inningNo == 2) {
            if (this.currScore >= matchTarget) {
                System.out.println("Innings ended!");
                return true;
            }
        }
        return false;
    }

    public void changeOver(){
        //Update bowler and print over stats
        currBowler.addBallsBowled(6);
        System.out.print(currBowler.getName() + " - " + currBowler.getWicketsTaken() + "/" + currBowler.getRunsConceded());
        changeStrike();
        System.out.print(" | " + currBatterOnStrike.getName() + " - " + currBatterOnStrike.getRunsScored() + "*");
        System.out.println("  " + currBatterOffStrike.getName() + " - " + currBatterOffStrike.getRunsScored());
    }

    public void changeStrike() {
        Player temp = this.currBatterOnStrike;
        this.currBatterOnStrike = this.currBatterOffStrike;
        this.currBatterOffStrike = temp;
    }

    public void printResults(int inningScore, Team battingTeam, Team bowlingTeam) {
        //Print match results
        if (inningScore >= this.matchTarget) {
            System.out.println(battingTeam.getTeamName() + " won by " + (this.maxWickets - battingTeam.getWicketsFell()) + " wickets.");
        }
        if (inningScore < this.matchTarget - 1) {
            System.out.println(bowlingTeam.getTeamName() + " won by " + (this.matchTarget - inningScore - 1) + " runs.");
        }
        // Check if match tied or print game results
        if (inningScore == this.matchTarget - 1) {
            System.out.println("Match tied!");
        }
        Utility.printDottedLine();
    }

    public void printScoreboard(Team battingTeam, Team bowlingTeam) {
        System.out.println("ScoreCard");
        System.out.println(bowlingTeam.getTeamName() + " innings");
        Utility.printBlankLine();
        bowlingTeam.printBattingStats();
        Utility.printBlankLine();
        battingTeam.printBowlingStats();
        Utility.printDottedLine();
        System.out.println(battingTeam.getTeamName() + " innings");
        battingTeam.printBattingStats();
        Utility.printBlankLine();
        bowlingTeam.printBowlingStats();
    }

    public Team[] flipCoin() {
        int toss = (int) (Math.random() * 2);
        Team[] teamOrder = new Team[2];

        // Shuffled random team order for toss
        if (toss == 1) {
            teamOrder[0] = team1;
            teamOrder[1] = team2;
        } else {
            teamOrder[0] = team2;
            teamOrder[1] = team1;
        }

        return teamOrder;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getMatchOvers() {
        return matchOvers;
    }
    public int getMatchTarget() {
        return matchTarget;
    }

    public void setMatchTarget(int matchTarget) {
        this.matchTarget = matchTarget;
    }

}
