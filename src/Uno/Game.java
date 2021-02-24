
package Uno;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {
    /**
     * A Integer to indicate current player.
     */
    private int currentPlayer;
    /**
     * The number of players within one game.
     */
    private int playerCounter;
    /**
     * The UnoDeck for the Game.
     */
    private UnoDeck Deck;
    /**
     * A Arraylist to hold all the players in this Game.
     */
    private ArrayList<Player> allPlayers;

    /**
     * A Color Variable to hold current valid Color in order to be played.
     */
    private UnoCard.Color validColor;
    /**
     * A Content Variable to hold current valid Content in order to be played.
     */
    private UnoCard.Content validContent;
    /**
     * A int type integer to define the direction of current playing turn.
     * 1 for clockwise, 0 for counterclockwise.
     */
    private int direction;
    /**
     * A int type integer to check whether winner occurs.
     * 0 for not, 1 for yes.
     */
    private int checkOver;
    /**
     * A int type variable that used to hold the penalty for the next player.
     */
    private int penalty;
    /**
     * A Color type variable to hold the valid color for addition/subtraction rules.
     */
    private UnoCard.Color validColor2;
    /**
     * A Content type variable to hold the valid content for addition/subtraction rules.
     */
    private UnoCard.Content validContent2;
    /**
     * A boolean type variable to check if during previous turn, two cards has been played.
     */
    private boolean checkTwoCards;
    /**
     * A boolean type variable to check if we need to handle second card.
     */
    private boolean handleSecond;
    /**
     * Constructor for the Game class.
     * @param names the String array that stores the name of the players that participate in this game.
     */
    public Game(String[] names) {
        currentPlayer = 0;
        playerCounter = names.length;
        Deck = new UnoDeck();
        Deck.fillCards();
        Deck.shuffle();
        allPlayers = new ArrayList<Player>(playerCounter);
        for (int i = 0; i < names.length; i++) {
            Player currPlayer = new Player(names[i]);
            for (int j = 0; j < 7; j++) {
                currPlayer.addCard(Deck.drawCard());
            }
            allPlayers.add(i, currPlayer);
        }
        direction = 1;
        checkOver = -1;
        penalty = 0;
    }

    /**
     * Method for begin the Uno Game.
     * First let the initial player to pick the toppest UnoCard from the Draw Pile until the UnoCard is not Reverse or Skip or Draw or Wild or WildF. IF it is, we shuffled the UnoCards.
     * Then the game is ready for play.
     */
    public void begin() {
        UnoCard firstCard = Deck.drawCard();
        while (firstCard.getContent() == UnoCard.Content.Reverse || firstCard.getContent() == UnoCard.Content.Skip || firstCard.getContent() == UnoCard.Content.Draw || firstCard.getContent() == UnoCard.Content.Wild || UnoCard.Content.WildF == firstCard.getContent())
        {
            Deck.discardCard(firstCard);
            Deck.discardAll();
            Deck.cleanDiscardCards();
            Deck.fillCards();
            Deck.shuffle();
            firstCard = Deck.drawCard();
        }
        validColor = firstCard.getColor();
        validContent = firstCard.getContent();
        Deck.discardCard(firstCard);
        currentPlayer++;

    }

    /** Method for getting the int variable playerCounter.
     * @return Return the playerCounter.
     */
    public int getPlayerCounter() {
        return playerCounter;
    }

    /**
     * Method for getting the int variable direction.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Method for getting the int variable penalty.
     */
    public int getPenalty() {
        return penalty;
    }

    /**
     * Method for getting the int variable checkOver.
     */
    public int getCheckOver() {
        return checkOver;
    }

    /**
     * Method for getting certain player in the allPlayers by index
     */
    public Player getPlayerByIndex(int index) {
        if (index < 0 || index >= playerCounter) {
            return null;
        }
        return allPlayers.get(index);
    }

    /**
     * Method for getting int variable currentPlayer.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method for changing int variable direction
     */
    public void changeDirection() {
        if (direction == 1) {
            direction = 0;
        } else {
            direction = 1;
        }
    }

    /**
     * Method for getting the int variable validColor.
     */
    public UnoCard.Color getValidColor() {
        return validColor;
    }

    /**
     * Method for getting the int variable validContent.
     */
    public UnoCard.Content getValidContent() {
        return validContent;
    }

    /**
     * Method for getting the geck of the Game.
     */
    public UnoDeck getDeck() {
        return Deck;
    }

    /**
     * Method for setting the valid color of the Game.
     */
    public void setValidColor(UnoCard.Color color) {
        validColor = color;
    }

    /**
     * Method for decrease the penalty of this game by 1.
     */
    public void setPenalty() {
        penalty--;
    }

    /**
     * Method for checking whether the winner has occured.
     * @return Return true for winner occurs, false otherwise.
     */
    public int checkWinner() {
        for (int i = 0; i < playerCounter; i++) {
            Player currPlayer = allPlayers.get(i);
            if (currPlayer.getCount() == 0) {
                checkOver = i;
                break;
            }
        }
        if (checkOver == 1) {
            return checkOver;
        } else {
            return checkOver;
        }
    }

    /**
     * method for helping players to drawCards from the drawCards pile.
     * @param currPlayerId The player that needs to draw the card.
     * @return Returns a boolean to indicate whether the player successfully draw the card.
     */
    public boolean drawCardfromDrawpile(int currPlayerId) {
        if (Deck.isEmpty() == true) {
            UnoCard lastCard = Deck.drawFromDiscard();
            validContent = lastCard.getContent();
            validColor = lastCard.getColor();
            for (int i = 0; i < Deck.getDiscardNum(); i++) {
                UnoCard addCard = Deck.drawFromDiscard();
                Deck.addDrawCards(addCard);
            }
            Deck.shuffle();
            Deck.cleanDiscardCards();
        }
        Player player = allPlayers.get(currPlayerId);
        if (player.getCount() == 12) {
            JLabel message = new JLabel( "You have 12 cards and you can't draw cards");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null,message);
            return false;
        } else {
            player = allPlayers.get(currPlayerId);
            player.addCard(Deck.drawCard());
            return true;
        }
    }

    /**
     * Method for updating the next player that should play cards.
     */
    public void updatePlayer() {
        if (direction == 1) {
            currentPlayer = (currentPlayer + 1) % playerCounter;
        } else {
            currentPlayer--;
            if (currentPlayer < 0) {
                currentPlayer += playerCounter;
            }
        }
    }

    /**
     * Original function.
     * Method for determining whether the current player has proper UnoCard to play and whether current player should accept the penalty.
     * @param currPlayer The player that should play in this round.
     */
    private void playerTurn(Player currPlayer) {
        int validIndex = checkValidIndex(currPlayer);
        if (validIndex == 0) {
            // no proper cards to play check new rule:addition or subtraction
            UnoCard[] twoAddCards = checkAddition(currPlayer);
            UnoCard[] twoSubCards = checkSubtraction(currPlayer);
            if (twoAddCards[0] != null && twoAddCards[1] != null) {
                //new addition rules could play
                if (penalty != 0) {
                    for (int i = 0; i < penalty; i++) {
                        currPlayer.addCard(Deck.drawCard());
                    }
                    penalty = 0;
                } else {
                    handleCard(twoAddCards[0]);
                    handleSecond = true;
                    handleCard(twoAddCards[1]);
                }
            } else if(twoSubCards[0] != null && twoSubCards[1] != null) {
                //new subtraction rules could play
                if (penalty != 0) {
                    for (int i = 0; i < penalty; i++) {
                        currPlayer.addCard(Deck.drawCard());
                    }
                    penalty = 0;
                } else {
                    handleCard(twoSubCards[0]);
                    handleSecond = true;
                    handleCard(twoSubCards[1]);
                }
            } else {
                if (penalty != 0) {
                    //has penalty
                    for (int i = 0; i < penalty; i++) {
                        currPlayer.addCard(Deck.drawCard());
                    }
                    penalty = 0;
                } else {
                    //no penalty
                    UnoCard newCard = Deck.drawCard();
                    if (checkValidCard(newCard) == true) {
                        handleCard(newCard);
                    } else {
                        currPlayer.addCard(newCard);
                    }
                }
            }

            if (penalty != 0) {
                //has penalty
                for (int i = 0; i < penalty; i++) {
                    currPlayer.addCard(Deck.drawCard());
                }
                penalty = 0;
            } else {
                //no penalty
                UnoCard newCard = Deck.drawCard();
                if (checkValidCard(newCard) == true) {
                    handleCard(newCard);
                } else {
                    currPlayer.addCard(newCard);
                }
            }

        } else {
            // have cards to play
            if (penalty != 0) {
                //has penalty
                if (shouldBePenalized(currPlayer.getcard(validIndex)) == false) {
                    handleCard(currPlayer.getcard(validIndex));
                    currPlayer.playCard(validIndex);
                } else {
                    for (int i = 0; i < penalty; i++) {
                        currPlayer.addCard(Deck.drawCard());
                    }
                    penalty = 0;
                }
            } else {
                //no penalty
                handleCard(currPlayer.getcard(validIndex));
                currPlayer.playCard(validIndex);
            }
        }
    }

    /**
     * Method for checking whether the new rule addition could be applied to current player.
     * @param currPlayer The player who need to be checked.
     * @return Return a UnoCard array to hold two UnoCards which satisfy the addition rule.
     */
    public UnoCard[] checkAddition(Player currPlayer) {
        UnoCard[] twoCards = new UnoCard[2];
        int firstValue = -1;
        int secondValue = -1;
        int targetValue = validContent.getValue();
        boolean valid = false;
        for (int i= 0; i < currPlayer.getCount(); i++) {
            if (valid == true) {
                break;
            }
            if (currPlayer.getcard(i+ 1).getColor() == validColor) {
                UnoCard firstCard = currPlayer.getcard(i+1);
                firstValue = firstCard.getContent().getValue();
                for (int j = i + 1; j < currPlayer.getCount(); j++) {
                    UnoCard secondCard = currPlayer.getcard(j + 1);
                    secondValue = secondCard.getContent().getValue();
                    if (firstValue + secondValue == targetValue) {
                        valid = true;
                        twoCards[0] = firstCard;
                        twoCards[1] = secondCard;
                        break;
                    }
                }
            }
        }
        return twoCards;
    }

    /**
     * Method for checking whether the new rule subtraction could be applied to current player.
     * @param currPlayer The player who need to be checked.
     * @return Return a UnoCard array to hold two UnoCards which satisfy the subtraction rule.
     */
    public UnoCard[] checkSubtraction(Player currPlayer) {
        UnoCard[] twoCards = new UnoCard[2];
        int firstValue = -1;
        int secondValue = -1;
        int targetValue = validContent.getValue();
        boolean valid = false;
        for (int i= 0; i < currPlayer.getCount(); i++) {
            if (valid == true) {
                break;
            }
            if (currPlayer.getcard(i+ 1).getColor() == validColor) {
                UnoCard firstCard = currPlayer.getcard(i+1);
                firstValue = firstCard.getContent().getValue();
                for (int j = i + 1; j < currPlayer.getCount(); j++) {
                    UnoCard secondCard = currPlayer.getcard(j + 1);
                    secondValue = secondCard.getContent().getValue();
                    if (Math.abs(firstValue - secondValue) == targetValue) {
                        valid = true;
                        twoCards[0] = firstCard;
                        twoCards[1] = secondCard;
                        break;
                    }
                }
            }
        }
        return twoCards;
    }

    /**
     * This method will test whether the current player should be penalized.
     */
    public boolean shouldBePenalized (UnoCard currCard) {
        if (currCard.getContent() == UnoCard.Content.Draw && validContent == UnoCard.Content.Draw) {
            return false;
        } else if (currCard.getContent() == UnoCard.Content.WildF && validContent == UnoCard.Content.WildF) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method helps to check if player has another DrawTwo card to play.
     */
    public boolean checkDraw(Player currPlayer) {
        ArrayList<UnoCard> cardsList = currPlayer.getAllCard();
        if (validContent == UnoCard.Content.Draw) {
            for (int i = 0; i < cardsList.size(); i++) {
                if (cardsList.get(i).getContent() == UnoCard.Content.Draw) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method helps to check if player has another WildF card to play.
     */
    public boolean checkWildF(Player currPlayer) {
        ArrayList<UnoCard> cardsList = currPlayer.getAllCard();
        if (validContent == UnoCard.Content.WildF) {
            for (int i = 0; i < cardsList.size(); i++) {
                if (cardsList.get(i).getContent() == UnoCard.Content.WildF) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method for handling the Card played by the player and modifies the Game State according to the handleCard.
     * @param handleCard The UnoCard played by the Player.
     */
    public void handleCard(UnoCard handleCard) {
        if (handleCard.getContent() == UnoCard.Content.Draw) {
                validColor = handleCard.getColor();
                validContent = handleCard.getContent();
                penalty += 2;
        } else if (handleCard.getContent() == UnoCard.Content.Skip) {
            validColor = handleCard.getColor();
            validContent = handleCard.getContent();
            updatePlayer();
        } else if (handleCard.getContent() == UnoCard.Content.Reverse) {
            validColor = handleCard.getColor();
            validContent = handleCard.getContent();
            if (direction == 1) {
                direction = 0;
            } else {
                direction = 1;
            }
        } else if (handleCard.getContent() == UnoCard.Content.Wild) {
            validContent = handleCard.getContent();
        } else if (handleCard.getContent() == UnoCard.Content.WildF) {
            validContent = handleCard.getContent();
            penalty += 4;
            updatePlayer();
        } else {
            if (handleSecond == true) {
                validColor2 = handleCard.getColor();
                validContent2 = handleCard.getContent();
                handleSecond = false;
                checkTwoCards = true;
            } else {
                validColor = handleCard.getColor();
                validContent = handleCard.getContent();
            }
        }
        Deck.discardCard(handleCard);
    }

    /**
     * Method for checking the proper cards that could be played by the current player.
     * @param currPlayer Player whose UnoCards should be checked.
     * @return return the proper UnoCards selected by the player.
     */
    private int checkValidIndex(Player currPlayer) {
        int cardIndex = 0;
        boolean invalid = true;
        while (invalid) {
            System.out.println("Enter the number of a valid card, or enter 0 to draw");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                cardIndex = Integer.parseInt(reader.readLine());
                if (cardIndex > currPlayer.getCount() || cardIndex < 0) {
                    continue;
                } else if (cardIndex == 0) {
                    invalid = false;
                } else {
                    UnoCard currCard = currPlayer.getcard(cardIndex);
                    if (checkValidCard(currCard)) {
                        checkTwoCards = false;
                        invalid = false;
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
        return cardIndex;
    }

    /**
     * Method for checking whether the player has cards to play
     * @param currPlayer The player that needs to be checked.
     * @return Returns true to indicate currPlayer has valid card to play. False otherwise.
     */
    public boolean checkPlayer(Player currPlayer) {
        ArrayList<UnoCard> cardList = currPlayer.getAllCard();
        boolean noCardToPlay = false;
        for (int i = 0; i < cardList.size(); i++) {
            if (checkValidCard(cardList.get(i)) == true) {
                noCardToPlay = true;
                break;
            }
        }
        return noCardToPlay;
    }

    /**
     * Method for checking the proper UnoCards could be player according to the Uno Rules.
     * @param currCard The UnoCard that need to be checked.
     * @return Return true for satisfying the rules, false otherwise.
     */
    public boolean checkValidCard(UnoCard currCard) {
        if (checkTwoCards == true) {
            if (currCard.getColor() == UnoCard.Color.Black) {
                return true;
            } else {
                if (validColor == currCard.getColor() || validColor2 == currCard.getColor()) {
                    return true;
                }
            }
            if (validContent == currCard.getContent() || validContent2 == currCard.getContent()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (currCard.getColor() == UnoCard.Color.Black) {
                //black color wild or wildF
                if (validContent != UnoCard.Content.WildF || validContent != UnoCard.Content.Wild) {
                    return true;
                } else {
                    if (validContent == UnoCard.Content.WildF) {
                        if (currCard.getContent() == UnoCard.Content.WildF) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                if (validColor == currCard.getColor()) {
                    return true;
                }
            }
            // color does not match check number
            if (validContent != UnoCard.Content.Draw || validContent != UnoCard.Content.Skip || validContent != UnoCard.Content.Reverse) {
                if (currCard.getContent() == validContent) {
                    return true;
                } else {
                    return false;
                }
            } else {
                //draw skip reverse
                if (validContent == UnoCard.Content.Draw && currCard.getContent() == UnoCard.Content.Draw) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * Method for helping normal bots to generate the random UnoCard.
     * @return Returns the index of that card.
     */
    public int helpNormalBots() {
        if (allPlayers.get(currentPlayer).getName().contains("NormalBot") == true || allPlayers.get(currentPlayer).getName().contains("SmartBot") == true) {
            ArrayList<UnoCard> cardList = new ArrayList<UnoCard>();
            for (int i = 0; i < allPlayers.get(currentPlayer).getAllCard().size(); i++) {
                if (checkValidCard(allPlayers.get(currentPlayer).getcard(i + 1)) == true) {
                    cardList.add(allPlayers.get(currentPlayer).getcard(i + 1));
                }
            }
            if (cardList.size() == 0) {
                return -1;
            } else {
                Random random = new Random();
                int randomInteger = java.lang.Math.abs((random.nextInt()) % cardList.size());
                UnoCard randomCard = cardList.get(randomInteger);
                int index = allPlayers.get(currentPlayer).getAllCard().indexOf(randomCard);
                return  index;
            }
        } else {
            JLabel message = new JLabel("Can not help player！");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null,message);
            return -2;
        }
    }

    /**
     * Method for helping smart bots to generate the UnoCard with highest priority.
     * @return Returns the index of that card
     */
    public int helpSmartBots() {
        if (allPlayers.get(currentPlayer).getName().contains("NormalBot") == true || allPlayers.get(currentPlayer).getName().contains("SmartBot") == true) {
            ArrayList<UnoCard> cardList = new ArrayList<UnoCard>();
            for (int i = 0; i < allPlayers.get(currentPlayer).getAllCard().size(); i++) {
                if (checkValidCard(allPlayers.get(currentPlayer).getcard(i + 1)) == true) {
                    cardList.add(allPlayers.get(currentPlayer).getcard(i + 1));
                }
            }
            if (cardList.size() == 0) {
                return -1;
            } else {
                UnoCard.Color[] prevColor = findPrevelentColor(allPlayers.get(currentPlayer));
                ArrayList<UnoCard> newList = new ArrayList<UnoCard>();
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getContent() == UnoCard.Content.WildF) {
                        newList.add(cardList.get(i));
                        cardList.remove(i);
                    }
                }
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getContent() == UnoCard.Content.Wild) {
                        newList.add(cardList.get(i));
                        cardList.remove(i);
                    }
                }
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getColor() == prevColor[3]) {
                        if (cardList.get(i).getContent() == validContent) {
                            continue;
                        }
                        newList.add(cardList.get(i));
                        cardList.remove(i);
                    }
                }
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getColor() == prevColor[2]) {
                        if (cardList.get(i).getContent() == validContent) {
                            continue;
                        }
                        newList.add(cardList.get(i));
                        cardList.remove(i);
                    }
                }
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getColor() == prevColor[1]) {
                        if (cardList.get(i).getContent() == validContent) {
                            continue;
                        }
                        newList.add(cardList.get(i));
                        cardList.remove(i);
                    }
                }
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getColor() == prevColor[0]) {
                        if (cardList.get(i).getContent() == validContent) {
                            continue;
                        }
                        newList.add(cardList.get(i));
                        cardList.remove(i);
                    }
                }
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getContent() == validContent) {
                        newList.add(cardList.get(i));
                        cardList.remove(i);
                    }
                }
                UnoCard SmartCard = newList.get(newList.size() - 1);
                int index = allPlayers.get(currentPlayer).getAllCard().indexOf(SmartCard);
                return  index;
            }
        } else {
            JLabel message = new JLabel("Can not help player！");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null,message);
            return -2;
        }
    }
    public UnoCard.Color generateRandomColor() {
        Random random = new Random();
        int randomInteger = java.lang.Math.abs((random.nextInt()) % 4);
        if (randomInteger == 0) {
            return UnoCard.Color.Red;
        } else if (randomInteger == 1) {
            return UnoCard.Color.Yellow;
        } else if (randomInteger == 2) {
            return  UnoCard.Color.Blue;
        } else if (randomInteger == 3) {
            return  UnoCard.Color.Green;
        }
        return UnoCard.Color.Red;
    }

    public UnoCard.Color[] findPrevelentColor(Player currPlayer) {
        ArrayList<UnoCard> allCards = currPlayer.getAllCard();
        HashMap<UnoCard.Color, Integer> colorDic = new HashMap<UnoCard.Color, Integer>();
        ValueComparator bvc = new ValueComparator(colorDic);
        TreeMap<UnoCard.Color, Integer> sorted_map = new TreeMap<UnoCard.Color, Integer>(bvc);
        colorDic.put(UnoCard.Color.Red, 0);
        colorDic.put(UnoCard.Color.Blue, 0);
        colorDic.put(UnoCard.Color.Yellow, 0);
        colorDic.put(UnoCard.Color.Green, 0);
        UnoCard.Color[] colors = new UnoCard.Color[4];
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).getColor() == UnoCard.Color.Red) {
                colorDic.put(UnoCard.Color.Red, colorDic.get(UnoCard.Color.Red) + 1);
            } else if (allCards.get(i).getColor() == UnoCard.Color.Blue) {
                colorDic.put(UnoCard.Color.Blue, colorDic.get(UnoCard.Color.Blue) + 1);
            } else if (allCards.get(i).getColor() == UnoCard.Color.Yellow) {
                colorDic.put(UnoCard.Color.Yellow, colorDic.get(UnoCard.Color.Yellow) + 1);
            } else if (allCards.get(i).getColor() == UnoCard.Color.Green) {
                colorDic.put(UnoCard.Color.Green, colorDic.get(UnoCard.Color.Green) + 1);
            }
        }
        sorted_map.putAll(colorDic);
        Set<Map.Entry<UnoCard.Color, Integer> > entrySet = sorted_map.entrySet();
        Map.Entry<UnoCard.Color, Integer>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        colors[0] = entryArray[0].getKey();
        colors[1] = entryArray[1].getKey();
        colors[2] = entryArray[2].getKey();
        colors[3] = entryArray[3].getKey();
        return colors;
    }

    class ValueComparator implements Comparator<UnoCard.Color> {
        Map<UnoCard.Color, Integer> base;

        public ValueComparator(Map<UnoCard.Color, Integer> base) {
            this.base = base;
        }

        public int compare(UnoCard.Color a, UnoCard.Color b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
