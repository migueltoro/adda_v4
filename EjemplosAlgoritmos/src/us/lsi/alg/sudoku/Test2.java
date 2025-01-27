package us.lsi.alg.sudoku;

import java.util.Locale;

public class Test2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosSudoku.leeFichero("ficheros/sudoku/sudoku1.txt");
		SudokuVertex e1 = SudokuVertex.first();
		System.out.println(SolucionSudoku.of(e1));
		System.out.println(e1);
//		e1 = e1.neighbor(3);
//		System.out.println(e1);
//		e1 = e1.neighbor(5);
//		System.out.println(e1);
	}

}
