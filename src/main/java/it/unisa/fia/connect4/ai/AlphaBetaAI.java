package it.unisa.fia.connect4.ai;

import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.List;

public class AlphaBetaAI implements AIPlayer{

    private int searchDepth;

    public AlphaBetaAI(int searchDepth) {
        this.searchDepth = searchDepth;
    }


    @Override
    public Move generateMove(Board board, int maxPlayer) {

        if(board.getNextPlayer() != maxPlayer || board.isGameFinished())
            return null;

        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;

        ScoredMove bestMove =  maxValue(board, maxPlayer, searchDepth - 1,  alpha, beta);
        return bestMove.getMove();

    }


    private ScoredMove maxValue(Board board, int maxPlayer, int depth, double alpha, double beta){

        if(cutoffTest(board, depth))
            return new ScoredMove(null, Evaluator.evaluation(board, maxPlayer));

        List<Move> moves = board.generateNextMoves();
        double maxScore = Integer.MIN_VALUE;
        ScoredMove bestMove = new ScoredMove(null, maxScore);

        for(Move move : moves){

            board.makeMove(move);
            ScoredMove scoredMove = minValue(board, maxPlayer, depth - 1, alpha, beta);

            if(scoredMove.getValue() > bestMove.getValue()) {
                bestMove.setValue(scoredMove.getValue());
                bestMove.setMove(move);
            }

            board.undoLastMove(move);

            if(bestMove.getValue() > alpha)
                alpha = bestMove.getValue();

            if(bestMove.getValue() >= beta)
                return bestMove;

        }

        return bestMove;

    }

    private ScoredMove minValue(Board board, int maxPlayer, int depth, double alpha, double beta){

        if(cutoffTest(board, depth))
            return new ScoredMove(null, Evaluator.evaluation(board, maxPlayer));

        List<Move> moves = board.generateNextMoves();
        double minScore = Integer.MAX_VALUE;
        ScoredMove bestMove = new ScoredMove(null, minScore);

        for(Move move : moves){

            board.makeMove(move);
            ScoredMove scoredMove = maxValue(board, maxPlayer, depth - 1, alpha, beta);

            if(scoredMove.getValue() < bestMove.getValue()) {
                bestMove.setValue(scoredMove.getValue());
                bestMove.setMove(move);
            }

            board.undoLastMove(move);

            if(bestMove.getValue() < beta)
                beta = bestMove.getValue();

            if(bestMove.getValue() <= alpha)
                return bestMove;


        }

        return bestMove;

    }


    private boolean cutoffTest(Board board, int depth){

        return depth <= 0 || board.isGameFinished();

    }


    public int getSearchDepth() {
        return searchDepth;
    }
}
