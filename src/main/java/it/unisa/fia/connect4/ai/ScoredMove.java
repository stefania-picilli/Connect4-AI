package it.unisa.fia.connect4.ai;

import it.unisa.fia.connect4.model.Move;

public class ScoredMove {

    private Move move;
    private double value;


    public ScoredMove(Move move, double value) {
        this.move = move;
        this.value = value;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
