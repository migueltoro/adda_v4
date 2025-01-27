package us.lsi.alg.candidatos;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT {

	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 1; id_fichero < 2; id_fichero++) {

			DatosCandidatos.iniDatos("ficheros/candidatos"+ id_fichero +".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
//			DatosSubconjuntos.toConsole();
			

			// V�rtices clave

			VertexCandidatos start = VertexCandidatosI.initial();

			// Grafo
			
			EGraph<VertexCandidatos, EdgeCandidatos> graph = EGraph.virtual(start)
					.pathType(PathType.Sum)
					.type(Type.Max)
					.edgeWeight(x -> x.weight())
					.heuristic(CandidatosHeuristic::heuristic)
					.build();

			System.out.println("\n\n#### PI-7 Ej3 Algoritmo BT ####");

			// Algoritmo BT
			BT<VertexCandidatos, EdgeCandidatos, SolucionCandidatos> bta = BT.ofGreedy(graph);
/*
			bta.withGraph = true;
			bta.bestValue = SubconjuntoHeuristic.voraz(start,DatosSubconjunto.getNumSubconjuntos());
			System.out.println("Best = "+bta.bestValue);
			SolucionSubconjunto sv = SubconjuntoHeuristic.solucionVoraz(start,DatosSubconjunto.getNumSubconjuntos());
			List<SubconjuntoEdge> le = SubconjuntoHeuristic.pathVoraz(start,DatosSubconjunto.getNumSubconjuntos());
			System.out.println("Sv = "+sv);
			*/
			Optional<GraphPath<VertexCandidatos, EdgeCandidatos>> gp = bta.search();
			
			if (gp.isPresent()) {
			    System.out.println(SolucionCandidatos.of(gp.get()));
			} else {
				System.out.println("No hay soluci�n");
			}
			//System.out.println(bta.getSolution().isPresent()?bta.getSolution().get():sv);
			//List<SubconjuntoEdge> ls = bta.optimalPath != null?bta.optimalPath.getEdgeList():null;
	/*		
			GraphColors.toDot(bta.graph(),"ficheros/subconjuntosBTGraph.gv",
					v->v.toGraph(),
					e->e.action().toString(),
					v->GraphColors.colorIf(Color.red,SubconjuntoVertex.goal().test(v)),
					e->GraphColors.colorIf(Color.red,(bta.optimalPath != null?ls:le).contains(e))
					);
		*/	
//			System.out.println(ls.stream().map(e->e.action()).toList());
		}
	}

}

