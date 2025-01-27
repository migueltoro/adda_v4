package us.lsi.alg.floydD;

import java.util.List;

import org.jgrapht.graph.GraphWalk;

import us.lsi.graphs.SimpleEdge;
import us.lsi.hypergraphsD.HyperEdgeD;
import us.lsi.path.EGraphPath;

public record FloydEdgeD(FloydVertexD source, List<FloydVertexD> targets, Boolean action)
		implements HyperEdgeD<FloydVertexD, FloydEdgeD, Boolean, GraphWalk<Integer,SimpleEdge<Integer>>> {

	public static FloydEdgeD of(FloydVertexD source, List<FloydVertexD> targets, Boolean action) {
		return new FloydEdgeD(source, targets, action);
	}

	@Override
	public Double weight(List<Double> solutions) {
		Double weight = null;
		if (!action()) weight = solutions.get(0);
		else weight = solutions.get(0) + solutions.get(1);
		return weight;
	}

	@Override
	public FloydEdgeD me() {
		return this;
	}

	@Override
	public GraphWalk<Integer, SimpleEdge<Integer>> solution(List<GraphWalk<Integer, SimpleEdge<Integer>>> solutions) {
		GraphWalk<Integer, SimpleEdge<Integer>> gp;
		if (!action())
			gp = solutions.get(0);
		else
			gp = solutions.get(0).concat(solutions.get(1),g->EGraphPath.weight(g));
		return gp;
	}

	@Override
	public String toString() {
		return this.action()?"T":"F";
	}

}
