package it.unisa.fia.connect4.ai;

import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.List;

public class AlphaBetaAI implements AIPlayer{

    //private final static int DEPTH = 10;

    private int searchDepth;

    public AlphaBetaAI(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    @Override
    public Move generateMove(Board board, int maxPlayer) {

        if(board.getNextPlayer() != maxPlayer || board.isGameFinished())
            return null;

        Move bestMove = null;
        double bestScore = Integer.MIN_VALUE;
        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;

        List<Move> moves = board.generateNextMoves();

        for(Move move : moves){

            board.makeMove(move);
            double score = minValue(board, maxPlayer, searchDepth - 1, alpha, beta);

            if(score > bestScore) {

                bestScore = score;
                bestMove = move;

            }

            board.undoLastMove(move);

            if(bestScore >= beta)
                return bestMove;

            if(bestScore > alpha)
                alpha = bestScore;

        }

        return bestMove;

    }


    private double maxValue(Board board, int maxPlayer, int depth, double alpha, double beta){

        if(cutoffTest(board, depth))
            return Evaluator.evaluation(board, maxPlayer);

        List<Move> moves = board.generateNextMoves();
        double maxScore = Integer.MIN_VALUE;

        for(Move move : moves){

            board.makeMove(move);
            double score = minValue(board, maxPlayer, depth - 1, alpha, beta);

            if(score > maxScore)
                maxScore = score;

            board.undoLastMove(move);

            if(maxScore > alpha)
                alpha = maxScore;

            if(maxScore >= beta)
                return maxScore;

        }

        return maxScore;

    }

    private double minValue(Board board, int maxPlayer, int depth, double alpha, double beta){

        if(cutoffTest(board, depth))
            return Evaluator.evaluation(board, maxPlayer);

        List<Move> moves = board.generateNextMoves();
        double minScore = Integer.MAX_VALUE;

        for(Move move : moves){

            board.makeMove(move);
            double score = maxValue(board, maxPlayer, depth - 1, alpha, beta);

            if(score < minScore)
                minScore = score;

            board.undoLastMove(move);

            if(minScore < beta)
                beta = minScore;

            if(minScore <= alpha)
                return minScore;


        }

        return minScore;

    }


    private boolean cutoffTest(Board board, int depth){

        return depth <= 1 || board.isGameFinished();

    }


    public int getSearchDepth() {
        return searchDepth;
    }
}
