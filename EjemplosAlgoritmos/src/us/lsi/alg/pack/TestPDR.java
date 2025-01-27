package us.lsi.alg.pack;

import java.util.Locale;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPDR {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		Data.data("ficheros/pack/pack1.txt");
		PackVertex e1 = PackVertex.first();
		
		EGraph<PackVertex,PackEdge> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Last)
				.type(Type.Min)
				.vertexWeight(v->(double)v.nc())
				.edgeWeight(e->e.weight())
				.heuristic(Heuristica::heuristic)
				.build();
		
		GreedyOnGraph<PackVertex,PackEdge> rr = GreedyOnGraph.of(graph);
	
		GraphPath<PackVertex, PackEdge> path = rr.path();
		SolucionPack sp = SolucionPack.of(path);
	
		Integer nc = sp.nc();
		System.out.println("Valor Voraz = "+nc);
		System.out.println("Heuristica = "+Heuristica.heuristic(e1, v->v.goal(), null));
		
		PDR<PackVertex, PackEdge,?> ms = PDR.of(
				graph,
				null,
				(double) nc,
				path,false);	
		
		ms.search();
		System.out.println(String.format("Volumen contenedor = %d,Numero de Objetos = %d",
				Data.volumenContenedor,Data.n));
		System.out.println(sp);

	}

}
