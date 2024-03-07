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

        List<ScoredMove> sortedMoves = sortMoves(board, board.generateNextMoves());

        double maxScore = Integer.MIN_VALUE;
        ScoredMove bestMove = new ScoredMove(null, maxScore);

        for(ScoredMove move : sortedMoves){

            board.makeMove(move.getMove());
            ScoredMove scoredMove = minValue(board, maxPlayer, depth - 1, alpha, beta);

            if(scoredMove.getValue() > bestMove.getValue()) {
                bestMove.setValue(scoredMove.getValue());
                bestMove.setMove(move.getMove());
            }

            board.undoLastMove(move.getMove());

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

        List<ScoredMove> sortedMoves = sortMoves(board, board.generateNextMoves());

        double minScore = Integer.MAX_VALUE;
        ScoredMove bestMove = new ScoredMove(null, minScore);

        for(ScoredMove move : sortedMoves){

            board.makeMove(move.getMove());
            ScoredMove scoredMove = maxValue(board, maxPlayer, depth - 1, alpha, beta);

            if(scoredMove.getValue() < bestMove.getValue()) {
                bestMove.setValue(scoredMove.getValue());
                bestMove.setMove(move.getMove());
            }

            board.undoLastMove(move.getMove());

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
