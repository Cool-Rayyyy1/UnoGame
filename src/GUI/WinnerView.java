package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * The last frame of this game to indicate the winner and ends the Game
 */
public class WinnerView extends JFrame{

    /**
     * The JFrame Components for the WinnerView.
     */
    private JPanel winnerMenu;
    private JLabel statement1;
    private JLabel statement2;
    private String winnerName;

    /**
     * The constructor for the Winner view
     * @param name The String variable holds the name of the winner player.
     */
    public WinnerView(String name) {
        //Initial components.
        winnerMenu = new JPanel();
        winnerMenu.setLayout(null);
        winnerName = name;
        statement1 = new JLabel("Game over!");
        statement1.setFont(new Font("Arial", Font.BOLD, 24));
        statement1.setBounds(400,300, 200, 200);
        winnerMenu.add(statement1);
        statement2 = new JLabel(winnerName + " is winner!");
        statement2.setFont(new Font("Arial", Font.BOLD, 24));
        statement2.setBounds(400,320, 200,200);
        winnerMenu.add(statement2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(winnerMenu);
        this.pack();
    }

    /**
     * The main function to run and display this view.
     */
    public static void main(String[] args) {
        JFrame frame = new WinnerView("Tony");
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
}
