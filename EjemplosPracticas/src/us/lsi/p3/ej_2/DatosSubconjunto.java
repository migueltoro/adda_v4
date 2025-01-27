package us.lsi.p3.ej_2;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.common.String2;

public class DatosSubconjunto {
	
	public static record Subconjunto(Integer id, Set<Integer> elementos, Double peso) {
		public static int cont;
		public static Subconjunto create(String s) {
			String[] v = s.split(":");
			return new Subconjunto(cont++, Set2.parse(v[0].trim(), "{,}" , Integer::parseInt),
				Double.parseDouble(v[1].trim()));
		}
		
		@Override
		public String toString() {
			return String.format("S%d = %s; Peso = %.1f", id, elementos, peso);
		}	
	}

	private static List<Integer> universo;
	private static List<Subconjunto> subconjuntos;

	public static void iniDatos(String fichero) {
		Subconjunto.cont=0;
		subconjuntos = List2.empty();
		Set<Integer> elems = new TreeSet<>();
		for(String linea: Files2.linesFromFile(fichero)) {
			Subconjunto s = Subconjunto.create(linea);
			subconjuntos.add(s);
			elems.addAll(s.elementos());
		}
		universo =  List2.ofCollection(elems);
		toConsole();
	}
	
	public static Integer getNumSubconjuntos() {
		return subconjuntos.size();
	}
	public static Integer getNumElementos() {
		return universo.size();
	}
	public static Double getPeso(Integer i) {
		return subconjuntos.get(i).peso();
	}
	public static Integer contieneElemento(Integer i, Integer j) {
		return subconjuntos.get(i).elementos().contains(universo.get(j))? 1: 0;
	}
	
	public static Subconjunto getSubConjunto(int index){
		return subconjuntos.get(index);
	}
	public static Set<Integer> getElementos(int index){
		return subconjuntos.get(index).elementos();
	}
	public static List<Subconjunto> getSubconjuntos(){
		return subconjuntos;
	}
	public static List<Integer> getUniverso(){
		return universo;
	}

	public static void toConsole() {
		String2.toConsole("Universo: %s", universo);
		String2.toConsole(subconjuntos,"Subconjuntos");
		String2.toConsole(String2.linea());		
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/p3/ejemplo2_1.txt");
	}
}

