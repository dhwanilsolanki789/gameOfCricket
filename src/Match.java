package src;

public class Match {
    Team t1;
    Team t2;
    private int matchOvers;

    public Match(int overs){
        t1 = new Team("India");
        t2 = new Team("Kiwis");
        matchOvers = overs;
    }

    public int getMatchOvers(){
        return matchOvers;
    }

    public boolean flipCoin(){
        int toss = (int)(Math.random()*2);
        return (toss == 1)?(true):(false);
    }
    public int bowlBall(){
        int ballOutcome  = (int)(Math.random()*8);
        return ballOutcome;
    }
}
