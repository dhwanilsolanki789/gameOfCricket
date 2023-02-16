package src.com.tekion.cricketgame.beans;

import src.com.tekion.cricketgame.controller.Utility;

public class Match {
    private final Team team1;
    private final Team team2;
    private final int matchOvers;
    private final int maxWickets;
    private Scorecard scorecard;
    private Inning currInning;
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
        initiateInning(battingTeam);
        implementInning(inningNo,battingTeam,bowlingTeam);
        //Print inning stats
        currInning.printInningScore();
        Utility.printDottedLine();
        return currScore;
    }

    public void initiateInning(Team battingTeam){
        //Inning setup
        this.currScore = 0;
        this.currWickets = 0;
        Player[] openers = battingTeam.assignOpeners();
        this.currBatterOnStrike = openers[0];
        this.currBatterOffStrike = openers[1];
    }

    public void implementInning(int inningNo,Team battingTeam, Team bowlingTeam){
        this.currInning = scorecard.getInning(inningNo);
        int currOver, currBall = 0;
        for (currOver = 0; currOver < this.matchOvers; currOver++) {
            currBall = 1;
            this.currBowler = bowlingTeam.assignBowler(currOver);
            for (; currBall <= 6; currBall++) {
                // Bowl ball & inning end checked
                System.out.print("(" + currOver + "." + currBall + ") ");
                int ballOutcome = currBatterOnStrike.playBall();
                updateMatch(ballOutcome, battingTeam, bowlingTeam);
                if (inningEndCheck(inningNo)) {
                    currBowler.addBallsBowled(currBall);
                    currInning.setBallsBowled(currBall);
                    break;
                }
            }
            if(currInning.checkInningEnded()) {
                break;
            } else {
                changeOver(currOver+1);
            }
        }
        //Store inning stats
        currInning.setOversBowled(currOver);
    }

    public void updateMatch(int ballOutcome, Team battingTeam, Team bowlingTeam) {
        // 7 -> wicket
        if (ballOutcome != 7) {
            //update score and log ball result
            this.currScore += ballOutcome;
            currInning.setRunsScored(this.currScore);
            currBowler.addRunsConceded(ballOutcome);
            //Switch Strike when needed
            if (ballOutcome % 2 == 1) {
                changeStrike();
            }
        } else {
            //update wickets and log ball result
            this.currWickets++;
            currInning.setWicketsFell(this.currWickets);
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
            currInning.setInningEnded();
            currBatterOnStrike = null;
            System.out.println("Innings ended!");
            return true;
        }
        // End inning if target reached
        if (inningNo == 2) {
            if (this.currScore >= matchTarget) {
                currInning.setInningEnded();
                System.out.println("Match Ended!");
                return true;
            }
        }
        return false;
    }

    public void changeOver(int overNo){
        //Update bowler and print over stats
        currBowler.addBallsBowled(6);
        currInning.setOversBowled(overNo);
        changeStrike();
        currInning.printPostOverStats(currBowler,currBatterOnStrike,currBatterOffStrike);
    }

    public void changeStrike() {
        Player temp = this.currBatterOnStrike;
        this.currBatterOnStrike = this.currBatterOffStrike;
        this.currBatterOffStrike = temp;
    }

    public void printScoreboard(Team battingTeam, Team bowlingTeam) {
        scorecard.printResults(battingTeam,bowlingTeam);
        System.out.println("ScoreCard");
        scorecard.print();
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

    public void createScorecard(Team battingTeam, Team bowlingTeam){
        this.scorecard = new Scorecard(battingTeam,bowlingTeam,maxWickets);
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
        scorecard.setMatchTarget(matchTarget);
    }
}