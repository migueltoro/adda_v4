package us.lsi.alg.matricesD;

import java.util.Locale;

import us.lsi.alg.matrices.DatosMatrices;
import us.lsi.common.String2;
import us.lsi.hypergraphsD.Data;


public class Test {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMatrices.leeFichero("./ficheros/matrices.txt",Data.DpType.Min);
		MatrixVertexD p = MatrixVertexD.initial();
		String2.toConsole(p.actions().toString());
		String2.toConsole(p.edgesOf().toString());
		String2.toConsole(p.neighbors(2).toString());
		String2.toConsole(p.sp().toString());
//		String s = p.solution();
//		String2.toConsole(s);
//		GraphTree2<MatrixVertex2, MatrixEdge2, Integer, String> t = p.graphTree();
//		String2.toConsole(t.toString());
//		String2.toConsole(t.toString());
		
		

	}

}
