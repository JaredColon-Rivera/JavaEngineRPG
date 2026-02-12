package main;

import javax.swing.JFrame;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException, FontFormatException {

        // INITIAL WINDOW CONFIGURATIONS
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("RPG Adventure");

        // CREATE GAME WINDOW
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // START THE GAME
        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
