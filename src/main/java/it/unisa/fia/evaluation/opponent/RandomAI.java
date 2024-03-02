package it.unisa.fia.evaluation.opponent;

import it.unisa.fia.connect4.ai.AIPlayer;
import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.List;
import java.util.Random;

public class RandomAI implements AIPlayer {

    @Override
    public Move generateMove(Board board, int player) {

        if(board == null || board.isGameFinished() || player != board.getNextPlayer())
            return null;

        List<Move> moves = board.generateNextMoves();

        Random rand = new Random();
        int index = rand.nextInt(moves.size());

        return moves.get(index);

    }

    public int getSearchDepth(){

        return 0;

    }

}
