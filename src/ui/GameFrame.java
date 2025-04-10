package ui;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Reversi AI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new BoardPanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
