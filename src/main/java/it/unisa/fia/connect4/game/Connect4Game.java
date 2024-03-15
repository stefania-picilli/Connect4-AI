package it.unisa.fia.connect4.game;

import it.unisa.fia.connect4.ai.AIPlayer;
import it.unisa.fia.connect4.model.Board;
import it.unisa.fia.connect4.model.Move;

import java.util.List;

public class Connect4Game {

    private Board board;
    private AIPlayer aiPlayer;
    private int userPlayer;

    public Connect4Game(AIPlayer aiPlayer, int rows, int columns){

        this.board = new Board(rows, columns);
        this.aiPlayer = aiPlayer;
        userPlayer = Board.NONE;

    }

    public boolean choosePlayer(int chosenPlayer){

        if(hasGameStarted())
            return false;

        if(chosenPlayer != Board.PLAYER_A && chosenPlayer != Board.PLAYER_B)
            return false;

        userPlayer = chosenPlayer;
        return true;

    }

    public int getUserPlayer(){

        return userPlayer;

    }

    public int getNextPlayer(){

        return board.getNextPlayer();

    }

    public boolean makeMove(int column){

        if(!hasGameStarted())
            return false;

        Move move = new Move(userPlayer, column);
        return board.makeMove(move);

    }


    public boolean generateNextMove(){

        if(!hasGameStarted())
            return false;

        Move move;

        if(userPlayer == Board.PLAYER_A)
            move = aiPlayer.generateMove(board, Board.PLAYER_B);
        else
            move = aiPlayer.generateMove(board, Board.PLAYER_A);

        if(move == null)
            return false;

        return board.makeMove(move);

    }


    public boolean hasGameStarted(){

        return userPlayer != Board.NONE;

    }


    public int[][] getBoardMatrix(){

        return board.getBoard().clone();

    }

    public List<Move> getNextMoves(){

        return board.generateNextMoves();

    }

    public boolean isGameFinished(){

        return board.isGameFinished();

    }

    public int getWinner(){

        return board.getWinner();

    }

    public void printBoard(){

        board.printBoard();

    }


}
