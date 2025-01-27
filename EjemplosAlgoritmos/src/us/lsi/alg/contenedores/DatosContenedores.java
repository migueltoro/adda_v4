package us.lsi.alg.contenedores;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosContenedores {
	public static record Contenedor(String nombre, Integer capacidad, String tipo) {
		public static Contenedor create(String s) {
			String[] v0 = s.split(":");
			String[] v1 = v0[1].split(";");
			String a = v1[0].split("=")[1].trim();
			String b = v1[1].split("=")[1].trim();
			return new Contenedor(v0[0].trim(),Integer.parseInt(a), b);
		}
		@Override
		public String toString() {		
			return nombre+": "+capacidad+"; "+tipo;
		}		
	}

	public static record Elemento(String nombre, Integer tam, List<String> tipos) {
		public static Elemento create(String s) {
			String[] v0 = s.split(":");
			String[] v1 = v0[1].split(";");
			Integer a = Integer.parseInt(v1[0].trim());
			List<String> b = List2.parse(v1[1].trim(), ", ", x->x);
			return new Elemento(v0[0].trim(), a, b);
		}	
		@Override
		public String toString() {		
			return nombre+": "+tam+"; "+tipos;
		}		
	}	
	
	private static List<Contenedor> contenedores;
	private static List<Elemento> elementos;
	
	public static void iniDatos(String fichero) {
		contenedores = List2.empty();
		elementos = List2.empty();
		for(String s: Files2.linesFromFile(fichero)) {
			if(s.startsWith("//"))
				continue;
			else if(s.startsWith("CONT"))
				contenedores.add(Contenedor.create(s));
			else
				elementos.add(Elemento.create(s));
		}
		Collections.sort(elementos,Comparator.<Elemento,Integer>comparing(e->(e.tam())).reversed());
		toConsole();
	}
	
	
	public static Integer getNumElementos() {
		return elementos.size();
	}
	public static Integer getNumContenedores() {
		return contenedores.size();
	}
	public static Integer getTamElemento(Integer i) {
		return elementos.get(i).tam();
	}
	public static Integer getTamContenedor(Integer j) {
		if(j.equals(DatosContenedores.getNumContenedores())) return Integer.MAX_VALUE;
		return contenedores.get(j).capacidad();
	}
	
	public static Boolean getPuedeUbicarse(Integer i, Integer j) {
		if(j.equals(DatosContenedores.getNumContenedores())) return true;
		String s = contenedores.get(j).tipo();
		return elementos.get(i).tipos().contains(s);
	}
	
	public static Elemento getElemento(Integer i) {
		return elementos.get(i);
	}	
	public static Contenedor getContenedor(Integer j) {
		return contenedores.get(j);
	}
	
	
	public static void toConsole() {
		String2.toConsole(contenedores,"Contenedores");
		String2.toConsole(elementos,"Elementos");
		String2.toConsole(String2.linea());
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) throws IOException {
		iniDatos("ficheros/PI5Ej4DatosEntrada1.txt");
	}
	
}
