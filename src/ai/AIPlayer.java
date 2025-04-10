package ai;

import game.ReversiBoard;
import game.Move;

public class AIPlayer {
    private final int aiPlayer;

    public AIPlayer(int aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public Move getBestMove(ReversiBoard board) {
        Minimax minimax = new Minimax(aiPlayer, 4); // Depth 4 is decent
        return minimax.findBestMove(board);
    }
}
