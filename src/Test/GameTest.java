package Test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import Uno.Game;
import Uno.Player;
import Uno.UnoCard;
import java.io.InputStream;

/**
 * This tests all the function for the Game class.
 */
public class GameTest {

    @Test
    /**
     * This tests whether the game has been correctly initialized.
     */
    public void testInitialization() {
        String[] names = {"Andy", "Tony","Mike"};
        Game server = new Game(names);
        assertEquals(3, server.getPlayerCounter());
        assertEquals(1, server.getDirection());
        assertEquals(0, server.getCheckOver());
        assertEquals(0, server.getPenalty());
    }

    @Test
    /**
     * This tests whether each player could correctly get seven cards.
     */
    public void testPlayerGetSevenCard() {
        String[] names = {"Andy", "Tony"};
        Game server = new Game(names);
        Player player1 = server.getPlayerByIndex(0);
        Player player2 = server.getPlayerByIndex(1);
        assertEquals(7, player1.getCount());
        assertEquals(7, player2.getCount());
    }

    @Test
    /**
     * This tests whether the game could correctly end and a winner could occur.
     */
    public void testCheckWinner() {
        String[] names = {"Andy", "Tony"};
        Game server = new Game(names);
        for (int i = 0; i < 7; i++) {
            server.getPlayerByIndex(1).playCard(1);
        }
        assertEquals(true, server.checkWinner());
        assertEquals(1, server.getCheckOver());
    }

    @Test
    /**
     * This tests whether the player could be correctly updated when current player finishes his round.
     */
    public void testUpdatePlayer() {
        String[] names = {"Andy", "Tony","Mike"};
        Game server = new Game(names);
        server.updatePlayer();
        assertEquals(1, server.getCurrentPlayer());
        server.changeDirection();
        server.updatePlayer();
        assertEquals(0,server.getDirection());
        assertEquals(0, server.getCurrentPlayer());
    }

