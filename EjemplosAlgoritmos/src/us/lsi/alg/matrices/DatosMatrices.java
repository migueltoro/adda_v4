package us.lsi.alg.matrices;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.hypergraphsD.Data;

public class DatosMatrices {
	
	public static List<MatrixInf> matrices;
	public static Integer n;
	
	public static void leeFichero(String fichero, Data.DpType type){
		List<Integer> ls = Files2.streamFromFile(fichero)
				.map(ln->Integer.parseInt(ln))
				.collect(Collectors.toList());	
		List<MatrixInf> r = new ArrayList<>();	
		for(int i = 0; i<ls.size()-1;i++) {
			MatrixInf m = MatrixInf.of(ls.get(i),ls.get(i+1));
			r.add(m);
		}
		DatosMatrices.matrices = r;
		DatosMatrices.n = matrices.size();
		System.out.println(" n = "+n);
		Data.type = type;
	}

	public static Integer nf(Integer i) {
		return DatosMatrices.matrices.get(i).nf;
	}
	
	public static Integer nc(Integer i) {
		return DatosMatrices.matrices.get(i).nc;
	}
}
