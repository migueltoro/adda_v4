package us.lsi.p4.ej_1.manual;

import us.lsi.alg.multiconjuntos.DatosMulticonjunto;

public class TestPDRManual {

	public static void main(String[] args) {
		for (Integer id_fichero = 0; id_fichero < 7; id_fichero++) {
			DatosMulticonjunto.iniDatos("ficheros/p4/multiconjuntos.txt", id_fichero);
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			System.out.println("Solucion obtenida: " + MulticonjuntoPDR.search());
		}
	}
	
}
