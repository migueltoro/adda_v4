package us.lsi.pli.sudoku;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import us.lsi.alg.sudoku.Casilla;
import us.lsi.alg.sudoku.DatosSudoku;
import us.lsi.alg.sudoku.SolucionSudoku;
import us.lsi.alg.sudoku.SudokuVertexI;
import us.lsi.common.Files2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class SudokuPLI {
	
	public static void  include(String file, String fileInclude,String fileOut) {
		String puzzle = Files2.streamFromFile(file)
				.map(ln->Casilla.parse(ln))
				.map(cs->String.format("x[%d,%d,%d] = 1",cs.f(),cs.c(),cs.value()))
				.collect(Collectors.joining("\n"));
		String result = Files2.text(fileInclude);
		Map<String,String> reglas = new HashMap<>();
		reglas.put("datos",puzzle);
		result = String2.transform(result,reglas);
		Files2.toFile(result,fileOut);
	}
	
	
	public static List<String> partes(String s) {
		return Arrays.asList(s.split("_"));
	}
	
	
	public static List<Casilla> solucion(GurobiSolution solution){
		List<Casilla> ls = solution.values.entrySet().stream()
				.filter(e->!e.getKey().contains("$") && e.getValue() > 0)
				.map(e->partes(e.getKey()))
				.map(p->Casilla.of(Integer.parseInt(p.get(1)),
						Integer.parseInt(p.get(2)),
						Integer.parseInt(p.get(3)),
						true,
						false))
				.collect(Collectors.toList());
		return ls;
	}

	public static Boolean fi(Integer f, Integer c, Integer t) {
		Integer k = 3;
		return t == c / k + k * (f / k);
	}

	public static void sudoku() throws IOException {	
		String txt = "ficheros/sudoku/sudoku1.txt";
		DatosSudoku.leeFichero(txt);
		System.out.println(SolucionSudoku.of(SudokuVertexI.first()));
		include(txt,"modelos/sudoku_p.lsi","modelos/sudoku.lsi");
		AuxGrammar.generate(SudokuPLI.class, "modelos/sudoku.lsi", "ficheros/sudoku.lp");
		GurobiSolution st = GurobiLp.gurobi("ficheros/sudoku.lp");
		Locale.setDefault(Locale.of("en", "US"));
		List<Casilla> lc = solucion(st);
		Collections.sort(lc,Comparator.comparing(c->c.p()));	
		
		System.out.println(SudokuVertexI.first());
		Collections.sort(DatosSudoku.casillas,Comparator.comparing(c->c.p()));	
		for(int i = 0; i < DatosSudoku.n;i++) {
			if(!DatosSudoku.casillas.get(i).isWithInitialValue())
				DatosSudoku.casillas.set(i,lc.get(i));
		}
		System.out.println(SolucionSudoku.of(SudokuVertexI.first()));
		System.out.println(SudokuVertexI.first());
	}
	
	

	public static void main(String[] args) throws IOException {
		sudoku();
//		System.out.println(AuxGrammar.constraintName(123));
//		include("ficheros/sudoku/sudoku1.txt","models/sudoku_p.lsi","models/sudoku.lsi");		
	}

}
