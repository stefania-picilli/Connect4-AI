package it.unisa.fia.evaluation.opponent;

import it.unisa.fia.connect4.ai.AIPlayer;
import it.unisa.fia.connect4.ai.Evaluator;
import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.Collections;
import java.util.List;

public class GreedyAI implements AIPlayer {

    @Override
    public Move generateMove(Board board, int player) {

        if(board == null || board.isGameFinished() || player != board.getNextPlayer())
            return null;

        List<Move> moves = board.generateNextMoves();
        Collections.shuffle(moves);

        double maxValue = - Double.MAX_VALUE;
        Move bestMove = null;

        for(Move move : moves){

            board.makeMove(move);

            double value = Evaluator.evaluation(board, player);

            if(value > maxValue){

                maxValue = value;
                bestMove = move;

            }

            board.undoLastMove(move);

        }

        return bestMove;

    }


    public int getSearchDepth(){

        return 1;

    }

}
