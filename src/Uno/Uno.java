package Uno;

public class Uno {
    /**
     * The server to begin the Uno Game
     */
    public static void main(String[] args) {
        String[] players = {"Ray", "Tony", "Andy"};
        Game server = new Game(players);
        server.begin();
    }
}