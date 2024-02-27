package it.unisa.fia.connect4.ai;

import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.List;

public class MinimaxAI implements AIPlayer{

    private final static int DEPTH = 8;

    @Override
    public Move generateMove(Board board, int maxPlayer) {

        //TODO: controlli che devono essere inseriti successivamenta nella classe Game
        if(board.getNextPlayer() != maxPlayer || board.isGameFinished())
            return null;


        Move bestMove = null;
        double bestScore = Integer.MIN_VALUE;
        List<Move> moves = board.generateNextMoves();

        for(Move move : moves){

            board.makeMove(move);
            double score = minValue(board, maxPlayer, DEPTH - 1);

            if(score > bestScore) {

                bestScore = score;
                bestMove = move;

            }

            board.undoLastMove(move);

        }

        return bestMove;

    }


    private double maxValue(Board board, int maxPlayer, int depth){

        if(cutoffTest(board, maxPlayer, depth))
            return Evaluator.evaluation(board, maxPlayer);

        List<Move> moves = board.generateNextMoves();
        double maxScore = Integer.MIN_VALUE;

        for(Move move : moves){

            board.makeMove(move);
            double score = minValue(board, maxPlayer, depth - 1);

            if(score > maxScore)
                maxScore = score;

            board.undoLastMove(move);

        }

        return maxScore;

    }

    private double minValue(Board board, int maxPlayer, int depth){

        if(cutoffTest(board, maxPlayer, depth))
            return Evaluator.evaluation(board, maxPlayer);

        List<Move> moves = board.generateNextMoves();
        double minScore = Integer.MAX_VALUE;

        for(Move move : moves){

            board.makeMove(move);
            double score = maxValue(board, maxPlayer, depth - 1);

            if(score < minScore)
                minScore = score;

            board.undoLastMove(move);

        }

        return minScore;

    }


    private boolean cutoffTest(Board board, int maxPlayer, int depth){

        return depth <= 1 || board.isGameFinished();

    }


}
