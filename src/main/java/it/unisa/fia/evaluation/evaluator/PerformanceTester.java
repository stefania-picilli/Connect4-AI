package it.unisa.fia.evaluation.evaluator;

import it.unisa.fia.connect4.ai.AIPlayer;
import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;
import it.unisa.fia.evaluation.model.MatchResult;
import it.unisa.fia.evaluation.model.TournamentResult;

public class PerformanceTester {

    public final static int PRINT_NONE = 0;
    public final static int PRINT_RESULT = 1; //solo toString di ogni match
    public final static int PRINT_BOARD = 2; //toString e board finale di ogni match
    public final static int PRINT_ALL = 3; //ogni mossa di ogni match


    public static double moveGenerationTime(Board board, AIPlayer ai, int aiPlayer){

        if(board == null || board.isGameFinished() || aiPlayer != board.getNextPlayer())
            return -1;

        long startTime = System.nanoTime();
        Move move = ai.generateMove(board, aiPlayer);
        long estimatedTime = System.nanoTime() - startTime;

        //se la mossa generata non è valida
        if(!board.makeMove(move))
            return -1;

        //conversione da nanosecondi a millisecondi
        return estimatedTime / 1000000.0;


    }


    public static MatchResult calculateMatchResult(Board board, AIPlayer player, AIPlayer opponent, boolean playerFirst, int print){

        MatchResult match = new MatchResult();

        match.setTestedAgent(player);
        match.setOpponentAgent(opponent);
        match.setColumns(board.getColumns());
        match.setRows(board.getRows());
        match.setDepth(player.getSearchDepth());

        match.setMovesGenerated(0);
        match.setMoveGenMinTime(Double.MAX_VALUE);
        match.setMoveGenMaxTime(- Double.MAX_VALUE);
        match.setMaxTimeMoveNumber(0);
        match.setMinTimeMoveNumber(0);

        if(print == PRINT_BOARD || print == PRINT_ALL) {

            if(playerFirst)
                System.out.println("\n\nPartita iniziata: " + player.getClass().getSimpleName() + " VS " + opponent.getClass().getSimpleName());
            else
                System.out.println("\n\nPartita iniziata: " + opponent.getClass().getSimpleName() + " VS " + player.getClass().getSimpleName());

        }


        if(playerFirst){

            match.setTestedAgentFirst(true);

            while(!board.isGameFinished()){

                calculateMove(board, player, Board.PLAYER_A, match, true, print);

                if(board.isGameFinished())
                    break;

                calculateMove(board, opponent, Board.PLAYER_B, match, false, print);

            }

        }else{

            match.setTestedAgentFirst(false);

            while(!board.isGameFinished()){

                calculateMove(board, opponent, Board.PLAYER_A, match, false, print);

                if(board.isGameFinished())
                    break;

                calculateMove(board, player, Board.PLAYER_B, match, true, print);


            }



        }

        defineWinner(board, match, playerFirst);


        if(print == PRINT_BOARD || print == PRINT_ALL) {

            System.out.println("\n\n\nPartita terminata");
            board.printBoard();
            System.out.println();

        }

        if(print == PRINT_RESULT || print == PRINT_BOARD || print == PRINT_ALL)
            System.out.println(match);

        if(print == PRINT_BOARD || print == PRINT_ALL) {

            if((board.getWinner() == Board.PLAYER_A && playerFirst) || (board.getWinner() == Board.PLAYER_B && !playerFirst))
                System.out.println("Vincitore = " + player.getClass().getSimpleName() + "\n\n\n");
            else if((board.getWinner() == Board.PLAYER_A && !playerFirst) || (board.getWinner() == Board.PLAYER_B && playerFirst))
                System.out.println("Vincitore = " + opponent.getClass().getSimpleName() + "\n\n\n");

        }



        return match;


    }


    private static void updateMatch(double time, MatchResult match){

        match.setMovesGenerated(match.getMovesGenerated() + 1);

        double newMean = calculateMean(match.getMoveGenMeanTime(), time, match.getMovesGenerated());
        match.setMoveGenMeanTime(newMean);

        if(time > match.getMoveGenMaxTime()){

            match.setMoveGenMaxTime(time);
            match.setMaxTimeMoveNumber(match.getMovesGenerated());

        }

        if(time < match.getMoveGenMinTime()){

            match.setMoveGenMinTime(time);
            match.setMinTimeMoveNumber(match.getMovesGenerated());

        }



    }


