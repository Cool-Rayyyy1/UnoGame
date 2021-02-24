package GUI;

import Uno.Game;
import Uno.UnoCard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The WildView class for displaying the View after playing the Wild or WildF cards.
 * Besides, this view also handles the Wild and WildF cards.
 */
public class WildView extends JFrame implements ActionListener {
    /**
     * The JFrame components and variables that are necessary for this class.
     */
    private JPanel wildmenu;
    private JButton red;
    private JButton blue;
    private JButton yellow;
    private JButton green;
    private JLabel image;

    private GameView view;
    private UnoCard chosenCard;
    private Game server;
    private int choice;
    private boolean checkWildF;
    private boolean playOnce;

    /**
     * Constructor for the WildView class.
     * @param card The WildF UnoCard or Wild UnoCard that is going to display on the frame.
     * @param gameview The GameView which creates this class.
     * @param game The Game that starts this game.
     * @param index The index of the UnoCard Arraylist which is also the index of the buttons.
     * @param check The boolean which indicates whether the player must play DrawTwo.
     * @param play The boolean which indicates whether the player has played in this turn.
     */
    public WildView(UnoCard card, GameView gameview, Game game, int index, boolean check, boolean play) {
        //setting variables to initial state.
        chosenCard = card;
        view = gameview;
        server = game;
        choice = index;
        checkWildF = check;
        playOnce = play;

        //Initial the JFrame Components.
        wildmenu = new JPanel();
        wildmenu.setLayout(null);
        String color = chosenCard.getColor().getValue();
        int content = chosenCard.getContent().getValue();
        String new_content = String.valueOf(content);
        ImageIcon statusImage = new ImageIcon("./UnoCardImages/"+ color + "_" + new_content +".png");
        image = new JLabel(statusImage);
        image.setBounds(250, 115, 150, 270);
        wildmenu.add(image);
        red = new JButton("Red");
        red.setFont(new Font("Arial", Font.BOLD, 10));
        red.setBackground(Color.RED);
        red.setForeground(Color.WHITE);
        red.setBounds(50, 20,70,80);
        red.addActionListener(this);
        wildmenu.add(red);
        blue = new JButton("Blue");
        blue.setBackground(Color.BLUE);
        blue.setForeground(Color.WHITE);
        blue.setBounds(50, 125,70,80);
        blue.setFont(new Font("Arial", Font.BOLD, 10));
        blue.addActionListener(this);
        wildmenu.add(blue);
        yellow = new JButton("Yellow");
        yellow.setBackground(Color.YELLOW);
        yellow.setForeground(Color.BLACK);
        yellow.setBounds(50,230,70,80);
        yellow.setFont(new Font("Arial", Font.BOLD, 10));
        yellow.addActionListener(this);
        wildmenu.add(yellow);
        green = new JButton("Green");
        green.setBackground(Color.GREEN);
        green.setForeground(Color.BLACK);
        green.setBounds(50, 335,70,80);
        green.setFont(new Font("Arial", Font.BOLD, 10));
        green.addActionListener(this);
        wildmenu.add(green);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(wildmenu);
        this.pack();

    }

