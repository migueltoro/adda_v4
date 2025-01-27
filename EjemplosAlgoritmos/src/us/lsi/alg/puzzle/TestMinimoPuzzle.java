package us.lsi.alg.puzzle;

import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestMinimoPuzzle {

	
	public static void main(String[] args) {
		VertexPuzzle start = VertexPuzzleI.of(1, 2, 3, 4, 5, 0, 6, 7, 8);
		VertexPuzzle end = VertexPuzzleI.of(1,2,3,4,6,5,8,7,0);
		EGraph<VertexPuzzle, EdgePuzzle> graph = 
				EGraph.virtual(start)
				.pathType(PathType.Sum)
				.type(Type.Min)
				.edgeWeight(x->x.weight())
				.endVertex(end)
				.heuristic((v1,p,v2)->0.)
				.build();
					
		AStar<VertexPuzzle, EdgePuzzle,?> a = AStar.of(graph,null,null,null);
		
		GraphPath<VertexPuzzle,EdgePuzzle> path = a.search().orElse(null);
		List<VertexPuzzle> vertices = path.getVertexList();
		for (VertexPuzzle v: vertices) {
			System.out.println(v);
			System.out.println("====================");
		}
	}

}
