package us.lsi.p4.ej_3;

import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPD {

	public static void main(String[] args) {
		DatosAlumnos.iniDatos("ficheros/p4/alumnos_1.txt");

		AlumnosVertex vInicial = AlumnosVertex.initial();
		
		EGraph<AlumnosVertex, AlumnosEdge> graph = //(AlumnosVertex v_inicial, Predicate<AlumnosVertex> es_terminal) { 
			EGraph.virtual(vInicial)
					.pathType(PathType.Sum)
					.type(Type.Max)
					.heuristic(AlumnosHeuristic::heuristic)
					.build();

		GreedyOnGraph<AlumnosVertex, AlumnosEdge> alg_voraz = GreedyOnGraph.of(graph);		
		GraphPath<AlumnosVertex, AlumnosEdge> path = alg_voraz.path();
		path = alg_voraz.isSolution(path)? path: null;

		PDR<AlumnosVertex,AlumnosEdge,SolucionAlumnos> alg_pdr = path==null? PDR.of(graph):
			PDR.of(graph, null, path.getWeight(), path, true);
		
		var res = alg_pdr.search().orElse(null);
		var outGraph = alg_pdr.outGraph();
		if(outGraph!=null) {
			Predicate<AlumnosVertex> vs = v -> res.getVertexList().contains(v);
			Predicate<AlumnosEdge> es = e -> res.getEdgeList().contains(e);
			GraphColors.toDot(outGraph, "ficheros_generados/p4/ejemplo3/AlumnosPDRGraph1.gv", 
					v -> v.toGraph(),
					e -> e.action().toString(), 
					v -> GraphColors.colorIf(Color.red, vs.test(v)),
					e -> GraphColors.colorIf(Color.red, es.test(e)));

		}	

		
		if(res!=null)
			System.out.println("Solucion PDR: " + SolucionAlumnos.of(res) + "\n");
		else 
			System.out.println("PDR no obtuvo solucion\n");
		
		
	}

}
