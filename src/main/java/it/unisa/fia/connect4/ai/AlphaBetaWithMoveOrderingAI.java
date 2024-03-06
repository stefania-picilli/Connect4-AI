package it.unisa.fia.connect4.ai;

import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class AlphaBetaWithMoveOrderingAI implements AIPlayer{

    private int searchDepth;

    public AlphaBetaWithMoveOrderingAI(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    public Move generateMove(Board board, int maxPlayer) {

        if(board.getNextPlayer() != maxPlayer || board.isGameFinished())
            return null;

        Move bestMove = null;
        double bestScore = Integer.MIN_VALUE;
        double alpha = Integer.MIN_VALUE;
        double beta = Integer.MAX_VALUE;

        List<Move> moves = board.generateNextMoves();
        List<ScoredMove> sortedMoves = sortMoves(board, moves);

        for(ScoredMove move : sortedMoves){

            board.makeMove(move.getMove());
            double score = minValue(board, maxPlayer, searchDepth - 1, alpha, beta);

            if(score > bestScore) {

                bestScore = score;
                bestMove = move.getMove();

            }

            board.undoLastMove(move.getMove());

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
        List<ScoredMove> sortedMoves = sortMoves(board, moves);

        double maxScore = Integer.MIN_VALUE;

        for(ScoredMove move : sortedMoves){

            board.makeMove(move.getMove());
            double score = minValue(board, maxPlayer, depth - 1, alpha, beta);

            if(score > maxScore)
                maxScore = score;

            board.undoLastMove(move.getMove());

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
        List<ScoredMove> sortedMoves = sortMoves(board, moves);

        double minScore = Integer.MAX_VALUE;

        for(ScoredMove move : sortedMoves){

            board.makeMove(move.getMove());
            double score = maxValue(board, maxPlayer, depth - 1, alpha, beta);

            if(score < minScore)
                minScore = score;

            board.undoLastMove(move.getMove());

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

    @Override
    public int getSearchDepth() {
        return searchDepth;
    }


    private static List<ScoredMove> sortMoves(Board board, List<Move> moves){

        List<ScoredMove> sortedMoves = new ArrayList<ScoredMove>();

        for(Move move : moves){

            double score = Evaluator.evaluateMove(board, move);
            insertScoredMove(sortedMoves, new ScoredMove(move, score));

        }

        return sortedMoves;

    }



    private static void insertScoredMove(List<ScoredMove> sortedMoves, ScoredMove move){

        for(int i = 0; i < sortedMoves.size(); i++){

            if(move.getValue() >= sortedMoves.get(i).getValue()) {

                sortedMoves.add(i, move);
                return;

            }

        }

        sortedMoves.add(move);


    }


}
