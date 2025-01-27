package us.lsi.flowgraph;

import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.gurobi.GurobiSolution;

public class FlowGraphSolution {
	
	public static FlowGraphSolution of(FlowGraph graph, GurobiSolution solution) {
		Map<FlowEdge, Double> flowEdges = solution.values.entrySet().stream()
				.filter(e->e.getKey().startsWith("y"))
				.map(e->new SimpleEntry<>(graph.edge(e.getKey()),e.getValue()))
				.collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
		Map<FlowVertex, Double> flowVertices = 	solution.values.entrySet().stream()
				.filter(e->e.getKey().startsWith("x"))
				.map(e->new SimpleEntry<>(graph.vertex(e.getKey()),e.getValue()))
				.collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
		return new FlowGraphSolution(graph, flowVertices, flowEdges, solution.objVal);
	}
	
	public static FlowGraphSolution of(FlowGraph graph, 
			Map<FlowVertex, Double> flowVertices,
			Map<FlowEdge, Double> flowEdges, 
			Double goal) {
		return new FlowGraphSolution(graph, flowVertices, flowEdges, goal);
	}
	
	private FlowGraphSolution(FlowGraph graph, Map<FlowVertex, Double> flowVertices, 
							Map<FlowEdge, Double> flowEdges, Double goal) {
		super();
		this.graph = graph;
		this.flowVertices = flowVertices;
		this.flowEdges = flowEdges;
		this.goal = goal;
		this.saturatedEdges = graph
				.edgeSet()
				.stream()
				.filter(e->e.max.equals(flowEdges.get(e)))
				.collect(Collectors.toSet());
		this.saturatedVertices = graph
				.vertexSet()
				.stream()
				.filter(v->v.max.equals(flowVertices.get(v)))
				.collect(Collectors.toSet());
	}
	public final FlowGraph graph;
	public final Map<FlowVertex,Double> flowVertices;
	public final Map<FlowEdge,Double> flowEdges;
	public final Double goal;
	public final Set<FlowEdge> saturatedEdges;
	public final Set<FlowVertex> saturatedVertices;
	
	public void toDot(String file) {
		GraphColors.<FlowVertex, FlowEdge>toDot(this.graph, file, 
				v->v.name+"="+this.flowVertices.get(v).toString(), 
	    		e->e.name+"="+this.flowEdges.get(e).toString(),
		        v->GraphColors.colorIf(Color.red,this.saturatedVertices.contains(v)),
		        e->GraphColors.styleIf(Style.bold,this.saturatedEdges.contains(e)));
	}
	
	public void toDotIndex(String file) {
		GraphColors.<FlowVertex, FlowEdge>toDot(this.graph, file, 
				v->this.graph.vertexIndex(v).toString()+"="+this.flowVertices.get(v).toString(), 
	    		e->e.name+"="+this.flowEdges.get(e).toString(),
		        v->GraphColors.colorIf(Color.red,this.saturatedVertices.contains(v)),
		        e->GraphColors.styleIf(Style.bold,this.saturatedEdges.contains(e)));
	}
}
