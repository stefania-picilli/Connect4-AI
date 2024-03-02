package it.unisa.fia.evaluation.model;

import it.unisa.fia.connect4.ai.AIPlayer;

import java.io.Serializable;

public class MatchResult implements Serializable {

    //risultati di una partita tra due agent
    //memorizzare lista di risultati in un file

    private AIPlayer testedAgent;
    private AIPlayer opponentAgent;

    private int depth;
    private int rows;
    private int columns;

    private boolean testedAgentFirst;
    private boolean testedAgentWon;
    private boolean wasTie;

    private int movesGenerated;

    private double moveGenMeanTime;

    private double moveGenMinTime;
    private int minTimeMoveNumber;

    private double moveGenMaxTime;
    private int maxTimeMoveNumber;


    public MatchResult() {
    }

    public MatchResult(AIPlayer testedAgent, AIPlayer opponentAgent, int depth, int rows, int columns, boolean testedAgentFirst, boolean testedAgentWon, boolean wasTie, int movesGenerated, double moveGenMeanTime, double moveGenMinTime, int minTimeMoveNumber, double moveGenMaxTime, int maxTimeMoveNumber) {
        this.testedAgent = testedAgent;
        this.opponentAgent = opponentAgent;
        this.depth = depth;
        this.rows = rows;
        this.columns = columns;
        this.testedAgentFirst = testedAgentFirst;
        this.testedAgentWon = testedAgentWon;
        this.wasTie = wasTie;
        this.movesGenerated = movesGenerated;
        this.moveGenMeanTime = moveGenMeanTime;
        this.moveGenMinTime = moveGenMinTime;
        this.minTimeMoveNumber = minTimeMoveNumber;
        this.moveGenMaxTime = moveGenMaxTime;
        this.maxTimeMoveNumber = maxTimeMoveNumber;
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

    public boolean isTestedAgentWon() {
        return testedAgentWon;
    }

    public void setTestedAgentWon(boolean testedAgentWon) {
        this.testedAgentWon = testedAgentWon;
    }

    public boolean isWasTie() {
        return wasTie;
    }

    public void setWasTie(boolean wasTie) {
        this.wasTie = wasTie;
    }

    public int getMovesGenerated() {
        return movesGenerated;
    }

    public void setMovesGenerated(int movesGenerated) {
        this.movesGenerated = movesGenerated;
    }

    public double getMoveGenMeanTime() {
        return moveGenMeanTime;
    }

    public void setMoveGenMeanTime(double moveGenMeanTime) {
        this.moveGenMeanTime = moveGenMeanTime;
    }

    public double getMoveGenMinTime() {
        return moveGenMinTime;
    }

    public void setMoveGenMinTime(double moveGenMinTime) {
        this.moveGenMinTime = moveGenMinTime;
    }

    public int getMinTimeMoveNumber() {
        return minTimeMoveNumber;
    }

    public void setMinTimeMoveNumber(int minTimeMoveNumber) {
        this.minTimeMoveNumber = minTimeMoveNumber;
    }

    public double getMoveGenMaxTime() {
        return moveGenMaxTime;
    }

    public void setMoveGenMaxTime(double moveGenMaxTime) {
        this.moveGenMaxTime = moveGenMaxTime;
    }

    public int getMaxTimeMoveNumber() {
        return maxTimeMoveNumber;
    }

    public void setMaxTimeMoveNumber(int maxTimeMoveNumber) {
        this.maxTimeMoveNumber = maxTimeMoveNumber;
    }

    public boolean isTestedAgentFirst() {
        return testedAgentFirst;
    }

    public void setTestedAgentFirst(boolean testedAgentFirst) {
        this.testedAgentFirst = testedAgentFirst;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "testedAgent=" + testedAgent +
                ", opponentAgent=" + opponentAgent +
                ", depth=" + depth +
                ", rows=" + rows +
                ", columns=" + columns +
                ", testedAgentFirst=" + testedAgentFirst +
                ", testedAgentWon=" + testedAgentWon +
                ", wasTie=" + wasTie +
                ", movesGenerated=" + movesGenerated +
                ", moveGenMeanTime=" + moveGenMeanTime +
                ", moveGenMinTime=" + moveGenMinTime +
                ", minTimeMoveNumber=" + minTimeMoveNumber +
                ", moveGenMaxTime=" + moveGenMaxTime +
                ", maxTimeMoveNumber=" + maxTimeMoveNumber +
                '}';
    }
}
