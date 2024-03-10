package it.unisa.fia.connect4.game;

import it.unisa.fia.connect4.ai.MinimaxAI;
import it.unisa.fia.connect4.model.Board;

import java.util.Scanner;

public class CommandLineInterface {

    private final static String PLAYER_A_PIECE = "X";
    private final static String PLAYER_B_PIECE = "O";

    public static void main(String[] args){

        //ritorna valore piece e non valore player (perchè player_a inizia sempre per primo)
        String playerChosen = choosePlayer();



        /*Connect4Game game = new Connect4Game(new MinimaxAI(), 6, 7);
        game.choosePlayer(Board.PLAYER_A);

        while(!game.isGameFinished()){

            System.out.println("Inserire colonna (0-6): ");
            Scanner scan = new Scanner(System.in);
            int nextColumn = scan.nextInt();

            System.out.println("Mossa giocatore (PLAYER_A) = " + game.makeMove(nextColumn));
            printMatrix(game.getBoardMatrix());

            System.out.println("Mossa ai (PLAYER_B) = " + game.generateNextMove());
            printMatrix(game.getBoardMatrix());

        }*/



    }

    private static String choosePlayer(){

        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println();

        while(true){

            //printSeparationLine();

            System.out.println("Choose your piece: " + PLAYER_A_PIECE + " or " + PLAYER_B_PIECE);
            System.out.println("1. Type 1 for " + PLAYER_A_PIECE);
            System.out.println("2. Type 2 for " + PLAYER_B_PIECE);
            //System.out.println();

            printSeparationLine();

            try {

                System.out.print("Type your choice: ");

                choice = scan.nextInt();

                if (choice == 1) {

                    System.out.println("You have chosen " + PLAYER_A_PIECE);
                    return PLAYER_A_PIECE;

                } else if (choice == 2) {

                    System.out.println("\nYou have chosen " + PLAYER_B_PIECE + "\n\n");
                    return PLAYER_B_PIECE;

                }

            }catch(Exception e){

                scan.nextLine();

            }


            System.out.println();
            printSeparationLine();
            System.out.println("Invalid input. Try again");
            printSeparationLine();
            System.out.println();


        }


    }



    private static void printMatrix(int[][] board){

        for(int i = 0; i < 6; i++) {
            System.out.println();
            for (int j = 0; j < 7; j++) {
                System.out.print(" | ");

                if(board[i][j] == Board.PLAYER_A)
                    System.out.print(PLAYER_A_PIECE);
                else if(board[i][j] == Board.PLAYER_B)
                    System.out.print(PLAYER_B_PIECE);
                else
                    System.out.print(" ");

            }
        }

        System.out.println();

    }


    private static void printSeparationLine(){

        System.out.println("--------------------------------------------");

    }
}
