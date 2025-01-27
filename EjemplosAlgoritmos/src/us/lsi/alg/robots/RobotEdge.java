package us.lsi.alg.robots;


import us.lsi.graphs.virtual.SimpleEdgeAction;

public record RobotEdge(RobotVertex source, RobotVertex target, Integer action, Double weight) 
		implements SimpleEdgeAction<RobotVertex,Integer> {
	
	public static RobotEdge of(RobotVertex c1, RobotVertex c2, Integer action) {
		return new RobotEdge(c1, c2, action,(double)(c2.x().get(3)-c1.x().get(3)));
	}


}
