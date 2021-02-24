package GUI;

import Uno.Game;
import Uno.UnoCard;
import Uno.UnoDeck;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The GameView class is the main JFrame for displaying the progress the UnoGame.
 * This class handles displaying the GameState, drawing cards, giving penalty to correspond players, and change turns between plaers.
 */
public class GameView extends JFrame implements ActionListener {
    /**
     * The JFrame components and variables that are necessary for this class.
     */
    private JPanel menu;
    private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton card4;
    private JButton card5;
    private JButton card6;
    private JButton card7;
    private JButton card8;
    private JButton card9;
    private JButton card10;
    private JButton card11;
    private JButton card12;
    private JLabel text1;
    private JButton drawCard;
    private JLabel penalty;
    private JLabel currentPlayer;
    private JButton finish;
    private JButton hide;
    private JButton reveal;
    private ImageIcon  draw_image;
    private JLabel gameState;
    private selectView window;
    private WildView colorWindow;

    private boolean drawPenalty;
    private boolean playOnce;
    private boolean preventDraw;
    private boolean finishDraw;
    private Game server;
    private ArrayList<JButton> cardButtons;
    private ArrayList<UnoCard> cards;

    /**
     * The Constructor for the GameView class
     * @param names The String array
     */
    public GameView(String[] names) {
        //Initial the variables to the initial State.
        server = new Game(names);
        server.begin();
        cards = server.getPlayerByIndex(1).getAllCard();
        drawPenalty = false;
        playOnce = false;
        preventDraw = false;
        finishDraw = false;
        cardButtons = new ArrayList<JButton>();

        //Initial the JFrame Components.
        menu = new JPanel();
        menu.setLayout(null);
        card1 = new JButton("Card1");
        card1.setBounds(70,700,100,160);
        card1.addActionListener(this);
        menu.add(card1);
        card2 = new JButton("Card2");
        card2.setBounds(180,700,100,160);
        card2.addActionListener(this);
        menu.add(card2);
        card3 = new JButton("Card3");
        card3.setBounds(290,700,100,160);
        card3.addActionListener(this);
        menu.add(card3);
        card4 = new JButton("Card4");
        card4.setBounds(400,700,100,160);
        card4.addActionListener(this);
        menu.add(card4);
        card5 = new JButton("Card5");
        card5.setBounds(510,700,100,160);
        card5.addActionListener(this);
        menu.add(card5);
        card6 = new JButton("Card6");
        card6.setBounds(620,700,100,160);
        card6.addActionListener(this);
        menu.add(card6);
        card7 = new JButton("Card7");
        card7.setBounds(730,700,100,160);
        card7.addActionListener(this);
        menu.add(card7);
        card8 = new JButton("Card8");
        card8.setBounds(840,700,100,160);
        card8.addActionListener(this);
        menu.add(card8);
        card9 = new JButton("Card9");
        card9.setBounds(950,700,100,160);
        card9.addActionListener(this);
        menu.add(card9);
        card10 = new JButton("Card10");
        card10.setBounds(1060,700,100,160);
        card10.addActionListener(this);
        menu.add(card10);
        card11 = new JButton("Card11");
        card11.setBounds(1170,700,100,160);
        card11.addActionListener(this);
        menu.add(card11);
        card12 = new JButton("Card12");
        card12.setBounds(1280,700,100,160);
        card12.addActionListener(this);
        menu.add(card12);
        text1 = new JLabel("Current Player's Cards");
        text1.setBounds(630, 800, 200, 200);
        menu.add(text1);
        draw_image = new ImageIcon("./UnoCardImages/card_back.png");
        drawCard = new JButton("Draw", draw_image);
        drawCard.setBounds(70, 160, 100,160);
        drawCard.addActionListener(this);
        menu.add(drawCard);
        UnoDeck deck = server.getDeck();
        ArrayList<UnoCard> discardPile = deck.getDiscardPile();
        UnoCard discardCard = discardPile.get(discardPile.size() - 1);
        String color = discardCard.getColor().getValue();
        int content = discardCard.getContent().getValue();
        String new_content = String.valueOf(content);
        ImageIcon statusImage = new ImageIcon("./UnoCardImages/"+ color + "_" + new_content +".png");
        gameState = new JLabel(statusImage);
        gameState.setBounds(1100,100,150,270);
        menu.add(gameState);
        int result = server.getPenalty();
        String numberPenalty = String.valueOf(result);
        penalty = new JLabel("Number of cards stacked is " + numberPenalty);
        penalty.setBounds(200, 120, 400, 200);
        penalty.setFont(new Font("Serif", Font.BOLD, 20));
        menu.add(penalty);
        String currentName = server.getPlayerByIndex(server.getCurrentPlayer()).getName();
        currentPlayer = new JLabel(currentName + "'s turn!");
        currentPlayer.setBounds(630,550,200,200);
        currentPlayer.setFont(new Font("Serif", Font.BOLD, 25));
        menu.add(currentPlayer);
        finish = new JButton("Finish");
        finish.setBounds(90, 650, 100,30);
        finish.addActionListener(this);
        menu.add(finish);
        hide = new JButton("Hide");
        hide.setBounds(1200, 650, 100,30);
        hide.addActionListener(this);
        menu.add(hide);
        reveal  = new JButton("Reveal");
        reveal.setBounds(1200, 600, 100,30);
        reveal.addActionListener(this);
        menu.add(reveal);
        addButtons();
        setCards();
        hideCards();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menu);
        this.pack();
    }

    /**
     * Method for changing the playOnce to true.
     */
    public void setPlay() {
        playOnce = true;
    }

    /**
     * Method for getting the JLable GameState.
     */
    public JLabel getGameState() {
        return gameState;
    }

    /**
     * Method for updating the Turn JText.
     * Called only when changing players.
     */
    public void setTurn() {
        currentPlayer.setText(server.getPlayerByIndex(server.getCurrentPlayer()).getName() + "'s turn!");
    }

    /**
     * Method for updating the cards arraylist.
     */
    public void updateCards() {
        cards = server.getPlayerByIndex(server.getCurrentPlayer()).getAllCard();
    }

    /**
     * Method for getting the JButtons Arraylist
     */
    public ArrayList<JButton> getCardButtons() {
        return cardButtons;
    }


    public void setCards(){
        ArrayList<UnoCard> currentCards = server.getPlayerByIndex(server.getCurrentPlayer()).getAllCard();
        for (int i = 0; i < 12; i++) {
            cardButtons.get(i).setIcon(null);
        }
        for (int i = 0; i < currentCards.size(); i++) {
            String color = currentCards.get(i).getColor().getValue();
            int content = currentCards.get(i).getContent().getValue();
            String new_content = String.valueOf(content);
            cardButtons.get(i).setIcon(new ImageIcon("./UnoCardImages/"+ color + "_" + new_content +".png"));
        }

    }

    /**
     * Method for taking care of the function of the hide JButton.
     */
    public void hideCards() {
        ArrayList<UnoCard> currentCards = server.getPlayerByIndex(server.getCurrentPlayer()).getAllCard();
        for (int i = 0; i < currentCards.size(); i++) {
            cardButtons.get(i).setIcon(null);
        }
    }

    /**
     * Method for updating the penalty for the next player.
     * Only called when changing from current player to next player.
     */
    public void updatePenalty() {
        int result = server.getPenalty();
        String numberPenalty = String.valueOf(result);
        penalty.setText("Number of cards stacked is " + numberPenalty);
    }

    /**
     * Method for updating the current GameState by changing the JLabel.
     * Only called when a Card has been played.
     */
    public void updateGameState() {
        UnoDeck deck = server.getDeck();
        ArrayList<UnoCard> discardPile = deck.getDiscardPile();
        UnoCard discardCard = discardPile.get(discardPile.size() - 1);
        String color = discardCard.getColor().getValue();
        int content = discardCard.getContent().getValue();
        String new_content = String.valueOf(content);
        ImageIcon statusImage = new ImageIcon("./UnoCardImages/"+ color + "_" + new_content +".png");
        gameState.setIcon(statusImage);
    }

    /**
     * Method for adding all the Card JButtons to the Arraylist.
     */
    public void addButtons() {
        cardButtons.add(card1);
        cardButtons.add(card2);
        cardButtons.add(card3);
        cardButtons.add(card4);
        cardButtons.add(card5);
        cardButtons.add(card6);
        cardButtons.add(card7);
        cardButtons.add(card8);
        cardButtons.add(card9);
        cardButtons.add(card10);
        cardButtons.add(card11);
        cardButtons.add(card12);
    }

    /**
     * Method for resetting all the flags back to the initial state.
     * Should only be called when change from current player to next player.
     */
    public void setBooleans() {
        drawPenalty = false;
        playOnce = false;
        preventDraw = false;
        finishDraw = false;
    }

    /**
     * The method for taking care of the clicks of the different JButtons on the GameView class.
     */
    public void actionPerformed(ActionEvent e) {
        // Handle different CardButtons by calling the checkCardButton method.
        if (e.getSource() == card1) {
            checkCardButton(0);
        } else if (e.getSource() == card2) {
            checkCardButton(1);
        } else if (e.getSource() == card3) {
            checkCardButton(2);
        } else if (e.getSource() == card4) {
            checkCardButton(3);
        } else if (e.getSource() == card5) {
            checkCardButton(4);
        } else if (e.getSource() == card6) {
            checkCardButton(5);
        } else if (e.getSource() == card7) {
            checkCardButton(6);
        } else if (e.getSource() == card8) {
            checkCardButton(7);
        } else if (e.getSource() == card9) {
            checkCardButton(8);
        } else if (e.getSource() == card10) {
            checkCardButton(9);
        } else if (e.getSource() == card11) {
            checkCardButton(10);
        } else if (e.getSource() == card12) {
            checkCardButton(11);
        } else if (e.getSource() == drawCard) {
            if (server.getPenalty() != 0) {
                // Handle drawing cards because of the penalty.
                drawPenalty = true;
                boolean judge = server.drawCardfromDrawpile(server.getCurrentPlayer());
                JLabel message = new JLabel(server.getPlayerByIndex(server.getCurrentPlayer()).getName() + "'s penalty - 1");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
                server.setPenalty();
                updatePenalty();
                if (server.getPenalty() == 0) {
                    // Penalty has been cleared.
                    drawPenalty = false;
                    preventDraw = true;
                }
                if (judge == true) {
                    for (int i = 0; i < 12; i++) {
                        if (cardButtons.get(i).getIcon() == null) {
                            // Displaying the UnoCard drawn from pile and showing on the first blank Button
                            UnoCard addCard = server.getPlayerByIndex(server.getCurrentPlayer()).getcard(server.getPlayerByIndex(server.getCurrentPlayer()).getCount() - 1);
                            String color = addCard.getColor().getValue();
                            int content = addCard.getContent().getValue();
                            String new_content = String.valueOf(content);
                            cardButtons.get(i).setIcon(new ImageIcon("./UnoCardImages/"+ color + "_" + new_content +".png"));
                            break;
                        }
                    }
                    this.updateCards();
                }
            } else {
                // No penalty, should draw only one card.
                if (preventDraw == true) {
                    JLabel message = new JLabel("Penalty has been cleared and you can not draw cards anymore!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    // has already played the Card and tries to draw Card.
                    if (playOnce == true) {
                        JLabel message = new JLabel("Can not draw card after playing Card!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    } else {
                        // Draw card because no proper cards to play and does not have penalty.
                        if (server.checkPlayer(server.getPlayerByIndex(server.getCurrentPlayer())) == false) {
                            boolean judge = server.drawCardfromDrawpile(server.getCurrentPlayer());
                            if (judge == true) {
                                JLabel message = new JLabel(server.getPlayerByIndex(server.getCurrentPlayer()).getName() + "draw a card!");
                                message.setFont(new Font("Arial", Font.BOLD, 48));
                                JOptionPane.showMessageDialog(null,message);
                                finishDraw = true;
                                for (int i = 0; i < 12; i++) {
                                    if (cardButtons.get(i).getIcon() == null) {
                                        UnoCard addCard = server.getPlayerByIndex(server.getCurrentPlayer()).getcard(server.getPlayerByIndex(server.getCurrentPlayer()).getCount());
                                        String color = addCard.getColor().getValue();
                                        int content = addCard.getContent().getValue();
                                        String new_content = String.valueOf(content);
                                        cardButtons.get(i).setIcon(new ImageIcon("./UnoCardImages/"+ color + "_" + new_content +".png"));
                                        break;
                                    }
                                }
                                this.updateCards();
                            }
                        } else {
                            // Has valid card to play but tries to draw card.
                            JLabel message = new JLabel("You have cards to play. Please play a card!");
                            message.setFont(new Font("Arial", Font.BOLD, 48));
                            JOptionPane.showMessageDialog(null,message);
                        }
                    }

                }
            }

        } else if (e.getSource() == finish) {
            int playerIds = server.checkWinner();
            if (playerIds != -1) {
                // Handling checking whether the game is over.
                String winnerName = server.getPlayerByIndex(playerIds).getName();
                JFrame frame1 = new WinnerView(winnerName);
                frame1.setSize(1000,1000);
                frame1.setVisible(true);
                this.dispose();
            } else {
                if ((drawPenalty == false && preventDraw == true) || playOnce == true || finishDraw == true) {
                    // could move to the next only if one of three cases has been satisfied.
                    // 1. Has penalty and cleared the penalty
                    // 2. Played one Card
                    // 3. No proper cards to play and draw one card.
                    this.updatePenalty();
                    server.updatePlayer();
                    this.updateCards();
                    this.setTurn();
                    this.setBooleans();
                    this.setBooleans();
                    this.setCards();
                    this.hideCards();
                } else {
                    JLabel message = new JLabel("Please finish your turn before move to the next!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                }
            }

        } else if (e.getSource() == hide) {
            this.hideCards();
        } else if (e.getSource() == reveal) {
            this.setCards();
        }
    }

    /**
     * This method helps to check the CardButtons
     * @param indexOfButtons The index of the buttons which is also the index of the cards within the arraylist.
     */
    public void checkCardButton(int indexOfButtons) {
        if (cardButtons.get(indexOfButtons).getIcon() != null) {
            //have cards
            if (preventDraw == true) {
                JLabel message = new JLabel("Penalty has been cleared and you can not play cards anymore!");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            } else {
                if (server.getPenalty() != 0) {
                    if (server.getPenalty() == 2) {
                        if (server.checkDraw(server.getPlayerByIndex(server.getCurrentPlayer())) == false) {
                            // must play draw two here
                            window = new selectView(cards.get(indexOfButtons), server, indexOfButtons, cardButtons, this, gameState, true, playOnce);
                            window.setBounds(200,200,500,500);
                            window.setVisible(true);
                            window.setResizable(false);
                            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        } else {
                            JLabel message = new JLabel("You can not play card! You must draw cards until penalty is 0");
                            message.setFont(new Font("Arial", Font.BOLD, 48));
                            JOptionPane.showMessageDialog(null,message);
                        }
                    } else if (server.getPenalty() == 4 && server.getValidContent() == UnoCard.Content.WildF) {
                        if (server.checkWildF(server.getPlayerByIndex(server.getCurrentPlayer())) == false) {
                            // must play wildF here
                            colorWindow = new WildView(cards.get(indexOfButtons), this, server,indexOfButtons, true, playOnce);
                            colorWindow.setBounds(200,200,500,500);
                            colorWindow.setVisible(true);
                            colorWindow.setResizable(false);
                            colorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        } else {
                            JLabel message = new JLabel("You can not play card! You must draw cards until penalty is 0");
                            message.setFont(new Font("Arial", Font.BOLD, 48));
                            JOptionPane.showMessageDialog(null,message);
                        }
                    } else {
                        JLabel message = new JLabel("You can not play card! You must draw cards until penalty is 0");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    }
                } else {
                    if (cards.get(indexOfButtons).getContent() == UnoCard.Content.WildF || cards.get(indexOfButtons).getContent() == UnoCard.Content.Wild) {
                        // Wild or WildF, go to WildView.
                        colorWindow = new WildView(cards.get(indexOfButtons), this, server,indexOfButtons, false, playOnce);
                        colorWindow.setBounds(200,200,500,500);
                        colorWindow.setVisible(true);
                        colorWindow.setResizable(false);
                        colorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } else {
                        // Normal Cards, go to selectView.
                        window = new selectView(cards.get(indexOfButtons), server, indexOfButtons, cardButtons, this, gameState, false, playOnce);
                        window.setBounds(200,200,500,500);
                        window.setVisible(true);
                        window.setResizable(false);
                        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
                }
            }
        }
    }

    /**
     * Method for running the GameView single file.
     */
    public static void main(String[] args) {
        String[] names = {"Andy", "ray", "tommy"};
        JFrame frame = new GameView(names);
        frame.setSize(1460,1000);
        frame.setVisible(true);
    }
}
