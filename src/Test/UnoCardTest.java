package Test;

import Uno.UnoCard;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This tests all the functions for the UnoCard class.
 */
public class UnoCardTest {

    @Test
    /**
     * This tests whether the getColor method could correctly return the card's color.
     */
    public void testGetColor() {
        UnoCard card = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.one);
        assertEquals(UnoCard.Color.Blue, card.getColor());
        assertEquals(UnoCard.Content.one, card.getContent());
    }

    /**
     * This tests whether the getContent method could correctly return the card's content.
     */
    @Test
    public void testGetContent() {
        UnoCard card = new UnoCard(UnoCard.Color.Red, UnoCard.Content.Skip);
        assertEquals(UnoCard.Content.Skip, card.getContent());
        assertEquals(UnoCard.Color.Red, card.getColor());
    }

    @Test
    /**
     * This tests whether the printCardInfo method could correctly show the card information.
     */
    public void testPrintCardInfo() {
        UnoCard card1 = new UnoCard(UnoCard.Color.Black, UnoCard.Content.Wild);
        card1.printCardInfo();
        UnoCard card2 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.Draw);
        card2.printCardInfo();
    }
}