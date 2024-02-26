package it.unisa.fia.connect4.model;

public class Move {

    private int player;
    private int column;

    public Move(int player, int column) {
        this.column= column;
        this.player = player;
    }

    public int getColumn() {
        return column;
    }

    public void setColumnPosition(int column) {
        this.column = column;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Move{" +
                "player=" + player +
                ", column=" + column +
                '}';
    }
}
