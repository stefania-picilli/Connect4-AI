package it.unisa.fia.connect4.game;

import it.unisa.fia.connect4.ai.AlphaBetaWithMoveOrderingAI;
import it.unisa.fia.connect4.model.Board;

import java.util.Scanner;

public class CommandLineInterface {

    public static void main(String[] args){

        int userChoice = choosePlayer();
        Connect4Game game = new Connect4Game(new AlphaBetaWithMoveOrderingAI(11), 6, 7);
        game.choosePlayer(userChoice);

        System.out.println("\n\n\n\nPARTITA INIZIATA");
        printSeparationLine();

        game.printBoard();

        while(!game.isGameFinished()){

            if(userChoice == Board.PLAYER_A){

                userMove(game);

                if(game.isGameFinished())
                    break;

                aiMove(game);

            }else{

                aiMove(game);

                if(game.isGameFinished())
                    break;

                userMove(game);

            }

        }


        printResult(game, userChoice);


    }


    private static void userMove(Connect4Game game){

        Scanner scan = new Scanner(System.in);

        while(true) {

            try {

                System.out.println("\n\nIl tuo turno");
                printSeparationLine();
                System.out.print("Inserire colonna (0-6): ");

                int nextColumn = scan.nextInt();

                if (nextColumn >= 0 && nextColumn <= 6) {

                    game.makeMove(nextColumn);
                    game.printBoard();
                    break;

                }


            } catch (Exception e) {

                scan.nextLine();

            }

            System.out.println();
            printSeparationLine();
            System.out.println("Input non valido. Riprova");
            printSeparationLine();

        }

    }

    private static void aiMove(Connect4Game game){

        System.out.println("\nTurno dell'IA");
        printSeparationLine();

        game.generateNextMove();
        game.printBoard();

    }


    private static int choosePlayer(){

        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println();

        while(true){

            System.out.println("Scegli la tua pedina: " + Board.PLAYER_A_PIECE + " o " + Board.PLAYER_B_PIECE);
            System.out.println("1. Scrivi 1 per " + Board.PLAYER_A_PIECE + " (fa la prima mossa)");
            System.out.println("2. Scrivi 2 per " + Board.PLAYER_B_PIECE + " (fa la seconda mossa)");

            printSeparationLine();

            try {

                System.out.print("La tua pedina: ");

                choice = scan.nextInt();

                if (choice == 1) {

                    System.out.println();
                    printSeparationLine();
                    System.out.println("Hai scelto " + Board.PLAYER_A_PIECE );
                    printSeparationLine();
                    System.out.println();
                    return Board.PLAYER_A;

                } else if (choice == 2) {

                    System.out.println();
                    printSeparationLine();
                    System.out.println("Hai scelto " + Board.PLAYER_B_PIECE);
                    printSeparationLine();
                    System.out.println();
                    return Board.PLAYER_B;

                }

            }catch(Exception e){

                scan.nextLine();

            }


            System.out.println();
            printSeparationLine();
            System.out.println("Input non valido. Riprova");
            printSeparationLine();
            System.out.println();


        }


    }


    private static void printResult(Connect4Game game, int player){

        if(game.getWinner() == player){

            System.out.println("\n\n");
            printSeparationLine();
            System.out.println("HAI VINTO");
            printSeparationLine();
            System.out.println("\n\n");

        }else if(game.getWinner() == Board.NONE){

            System.out.println("\n\n");
            printSeparationLine();
            System.out.println("PAREGGIO");
            printSeparationLine();
            System.out.println("\n\n");

        }else{

            System.out.println("\n\n");
            printSeparationLine();
            System.out.println("HAI PERSO");
            printSeparationLine();
            System.out.println("\n\n");

        }

    }

    private static void printSeparationLine(){

        System.out.println("--------------------------------------------");

    }
}
