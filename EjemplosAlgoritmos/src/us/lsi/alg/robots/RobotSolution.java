package us.lsi.alg.robots;

import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.common.List2;

public record RobotSolution(RobotVertex vertex, List<Integer> actions) {
	
	public static RobotSolution of(GraphPath<RobotVertex,RobotEdge> path) {
		RobotVertex vertex = List2.last(path.getVertexList());
		List<Integer> actions = path.getEdgeList().stream().map(e->e.action()).toList();
		return new RobotSolution(vertex,actions);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
