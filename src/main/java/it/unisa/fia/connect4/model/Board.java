package it.unisa.fia.connect4.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    public static final int PLAYER_A = 1;
    public static final int PLAYER_B = 2;
    public static final int NONE = 0;

    public static final String PLAYER_A_PIECE = "W";
    public static final String PLAYER_B_PIECE = "0";


    private int[][] board;
    private int nextPlayer;
    private int rows;
    private int columns;
    private int[] nextDropPosition;

    private boolean gameFinished;
    private int winner;


    public Board(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        this.board = new int[rows][columns];
        this.nextPlayer = PLAYER_A;
        this.winner = NONE;
        this.gameFinished = false;

        this.nextDropPosition = new int[columns];
        for(int j = 0; j < columns; j++)
            nextDropPosition[j] = rows - 1;

    }

    //aggiugere costruttore vuoto o con board già occupata, se necessario (ad es. per trasposizioni(?))


    public int[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[] getNextDropPosition() {
        return nextDropPosition;
    }

    public int getNextPlayer(){
        return nextPlayer;
    }

    public int getWinner(){
        return winner;
    }

    public boolean isGameFinished(){
        return gameFinished;
    }

    public int getElement(int i, int j){
        return board[i][j];
    }


    public boolean makeMove(Move move){

        if(gameFinished)
            return false;

        if(!isValidMove(move))
            return false;

        int player = move.getPlayer();
        int column = move.getColumn();

        int i = nextDropPosition[column];
        int j = column;

        board[i][j] = player;
        nextDropPosition[column]--;


        if(hasPlayerWon(player, i, j)){

            gameFinished = true;
            winner = player;

        }else if(isBoardFull()){
            gameFinished = true;
        }else{
            changePlayer(player);
        }


        return true;

    }

    private boolean isValidMove(Move move){

        if(move == null)
            return false;

        int player = move.getPlayer();
        int column = move.getColumn();

        if(player != nextPlayer)
            return false;

        if(column < 0 || column >= columns)
            return false;

        //controlla se colonna è piena
        if(nextDropPosition[column] < 0)
            return false;

        return true;

    }

    private void changePlayer(int player){

        if(player == PLAYER_A)
            nextPlayer = PLAYER_B;

        if(player == PLAYER_B)
            nextPlayer = PLAYER_A;

    }


    private boolean hasPlayerWon(int player, int i, int j){

        return horizontalCheck(player, i, j) || verticalCheck(player, i, j) || diagonalCheck(player, i, j);

    }


    private boolean verticalCheck(int player, int i, int j){

        int count = 1;

        for(int t = 1; t <= 3 && i + t < rows; t++){

            if(board[i+t][j] == player){
                count++;
            }else{
                break;
            }

        }

        return count == 4;

    }

    private boolean horizontalCheck(int player, int i, int j){

        int count = 1;

        //verso sinistra
        for(int t = 1; t <= 3 && j - t >= 0; t++){

            if(board[i][j - t] == player)
                count++;
            else
                break;

        }

        //verso destra
        for(int t = 1; t <= 3 && j + t < columns; t++){

            if(board[i][j + t] == player)
                count++;
            else
                break;

        }

        return count >= 4;

    }


    private boolean diagonalCheck(int player, int i, int j){

        return mainDiagonalCheck(player, i, j) || antiDiagonalCheck(player, i, j);

    }

    private boolean mainDiagonalCheck(int player, int i, int j){

        int count = 1;

        //in alto a sinistra
        for(int t = 1; t <= 3 && j - t >= 0 && i - t >= 0; t++){

            if(board[i - t][j - t] == player)
                count++;
            else
                break;

        }

        //in basso a destra
        for(int t = 1; t <= 3 && j + t < columns && i + t < rows; t++){

            if(board[i + t][j + t] == player)
                count++;
            else
                break;

        }

        return count >= 4;


    }

    private boolean antiDiagonalCheck(int player, int i, int j){

        int count = 1;

        //in alto a destra
        for(int t = 1; t <= 3 && j + t < columns && i - t >= 0; t++){

            if(board[i - t][j + t] == player)
                count++;
            else
                break;

        }

        //in basso a sinistra
        for(int t = 1; t <= 3 && j - t >= 0 && i + t < rows; t++){

            if(board[i + t][j - t] == player)
                count++;
            else
                break;

        }

        return count >= 4;

    }



    public boolean isBoardFull(){

       for(int i = 0; i < columns; i++){

           if(board[0][i] == NONE)
               return false;

       }

       return true;

    }



    //rimuove l'ultimo elemento inserito nella colonna (in move), se è del player (in move)
    //la move da passare deve essere la precedente
    public boolean undoLastMove(Move move){

        if(!isUndoValid(move))
            return false;

        if(isGameFinished()){

            winner = NONE;
            gameFinished = false;

        }

        int i = nextDropPosition[move.getColumn()] + 1;
        int j = move.getColumn();
        board[i][j] = NONE;

        nextDropPosition[move.getColumn()]++;
        nextPlayer = move.getPlayer();

        return true;

    }

    private boolean isUndoValid(Move move){

        if(move.getPlayer() != PLAYER_A && move.getPlayer() != PLAYER_B)
            return false;

        if((gameFinished && move.getPlayer() != nextPlayer) || (!gameFinished && move.getPlayer() == nextPlayer))
            return false;

        if(move.getColumn() < 0 || move.getColumn() >= columns)
            return false;

        //controlla se colonna è vuota
        if(nextDropPosition[move.getColumn()] == rows - 1)
            return false;


        return true;

    }


    public List<Move> generateNextMoves(){

        if(isGameFinished())
            return null;

        List<Move> list = new ArrayList<>();

        for(int i = 0; i < columns; i++){

            if(nextDropPosition[i] >= 0)
                list.add(new Move(nextPlayer, i));

        }

        return list;

    }


    public void printBoard(){

        printTopMessage();

        for(int i = 0; i < 6; i++) {
            System.out.println();
            for (int j = 0; j < 7; j++) {
                System.out.print(" | ");

                if(board[i][j] == Board.PLAYER_A)
                    System.out.print("W");
                else if(board[i][j] == Board.PLAYER_B)
                    System.out.print("O");
                else
                    System.out.print(" ");

            }

        }

        System.out.println();

        for (int j = 0; j < 7; j++) {
            System.out.print(" | ");
            System.out.print("-");
        }

        System.out.println();

        for (int j = 0; j < 7; j++) {
            System.out.print(" | ");
            System.out.print(j);
        }

        System.out.println("\n");

    }

    private void printTopMessage(){

        if(!isGameFinished()){

            System.out.print("\n\nGame = NOT FINISHED");
            System.out.print("  /  ");
            System.out.println("Next move = " + getPiece(nextPlayer));

        }else{

            if(winner == NONE){

                System.out.print("\n\nGame = FINISHED");
                System.out.print("  /  ");
                System.out.println("TIE");

            }else {

                System.out.print("\n\nGame = FINISHED");
                System.out.print("  /  ");
                System.out.println("WINNER = " + getPiece(winner));

            }


        }

    }

    private String getPiece(int player){

        if(player == PLAYER_A)
            return PLAYER_A_PIECE;

        if(player == PLAYER_B)
            return PLAYER_B_PIECE;

        return " ";

    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(board) +
                ", nextPlayer=" + nextPlayer +
                ", rows=" + rows +
                ", columns=" + columns +
                ", nextDropPosition=" + Arrays.toString(nextDropPosition) +
                ", winner=" + winner +
                ", gameFinished=" + gameFinished +
                '}';
    }
}
