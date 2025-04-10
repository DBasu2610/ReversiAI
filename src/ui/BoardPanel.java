package ui;

import game.Move;
import game.ReversiBoard;
import utils.Constants;

import javax.swing.*;

import ai.AIPlayer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BoardPanel extends JPanel {
    private final ReversiBoard board;
    private final AIPlayer ai = new AIPlayer(Constants.WHITE); // AI plays WHITE


    public BoardPanel() {
        setPreferredSize(new Dimension(640, 640));
        setBackground(new Color(30, 130, 76)); // Green
        this.board = new ReversiBoard();

        addMouseListener(new MouseAdapter() {
            @Override
           
public void mousePressed(MouseEvent e) {
    int cellSize = getWidth() / Constants.BOARD_SIZE;
    int col = e.getX() / cellSize;
    int row = e.getY() / cellSize;

    if (board.applyMove(row, col)) {
        board.switchPlayer(); // Switch to AI
        repaint();

        Timer aiTimer = new Timer(300, evt -> {
            if (!board.isGameOver()) {
                Move aiMove = ai.getBestMove(new ReversiBoard(board));
                if (aiMove != null) {
                    board.applyMove(aiMove.getRow(), aiMove.getCol());
                    board.switchPlayer(); // Back to human
                    repaint();
                }
            }

            // ✅ Game Over Check AFTER AI move
            if (board.isGameOver()) {
                int blackScore = board.getScore(Constants.BLACK);
                int whiteScore = board.getScore(Constants.WHITE);

                String message;
                if (blackScore > whiteScore) message = "You win! 🖤";
                else if (whiteScore > blackScore) message = "AI wins! ⚪";
                else message = "It's a draw!";

                JOptionPane.showMessageDialog(BoardPanel.this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

            }

            ((Timer) evt.getSource()).stop();
        });
        aiTimer.setRepeats(false);
        aiTimer.start();
    }
}

        });
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = getWidth();
        int cellSize = size / Constants.BOARD_SIZE;

        // Draw grid lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= Constants.BOARD_SIZE; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, size);
            g.drawLine(0, i * cellSize, size, i * cellSize);
        }

        // Draw pieces
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                int value = board.getCell(row, col);
                if (value == Constants.BLACK) {
                    g.setColor(Color.BLACK);
                    g.fillOval(col * cellSize + 5, row * cellSize + 5, cellSize - 10, cellSize - 10);
                } else if (value == Constants.WHITE) {
                    g.setColor(Color.WHITE);
                    g.fillOval(col * cellSize + 5, row * cellSize + 5, cellSize - 10, cellSize - 10);
                }
            }
        }
    }
}
