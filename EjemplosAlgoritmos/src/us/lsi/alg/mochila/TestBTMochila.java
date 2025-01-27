package us.lsi.alg.mochila;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.mochila.datos.DatosMochila;
import us.lsi.path.EGraphPath.PathType;



public class TestBTMochila {


	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMochila.iniDatos("ficheros/mochila/objetosMochila.txt");
		MochilaVertexI.capacidadInicial = 78;
		MochilaVertex e1 = MochilaVertexI.initialVertex();
		
		EGraph<MochilaVertex, MochilaEdge> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Sum)
				.type(Type.Max)
				.heuristic(MochilaHeuristic::heuristic1)
				.build();	
		
		GreedyOnGraph<MochilaVertex, MochilaEdge> rr = GreedyOnGraph.of(graph);
		
		GraphPath<MochilaVertex, MochilaEdge> path = rr.path();
		
//		SolucionMochila s0 = MochilaVertex.getSolucion(path);
//		
//		System.out.println(s0);
		System.out.println(path.getEdgeList().stream().map(e->e.action()).toList());
		
		BT<MochilaVertex,MochilaEdge,SolucionMochila> ms = BT.of(
				graph,
				MochilaVertex::getSolucion,
				path.getWeight(),path,true);		
		
		Optional<GraphPath<MochilaVertex, MochilaEdge>> gp = ms.search();
//		SolucionMochila s = MochilaVertex.getSolucion(ms.optimalPath().get());
		System.out.println(MochilaVertex.getSolucion(gp.get()));
		System.out.println(ms.optimalPath().get().getEdgeList().stream().map(e->e.action()).toList());
			
		GraphPath<MochilaVertex, MochilaEdge> sp = gp.get();
		GraphColors.toDot(ms.outGraph(),"ficheros/MochilaBTGraph2.gv",
				v->String.format("(%d,%d)",v.index(),v.capacidadRestante()),
				e->e.action().toString(),
				v->GraphColors.colorIf(Color.red,v.goal()),
				e->GraphColors.colorIf(Color.red,sp.getEdgeList().contains(e))
				);
	}

}
