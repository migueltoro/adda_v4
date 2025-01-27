package us.lsi.alg.sudoku;

import java.util.Locale;

public class Test {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosSudoku.leeFichero("ficheros/sudoku/sudoku3.txt");
		
		SudokuVertexI e1 = SudokuVertexI.first();
		System.out.println(e1);
		for(int i =0; i<50;i++) {
			System.out.println(i+" = "+e1.valoresLibresEnCasilla(e1.casilla(i)));
		}
		System.out.println(e1.numValoresLibresEnCasillas());
//		for(int i =0; i<50;i++) {
//			System.out.println(e1);	
//			System.out.println("___________________________");	
//			e1 = e1.neighbor(e1.actions().get(0));
//		}
	}

}
