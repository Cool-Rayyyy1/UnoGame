package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The BotMenuView class displays the view after choosing to play against AI.
 */
public class BotMenuView extends JFrame implements ActionListener {
    /**
     * The JFrame components and variables that are necessary for this class.
     */
    private JLabel normalBots;
    private JLabel smartBots;
    private JLabel enterPlayerName;
    private JTextField textNormal;
    private JTextField textSmart;
    private JTextField playerName;
    private JButton save;
    private JButton start;
    private JPanel menu;
    private JLabel botImage;
    private String name;
    private int numOfNormal;
    private int numOfSmart;
    private JButton backButton;
    /**
     * The Constructor for the MenuView Class.
     */
    public BotMenuView() {
        //Initial the JFrame Components.
        menu = new JPanel();
        menu.setLayout(null);
        normalBots = new JLabel("Please enter the number of baseline Bots");
        normalBots.setFont(new Font("Arial", Font.BOLD, 25));
        normalBots.setBounds(75,200,500,40);
        menu.add(normalBots);
        smartBots = new JLabel("Please enter the number of custom Bots");
        smartBots.setFont(new Font("Arial", Font.BOLD, 25));
        smartBots.setBounds(75,280,500,40);
        menu.add(smartBots);
        enterPlayerName = new JLabel("Please enter your name");
        enterPlayerName.setFont(new Font("Arial", Font.BOLD, 25));
        enterPlayerName.setBounds(75, 360, 500,40);
        menu.add(enterPlayerName);
        playerName = new JTextField();
        playerName.setBounds(600, 360, 300,40);
        menu.add(playerName);
        textNormal = new JTextField();
        textNormal.setBounds(600,200,300,40);
        menu.add(textNormal);
        textSmart = new JTextField();
        textSmart.setBounds(600,280,300,40);
        menu.add(textSmart);
        save = new JButton("Save");
        save.setBounds(150,450,80,40);
        save.addActionListener(this);
        menu.add(save);
        start = new JButton("Start");
        start.setBounds(650,450,80,40);
        start.addActionListener(this);
        menu.add(start);
        ImageIcon image = new ImageIcon("./UnoCardImages/Bot_Image.png");
        botImage = new JLabel(image);
        botImage.setBounds(250,500,400,400);
        menu.add(botImage);
        backButton = new JButton("Back");
        backButton.setBounds(400,450,80,40);
        backButton.addActionListener(this);
        menu.add(backButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menu);
        this.pack();
    }


    /**
     * Method for taking care of the clicks of the JButtons on the MenuView frame.
     * Helps the players to save their name and start the Game.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            if (textNormal.getText().isEmpty()) {
                JLabel error_message = new JLabel("Please enter the number of normal bots!");
                error_message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, error_message);
            } else if (textSmart.getText().isEmpty()) {
                JLabel error_message = new JLabel("Please enter the number of custom bots!");
                error_message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, error_message);
            } else if (playerName.getText().isEmpty()) {
                JLabel error_message = new JLabel("Please enter your name1");
                error_message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, error_message);
            } else {
                String number1 = textNormal.getText().trim();
                String number2 = textSmart.getText().trim();
                numOfNormal = Integer.parseInt(number1);
                numOfSmart = Integer.parseInt(number2);
                name = playerName.getText().trim();
                if ((numOfNormal + numOfSmart) > 9) {
                    JLabel error_message = new JLabel("Can not have more than 9 bots!");
                    error_message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null, error_message);
                    numOfNormal = 0;
                    numOfSmart = 1;
                } else {
                    JLabel error_message = new JLabel("successfully save!");
                    error_message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null, error_message);
                }
            }
        } else if (e.getSource() == start) {
            if ((numOfNormal + numOfSmart) == 0) {
                JLabel error_message = new JLabel("At least one bot should participate!");
                error_message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, error_message);
            } else {
                this.dispose();
                JFrame frame2 = new BotGameView(numOfNormal, numOfSmart, name);
                frame2.setSize(1460,1000);
                frame2.setVisible(true);
            }
        } else if (e.getSource() == backButton) {
            this.dispose();
            JFrame frame = new StartView();
            frame.setSize(1000,1000);
            frame.setVisible(true);
        }

    }


    /**
     * Method for running the MenuView single file.
     */
    public static void main(String[] args) {
        JFrame frame = new BotMenuView();
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
}
