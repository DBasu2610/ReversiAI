package game;

import utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ReversiBoard {
    private final int[][] board;
    private int currentPlayer = Constants.BLACK; // start with BLACK


    public ReversiBoard() {
        board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        initializeBoard();
    }

    // Copy constructor
    public ReversiBoard(ReversiBoard other) {
        board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            System.arraycopy(other.board[i], 0, board[i], 0, Constants.BOARD_SIZE);
        }
    }

    private void initializeBoard() {
        int mid = Constants.BOARD_SIZE / 2;
        board[mid - 1][mid - 1] = Constants.WHITE;
        board[mid][mid] = Constants.WHITE;
        board[mid - 1][mid] = Constants.BLACK;
        board[mid][mid - 1] = Constants.BLACK;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Constants.BLACK) ? Constants.WHITE : Constants.BLACK;
    }
    

    public int getCell(int row, int col) {
        return board[row][col];
    }

    public boolean applyMove(Move move) {
        return applyMove(move.getRow(), move.getCol());
    }

    public boolean applyMove(int row, int col) {
        int currentPlayer = getCurrentPlayer();
        List<int[]> toFlip = getFlippableDiscs(row, col, currentPlayer);
        if (board[row][col] != Constants.EMPTY || toFlip.isEmpty()) {
            return false;
        }
        board[row][col] = currentPlayer;
        for (int[] disc : toFlip) {
            board[disc[0]][disc[1]] = currentPlayer;
        }
        return true;
    }

    public List<Move> getValidMoves(int player) {
        List<Move> moves = new ArrayList<>();
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                if (board[row][col] == Constants.EMPTY && !getFlippableDiscs(row, col, player).isEmpty()) {
                    moves.add(new Move(row, col));
                }
            }
        }
        return moves;
    }

    public boolean isGameOver() {
        return getValidMoves(Constants.BLACK).isEmpty() && getValidMoves(Constants.WHITE).isEmpty();
    }

    public int getScore(int player) {
        int score = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == player) score++;
            }
        }
        return score;
    }

    private List<int[]> getFlippableDiscs(int row, int col, int player) {
        List<int[]> discsToFlip = new ArrayList<>();
        int opponent = (player == Constants.BLACK) ? Constants.WHITE : Constants.BLACK;

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] dir : directions) {
            int r = row + dir[0], c = col + dir[1];
            List<int[]> tempFlips = new ArrayList<>();

            while (isOnBoard(r, c) && board[r][c] == opponent) {
                tempFlips.add(new int[]{r, c});
                r += dir[0];
                c += dir[1];
            }

            if (isOnBoard(r, c) && board[r][c] == player) {
                discsToFlip.addAll(tempFlips);
            }
        }

        return discsToFlip;
    }

    private boolean isOnBoard(int row, int col) {
        return row >= 0 && row < Constants.BOARD_SIZE && col >= 0 && col < Constants.BOARD_SIZE;
    }

    public int getCurrentPlayer() {
        int black = 0, white = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == Constants.BLACK) black++;
                else if (cell == Constants.WHITE) white++;
            }
        }
        return black <= white ? Constants.BLACK : Constants.WHITE;
    }

    public int[][] getBoard() {
        return board;
    }
}
