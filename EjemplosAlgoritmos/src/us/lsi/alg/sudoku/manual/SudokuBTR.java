package us.lsi.alg.sudoku.manual;


import java.util.List;
import java.util.Locale;

import us.lsi.alg.sudoku.DatosSudoku;
import us.lsi.alg.sudoku.SolucionSudoku;
import us.lsi.alg.sudoku.SudokuVertexI;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class SudokuBTR {
	
	public static SudokuBTR of() {
		return new SudokuBTR();
	}

	private StateSudoku estado;
	private SolucionSudoku solucion;
	private Integer iteraciones = 0;
	private Integer threshold;
	private Long time;
	private Integer iteracionesMax;
	

	private SudokuBTR() {
		super();
	}
	
	public Long time() {
		return time;
	}
	
	public SolucionSudoku solucion() {
		return solucion;
	}

	public Integer iteraciones() {
		return iteraciones;
	}

	public void bt(SudokuVertexI start, Integer iteracionesMax, Integer threshold) {
		this.threshold = threshold;
		this.iteracionesMax = iteracionesMax;
		this.time = System.nanoTime();
		this.estado = StateSudoku.of(start);
		this.solucion = null;
		do {
			bt();
			iteraciones++;
		} while (this.solucion == null && iteraciones < this.iteracionesMax);
		this.time = System.nanoTime() - this.time;
	}

	public void bt() {
		if (this.estado.vertice().index() == DatosSudoku.n) {
			SolucionSudoku s = this.estado.solucion();
			if (s.errores() == 0) this.solucion = s;
		} else {
			List<Integer> alternativas = this.estado.vertice().actions();
			if (DatosSudoku.n - this.estado.vertice().index() > this.threshold) {
				alternativas = List2.randomUnitary(alternativas);
			}
			for (Integer a : alternativas) {
				this.estado.forward(a);
				this.bt();
				this.estado.back(a);
			}
		}
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosSudoku.tamSubCuadro = 3;
		DatosSudoku.leeFichero("ficheros/sudoku/sudoku1.txt");	
		SudokuBTR a = SudokuBTR.of();		
		a.bt(SudokuVertexI.first(),15000,10);
		System.out.println("Tiempo = " + a.time());
		System.out.println("Iteraciones = " + a.iteraciones());
		String2.toConsole(a.solucion().toString(), "Solucion");
	}

}
