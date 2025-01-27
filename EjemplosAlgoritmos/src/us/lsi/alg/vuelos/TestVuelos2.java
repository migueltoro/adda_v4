package us.lsi.alg.vuelos;

import java.time.LocalDate;
import java.util.Locale;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.graphs.virtual.VirtualEdgeMG;
import us.lsi.graphs.virtual.VirtualVertexMG;
import us.lsi.path.EGraphPath.PathType;

public class TestVuelos2 {
	
	public static DirectedWeightedMultigraph<String,Vuelo> leeGrafo(String fichero) {
		DirectedWeightedMultigraph<String,Vuelo> graph = GraphsReader.newGraph(fichero, 
				s->s[0], 
			    Vuelo::ofFormat,
				Graphs2::directedWeightedMultigraph, 
				v->v.getDuracion().doubleValue());
		return graph;
	}
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Vuelo.fecha = LocalDate.now();
		DirectedWeightedMultigraph<String,Vuelo> graph = leeGrafo("ficheros/vuelos2.txt");
		System.out.println(graph);
		String end = "Malaga";
		System.out.println("__________________");
		
		
		EGraph<VirtualVertexMG<String, Vuelo>, VirtualEdgeMG<String, Vuelo>> gv = 
				EGraph.virtualMG(graph,"Sevilla",
						v->v.equals(end),PathType.Sum,Type.Min)
				.edgeWeight(e->e.action().getDuracion().doubleValue())
				.vertexPassWeight((v,e1,e2)-> Vuelo.getVertexPassWeight(v.vertex(),e1.action(),e2.action()))
				.heuristic((p1,v,p2)->0.)
				.build();
		
		System.out.println("AStar");
		AStar<VirtualVertexMG<String, Vuelo>, VirtualEdgeMG<String, Vuelo>, Object> ms = 
				AStar.of(gv,p->p,null,null);
		
		GraphPath<String, Vuelo> path = 
				EGraph.pathMG(ms.search().orElse(null),graph);
		System.out.printf("Aeropuertos = %s\n",path.getVertexList());
		System.out.printf("Recorrido = %s\n",Vuelo.vuelos(path.getEdgeList()));
		System.out.printf("Tiempo de Recorrido = %.2f\n",path.getWeight());
		System.out.println("__________________");
		System.out.println("Backtracking");
		BT<VirtualVertexMG<String, Vuelo>, VirtualEdgeMG<String, Vuelo>, Object> bt = BT.of(gv);
		
		GraphPath<String,Vuelo> btp = 
				EGraph.pathMG(bt.search().orElse(null),graph);
		System.out.printf("Aeropuertos = %s\n",btp.getVertexList());
		System.out.printf("Recorrido = %s\n",Vuelo.vuelos(btp.getEdgeList()));
		System.out.printf("Tiempo de Recorrido = %.2f\n",btp.getWeight());
		System.out.println("__________________");
		System.out.println("PDR");
		PDR<VirtualVertexMG<String, Vuelo>, VirtualEdgeMG<String, Vuelo>, Object> pd = PDR.of(gv,null,null,null,true);
		
		GraphPath<String, Vuelo> pdp = 
				EGraph.pathMG(pd.search().orElse(null),graph);
		System.out.printf("Aeropuertos = %s\n",pdp.getVertexList());
		System.out.printf("Recorrido = %s\n",Vuelo.vuelos(pdp.getEdgeList()));
		System.out.printf("Tiempo de Recorrido = %.2f\n",pdp.getWeight());
		
//		Predicate<Pair<String,Integer>> pv = v->pd.optimalPath().get().getVertexList().contains(v);
//		Predicate<Vuelo> pe= e->pd.optimalPath().get().getEdgeList().contains(e);
		
//		GraphColors.toDot(pd.outGraph,"ficheros/vuelosPDRGraph.gv",
//				v->String.format("%s",v),
//				e->e.getHoraDeSalida().toLocalTime().toString(),
//				v->all(colorIf(Color.red,pv.test(v)),styleIf(Style.bold,pv.test(v))),
//				e->all(colorIf(Color.red,pe.test(e)),styleIf(Style.bold,pe.test(e)))
//				);
		
		
	}

}

