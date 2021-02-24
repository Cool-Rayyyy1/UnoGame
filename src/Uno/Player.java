package Uno;

import java.util.ArrayList;

/**
 * The player class represents each Player in the Uno Game
 */
public class Player {
    /**
     * A String type variable to hold the name of the current player
     */
    private String name;

    private int cardCount;
    /**
     * A Arraylist of type UnoCard to hold the UnoCards of the player
     */
    public  ArrayList<UnoCard> playerCard;

    /**
     * The constructor for the Player class
     * @param GivenName Name for the player created
     */
    public Player(String GivenName) {
        cardCount = 0;
        name = GivenName;
        playerCard = new ArrayList<UnoCard>();
    }

    /**
     * method for drawing cards from the main pile and add it at the end of the arraylist
     * @param newCard the Card that the player need to draw
     */
    public void addCard(UnoCard newCard) {
        playerCard.add(newCard);
        cardCount++;
    }

    /**
     * method for getting the number of cards that current player has
     */
    public int getCount() {
        return cardCount;
    }
    /**
     * method for getting cards from the playerCards
     * @param Index the index for the card that we want to get
     * @return
     */
    public UnoCard getcard(int Index) {
        return playerCard.get(Index - 1);
    }

    /**
     * method for playing certain cards
     * @param Index index for the card that player want to play
     * @return
     */
    public UnoCard playCard(int Index) {
        UnoCard cardPlayed =  playerCard.get(Index - 1);
        playerCard.remove(Index - 1);
        cardCount--;
        return cardPlayed;
    }

    /**
     * method for showing the information that current player has
     */
    public void showCards() {
        System.out.println("You have cards: ");
        for (int i = 0; i < cardCount; i++) {
            System.out.print(i + 1);
            System.out.println(" is ");
            UnoCard currCard = playerCard.get(i);
            currCard.printCardInfo();
        }
    }

    /**
     * method for getting the names of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * method for getting the arraylist of the UnoCard of the current player.
     */
    public ArrayList<UnoCard> getAllCard() {
        return playerCard;
    }

}
