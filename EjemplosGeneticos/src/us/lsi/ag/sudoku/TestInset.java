package us.lsi.ag.sudoku;

import java.util.List;

import us.lsi.alg.sudoku.DatosSudoku;
import us.lsi.alg.sudoku.SudokuVertex;

public class TestInset {

	public static void main(String[] args) {
		DatosSudoku.tamSubCuadro = 3;
		DatosSudoku.leeFichero("ficheros/sudoku.txt");
		
		SudokuVertex sv = SudokuVertex.first();
		
		InSetDatosSudokuAG p = new InSetDatosSudokuAG(sv);
		
		System.out.println(sv);
		System.out.println("-------------------");
		System.out.println(p.values());
		System.out.println(p.solucion(List.of(4, 2, 2, 2, 2, 4, 7, 7, 2, 4, 5, 6, 4, 9, 4, 6, 2, 2, 9, 8, 4, 2, 4, 4, 7, 2, 5, 8, 4, 7, 9, 2, 2, 1, 3, 1, 9, 6, 9, 9, 3, 8, 7, 2, 3, 4, 9, 1, 4, 4, 9, 5, 1, 4)));
	}

}
