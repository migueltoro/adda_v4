package us.lsi.graphs.examples;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.String2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class LeerFichero {
	
	public static record CV(Integer id, String a, Integer b, Boolean c) {
		public static Boolean toBoolean(String s) {
			return switch(s) {
			case "true" -> true;
			case "false" -> false;
			default -> false;
			};
		}
		public static CV of(String[] formato) {
			return new CV(Integer.parseInt(formato[0]),formato[1],
					Integer.parseInt(formato[2]),toBoolean(formato[3]));
		}
	}
	
	public static record CE(String s, Integer i) {
		public static CE of(String[] formato) {
			return new CE(formato[2],Integer.parseInt(formato[3]));
		}
	}
	
	public static Graph<CV, CE> leeGrafo(String file) {
		SimpleWeightedGraph<CV, CE> graph = GraphsReader.newGraph(file, CV::of, CE::of,
				Graphs2::simpleWeightedGraph,e->1.5);
		return graph;
	}
	
	public static void main(String[] args) {
		Graph<CV, CE> graph = leeGrafo("ficheros/datos.txt");
		String2.toConsole("%s",graph);
		CE edge = graph.edgeSet().stream().toList().get(0);
		String2.toConsole("%.2f",graph.getEdgeWeight(edge));
	}

}
