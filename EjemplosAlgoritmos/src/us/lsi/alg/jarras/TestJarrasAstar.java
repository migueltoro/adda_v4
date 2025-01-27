package us.lsi.alg.jarras;


import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;

public class TestJarrasAstar {

		public static void main(String[] args) {
			Locale.setDefault(Locale.of("en", "US"));
			
			JarrasVertex.data(3,5,0,0,3,7);;
			JarrasVertex e1 = JarrasVertex.first();
			JarrasVertex e2 = JarrasVertex.last();
			
			EGraph<JarrasVertex, JarrasEdge> graph = EGraph.virtual(e1)
					.endVertex(e2)
					.heuristic((v1,p,v2)->0.)
					.build();		
			
			AStar<JarrasVertex, JarrasEdge,JarrasSolution> ms = AStar.of(graph);
			
//			Optional<JarrasVertex> r = ms.stream().peek(e->System.out.println(e)).filter(e->e.equals(e2)).findFirst();
			
			Optional<GraphPath<JarrasVertex, JarrasEdge>> path = ms.search();
//			List<JarrasEdge> edges = path.getEdgeList();
//			System.out.println(edges);
			JarrasSolution s = JarrasSolution.of(path.get());
			System.out.println(s);
			
//			System.out.println(e1);
//			System.out.println(e1.actions());
//			JarrasVertex v2 = e1.neighbor(e1.actions().get(0));
//			System.out.println(v2);
//			System.out.println(v2.actions());
//			JarrasVertex v3 = v2.neighbor(v2.actions().get(2));
//			System.out.println(v3);
//			System.out.println(v3.actions());
//			JarrasVertex v4= v3.neighbor(v3.actions().get(3));
//			System.out.println(v4);
//			System.out.println(v4.actions());	
		}


}