    public static void calculateMove(Board board, AIPlayer aiPlayer, int player, MatchResult match, boolean isPlayer, int print){

        if(print == PRINT_ALL)
            System.out.println("\n\n\nTocca a " + aiPlayer.getClass().getSimpleName() + ":");

        double time = PerformanceTester.moveGenerationTime(board, aiPlayer, player);

        if(isPlayer)
            updateMatch(time, match);

        if(print == PRINT_ALL) {

            System.out.print("Tempo impiegato per calcolare mossa = " + time + " ms\n");
            board.printBoard();

        }

    }

    private static void defineWinner(Board board, MatchResult match, boolean playerFirst){

        if(board.isGameFinished() && board.getWinner() == Board.NONE){

            match.setTestedAgentWon(false);
            match.setWasTie(true);
            return;

        }

        match.setWasTie(false);

        if(playerFirst)
            match.setTestedAgentWon(board.getWinner() == Board.PLAYER_A);
        else
            match.setTestedAgentWon(board.getWinner() == Board.PLAYER_B);


    }



    public static TournamentResult calculateTournamentResult(int rows, int column, AIPlayer player, AIPlayer opponent, int halfRounds, int print){

        TournamentResult tournament = new TournamentResult();

        tournament.setTestedAgent(player);
        tournament.setOpponentAgent(opponent);
        tournament.setColumns(column);
        tournament.setRows(rows);
        tournament.setDepth(player.getSearchDepth());
        tournament.setNumberOfMatches(halfRounds*2);

        tournament.setNumberOfVictories(0);
        tournament.setNumberOfTies(0);
        tournament.setMeanMovesGenerated(0);
        tournament.setMoveGenMeanTime(0);
        tournament.setMeanMoveGenMaxTime(- Double.MAX_VALUE);
        tournament.setMeanMoveGenMinTime(Double.MAX_VALUE);

        MatchResult matchResult = null;

        if(print == PRINT_RESULT || print == PRINT_BOARD || print == PRINT_ALL){

            System.out.println("\nTORNEO: " + player.getClass().getSimpleName() + " VS " + opponent.getClass().getSimpleName());
            System.out.println("Rounds: " + (halfRounds*2) + "\n");

        }

        //inizia player
        for(int i = 0; i < halfRounds; i++){

            matchResult = calculateMatchResult(new Board(rows, column), player, opponent, true, print);
            updateTournament(tournament, matchResult, i + 1);

        }

        //inizia opponent
        for(int i = 0; i < halfRounds; i++){

            matchResult = calculateMatchResult(new Board(rows, column), player, opponent, false, print);
            updateTournament(tournament, matchResult, i + 1);

        }

        if(print == PRINT_BOARD || print == PRINT_ALL)
            System.out.println("\n\nTorneo terminato");

        if(print == PRINT_RESULT)
            System.out.println();

        if(print == PRINT_RESULT || print == PRINT_BOARD || print == PRINT_ALL)
            System.out.println("Risultato finale: " + tournament);

        return tournament;

    }


    private static void updateTournament(TournamentResult tournament, MatchResult match, int round){

        if(match.isTestedAgentWon())
            tournament.setNumberOfVictories(tournament.getNumberOfVictories() + 1);

        if(match.isWasTie())
            tournament.setNumberOfTies(tournament.getNumberOfTies() + 1);


        double meanMovesGenerated = calculateMean(tournament.getMeanMovesGenerated(), match.getMovesGenerated(), round);
        tournament.setMeanMovesGenerated(meanMovesGenerated);

        double moveGenMeanTime = calculateMean(tournament.getMoveGenMeanTime(), match.getMoveGenMeanTime(), round);
        tournament.setMoveGenMeanTime(moveGenMeanTime);

        double meanMoveGenMaxTime = calculateMean(tournament.getMeanMoveGenMaxTime(), match.getMoveGenMaxTime(), round);
        tournament.setMeanMoveGenMaxTime(meanMoveGenMaxTime);

        double meanMoveGenMinTime = calculateMean(tournament.getMeanMoveGenMinTime(), match.getMoveGenMinTime(), round);
        tournament.setMeanMoveGenMinTime(meanMoveGenMinTime);

    }




    private static double calculateMean(double oldMean, double newValue, int newN){

        return oldMean + ((newValue - oldMean)/newN);

    }

}
