package GUI;

import Uno.Game;
import Uno.Player;
import Uno.UnoCard;
import Uno.UnoDeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The BotGameView class is the main JFrame for displaying the progress the UnoGame playing against AI.
 * This class handles displaying the GameState, drawing cards, giving penalty to correspond players, and change turns between players and AI.
 */
public class BotGameView extends JFrame implements ActionListener {
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
    private JButton action;
    private ImageIcon  draw_image;
    private JLabel gameState;
    private BotSelectView window;
    private BotWildView colorWindow;

    private boolean drawPenalty;
    private boolean playOnce;
    private boolean preventDraw;
    private boolean finishDraw;
    private boolean checkAction;
    private Game server;
    private ArrayList<JButton> cardButtons;
    private ArrayList<UnoCard> currCards;
    private String nameOfPlayer;
    private String oldName;

    /**
     * The Constructor for the BotGameView class
     * @param numOfNormal The number of normal bots in this game.
     * @param numOfSmart The number of custom bots in this game.
     * @param name The name of the player
     */
    BotGameView(int numOfNormal, int numOfSmart, String name) {
        //Initial the variables to the initial State.
        nameOfPlayer = name;
        oldName = "No.1_NormalBot";
        String[] arrayOfPlayer = new String[(numOfNormal+numOfSmart)+ 1];
        arrayOfPlayer[0] = nameOfPlayer;
        for (int i = 1; i < (1 + numOfNormal); i ++) {
            String normalBot = "NO." + i + "_NormalBot";
            arrayOfPlayer[i] = normalBot;
        }
        for (int i = (1 + numOfNormal); i < (numOfNormal+numOfSmart)+ 1; i ++) {
            String smartBot = "NO." + i + "_SmartBot";
            arrayOfPlayer[i] = smartBot;
        }
        server = new Game(arrayOfPlayer);
        server.begin();
        currCards = server.getPlayerByIndex(1).getAllCard();
        drawPenalty = false;
        playOnce = false;
        preventDraw = false;
        finishDraw = false;
        checkAction = false;
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
        currentPlayer.setBounds(550,550,400,200);
        currentPlayer.setFont(new Font("Serif", Font.BOLD, 25));
        menu.add(currentPlayer);
        finish = new JButton("Finish");
        finish.setBounds(80, 650, 100,30);
        finish.addActionListener(this);
        menu.add(finish);
        action = new JButton("Action");
        action.setBounds(1200, 650, 100,30);
        action.addActionListener(this);
        menu.add(action);
        addButtons();
        setCards();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menu);
        this.pack();
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
     * Method for resetting the UnoCards displayed on the GameView
     * First setting them to null and based on the currents cards information, gradually change the ImageIcons for each Button
     * Sets the bots card to null but display player's cards.
     */
    public void setCards() {
        ArrayList<UnoCard> currentCards = server.getPlayerByIndex(server.getCurrentPlayer()).getAllCard();
        if (server.getPlayerByIndex(server.getCurrentPlayer()).getName().contains("NormalBot") == true || server.getPlayerByIndex(server.getCurrentPlayer()).getName().contains("SmartBot") == true) {
            //Bots turn;
            for (int i = 0; i < 12; i++) {
                cardButtons.get(i).setIcon(null);
            }
        } else {
            // player turn
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
     * Method for resetting all the flags back to the initial state.
     * Should only be called when change from current player to next player.
     */
    public void setBooleans() {
        drawPenalty = false;
        playOnce = false;
        preventDraw = false;
        finishDraw = false;
        checkAction = false;
    }

    /**
     * Method for getting the JLable GameState.
     */
    public JLabel getGameState() {
        return gameState;
    }

    /**
     * Method for changing the playOnce to true.
     */
    public void setPlay() {
        playOnce = true;
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
        currCards = server.getPlayerByIndex(server.getCurrentPlayer()).getAllCard();
    }

    /**
     * The method for taking care of the clicks of the different JButtons on the GameView class.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == card1) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            } else {
                checkCardButton(0);
            }
        } else if (e.getSource() == card2) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            } else {
                checkCardButton(1);
            }
        } else if (e.getSource() == card3) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(2);
            }
        } else if (e.getSource() == card4) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(3);
            }
        } else if (e.getSource() == card5) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(4);
            }
        } else if (e.getSource() == card6) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(5);
            }
        } else if (e.getSource() == card7) {
            if (checkBotOrPlayer(oldName) == true ) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(6);
            }
        } else if (e.getSource() == card8) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(7);
            }
        } else if (e.getSource() == card9) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(8);
            }
        } else if (e.getSource() == card10) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(9);
            }
        } else if (e.getSource() == card11) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(10);
            }
        } else if (e.getSource() == card12) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("It is Bot's turn and you can not play card by clicking buttons");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
                checkCardButton(11);
            }
        } else if (e.getSource() == action) {
            if (checkBotOrPlayer(oldName) == true) {
                if (oldName.contains("NormalBot") == true) {
                    if (server.getPenalty() != 0) {
                        for (int i = 0; i < server.getPenalty(); i++) {
                            server.drawCardfromDrawpile(server.getCurrentPlayer());
                        }
                        checkAction = true;
                        JLabel message = new JLabel("Bot received penalty!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null, message);
                    } else {
                        //penalty = 0
                        if (server.helpNormalBots() < 0) {
                            //no card to play
                            server.drawCardfromDrawpile(server.getCurrentPlayer());
                            Player currPlayer = server.getPlayerByIndex(server.getCurrentPlayer());
                            UnoCard drawCard = currPlayer.getcard(currPlayer.getCount());
                            if (server.checkValidCard(drawCard) == true) {
                                if (drawCard.getContent() == UnoCard.Content.WildF || drawCard.getContent() == UnoCard.Content.Wild) {
                                    UnoCard.Color color = server.generateRandomColor();
                                    currPlayer.playCard(currPlayer.getCount());
                                    server.setValidColor(color);
                                    server.handleCard(drawCard);
                                    if (color == UnoCard.Color.Red) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Red_Card.jpg"));
                                    } else if (color == UnoCard.Color.Blue) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Blue_Card.jpg"));
                                    } else if (color == UnoCard.Color.Green) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Green_Card.jpg"));
                                    } else if (color == UnoCard.Color.Yellow) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Yellow_Card.jpg"));
                                    }
                                    JLabel message = new JLabel("Bot has no proper card, draw a valid card and play that card!");
                                    message.setFont(new Font("Arial", Font.BOLD, 48));
                                    JOptionPane.showMessageDialog(null, message);
                                } else {
                                    currPlayer.playCard(currPlayer.getCount());
                                    server.handleCard(drawCard);
                                    updateGameState();
                                    String cardInfo = drawCard.getColor().getValue() + drawCard.getContent().getValue();
                                    JLabel message = new JLabel("Bot has no proper card, draw a valid card and played " + cardInfo);
                                    message.setFont(new Font("Arial", Font.BOLD, 48));
                                    JOptionPane.showMessageDialog(null, message);
                                }
                            } else {
                                JLabel message = new JLabel("Bot has no proper card, draw a invalid card and keep that card!");
                                message.setFont(new Font("Arial", Font.BOLD, 48));
                                JOptionPane.showMessageDialog(null, message);
                            }
                            checkAction = true;
                        } else {
                            //has card to play
                            int index = server.helpNormalBots();
                            Player currPlayer = server.getPlayerByIndex(server.getCurrentPlayer());
                            UnoCard playCard = currPlayer.getAllCard().get(index);
                            if (playCard.getContent() == UnoCard.Content.WildF || playCard.getContent() == UnoCard.Content.Wild) {
                                UnoCard.Color color = server.generateRandomColor();
                                currPlayer.playCard(index + 1);
                                server.setValidColor(color);
                                server.handleCard(playCard);
                                if (color == UnoCard.Color.Red) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Red_Card.jpg"));
                                } else if (color == UnoCard.Color.Blue) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Blue_Card.jpg"));
                                } else if (color == UnoCard.Color.Green) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Green_Card.jpg"));
                                } else if (color == UnoCard.Color.Yellow) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Yellow_Card.jpg"));
                                }
                            } else {
                                currPlayer.playCard(index + 1);
                                server.handleCard(playCard);
                                updateGameState();
                            }
                            String cardInfo = playCard.getColor().getValue() + playCard.getContent().getValue();
                            JLabel message = new JLabel("Bot has proper card and played " + cardInfo);
                            message.setFont(new Font("Arial", Font.BOLD, 48));
                            JOptionPane.showMessageDialog(null, message);
                            checkAction = true;
                        }
                    }
                } else {
                    // smart bot
                    if (server.getPenalty() != 0) {
                        for (int i = 0; i < server.getPenalty(); i++) {
                            server.drawCardfromDrawpile(server.getCurrentPlayer());
                        }
                        checkAction = true;
                        JLabel message = new JLabel("Bot received penalty!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null, message);
                    } else {
                        //penalty = 0
                        if (server.helpSmartBots() < 0) {
                            //no card to play
                            server.drawCardfromDrawpile(server.getCurrentPlayer());
                            Player currPlayer = server.getPlayerByIndex(server.getCurrentPlayer());
                            UnoCard drawCard = currPlayer.getcard(currPlayer.getCount());
                            if (server.checkValidCard(drawCard) == true) {
                                if (drawCard.getContent() == UnoCard.Content.WildF || drawCard.getContent() == UnoCard.Content.Wild) {
                                    UnoCard.Color color = server.findPrevelentColor(currPlayer)[3];
                                    currPlayer.playCard(currPlayer.getCount());
                                    server.setValidColor(color);
                                    server.handleCard(drawCard);
                                    if (color == UnoCard.Color.Red) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Red_Card.jpg"));
                                    } else if (color == UnoCard.Color.Blue) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Blue_Card.jpg"));
                                    } else if (color == UnoCard.Color.Green) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Green_Card.jpg"));
                                    } else if (color == UnoCard.Color.Yellow) {
                                        getGameState().setIcon(null);
                                        getGameState().setIcon(new ImageIcon("./UnoCardImages/Yellow_Card.jpg"));
                                    }
                                    String cardInfo = drawCard.getColor().getValue() + drawCard.getContent().getValue();
                                    JLabel message = new JLabel("Bot has no proper card, draw a valid card and played " + cardInfo);
                                    message.setFont(new Font("Arial", Font.BOLD, 48));
                                    JOptionPane.showMessageDialog(null, message);
                                } else {
                                    currPlayer.playCard(currPlayer.getCount());
                                    server.handleCard(drawCard);
                                    updateGameState();
                                    JLabel message = new JLabel("Bot has no proper card, draw a valid card and play that card!");
                                    message.setFont(new Font("Arial", Font.BOLD, 48));
                                    JOptionPane.showMessageDialog(null, message);
                                }
                            } else {
                                JLabel message = new JLabel("Bot has no proper card, draw a invalid card and keep that card!");
                                message.setFont(new Font("Arial", Font.BOLD, 48));
                                JOptionPane.showMessageDialog(null, message);
                            }
                            checkAction = true;
                        } else {
                            //has card to play
                            int index = server.helpSmartBots();
                            Player currPlayer = server.getPlayerByIndex(server.getCurrentPlayer());
                            UnoCard playCard = currPlayer.getAllCard().get(index);
                            if (playCard.getContent() == UnoCard.Content.WildF || playCard.getContent() == UnoCard.Content.Wild) {
                                UnoCard.Color color = server.findPrevelentColor(currPlayer)[3];
                                currPlayer.playCard(index + 1);
                                server.setValidColor(color);
                                server.handleCard(playCard);
                                if (color == UnoCard.Color.Red) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Red_Card.jpg"));
                                } else if (color == UnoCard.Color.Blue) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Blue_Card.jpg"));
                                } else if (color == UnoCard.Color.Green) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Green_Card.jpg"));
                                } else if (color == UnoCard.Color.Yellow) {
                                    getGameState().setIcon(null);
                                    getGameState().setIcon(new ImageIcon("./UnoCardImages/Yellow_Card.jpg"));
                                }
                            } else {
                                currPlayer.playCard(index + 1);
                                server.handleCard(playCard);
                                updateGameState();
                            }
                            String cardInfo = playCard.getColor().getValue() + playCard.getContent().getValue();
                            JLabel message = new JLabel("Bot has proper card and played " + cardInfo);
                            message.setFont(new Font("Arial", Font.BOLD, 48));
                            JOptionPane.showMessageDialog(null, message);
                            checkAction = true;
                        }
                    }
                }
            } else {
                JLabel message = new JLabel("This button is used for Bots to do actions not player!");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            }
        } else if (e.getSource() == finish) {
            if (checkBotOrPlayer(oldName) == true) {
                int playerIds = server.checkWinner();
                if (playerIds != -1) {
                    // Handling checking whether the game is over.
                    String winnerName = server.getPlayerByIndex(playerIds).getName();
                    JFrame frame1 = new WinnerView(winnerName);
                    frame1.setSize(1000,1000);
                    frame1.setVisible(true);
                    this.dispose();
                } else {
                    if (checkAction == true) {
                        this.updatePenalty();
                        server.updatePlayer();
                        oldName = server.getPlayerByIndex(server.getCurrentPlayer()).getName();
                        this.updateCards();
                        this.setTurn();
                        this.setBooleans();
                        this.setCards();
                    } else {
                        JLabel message = new JLabel("Please press Action button for Bots!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    }
                }
            } else {
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
                        oldName = server.getPlayerByIndex(server.getCurrentPlayer()).getName();
                        this.updateCards();
                        this.setTurn();
                        this.setBooleans();
                        this.setCards();
                    } else {
                        JLabel message = new JLabel("Please finish your turn before move to the next!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    }
                }
            }
        } else if (e.getSource() == drawCard) {
            if (checkBotOrPlayer(oldName) == true) {
                JLabel message = new JLabel("You can not help bots to draw Card!");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
            } else {
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
            }
        }
    }

    /**
     * Method for checking whether current player is AI or player.
     * @return
     */
    public boolean checkBotOrPlayer(String nameTobeChecked) {
        if (nameTobeChecked.contains("NormalBot") == true) {
            return true;
        }
        if (nameTobeChecked.contains("SmartBot") == true) {
            return true;
        }
        return false;
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
                            window = new BotSelectView(currCards.get(indexOfButtons), server, indexOfButtons, cardButtons, this, gameState, true, playOnce);
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
                            colorWindow = new BotWildView(currCards.get(indexOfButtons), this, server,indexOfButtons, true, playOnce);
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
                    if (currCards.get(indexOfButtons).getContent() == UnoCard.Content.WildF || currCards.get(indexOfButtons).getContent() == UnoCard.Content.Wild) {
                        // Wild or WildF, go to WildView.
                        colorWindow = new BotWildView(currCards.get(indexOfButtons), this, server,indexOfButtons, false, playOnce);
                        colorWindow.setBounds(200,200,500,500);
                        colorWindow.setVisible(true);
                        colorWindow.setResizable(false);
                        colorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } else {
                        // Normal Cards, go to selectView.
                        window = new BotSelectView(currCards.get(indexOfButtons), server, indexOfButtons, cardButtons, this, gameState, false, playOnce);
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
     * Method for running this file.
     */
    public static void main(String[] args) {
        JFrame frame = new BotGameView(1,1,"Ray");
        frame.setSize(1460,1000);
        frame.setVisible(true);
    }
}