    /**
     * The method that takes cares of the click on different JButtons.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == red) {
            // Handle case WildF -> WildF
            if (chosenCard.getContent() == UnoCard.Content.WildF && checkWildF == true) {
                if (playOnce == true) {
                    // Has already play card in current turn.
                    JLabel message = new JLabel("Can not play two cards in one round!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    // Play the WildF
                    server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                    view.setCards();
                    server.handleCard(chosenCard);
                    server.setValidColor(UnoCard.Color.Red);
                    view.setPlay();
                    view.getGameState().setIcon(null);
                    view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Red_Card.jpg"));
                    this.dispose();
                }
            } else if (chosenCard.getContent() != UnoCard.Content.WildF && checkWildF == true) {
                // Have WildF but did not play WildF
                JLabel message = new JLabel("You have WildF, please play WildF");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            } else {
                // Chosen Card is Wild but previous Card is WildF
                if (server.getValidContent() == UnoCard.Content.WildF) {
                    JLabel message = new JLabel("Invalid Card to play!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    // Have played once in turn.
                    if (playOnce == true) {
                        JLabel message = new JLabel("Can not play two cards in one round!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    } else {
                        // Plays the Wild Card.
                        server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                        view.setCards();
                        server.handleCard(chosenCard);
                        server.setValidColor(UnoCard.Color.Red);
                        view.setPlay();
                        view.getGameState().setIcon(null);
                        view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Red_Card.jpg"));
                        this.dispose();
                    }
                }
            }
        } else if (e.getSource() == blue) {
            if (chosenCard.getContent() == UnoCard.Content.WildF && checkWildF == true) {
                if (playOnce == true) {
                    JLabel message = new JLabel("Can not play two cards in one round!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                    view.setCards();
                    server.handleCard(chosenCard);
                    server.setValidColor(UnoCard.Color.Blue);
                    view.setPlay();
                    view.getGameState().setIcon(null);
                    view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Blue_Card.jpg"));
                    this.dispose();
                }
            } else if (chosenCard.getContent() != UnoCard.Content.WildF && checkWildF == true) {
                JLabel message = new JLabel("You have DrawTwo, please play DrawTwo");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            } else {
                if (server.getValidContent() == UnoCard.Content.WildF) {
                    JLabel message = new JLabel("Invalid Card to play!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    if (playOnce == true) {
                        JLabel message = new JLabel("Can not play two cards in one round!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    } else {
                        server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                        view.setCards();
                        server.handleCard(chosenCard);
                        server.setValidColor(UnoCard.Color.Blue);
                        view.setPlay();
                        view.getGameState().setIcon(null);
                        view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Blue_Card.jpg"));
                        this.dispose();
                    }
                }
            }
        } else if (e.getSource() == yellow) {
            if (chosenCard.getContent() == UnoCard.Content.WildF && checkWildF == true) {
                if (playOnce == true) {
                    JLabel message = new JLabel("Can not play two cards in one round!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                    view.setCards();
                    server.handleCard(chosenCard);
                    server.setValidColor(UnoCard.Color.Yellow);
                    view.setPlay();
                    view.getGameState().setIcon(null);
                    view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Yellow_Card.jpg"));
                    this.dispose();
                }
            } else if (chosenCard.getContent() != UnoCard.Content.WildF && checkWildF == true) {
                JLabel message = new JLabel("You have DrawTwo, please play DrawTwo");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            } else {
                if (server.getValidContent() == UnoCard.Content.WildF) {
                    JLabel message = new JLabel("Invalid Card to play!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    if (playOnce == true) {
                        JLabel message = new JLabel("Can not play two cards in one round!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    } else {
                        server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                        view.setCards();
                        server.handleCard(chosenCard);
                        server.setValidColor(UnoCard.Color.Yellow);
                        view.setPlay();
                        view.getGameState().setIcon(null);
                        view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Yellow_Card.jpg"));
                        this.dispose();
                    }
                }
            }
        } else if (e.getSource() == green) {
            if (chosenCard.getContent() == UnoCard.Content.WildF && checkWildF == true) {
                if (playOnce == true) {
                    JLabel message = new JLabel("Can not play two cards in one round!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                    view.setCards();
                    server.handleCard(chosenCard);
                    server.setValidColor(UnoCard.Color.Green);
                    view.setPlay();
                    view.getGameState().setIcon(null);
                    view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Green_Card.jpg"));
                    this.dispose();
                }
            } else if (chosenCard.getContent() != UnoCard.Content.WildF && checkWildF == true) {
                JLabel message = new JLabel("You have DrawTwo, please play DrawTwo");
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            } else {
                if (server.getValidContent() == UnoCard.Content.WildF) {
                    JLabel message = new JLabel("Invalid Card to play!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                } else {
                    if (playOnce == true) {
                        JLabel message = new JLabel("Can not play two cards in one round!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    } else {
                        server.getPlayerByIndex(server.getCurrentPlayer()).playCard(choice + 1);
                        view.setCards();
                        server.handleCard(chosenCard);
                        server.setValidColor(UnoCard.Color.Green);
                        view.setPlay();
                        view.getGameState().setIcon(null);
                        view.getGameState().setIcon(new ImageIcon("./UnoCardImages/Green_Card.jpg"));
                        this.dispose();
                    }
                }
            }
        }
    }

}
