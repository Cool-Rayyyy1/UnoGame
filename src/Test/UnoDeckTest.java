package Test;

import Uno.UnoCard;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import Uno.UnoDeck;

/**
 * This tests all the functions for the UnoDeck class.
 */
public class UnoDeckTest {

    @Test
    /**
     * This test tests whether the UnoDeck has been successfully created.
     */
    public void testCorrectShuffle() {
        UnoDeck deck = new UnoDeck();
        UnoDeck compareDeck = new UnoDeck();
        deck.fillCards();
        compareDeck.fillCards();
        deck.shuffle();
        assertNotSame(deck, compareDeck);
    }
    
    @Test
    /**
     * This tests whether the empty function correctly determine whether the DrawPile is empty.
     */
    public void testEmpty() {
        UnoDeck deck = new UnoDeck();
        deck.fillCards();
        deck.drawCards(108);
        assertEquals(true, deck.isEmpty());
    }

    @Test
    /**
     * This tests whether the DrawCard method correctly draws one card from the draw pile
     */
    public void testDrawCard() {
        UnoDeck deck = new UnoDeck();
        deck.fillCards();
        deck.drawCard();
        assertEquals(107, deck.getTotalCards());
    }

    @Test
    /**
     * This tests whether the DrawCards method correctly draws n cards from the draw pile
     */
    public void testDrawCards() {
        UnoDeck deck = new UnoDeck();
        deck.fillCards();
        deck.drawCards(50);
        assertEquals(58, deck.getTotalCards());
    }

    @Test
    /**
     * This tests whether the discardCard method correctly discard one card and put it into the discard pile.
     */
    public void testDiscardCard() {
        UnoDeck deck = new UnoDeck();
        deck.fillCards();
        UnoCard lastDeck = deck.drawCard();
        deck.discardCard(lastDeck);
        System.out.println(deck.getDiscardNum());
        assertEquals(lastDeck, deck.drawFromDiscard());
    }

    @Test
    /**
     * This tests whether the discardAll method correctly discard all the cards in the draw pile.
     */
    public void testDiscardAll() {
        UnoDeck deck = new UnoDeck();
        deck.fillCards();
        deck.discardAll();
        assertEquals(108, deck.getDiscardNum());
        assertEquals(0, deck.getTotalCards());
    }

    @Test
    /**
     * This tests whether the addDiscard method is correct or not.
     */
    public void testAddDiscard() {
        UnoDeck deck = new UnoDeck();
        deck.fillCards();
        UnoCard card = deck.drawCard();
        deck.discardCard(card);
        UnoCard addCard = deck.getDiscard(0);
        assertEquals(card, addCard);
    }
}