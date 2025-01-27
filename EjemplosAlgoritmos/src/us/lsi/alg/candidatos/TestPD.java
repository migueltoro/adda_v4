package us.lsi.alg.candidatos;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPD {

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

			System.out.println("\n\n#### PI-7 Ej3 Algoritmo PD ####");

			// Algoritmo PD
			PDR<VertexCandidatos, EdgeCandidatos, SolucionCandidatos> pdr = 
					PDR.ofGreedy(graph);
			/*
			pdr.bestValue = SubconjuntoHeuristic.voraz(start,DatosSubconjunto.getNumSubconjuntos());
			System.out.println("Best = "+pdr.bestValue);
			SolucionSubconjunto sv = SubconjuntoHeuristic.solucionVoraz(start,DatosSubconjunto.getNumSubconjuntos());
			List<SubconjuntoEdge> le = SubconjuntoHeuristic.pathVoraz(start,DatosSubconjunto.getNumSubconjuntos());
			System.out.println("Sv = "+sv);
			pdr.withGraph = true;
			*/
			Optional<GraphPath<VertexCandidatos, EdgeCandidatos>> gp = pdr.search();
			System.out.println(gp);
			SolucionCandidatos s_pdr;
			if (gp.isPresent()) {
				/*List<Integer> gp_pdr = gp.get().getEdgeList().stream().map(x -> x.action())
						.collect(Collectors.toList());
						*/
				s_pdr = SolucionCandidatos.of(gp.get());
				System.out.println(s_pdr);
				
			} else {
				System.out.println("Solucion no encontrada!!!");
				//s_pdr = sv;
			}

			/*
		    System.out.println(s_pdr);

			GraphColors.toDot(pdr.outGraph,"ficheros/subconjuntosPDGraph.gv",
					v->v.toGraph(),
					e->e.action().toString(),
					v->GraphColors.colorIf(Color.red,SubconjuntoVertex.goal().test(v)),
					e->GraphColors.colorIf(Color.red,(gp.isPresent()?gp.get().getEdgeList():le).contains(e))
					);
					*/
		}
	}

}

