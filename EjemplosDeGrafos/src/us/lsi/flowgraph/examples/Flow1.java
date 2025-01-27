package us.lsi.flowgraph.examples;



import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.flowgraph.FlowData;
import us.lsi.flowgraph.FlowGraph;
import us.lsi.flowgraph.FlowGraphSolution;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;




/**
 * Un ejemplo de red de flujo
 * 
 * @author Miguel Toro
 *
 */
public class Flow1 {
	
	public static void maxFlow() throws IOException {
		FlowGraph g = FlowGraph.newGraph("ficheros/flow3.txt");
		FlowData.graph = g;			
		System.out.println(g.vertexSet().stream()
				.map(v->String.format("%s = %s",v,v.tipo.toString())).collect(Collectors.joining("\n")));
		AuxGrammar.generate(FlowData.class,"models/max_flow.lsi","ficheros/max_flow3.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/max_flow3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		FlowGraphSolution flowSolution = FlowGraphSolution.of(g,solution);
		flowSolution.toDotIndex("ficheros/max_flow3_solution.gv");
	}
	
	public static void minCost() throws IOException {
		FlowGraph g = FlowGraph.newGraph("ficheros/flow3.txt");
		FlowData.graph = g;			
		System.out.println(g.vertexSet().stream()
				.map(v->String.format("%s = %s",v,v.tipo.toString())).collect(Collectors.joining("\n")));
		AuxGrammar.generate(FlowData.class,"models/min_cost.lsi","ficheros/min_cost3.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/min_cost3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		FlowGraphSolution flowSolution = FlowGraphSolution.of(g,solution);
		flowSolution.toDotIndex("ficheros/min_cost3_solution.gv");
	}
	
	public static void minCut() throws IOException {
		FlowGraph g = FlowGraph.newGraph("ficheros/flow3.txt");
		FlowData.graph = g;			
		System.out.println(g.vertexSet().stream()
				.map(v->String.format("%s = %s",v,v.tipo.toString())).collect(Collectors.joining("\n")));
		AuxGrammar.generate(FlowData.class,"models/min_cut.lsi","ficheros/min_cut3.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/min_cut3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	
	/**
	 * @param args Argumentos
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {	
		maxFlow();
	}
	
}
