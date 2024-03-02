package it.unisa.fia.evaluation.match;

import it.unisa.fia.connect4.ai.AIPlayer;
import it.unisa.fia.connect4.ai.AlphaBetaAI;
import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.evaluation.evaluator.PerformanceTester;
import it.unisa.fia.evaluation.opponent.RandomAI;
import it.unisa.fia.evaluation.model.MatchResult;

public class QuickMatch {

    public static void main(String[] args){

        Board board = new Board(6, 7);
        AIPlayer playerA =  new AlphaBetaAI(9);
        AIPlayer playerB = new RandomAI();

        MatchResult match = PerformanceTester.calculateMatchResult(board, playerA,  playerB, true, PerformanceTester.PRINT_ALL);


    }


}
