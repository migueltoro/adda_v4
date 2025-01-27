package us.lsi.p3.ej_1;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosMulticonjunto {
	
	public static int SUMA;	
	private static List<Integer> numeros; 
	
	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		for(int i=0;i<lineas.size();i++) {
			String[] v = lineas.get(i).split(":");
			SUMA = Integer.parseInt(v[1].trim());
			numeros = List2.parse(v[0], ",", Integer::parseInt);	
			toConsole();
		}
	}

	public static Integer getSuma() {
		return SUMA;
	}
	public static Integer getNumElementos() {
		return numeros.size();
	}
	public static Integer getElemento(Integer i) {
		return numeros.get(i);
	}
	public static Integer getMultiplicidad(Integer i){
		return SUMA / numeros.get(i);
	}
	public static List<Integer> getListaNumeros(){
		return numeros;
	}
	
	public static void toConsole() {
		String2.toConsole("Conjunto de Entrada: %s\nSuma objetivo: %d", numeros, SUMA);	
	}	
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/p3/ejemplo1_1.txt");
	}	
}

