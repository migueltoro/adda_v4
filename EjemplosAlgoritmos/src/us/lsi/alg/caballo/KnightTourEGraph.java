package us.lsi.alg.caballo;

import us.lsi.graphs.virtual.EGraph;

public class KnightTourEGraph {
    public static EGraph<Position, KnightEdge> createGraph() {
        Position startVertex = new Position(0, 0);
        return EGraph.virtual(startVertex).build();
    }
}
