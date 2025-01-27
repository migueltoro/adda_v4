package us.lsi.p4.ej_2;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;

import us.lsi.graphs.alg.GreedyOnGraph;


public class TestPD {

		public static void main(String[] args) {

			// Set up
			Locale.setDefault(Locale.of("en", "US"));

			for (Integer id_fichero = 1; id_fichero < 3; id_fichero++) {

				DatosSubconjuntos.iniDatos("ficheros/p4/subconjuntos" + id_fichero + ".txt");
				System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
//				DatosSubconjuntos.toConsole();
				// V�rtices clave

				SubconjuntosVertex start = SubconjuntosVertex.initial();

				// Grafo
				
				EGraph<SubconjuntosVertex, SubconjuntosEdge> graph = 
						EGraph.virtual(start)
						.edgeWeight(x-> x.weight())
						.heuristic(SubconjuntosHeuristic::heuristic)
						.build();

				Boolean conVoraz = false;

				System.out.println("\n\n#### Algoritmo PD ####");
				System.out.println(conVoraz?"\nCon Voraz":"\nSin Voraz");

				PDR<SubconjuntosVertex, SubconjuntosEdge,?> pdr = null;
				SolucionSubconjuntos sv = null;
				List<SubconjuntosEdge> lev = null;
				
				Optional<GraphPath<SubconjuntosVertex, SubconjuntosEdge>> gpv = Optional.empty();
				if (conVoraz) {
					GreedyOnGraph<SubconjuntosVertex, SubconjuntosEdge> ga = GreedyOnGraph.of(graph);
					gpv = ga.search();
					if (gpv.isPresent()) {
						sv = SolucionSubconjuntos.of(gpv.get());
						lev = gpv.get().getEdgeList();
					}
					System.out.println("Sv = "+sv);
				}
				if(gpv.isPresent()) 
					pdr = PDR.of(graph,SolucionSubconjuntos::of,gpv.get().getWeight(),gpv.get(),true);
				else 
					pdr = PDR.of(graph, null, null, null, true);
				
				Optional<GraphPath<SubconjuntosVertex, SubconjuntosEdge>> gp = pdr.search();
				List<SubconjuntosEdge> le = lev;
				
				System.out.println(gp);
				SolucionSubconjuntos s_pdr;
				if (gp.isPresent()) {
					List<Integer> gp_pdr = gp.get().getEdgeList().stream().map(x -> x.action())
							.collect(Collectors.toList());
					s_pdr = SolucionSubconjuntos.of(gp_pdr);
				} else { 				
					s_pdr = sv;
				}

				System.out.println(s_pdr);
				
				GraphColors.toDot(pdr.outGraph,"ficheros_generados/p4/ejemplo2/subconjuntosPDGraph.gv",
						v->v.toGraph(),
						e->e.action().toString(),
						v->GraphColors.colorIf(Color.red,v.goal()),
						e->GraphColors.colorIf(Color.red,(gp.isPresent()?gp.get().getEdgeList():le).contains(e))
						);
			}
		}

	}



