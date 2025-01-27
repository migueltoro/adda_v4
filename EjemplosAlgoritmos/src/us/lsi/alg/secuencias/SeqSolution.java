package us.lsi.alg.secuencias;

import org.jgrapht.GraphPath;

import java.util.List;
import java.util.stream.Collectors;

public class SeqSolution {
	
	public static SeqSolution of(GraphPath<SeqVertex, SeqEdge> path) {
		return new SeqSolution(path);
	}

	List<SeqEdge> edges;


	private SeqSolution(GraphPath<SeqVertex, SeqEdge> path) {
		super();
		this.edges = path.getEdgeList();
	}


	@Override
	public String toString() {
		return this.edges.stream()
				.filter(e->!e.action().equals(SeqAction.m))
				.map(e->String.format("(%s,%d)",e.action(),e.source().index()))
				.collect(Collectors.joining(",","{","}"));
	}
	

}
