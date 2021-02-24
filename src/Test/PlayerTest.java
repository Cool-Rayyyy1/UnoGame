package Test;
import Uno.UnoCard;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import Uno.Player;

/**
 * This tests all the functions for the Player class.
 */
public class PlayerTest {

    @Test
    /**
     * This tests whether we could correctly get card from the player.
     */
    public void testGetCard() {
        String name = "Steven";
        Player player =  new Player(name);
        UnoCard card = new UnoCard(UnoCard.Color.Green, UnoCard.Content.Draw);
        player.addCard(card);
        assertEquals(card, player.getcard(1));
        assertEquals(1, player.getCount());
    }

    @Test
    /**
     * This tests whether the player could plays the card
     */
    public void testPlayCard() {
        String name = "Tony";
        Player player =  new Player(name);
        UnoCard card = new UnoCard(UnoCard.Color.Black, UnoCard.Content.Wild);
        player.addCard(card);
        UnoCard removed = player.playCard(1);
        assertEquals(0, player.getCount());
    }

    @Test
    /**
     * This test whether addCard method could add card to one player.
     */
    public void testAddCorrectCard() {
        String name = "MIke";
        Player player =  new Player(name);
        UnoCard card = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.zero);
        player.addCard(card);
        assertEquals(UnoCard.Color.Blue, player.getcard(1).getColor());
        assertEquals(UnoCard.Content.zero, player.getcard(1).getContent());
    }

    @Test
    /**
     * This tests whether the play method could be successfully implemented.
     */
    public void testPlayCorrectCard() {
        String name = "MIke";
        Player player =  new Player(name);
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.zero);
        UnoCard card2 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.Reverse);
        player.addCard(card1);
        player.addCard(card2);
        player.playCard(1);
        assertEquals(UnoCard.Color.Yellow, player.getcard(1).getColor());
        assertEquals(UnoCard.Content.Reverse, player.getcard(1).getContent());
    }

    @Test
    /**
     * This tests whether the showCards method could show all the cards information for one player.
     */
    public void testShowCards() {
        String name = "John";
        Player player =  new Player(name);
        UnoCard card1 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.zero);
        UnoCard card2 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.Reverse);
        UnoCard card3 = new UnoCard(UnoCard.Color.Black, UnoCard.Content.WildF);
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);
        player.showCards();
    }
}