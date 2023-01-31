package src.com.tekion.cricketgame.beans;

import java.util.SortedMap;

public class Match {
    private final Team t1;
    private final Team t2;
    private final int matchOvers;

    public Match(int overs, String team1Name, String team2Name) {
        t1 = new Team(team2Name);
        t2 = new Team(team2Name);
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
}
