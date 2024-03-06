package it.unisa.fia.connect4.ai;

import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.List;

public class Evaluator {

    /*private static final int ONE_DISC_VALUE = 10;
    private static final int TWO_DISC_VALUE = 40;
    private static final int THREE_DISC_VALUE = 200;*/

    public static final int ONE_DISC_VALUE = 10;
    public static final int TWO_DISC_VALUE = 50;
    public static final int THREE_DISC_VALUE = 200;

    public static double evaluation(Board board, int maxPlayer){

        int minPlayer;

        if(maxPlayer == Board.PLAYER_A)
            minPlayer = Board.PLAYER_B;
        else
            minPlayer = Board.PLAYER_A;


        //i pesi da usare, per stimare il guadagno atteso di max, cambiano sulla base di chi deve fare la prossima mossa nello stato raggiunto

        if(board.getNextPlayer() == maxPlayer)
            return evaluateForPlayer(board, maxPlayer) - (0.4 * evaluateForPlayer(board, minPlayer));

        return evaluateForPlayer(board, maxPlayer) - (0.6 * evaluateForPlayer(board, minPlayer));

    }

    private static int evaluateForPlayer(Board board, int player){

        if(board.isGameFinished()){

            //aggiungere valore q per gestire il caso di grandi dimensioni della matrice
            //(solo se valori m e n della board possono essere cambiati)

            if(board.getWinner() == player)
                return 5000; //VALORE VITTORIA

            if(board.getWinner() == Board.NONE)
                return 2000; //VALORE PAREGGIO

            return 0; //VALORE SCONFITTA

        }

        return calculateValue(board, player);

    }

    private static int calculateValue(Board board, int player){

        //individua i buchi
        List<Move> moves = board.generateNextMoves();

        int boardValue = 0;

        //calcola valore di ogni buco
        for(Move move : moves){

            int j = move.getColumn();
            int i = board.getNextDropPosition()[j];

            boardValue += verticalValue(board, player, i, j) + horizontalValue(board, player, i, j) + diagonalValue(board, player, i, j);

        }

        return boardValue;

    }

    private static int verticalValue(Board board, int player, int i, int j){

        int count = 0;

        for(int t = 1; t <= 3 && i + t < board.getRows(); t++) {

            if (board.getBoard()[i + t][j] == player) {
                count++;
            } else {
                break;
            }

        }

        return countValue(count);

    }

    private static int horizontalValue(Board board, int player, int i, int j){

        int count = 0;

        //verso sinistra
        for(int t = 1; t <= 3 && j - t >= 0; t++){

            if(board.getBoard()[i][j - t] == player)
                count++;
            else
                break;

        }

        //verso destra
        for(int t = 1; t <= 3 && j + t < board.getColumns(); t++){

            if(board.getBoard()[i][j + t] == player)
                count++;
            else
                break;

        }


        return countValue(count);

    }

    private static int diagonalValue(Board board, int player, int i, int j){

        return countValue(mainDiagonalValue(board, player, i, j)) + countValue(antiDiagonalValue(board, player, i, j));

    }


    private static int mainDiagonalValue(Board board, int player, int i, int j){

        int count = 0;

        //in alto a sinistra
        for(int t = 1; t <= 3 && j - t >= 0 && i - t >= 0; t++){

            if(board.getBoard()[i - t][j - t] == player)
                count++;
            else
                break;

        }

        //in basso a destra
        for(int t = 1; t <= 3 && j + t < board.getColumns() && i + t < board.getRows(); t++){

            if(board.getBoard()[i + t][j + t] == player)
                count++;
            else
                break;

        }

        return count;


    }

    private static int antiDiagonalValue(Board board, int player, int i, int j){

        int count = 0;

        //in alto a destra
        for(int t = 1; t <= 3 && j + t < board.getColumns() && i - t >= 0; t++){

            if(board.getBoard()[i - t][j + t] == player)
                count++;
            else
                break;

        }

        //in basso a sinistra
        for(int t = 1; t <= 3 && j - t >= 0 && i + t < board.getRows(); t++){

            if(board.getBoard()[i + t][j - t] == player)
                count++;
            else
                break;

        }

        return count;

    }


    public static double evaluateMove(Board board, Move move){

        int player = move.getPlayer();
        int j = move.getColumn();
        int i = board.getNextDropPosition()[j];

        return verticalValue(board, player, i, j) + horizontalValue(board, player, i, j) + diagonalValue(board, player, i, j);

    }


    private static int countValue(int count){

        if(count == 1)
            return ONE_DISC_VALUE;
        if(count == 2)
            return TWO_DISC_VALUE;
        if(count >= 3)
            return THREE_DISC_VALUE;

        return 0;

    }

}
