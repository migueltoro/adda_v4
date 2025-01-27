package us.lsi.alg.caballo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import us.lsi.graphs.virtual.VirtualVertex;

public class Position implements VirtualVertex<Position, KnightEdge, KnightMove> {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public List<KnightMove> actions() {
        List<KnightMove> moves = new ArrayList<>();
        for (KnightMove move : KnightMove.values()) {
            Position nextPos = move.move(this);
            if (isValidPosition(nextPos)) {
                moves.add(move);
            }
        }
        return moves;
    }

    @Override
    public Position neighbor(KnightMove move) {
        return move.move(this);
    }

    @Override
    public KnightEdge edge(KnightMove move) {
        Position neighbor = neighbor(move);
        return new KnightEdge(this, neighbor, move);
    }

    @Override
    public Boolean goal() {
        return false; // No specific goal, we want to visit all positions
    }

    @Override
    public Boolean goalHasSolution() {
        return false; // Not used in this context
    }

    @Override
    public KnightMove greedyAction() {
        return null; // Not used in this context
    }

    private boolean isValidPosition(Position pos) {
        return pos.row >= 0 && pos.row < 8 && pos.col >= 0 && pos.col < 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
