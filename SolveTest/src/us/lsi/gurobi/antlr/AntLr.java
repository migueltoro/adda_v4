package us.lsi.gurobi.antlr;

import java.io.IOException;

import us.lsi.mochila_test.DataMochila;
import us.lsi.solve_test.AuxGrammar2;

public class AntLr {
	
	public static void test(String file) throws IOException {
		AuxGrammar2.generate(DataMochila.class,"ficheros/"+file+".lsi","ficheros/"+file+".lp");	
	}
	

	public static void main(String[] args) throws IOException {
			test("prueba_0");
	}

}
