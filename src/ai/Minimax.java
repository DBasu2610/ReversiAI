package ai;

import game.ReversiBoard;
import game.Move;
import utils.Constants;

import java.util.List;

public class Minimax {
    private final int maxDepth;
    private final int aiPlayer;
    private final int opponent;

    public Minimax(int aiPlayer, int maxDepth) {
        this.aiPlayer = aiPlayer;
        this.maxDepth = maxDepth;
        this.opponent = (aiPlayer == Constants.BLACK) ? Constants.WHITE : Constants.BLACK;
    }

    public Move findBestMove(ReversiBoard board) {
        List<Move> validMoves = board.getValidMoves(aiPlayer);
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (Move move : validMoves) {
            ReversiBoard copy = new ReversiBoard(board);
            copy.applyMove(move);
            int score = minimax(copy, maxDepth - 1, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int minimax(ReversiBoard board, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (depth == 0 || board.isGameOver()) {
            return evaluateBoard(board);
        }

        int currentPlayer = maximizingPlayer ? aiPlayer : opponent;
        List<Move> moves = board.getValidMoves(currentPlayer);

        if (moves.isEmpty()) {
            return minimax(board, depth - 1, !maximizingPlayer, alpha, beta); // Pass turn
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : moves) {
                ReversiBoard copy = new ReversiBoard(board);
                copy.applyMove(move);
                int eval = minimax(copy, depth - 1, false, alpha, beta);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : moves) {
                ReversiBoard copy = new ReversiBoard(board);
                copy.applyMove(move);
                int eval = minimax(copy, depth - 1, true, alpha, beta);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            return minEval;
        }
    }

    private int evaluateBoard(ReversiBoard board) {
        int aiScore = board.getScore(aiPlayer);
        int opponentScore = board.getScore(opponent);
        return aiScore - opponentScore;
    }
}
