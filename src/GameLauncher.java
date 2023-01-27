package src;

public class GameLauncher {
    public static void main(String[] args) {
        MatchController newMatch = new MatchController(5);
        newMatch.startGame();
    }
}
