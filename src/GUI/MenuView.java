package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The MenuView which displays the players and shows them how to fill their names and begin the UnoGame.
 */
public class MenuView extends JFrame implements ActionListener {
    /**
     * The JFrame components and variables that are necessary for this class.
     */
    private JPanel menu;
    private JLabel theme;
    private JLabel names;
    private JTextField player_names;
    private JButton save;
    private JButton start;
    private JLabel player1;
    private JLabel player2;
    private JLabel player3;
    private JLabel player4;
    private JLabel player5;
    private JLabel player6;
    private JLabel player7;
    private JLabel player8;
    private JLabel player9;
    private JLabel player10;
    private JButton backButton;
    public ArrayList<String> playerIDs;

    /**
     * The Constructor for the MenuView Class.
     */
    public MenuView() {
        //Initial the variables to the initial state.
        playerIDs = new ArrayList<>();

        //Initial the JFrame Components.
        menu = new JPanel();
        menu.setLayout(null);
        theme = new JLabel("Please add the name of the player");
        theme.setFont(new Font("Times New Roman", Font.BOLD,30));
        theme.setBounds(250,100,600,50);
        menu.add(theme);
        names = new JLabel("Name of the added player");
        names.setFont(new Font("Times New Roman", Font.BOLD,25));
        names.setBounds(150, 200, 500, 40 );
        menu.add(names);
        player_names = new JTextField();
        player_names.setBounds(450,200,300,40);
        menu.add(player_names);
        save = new JButton("Save");
        save.setBounds(150,650,80,40);
        save.addActionListener(this);
        menu.add(save);
        start = new JButton("Start");
        start.setBounds(650,650,80,40);
        start.addActionListener(this);
        menu.add(start);
        player1 = new JLabel("");
        player1.setBounds(250, 250,100,50);
        player1.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player1);
        player2 = new JLabel("");
        player2.setBounds(600, 250,100,50);
        player2.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player2);
        player3 = new JLabel("");
        player3.setBounds(250, 320,100,50);
        player3.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player3);
        player4 = new JLabel("");
        player4.setBounds(600, 320,100,50);
        player4.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player4);
        player5 = new JLabel("");
        player5.setBounds(250, 390,100,50);
        player5.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player5);
        player6 = new JLabel("");
        player6.setBounds(600, 390,100,50);
        player6.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player6);
        player7 = new JLabel("");
        player7.setBounds(250, 460,100,50);
        player7.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player7);
        player8 = new JLabel("");
        player8.setBounds(600, 460,100,50);
        player8.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player8);
        player9 = new JLabel("");
        player9.setBounds(250, 530,100,50);
        player9.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player9);
        player10 = new JLabel("");
        player10.setBounds(600, 530,100,50);
        player10.setFont(new Font("Times New Roman", Font.BOLD,20));
        menu.add(player10);
        backButton = new JButton("Back");
        backButton.setBounds(425,650,80,40);
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
            //save button
            if (player_names.getText().isEmpty()) {
                JLabel error_message = new JLabel("Please enter a name!");
                error_message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, error_message);
            } else {
                String name = player_names.getText().trim();
                playerIDs.add(name);
                if (playerIDs.size() == 1) {
                    player1.setText(playerIDs.get(0));
                } else if (playerIDs.size() == 2) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                } else if (playerIDs.size() == 3) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                } else if (playerIDs.size() == 4) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                    player4.setText(playerIDs.get(3));
                } else if (playerIDs.size() == 5) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                    player4.setText(playerIDs.get(3));
                    player5.setText(playerIDs.get(4));
                } else if (playerIDs.size() == 6) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                    player4.setText(playerIDs.get(3));
                    player5.setText(playerIDs.get(4));
                    player6.setText(playerIDs.get(5));
                } else if (playerIDs.size() == 7) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                    player4.setText(playerIDs.get(3));
                    player5.setText(playerIDs.get(4));
                    player6.setText(playerIDs.get(5));
                    player7.setText(playerIDs.get(6));
                } else if (playerIDs.size() == 8) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                    player4.setText(playerIDs.get(3));
                    player5.setText(playerIDs.get(4));
                    player6.setText(playerIDs.get(5));
                    player7.setText(playerIDs.get(6));
                    player8.setText(playerIDs.get(7));
                } else if (playerIDs.size() == 9) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                    player4.setText(playerIDs.get(3));
                    player5.setText(playerIDs.get(4));
                    player6.setText(playerIDs.get(5));
                    player7.setText(playerIDs.get(6));
                    player8.setText(playerIDs.get(7));
                    player9.setText(playerIDs.get(8));
                } else if (playerIDs.size() == 10) {
                    player1.setText(playerIDs.get(0));
                    player2.setText(playerIDs.get(1));
                    player3.setText(playerIDs.get(2));
                    player4.setText(playerIDs.get(3));
                    player5.setText(playerIDs.get(4));
                    player6.setText(playerIDs.get(5));
                    player7.setText(playerIDs.get(6));
                    player8.setText(playerIDs.get(7));
                    player9.setText(playerIDs.get(8));
                    player10.setText(playerIDs.get(9));
                }
                if ( playerIDs.size() > 0 && playerIDs.size() < 11) {
                    JLabel error_message = new JLabel("successfully save!");
                    error_message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null, error_message);
                    player_names.setText("");
                }
                if (playerIDs.size() >= 11) {
                    JLabel error_message = new JLabel("Can not have more than 10 players!");
                    error_message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null, error_message);
                    player_names.setText("");
                }
            }
        } else if (e.getSource() == start) {
            //Unsuccessfully start the UnoGame.
            if (playerIDs.size() == 1 || playerIDs.size() == 0) {
                JLabel error_message = new JLabel("At least two player should participate the game!");
                error_message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, error_message);
                player_names.setText("");
            } else {
                //Successfully starts the GameView page and closes the current JFrame.
                this.dispose();
                String[] new_playerIds = new String[playerIDs.size()];
                for (int i = 0; i < playerIDs.size(); i++) {
                    new_playerIds[i] = playerIDs.get(i);
                }
                // Create a new GameView class, pass in the players' names and start the games.
                JFrame frame2 = new GameView(new_playerIds);
                frame2.setSize(1460,1000);
                frame2.setVisible(true);
            }
        } else if (e.getSource() == backButton) {
            this.dispose();
            JFrame frame2 = new StartView();
            frame2.setSize(1000,1000);
            frame2.setVisible(true);
        }
    }

    /**
     * Method for running the MenuView single file.
     */
    public static void main(String[] args) {
        JFrame frame = new MenuView();
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

}
