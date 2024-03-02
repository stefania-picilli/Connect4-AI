package it.unisa.fia.evaluation.model;

import it.unisa.fia.connect4.ai.AIPlayer;

import java.io.Serializable;

public class TournamentResult implements Serializable {


    private AIPlayer testedAgent;
    private AIPlayer opponentAgent;

    private int depth;
    private int rows;
    private int columns;

    private int numberOfMatches;
    private int numberOfVictories;
    private int numberOfTies;

    private double meanMovesGenerated;

    private double moveGenMeanTime;

    private double meanMoveGenMinTime;
    private double meanMoveGenMaxTime;

    public TournamentResult() {
    }

    public TournamentResult(AIPlayer testedAgent, AIPlayer opponentAgent, int depth, int rows, int columns, int numberOfMatches, int numberOfVictories, int numberOfTies, double meanMovesGenerated, double moveGenMeanTime, double meanMoveGenMinTime, double meanMoveGenMaxTime) {
        this.testedAgent = testedAgent;
        this.opponentAgent = opponentAgent;
        this.depth = depth;
        this.rows = rows;
        this.columns = columns;
        this.numberOfMatches = numberOfMatches;
        this.numberOfVictories = numberOfVictories;
        this.numberOfTies = numberOfTies;
        this.meanMovesGenerated = meanMovesGenerated;
        this.moveGenMeanTime = moveGenMeanTime;
        this.meanMoveGenMinTime = meanMoveGenMinTime;
        this.meanMoveGenMaxTime = meanMoveGenMaxTime;
    }

    public AIPlayer getTestedAgent() {
        return testedAgent;
    }

    public void setTestedAgent(AIPlayer testedAgent) {
        this.testedAgent = testedAgent;
    }

    public AIPlayer getOpponentAgent() {
        return opponentAgent;
    }

    public void setOpponentAgent(AIPlayer opponentAgent) {
        this.opponentAgent = opponentAgent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    public int getNumberOfTies() {
        return numberOfTies;
    }

    public void setNumberOfTies(int numberOfTies) {
        this.numberOfTies = numberOfTies;
    }

    public double getMeanMovesGenerated() {
        return meanMovesGenerated;
    }

    public void setMeanMovesGenerated(double meanMovesGenerated) {
        this.meanMovesGenerated = meanMovesGenerated;
    }

    public double getMoveGenMeanTime() {
        return moveGenMeanTime;
    }

    public void setMoveGenMeanTime(double moveGenMeanTime) {
        this.moveGenMeanTime = moveGenMeanTime;
    }

    public double getMeanMoveGenMinTime() {
        return meanMoveGenMinTime;
    }

    public void setMeanMoveGenMinTime(double meanMoveGenMinTime) {
        this.meanMoveGenMinTime = meanMoveGenMinTime;
    }

    public double getMeanMoveGenMaxTime() {
        return meanMoveGenMaxTime;
    }

    public void setMeanMoveGenMaxTime(double meanMoveGenMaxTime) {
        this.meanMoveGenMaxTime = meanMoveGenMaxTime;
    }

    @Override
    public String toString() {
        return "TournamentResult{" +
                "testedAgent=" + testedAgent +
                ", opponentAgent=" + opponentAgent +
                ", depth=" + depth +
                ", rows=" + rows +
                ", columns=" + columns +
                ", numberOfMatches=" + numberOfMatches +
                ", numberOfVictories=" + numberOfVictories +
                ", numberOfTies=" + numberOfTies +
                ", meanMovesGenerated=" + meanMovesGenerated +
                ", moveGenMeanTime=" + moveGenMeanTime +
                ", meanMoveGenMinTime=" + meanMoveGenMinTime +
                ", meanMoveGenMaxTime=" + meanMoveGenMaxTime +
                '}';
    }
}
