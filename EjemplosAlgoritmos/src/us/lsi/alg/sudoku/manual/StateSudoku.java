package us.lsi.alg.sudoku.manual;

import java.util.ArrayList;
import java.util.List;
import us.lsi.alg.sudoku.SolucionSudoku;
import us.lsi.alg.sudoku.SudokuVertexI;


public class StateSudoku {

	private List<SudokuVertexI> vertices;

	private StateSudoku(List<SudokuVertexI> vertices) {
		super();
		this.vertices = vertices;
	}

	public static StateSudoku of(SudokuVertexI vertex) {
		List<SudokuVertexI> vt = new ArrayList<>();
		vt.add(vertex);
		return new StateSudoku(vt);
	}

	void forward(Integer a) {
		SudokuVertexI vcn = this.vertice().neighbor(a);
		this.vertices.add(vcn);
	}

	void back(Integer a) {
		this.vertices.remove(this.vertices.size() - 1);
	}

	SolucionSudoku solucion() {
		return new SolucionSudoku(this.vertice());
	}

	public SudokuVertexI vertice() {
		return this.vertices.get(this.vertices.size() - 1);
	}

}
