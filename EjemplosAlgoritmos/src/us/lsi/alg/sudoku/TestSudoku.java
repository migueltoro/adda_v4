package us.lsi.alg.sudoku;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestSudoku {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosSudoku.leeFichero("ficheros/sudoku/sudoku3.txt");
		SudokuVertex e1 = SudokuVertex.first();
		
		
		EGraph<SudokuVertex,SimpleEdgeAction<SudokuVertex,Integer>> graph = 
				EGraph.virtual(e1)
				.pathType(PathType.Last)
				.type(Type.Min)
				.vertexWeight(v->-(double)v.errores())
				.build();
		
		
		BT<SudokuVertex,SimpleEdgeAction<SudokuVertex,Integer>,SolucionSudoku> ms = 
				BT.of(
				graph,
				SolucionSudoku::of);
		
		Optional<GraphPath<SudokuVertex, SimpleEdgeAction<SudokuVertex, Integer>>> gp = ms.search();
		System.out.println("Solucion = "+SolucionSudoku.of(gp.get().getEndVertex()));
	}

}
