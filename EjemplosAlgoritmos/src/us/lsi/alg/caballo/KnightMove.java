package us.lsi.alg.caballo;

public enum KnightMove {
    UP_LEFT(-2, -1), UP_RIGHT(-2, 1), RIGHT_UP(-1, 2), RIGHT_DOWN(1, 2),
    DOWN_RIGHT(2, 1), DOWN_LEFT(2, -1), LEFT_DOWN(1, -2), LEFT_UP(-1, -2);

    public final int dRow;
    public final int dCol;

    KnightMove(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public Position move(Position pos) {
        return new Position(pos.row + dRow, pos.col + dCol);
    }
}