    /**
     * This tests for testing handling different six categories cards.
     */
    @Test
    public void testHandleNormalCard() {
        String[] names = {"Andy", "Tony"};
        Game server = new Game(names);
        UnoCard card = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.zero);
        server.handleCard(card);
        assertEquals(UnoCard.Color.Blue, server.getValidColor());
        assertEquals(UnoCard.Content.zero, server.getValidContent());
    }

    @Test
    /**
     * This tests whether the handle method could correctly handle the Draw Card.
     */
    public void testHandleDrawCard() {
        String[] names = {"Andy", "Tony","Mike"};
        Game server = new Game(names);
        UnoCard card = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.Draw);
        server.handleCard(card);
        assertEquals(UnoCard.Color.Blue, server.getValidColor());
        assertEquals(UnoCard.Content.Draw, server.getValidContent());
        server.handleCard(card);
        assertEquals(4, server.getPenalty());
    }

    @Test
    /**
     * This tests whether the handle method could correctly handle the Reverse Card.
     */
    public void testHandleReverseCard() {
        String[] names = {"Andy", "Tony","Mike"};
        Game server = new Game(names);
        UnoCard card = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.Reverse);
        server.handleCard(card);
        assertEquals(UnoCard.Color.Yellow, server.getValidColor());
        assertEquals(UnoCard.Content.Reverse, server.getValidContent());
        assertEquals(0, server.getDirection());
    }

    @Test
    /**
     * This tests whether the handle method could correctly handle the Skip Card.
     */
    public void testHandleSkipCard() {
        String[] names = {"Andy", "Tony","Mike"};
        Game server = new Game(names);
        UnoCard card = new UnoCard(UnoCard.Color.Red, UnoCard.Content.Skip);
        server.handleCard(card);
        assertEquals(UnoCard.Color.Red, server.getValidColor());
        assertEquals(UnoCard.Content.Skip, server.getValidContent());
        assertEquals(1, server.getCurrentPlayer());
    }

    @Test
    /**
     * This tests whether the handle method could correctly handle the Wild Card.
     */
    public void testHandleWildCard() {
        String[] names = {"Andy", "Tony"};
        Game server = new Game(names);
        UnoCard card = new UnoCard(UnoCard.Color.Black, UnoCard.Content.Wild);

        String newColor = "yellow";
        InputStream stringStream = new java.io.ByteArrayInputStream(newColor.getBytes());
        System.setIn(stringStream);

        server.handleCard(card);
        assertEquals(UnoCard.Color.Yellow, server.getValidColor());
        assertEquals(UnoCard.Content.Wild, server.getValidContent());
    }

    @Test
    /**
     * This tests whether the handle method could correctly handle the WildF Card.
     */
    public void testHandleWildFCard() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card = new UnoCard(UnoCard.Color.Black, UnoCard.Content.WildF);

        String newColor = "Red";
        InputStream stringStream = new java.io.ByteArrayInputStream(newColor.getBytes());
        System.setIn(stringStream);

        server.handleCard(card);
        assertEquals(UnoCard.Color.Red, server.getValidColor());
        assertEquals(UnoCard.Content.WildF, server.getValidContent());
        assertEquals(4, server.getPenalty());
        assertEquals(1, server.getCurrentPlayer());
    }

    /**
     * This tests whether the penalty could be correctly generated.
     * Penalty has been generated since Draw card occurs.
     */
    @Test
    public void testShouldBePenalized1() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card1 = new UnoCard(UnoCard.Color.Red, UnoCard.Content.Draw);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.nine);
        boolean result = server.shouldBePenalized(card2);
        assertEquals(true, result);
    }

    /**
     * This tests whether the penalty could be correctly generated.
     * No penalty for current player since it has another Draw Card.
     * Penalty will stack to 4 for next player.
     */
    @Test
    public void testShouldBePenalized2() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card1 = new UnoCard(UnoCard.Color.Red, UnoCard.Content.Draw);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.Draw);
        boolean result = server.shouldBePenalized(card2);
        assertEquals(false, result);
    }


    /**
     * This tests whether the penalty could be correctly generated.
     * Penalty still occurs because current player does not have Draw even if current player plays WildF.
     */
    @Test
    public void testShouldBePenalized4() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card1 = new UnoCard(UnoCard.Color.Red, UnoCard.Content.Draw);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.WildF);
        boolean result = server.shouldBePenalized(card2);
        assertEquals(true, result);
    }

    @Test
    /**
     * This tests whether the CheckValid method could correctly determine the validness of playing cards in such order.
     * Check different color.
     */
    public void testDiffColorCheckValid() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card1 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.one);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.two);
        assertEquals(true, server.checkValidCard(card2));
        UnoCard card3 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.three);
        assertEquals(false, server.checkValidCard(card3));
    }

    @Test
    /**
     * This tests whether the CheckValid method could correctly determine the validness of playing cards in such order.
     * Check different number.
     */
    public void testDiffNumCheckValid() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card1 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.two);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Red, UnoCard.Content.two);
        assertEquals(true, server.checkValidCard(card2));
        UnoCard card3 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.three);
        assertEquals(false, server.checkValidCard(card3));
    }

    @Test
    /**
     * This tests whether the CheckValid method could correctly determine the validness of playing cards in such order.
     * Check different action type card.
     */
    public void testDiffActionCheckValid() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card1 = new UnoCard(UnoCard.Color.Green, UnoCard.Content.Reverse);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Red, UnoCard.Content.Skip);
        assertEquals(false, server.checkValidCard(card2));
        UnoCard card3 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.Reverse);
        assertEquals(true, server.checkValidCard(card3));
    }

    @Test
    /**
     * This tests whether the CheckValid method could correctly determine the validness of playing cards in such order.
     * Check Wild and WilfF card.
     */
    public void testDiffWildCheckValid() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.five);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Black, UnoCard.Content.Wild);
        assertEquals(true, server.checkValidCard(card2));
        UnoCard card3 = new UnoCard(UnoCard.Color.Black, UnoCard.Content.WildF);
        assertEquals(true, server.checkValidCard(card3));
    }


    @Test
    /**
     * This tests whether the new rules Addition could works well.
     * Check simple case.
     */

    public void testAddition1() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        Player Ray = new Player("Ray");
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.five);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.one);
        UnoCard card3 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.four);
        Ray.addCard(card2);
        Ray.addCard(card3);
        UnoCard[] checkArray = server.checkAddition(Ray);
        assertEquals(UnoCard.Content.one, checkArray[0].getContent());
        assertEquals(UnoCard.Content.four, checkArray[1].getContent());
    }

    @Test
    /**
     * This tests whether the new rules Addition could works well.
     * Check more complex case with different color.
     */


    public void testAddition2() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        Player Ray = new Player("Ray");
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.five);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.two);
        UnoCard card3 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.one);
        UnoCard card4 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.four);
        Ray.addCard(card2);
        Ray.addCard(card3);
        Ray.addCard(card4);
        UnoCard[] checkArray = server.checkAddition(Ray);
        assertEquals(UnoCard.Content.one, checkArray[0].getContent());
        assertEquals(UnoCard.Content.four, checkArray[1].getContent());
    }

    @Test
    /**
     * This tests whether the new rules Addition could works well.
     * Check more complex case with same color but sum does not equal target value.
     */


    public void testAddition3() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        Player Ray = new Player("Ray");
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.five);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.two);
        UnoCard card3 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.zero);
        UnoCard card4 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.five);
        Ray.addCard(card2);
        Ray.addCard(card3);
        Ray.addCard(card4);
        UnoCard[] checkArray = server.checkAddition(Ray);
        assertEquals(UnoCard.Content.zero, checkArray[0].getContent());
        assertEquals(UnoCard.Content.five, checkArray[1].getContent());
    }

    @Test
    /**
     * This tests whether the new rules Subtraction could works well.
     * Check simple case.
     */


    public void testSubtraction1() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        Player Ray = new Player("Ray");
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.five);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.eight);
        UnoCard card3 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.three);
        Ray.addCard(card2);
        Ray.addCard(card3);
        UnoCard[] checkArray = server.checkSubtraction(Ray);
        assertEquals(UnoCard.Content.eight, checkArray[0].getContent());
        assertEquals(UnoCard.Content.three, checkArray[1].getContent());
    }

    @Test
    /**
     * This tests whether the new rules Subtraction could works well.
     * Check more complex case with different color.
     */


    public void testSubtraction2() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        Player Ray = new Player("Ray");
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.five);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Yellow, UnoCard.Content.two);
        UnoCard card3 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.nine);
        UnoCard card4 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.four);
        Ray.addCard(card2);
        Ray.addCard(card3);
        Ray.addCard(card4);
        UnoCard[] checkArray = server.checkSubtraction(Ray);
        assertEquals(UnoCard.Content.nine, checkArray[0].getContent());
        assertEquals(UnoCard.Content.four, checkArray[1].getContent());
    }

    @Test
    /**
     * This tests whether the new rules Subtraction could works well.
     * Check more complex case with same color but subtraction does not equal target value.
     */
    public void testSubtraction3() {
        String[] names = {"Andy", "Tony", "Ray"};
        Game server = new Game(names);
        Player Ray = new Player("Ray");
        UnoCard card1 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.four);
        server.handleCard(card1);
        UnoCard card2 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.one);
        UnoCard card3 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.eight);
        UnoCard card4 = new UnoCard(UnoCard.Color.Blue, UnoCard.Content.four);
        Ray.addCard(card2);
        Ray.addCard(card3);
        Ray.addCard(card4);
        UnoCard[] checkArray = server.checkSubtraction(Ray);
        assertEquals(UnoCard.Content.eight, checkArray[0].getContent());
        assertEquals(UnoCard.Content.four, checkArray[1].getContent());
    }

}