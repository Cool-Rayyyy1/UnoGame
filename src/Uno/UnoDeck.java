package Uno;

import java.util.ArrayList;
import java.util.Random;



/**
 * The UnoDeck class represents the Uno deck which consists 108 UnoCards
 * The total 108 UnoCards includes four colors which are red, green, blue and yellow and one more special black color.
 * For each color, it includes one 0 card, and two 1,2,3,4,5,6,7,8,9,DrawT,skip,reverse and four wild and wildF.
 */
public class UnoDeck {
    /**
     * A UnoCard type arraylist to store the UnoCards
     */
    private ArrayList<UnoCard> drawCards;
    /**
     * A UnoCard type arraylist to store the discard UnoCards
     */
    private ArrayList<UnoCard> discardCards;
    /**
     * A int type variable to store the number of UnoCards in the deck.
     */
    private int totalCards;
    /**
     * A int type variable to store the number of UnoCards in the discard pile
     */
    private int discardNum;
    /**
     * Constructor for the UnoDeck
     */
    public UnoDeck() {
        drawCards = new ArrayList<UnoCard>();
        totalCards = 0;
        discardCards = new ArrayList<UnoCard>();
        discardNum = 0;
    }


    /**
     * Method reset helps to reset the values in the UnoCard[] cards, fill in the values
     */
    public void fillCards() {
        totalCards = 0;
        UnoCard.Color[] colors = UnoCard.Color.values();
        UnoCard.Content[] contents = UnoCard.Content.values();
        for (int i = 0; i < (colors.length - 2); i++) {
            UnoCard.Color curr_color = colors[i];
            UnoCard.Content curr_content = contents[0];
            UnoCard addCardZero = new UnoCard(curr_color,curr_content);
            drawCards.add(addCardZero);
            totalCards++;
            for (int j = 1; j < 10; j++) {
                curr_content = contents[j];
                UnoCard addCardNumber1 = new UnoCard(curr_color,curr_content);
                drawCards.add(addCardNumber1);
                totalCards++;
                curr_content = contents[j];
                UnoCard addCardNumber2 = new UnoCard(curr_color,curr_content);
                drawCards.add(addCardNumber2);
                totalCards++;
            }
            for (int j = 10; j < 13; j++) {
                curr_content = contents[j];
                UnoCard addCardSpecial1 = new UnoCard(curr_color,curr_content);
                drawCards.add(addCardSpecial1);
                totalCards++;
                UnoCard addCardSpecial2 = new UnoCard(curr_color,curr_content);
                drawCards.add(addCardSpecial2);
                totalCards++;
            }
        }
        UnoCard.Color black_color = UnoCard.Color.Black;
        for (int i = 13; i < contents.length; i++) {
            for (int j = 0; j < 4; j++) {
                UnoCard addCardWild = new UnoCard(black_color, contents[i]);
                drawCards.add(addCardWild);
                totalCards++;
            }
        }
    }


    /**
     * method for checking whether the UnoDeck is empty
     * @return return True for empty otherwise false.
     */
    public boolean isEmpty() {
        if (totalCards == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * method for shuffling the DrawCards.
     */
    public void shuffle() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            for (int j = 0; j < drawCards.size(); j++){
                int randomInteger = java.lang.Math.abs((j + random.nextInt()) % drawCards.size());
                UnoCard randomCard = drawCards.get(randomInteger);
                drawCards.set(randomInteger, drawCards.get(j));
                drawCards.set(j, randomCard);
            }
        }
    }

    /**
     * method for drawing one card from the top of the UnoDeck
     * @return  return the top UnoCard of the UnoDeck
     * @throws IllegalArgumentException
     */
    public UnoCard drawCard() throws IllegalArgumentException {
        if (isEmpty() == true) {
            throw new IllegalArgumentException("UnoDeck is empty, can not draw card");
        }
        UnoCard Card = drawCards.get(drawCards.size() - 1);
        drawCards.remove(drawCards.size() - 1);
        totalCards--;
        return Card;
    }

    /**
     * method for drawing n UnoCard from the top of the UnoDeck
     * @param number number of UnoCard that we want to Draw
     * @return return the top N UnoCard of the UnoDeck
     */
    public UnoCard[] drawCards(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Can not draw negative UnoCards");
        }
        if (number > totalCards) {
            throw new IllegalArgumentException("Can not draw cards which exceed the number of left cards");
        }
        UnoCard[] cardsReturn = new UnoCard[number];
        for (int i = 0; i < number; i++) {
            cardsReturn[i] = drawCards.get(drawCards.size() - 1);
            drawCards.remove(drawCards.size() - 1);
            totalCards--;
        }
        return cardsReturn;
    }

    /**
     * method for discarding certain UnoDeck into the discard pile
     * @param card UnoCard that we want to discard
     */
    public void discardCard(UnoCard card) {
        discardCards.add(card);
        discardNum++;
    }

    /**
     * method for getting specific UnoCard from discard pile without deleting it.
     */
    public UnoCard getDiscard(int index) {
        return discardCards.get(index);
    }

    /**
     * method for discarding all the UnoCards
     */
    public void discardAll() {
        discardCards.addAll(drawCards);
        discardNum += totalCards;
        totalCards = 0;
    }

    /**
     * method for reinitializing a new Discard Pile
     */
    public void cleanDiscardCards() {
        discardCards = new ArrayList<UnoCard>();
        discardNum = 0;
    }

    /**
     * method for drawing the toppest card from the discard pile
     * @return return this UnoCard.
     */
    public UnoCard drawFromDiscard() {
        UnoCard lastCard = discardCards.get(discardCards.size() - 1);
        discardCards.remove(discardCards.size() - 1);
        discardNum--;
        return lastCard;
    }

    /**
     * method for returning the totalCards
     */
    public int getTotalCards() {
        return totalCards;
    }


    /**
     * method for getting the discardNum
     */
    public int getDiscardNum() {
        return discardNum;
    }

    /**
     * method for add UnoCard to the draw cards pile.
     * @param addCard
     */
    public void addDrawCards(UnoCard addCard) {
        drawCards.add(addCard);
        totalCards++;
    }

    /**
     * method for getting the arraylist of UnoCards of the discard pile.
     * @return
     */
    public ArrayList<UnoCard> getDiscardPile() {
        return discardCards;
    }
}
