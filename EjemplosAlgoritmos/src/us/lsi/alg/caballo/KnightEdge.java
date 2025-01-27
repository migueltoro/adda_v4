package us.lsi.alg.caballo;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public class KnightEdge implements SimpleEdgeAction<Position, KnightMove> {
    private final Position source;
    private final Position target;
    private final KnightMove action;

    public KnightEdge(Position source, Position target, KnightMove action) {
        this.source = source;
        this.target = target;
        this.action = action;
    }

    @Override
    public Position source() {
        return source;
    }

    @Override
    public Position target() {
        return target;
    }

    @Override
    public KnightMove action() {
        return action;
    }

    @Override
    public Double weight() {
        return 1.0;
    }
}
