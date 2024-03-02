package it.unisa.fia.evaluation.match;

import it.unisa.fia.connect4.ai.AIPlayer;
import it.unisa.fia.connect4.ai.AlphaBetaAI;
import it.unisa.fia.evaluation.evaluator.PerformanceTester;
import it.unisa.fia.evaluation.opponent.RandomAI;
import it.unisa.fia.evaluation.model.TournamentResult;

public class QuickTournament {

    public static void main(String[] args){

        AIPlayer playerA = new AlphaBetaAI(8);
        AIPlayer playerB = new RandomAI();

        TournamentResult result = PerformanceTester.calculateTournamentResult(6, 7, playerA, playerB, 3, PerformanceTester.PRINT_BOARD);

    }


}
