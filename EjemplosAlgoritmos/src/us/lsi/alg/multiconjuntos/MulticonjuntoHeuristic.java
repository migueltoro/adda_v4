package us.lsi.alg.multiconjuntos;


import java.util.Locale;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.Greedy;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;


public class MulticonjuntoHeuristic {

	public static Double heuristic(MulticonjuntoVertex v1, Predicate<MulticonjuntoVertex> goal, 
			MulticonjuntoVertex v2) {
		return hu(Md.of(v1.indice(),(double)v1.sumaRestante()),
				v->v.index()==DatosMulticonjunto.NUM_E|| v.cr()==0.);
	}
	
	public static record Md(Integer index, Double cr) {
		public static Md of(Integer index, Double cr) {
			return new Md(index,cr);
		}
		public Double heuristicAction() {
			return ((double)this.cr)/ DatosMulticonjunto.getElemento(this.index);
		}
		
		public Md next() {
			Double a = heuristicAction();
			return new Md(this.index()+1,this.cr()-DatosMulticonjunto.getElemento(this.index()) * a);
		}
		public Double weight() {
			if(this.index >= DatosMulticonjunto.NUM_E) return 0.;
			return heuristicAction();
		}
	}

	public static Double hu(Md v1, Predicate<Md> goal) {	
		Greedy<Md> r = Greedy.of(v1,v->v.next(),goal);
		return r.stream().mapToDouble(v->v.weight()).sum();
	}
	
	
	public static void main(String[] args) {

		// Set up
		Locale.setDefault(Locale.of("en", "US"));

		for (Integer id_fichero = 0; id_fichero < 7; id_fichero++) {

			DatosMulticonjunto.iniDatos("ficheros/multiconjuntos.txt", id_fichero);
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			
			DatosMulticonjunto.toConsole();
			
			// V�rtices clave

			MulticonjuntoVertex start = MulticonjuntoVertex.start();
		

			// Grafo
			
			EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph =
					EGraph.virtual(start)
					.pathType(PathType.Sum)
					.type(Type.Min)
					.edgeWeight(x -> x.weight())
					.heuristic(MulticonjuntoHeuristic::heuristic)
					.build();
			
			GraphPath<MulticonjuntoVertex, MulticonjuntoEdge> r = 
					GreedyOnGraph.of(graph,MulticonjuntoVertex::greedyEdge).path();
			
			
			
			System.out.println();
//			System.out.println(valEntero(start,DatosMulticonjunto.NUM_E));
			System.out.println(r.getEndVertex().goalHasSolution());
			System.out.println(r.getWeight());
			System.out.println(heuristic(start,v->v.goal(),null));
		}
	}

}

