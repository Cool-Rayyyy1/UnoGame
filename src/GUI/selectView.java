package GUI;

import Uno.Game;
import Uno.UnoCard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The selectView class is the JFrame that displays and handles the UnoCard after player clicks some non WildF or Wild Card.
 * Besides, it handles and revised some variables for GameView.
 */
public class selectView extends JFrame implements ActionListener {
    /**
     * The JFrame components and variables that are necessary for this class.
     */
    private JPanel menu;
    private JLabel Image;
    private JButton useCard;
    private JButton cancel;
    private JLabel topCard;

    private UnoCard chosenCard;
    private Game server;
    private boolean checkDraw;
    private boolean playOnce;
    private int choice;
    private ArrayList<JButton> cardButtons;
    private GameView gamest;

    /**
     * The Constructor for the selectView class.
     * @param card The UnoCards that is going to be displayed on this JFrame.
     * @param server The Game that starts the game.
     * @param index The index of the UnoCard Arraylist which is also the index of the buttons.
     * @param buttons The arraylist of the card buttons
     * @param gameview The GameView which calls the current selectView.
     * @param gameStatus The JLabel which indicates the current status of this Game.
     * @param check The boolean variable which indicates whether we should check currentCard is Draw.
     * @param play The boolean variable which indicates whether player has already played once.
     */
    public selectView(UnoCard card, Game server, int index, ArrayList<JButton> buttons, GameView gameview, JLabel gameStatus, boolean check, boolean play) {
        //Initiate the variable to the initial state
        chosenCard = card;
        this.server = server;
        choice = index;
        cardButtons = buttons;
        gamest = gameview;
        topCard = gameStatus;
        playOnce = play;
        checkDraw = check;

        // Initial the JFrame Components.
        menu = new JPanel();
        menu.setLayout(null);
        String color = card.getColor().getValue();
        int content = card.getContent().getValue();
        String new_content = String.valueOf(content);
        ImageIcon statusImage = new ImageIcon("./UnoCardImages/"+ color + "_" + new_content +".png");
        Image = new JLabel(statusImage);
        Image.setBounds(175, 80, 150,270);
        menu.add(Image);
        useCard = new JButton("Use Card");
        useCard.setBounds(50,400, 160,50);
        useCard.setFont(new Font("Arial", Font.BOLD, 20));
        useCard.addActionListener(this);
        menu.add(useCard);
        cancel = new JButton("Cancel");
        cancel.setBounds(300,400, 160,50);
        cancel.setFont(new Font("Arial", Font.BOLD, 20));
        cancel.addActionListener(this);
        menu.add(cancel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menu);
        this.pack();
    }

    /**
     * Method for getting the chosenCard.
     */
    public UnoCard getUnoCard() {
        return chosenCard;
    }

    /**
     * Method for getting the server.
     */
    public Game getServer() {
        return server;
    }

    /**
     * Method for getting the GameView.
     */
    public GameView getView() {
        return gamest;
    }

    /**
     * The method for taking care of the clicks of JButtons of the selectView.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == useCard) {
            //Check useCard button
            if (checkDraw == true) {
                //This turn Draw must be played.
                if (chosenCard.getContent() == UnoCard.Content.Draw) {
                    // The UnoCard that has been passed is Draw
                    if (playOnce == true) {
                        // Has played once
                        JLabel message = new JLabel("Can not play two cards in one round!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    } else {
                        // Play the Draw
                        this.getServer().getPlayerByIndex(this.getServer().getCurrentPlayer()).playCard(choice + 1);
                        this.getView().setCards();
                        this.getView().setPlay();
                        this.getServer().handleCard(this.getUnoCard());
                        this.getView().updateGameState();
                        this.dispose();
                    }
                } else {
                    // Have Draw and must play Draw but the player does not choose to Play Draw.
                    JLabel message = new JLabel("You have DrawTwo, please play DrawTwo");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                }
            } else {
                // Check the validity of the chosen Card.
                if (this.getServer().checkValidCard(this.getUnoCard()) == true) {
                    //could play this card
                    if (playOnce == true) {
                        // Has already played once.
                        JLabel message = new JLabel("Can not play two cards in one round!");
                        message.setFont(new Font("Arial", Font.BOLD, 48));
                        JOptionPane.showMessageDialog(null,message);
                    } else {
                        // Play the Card.
                        this.getServer().getPlayerByIndex(this.getServer().getCurrentPlayer()).playCard(choice + 1);
                        this.getView().setCards();
                        this.getServer().handleCard(this.getUnoCard());
                        this.getView().setPlay();
                        this.getView().updateGameState();
                        this.dispose();
                    }
                } else {
                    // Invalid Card.
                    JLabel message = new JLabel("Invalid Card to play!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null,message);
                }
            }
        } else if (e.getSource() == cancel) {
            // Give up playing this card.
            this.dispose();
        }
    }

}
