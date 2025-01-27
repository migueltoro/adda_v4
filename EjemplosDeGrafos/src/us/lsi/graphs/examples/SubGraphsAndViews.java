package us.lsi.graphs.examples;



import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.String2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.CompleteGraphView;

public class SubGraphsAndViews {

	
	public static void main(String[] args) {
		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph("ficheros/andalucia.txt", 
				Ciudad::ofFormat,
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Carretera::km);	
		
		Graph<Ciudad, Carretera> graph2 = CompleteGraphView.of(graph,()->Carretera.of(1000.));
		
		HamiltonianCycleAlgorithm<Ciudad, Carretera> a = new HeldKarpTSP<>();
		GraphPath<Ciudad, Carretera> r = a.getTour(graph2);
			
		String2.toConsole(r.getEdgeList(), "Camino");	
		
		GraphColors.<Ciudad,Carretera>toDot(graph,"ficheros/andaluciaSpanningTree.gv",
				x->String.format("%s",x.nombre()),
				x->String.format("%.sf",x.km()),
				v->GraphColors.color(Color.black),
				e->GraphColors.styleIf(Style.bold,r.getEdgeList().contains(e)));
		
	}

}
