package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The StartView class which is the first JFrame to begin the Uno Game.
 */
public class StartView extends JFrame implements ActionListener {
    /**
     * The JFrame Components that are necessary for the StartView class.
     */
    private JPanel menu;
    private JLabel label1;
    private JLabel name;
    private ImageIcon uno_image;
    private JButton startPlayer;
    private JButton exit;
    private JButton startBots;

    /**
     * The Constructor for the StartView class
     */
    public StartView() {
        //Initial the JFrame Components.
        menu = new JPanel();
        menu.setLayout(null);
        uno_image = new ImageIcon("./UnoCardImages/UnoCard.png");
        label1 = new JLabel(uno_image);
        label1.setBounds(50,100,486,759);
        menu.add(label1);
        name = new JLabel("The Uno Game");
        name.setFont(new Font("Arial", Font.BOLD, 40));
        name.setBounds(620,150, 300,200);
        menu.add(name);
        exit = new JButton("Exit");
        exit.setBounds(700,700,140,100);
        exit.setBackground(Color.WHITE);
        exit.addActionListener(this);
        menu.add(exit);
        startPlayer = new JButton("Start with players");
        startPlayer.setBounds(700,550,140,100);
        startPlayer.setBackground(Color.WHITE);
        startPlayer.addActionListener(this);
        menu.add(startPlayer);
        startBots = new JButton("Start with Bots");
        startBots.setBounds(700,400,140,100);
        startBots.setBackground(Color.WHITE);
        startBots.addActionListener(this);
        menu.add(startBots);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menu);
        this.pack();
    }

    /**
     * The method that takes cares of the click on different JButtons.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            //Directly quit the game.
            System.exit(0);
        } else if (e.getSource() == startPlayer) {
            //Jumps to the next JFrame.
            JFrame frame1 = new MenuView();
            frame1.setSize(1000,1000);
            frame1.setVisible(true);
            this.dispose();
        } else if (e.getSource() == startBots) {
            //Jumps to the next JFrame.
            JFrame frame1 = new BotMenuView();
            frame1.setSize(1000,1000);
            frame1.setVisible(true);
            this.dispose();
        }
    }

    /**
     * Method for starting the UnoGame.
     */
    public static void main(String[] args) {
        JFrame frame = new StartView();
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
}
