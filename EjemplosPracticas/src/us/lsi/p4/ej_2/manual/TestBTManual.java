package us.lsi.p4.ej_2.manual;

import us.lsi.p4.ej_2.DatosSubconjuntos;

public class TestBTManual {

	public static void main(String[] args) {
		for (Integer id_fichero = 1; id_fichero < 3; id_fichero++) {

			DatosSubconjuntos.iniDatos("ficheros/p4/subconjuntos" + id_fichero + ".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			DatosSubconjuntos.toConsole();
			
			SubconjuntosBT.search();
			
			System.out.println(SubconjuntosBT.getSolucion()+ "\n");
//			
			
		}
	}
	
}
