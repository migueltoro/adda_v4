package us.lsi.alg.sudoku;

import java.util.List;

import us.lsi.common.IntegerSet;
import us.lsi.graphs.virtual.SimpleEdgeAction;
import us.lsi.graphs.virtual.VirtualVertex;

public interface SudokuVertex extends VirtualVertex<SudokuVertex,SimpleEdgeAction<SudokuVertex,Integer>,Integer> {
	
	public static SudokuVertex of(Integer index, List<Casilla> casillas) {
		return new SudokuVertexI(index,casillas);
	}
	
	public static SudokuVertex first() {
		return SudokuVertexI.of(0,DatosSudoku.casillas);
	}
	
	Boolean goal();
	
	Boolean goalHasSolution();
	
	Integer index();

	List<Casilla> casillas();

	List<IntegerSet> valoresOcupadosEnFilas();

	List<IntegerSet> valoresOcupadosEnColumnas();

	List<IntegerSet> valoresOcupadosEnSubtablas();

	Integer errores();

	IntegerSet valoresLibresEnCasilla(Casilla c);

	Casilla casilla(Integer i);

}