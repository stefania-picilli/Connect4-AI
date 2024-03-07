package it.unisa.fia.connect4.ai;

import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.List;

public class MinimaxAI implements AIPlayer{

    private int searchDepth;

    public MinimaxAI(int searchDepth) {
        this.searchDepth = searchDepth;
    }


    @Override
    public Move generateMove(Board board, int maxPlayer) {

        if(board.getNextPlayer() != maxPlayer || board.isGameFinished())
            return null;

        ScoredMove bestMove =  maxValue(board, maxPlayer, searchDepth - 1);
        return bestMove.getMove();

    }


    private ScoredMove maxValue(Board board, int maxPlayer, int depth){

        if(cutoffTest(board, depth))
            return new ScoredMove(null, Evaluator.evaluation(board, maxPlayer));

        List<Move> moves = board.generateNextMoves();

        double maxScore = Integer.MIN_VALUE;
        ScoredMove bestMove = new ScoredMove(null, maxScore);

        for(Move move : moves){

            board.makeMove(move);

            ScoredMove scoredMove = minValue(board, maxPlayer, depth - 1);

            if(scoredMove.getValue() > bestMove.getValue()) {
                bestMove.setValue(scoredMove.getValue());
                bestMove.setMove(move);
            }

            board.undoLastMove(move);

        }

        return bestMove;

    }

    private ScoredMove minValue(Board board, int maxPlayer, int depth){

        if(cutoffTest(board, depth))
            return new ScoredMove(null, Evaluator.evaluation(board, maxPlayer));

        List<Move> moves = board.generateNextMoves();

        double minScore = Integer.MAX_VALUE;
        ScoredMove bestMove = new ScoredMove(null, minScore);

        for(Move move : moves){

            board.makeMove(move);
            ScoredMove scoredMove =  maxValue(board, maxPlayer, depth - 1);

            if(scoredMove.getValue() < bestMove.getValue()){

                bestMove.setValue(scoredMove.getValue());
                bestMove.setMove(move);

            }

            board.undoLastMove(move);

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
