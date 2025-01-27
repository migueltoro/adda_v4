package us.lsi.flowgraph;


public class FlowData {
	
	public static FlowGraph graph;
	
	public static void leeDatos(String file) {
		FlowData.graph = FlowGraph.newGraph(file);
		FlowData.graph.integerGraph();
	}
	
	public static Double edgeMax(Integer i, Integer j) {	
		return  graph.maxEdge(i,j);
	}
	
	public static Boolean edgeHasMax(Integer i, Integer j) {	
		return  graph.maxEdge(i,j) < Double.MAX_VALUE;
	}
	
	public static Double edgeMin(Integer i, Integer j) {
		return  graph.minEdge(i,j);
	}
	
	public static Boolean edgHaseMin(Integer i, Integer j) {
		return  graph.minEdge(i,j) > 0.;
	}
	
	public static  Double edgeCost(Integer i, Integer j) {
		 return graph.costEdge(i,j);
	}
	
	public static Boolean containsEdge(Integer i, Integer j) {
		return graph.containsEdge(i,j);
	}
	
	public static Double vertexMax(Integer i) {
		return graph.maxVertex(i);
	}
	
	public static Boolean vertexHasMax(Integer i) {
		return graph.maxVertex(i) < Double.MAX_VALUE;
	}
	
	public static Double vertexMin(Integer i) {
		return graph.minVertex(i);
	}
	
	public static Boolean vertexHasMin(Integer i) {
		return graph.minVertex(i) > 0.;
	}
	
	public static Double vertexCost(Integer i) {
		return graph.costVertex(i);
	}
	
	public static Boolean containsVertex(Integer i) {
		return graph.containsVertex(i);
	}
	
	public static Integer getN() {
		return graph.getN();
	}
	
	public static Boolean isSource(Integer i) {
		return graph.vertex(i).isSource();
	}
	
	public static Boolean isSink(Integer i) {
		return graph.vertex(i).isSink();
	}
	
	public static Boolean isIntermediate(Integer i) {
		return graph.vertex(i).isIntermediate();
	}

}
