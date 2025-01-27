package us.lsi.alg.multiconjuntos;


import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DatosMulticonjunto {

	public static int NUM_E, SUM;

	private static List<Integer> numeros;

	public static void iniDatos(String ruta, Integer linea) {
		iniDatos(Files2.linesFromFile(ruta).get(linea));
	}

	public static void iniDatos(String linea) {
		String[] tokens = linea.split(":");
		SUM = Integer.parseInt(tokens[1].trim());
		Set<Integer> nums = new HashSet<>();
		for (String x : tokens[0].split(",")) {
			nums.add(Integer.parseInt(x.trim()));
		}
		numeros = nums.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		NUM_E = numeros.size();
	}

	public static List<Integer> getNumeros() {
		return numeros;
	}

	public static Integer getElemento(int index) {
		return numeros.get(index);
	}

	public static Integer getMultiplicidad(Integer i) {
		Integer res = 0;
		if (getElemento(i) > 0 && SUM >= getElemento(i)) {
			res = SUM / getElemento(i);
		}
		return res;
	}

	public static void toConsole() {
		String2.toConsole("Conjunto de Entrada: " + numeros + "\nSuma objetivo: " + SUM);
	}

	// Test de la lectura del fichero
	public static void main(String[] args) {
		for (String linea : Files2.linesFromFile("ficheros/multiconjuntos.txt")) {
			iniDatos(linea);
			toConsole();
		}
	}
}

