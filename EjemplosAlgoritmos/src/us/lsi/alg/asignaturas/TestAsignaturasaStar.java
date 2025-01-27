package us.lsi.alg.asignaturas;


import java.util.Locale;
import java.util.Optional;
import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;


public class TestAsignaturasaStar {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "us"));
		String fichero = "ficheros/asignaturas.txt";

		DatosAsignaturas.iniciarDatos(fichero);
		System.out.println(DatosAsignaturas.mejoras);
		AsignaturasVertice v0 = AsignaturasVertice.inicial();
		
	
		EGraph<AsignaturasVertice,AsignaturasEdge> grafo = 
				EGraph.<AsignaturasVertice,AsignaturasEdge>virtual(v0)
				.pathType(PathType.Last)
				.type(Type.Max)
				.vertexWeight(v->(double)v.getPeso())
				.heuristic(Heuristica::heuristic)
				.build();
		
		AStar<AsignaturasVertice, AsignaturasEdge, SolucionAsignaturas> as = AStar.ofGreedy(grafo);
		
		GraphPath<AsignaturasVertice, AsignaturasEdge> s1 = as.search().get();
		
		System.out.println(SolucionAsignaturas.of(s1));
		
		System.out.println("___________________");

		PDR<AsignaturasVertice,AsignaturasEdge,SolucionAsignaturas> pd = 
				PDR.ofGreedy(grafo);

		
		GraphPath<AsignaturasVertice, AsignaturasEdge> s2 = pd.search().get();
		
		System.out.println(SolucionAsignaturas.of(s2));
		
		System.out.println("___________________");

		BT<AsignaturasVertice, AsignaturasEdge,SolucionAsignaturas> bt = BT.ofGreedy(
				grafo);
		Optional<GraphPath<AsignaturasVertice, AsignaturasEdge>> gp = bt.search();
		System.out.println(SolucionAsignaturas.of(gp.get()));
		System.out.println("___________________");

	}
}
