package us.lsi.ag.sudoku;

import us.lsi.alg.sudoku.DatosSudoku;
import us.lsi.alg.sudoku.SudokuVertex;

public class TestFilas {
	
	public static void main(String[] args) {
		
		DatosSudoku.tamSubCuadro = 3;
		DatosSudoku.leeFichero("ficheros/sudoku.txt");
		
		SudokuVertex sv = SudokuVertex.first();
		
		BlocksDatosSudokuFilasAG p = new BlocksDatosSudokuFilasAG(sv);
		
		System.out.println(sv);
		System.out.println("-------------------");
		System.out.println(p.initialValues);
		System.out.println(p.initialValues.size());
		System.out.println(p.blocksLimits);
		System.out.println(p.blocksLimits.size());
	}

}
