package us.lsi.alg.bufete;

import java.util.List;
import java.util.stream.Collectors;


import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DatosBufete {
	public static int NUM_CASOS, NUM_ABOGADOS;
	private static List<Abogado> abogados; 
	
	public static void iniDatos(String fichero) {
		DatosBufete.abogados = Files2.streamFromFile(fichero)
		.map(s->Abogado.create(s)).collect(Collectors.toList());
		
		DatosBufete.NUM_ABOGADOS = abogados.size();
		DatosBufete.NUM_CASOS = abogados.get(0).horas().size();		
	}
	
	public static List<Abogado> abogados() {
		return abogados;
	}

	public static Abogado abogado(int index) {
		return abogados.get(index);
	}
	/**
	 * 
	 * @param i Abogado (ALTERNATIVA)
	 * @param j Caso (INDICE)
	 * @return Horas del abogado en este caso
	 */
	public static Integer horas(Integer alternativa, Integer indice) {
		return DatosBufete.abogados.get(alternativa).horasCaso(indice);
	}
	
	private static String mem;
	public static void toConsole() {
		DatosBufete.mem = "Nº de casos: "+NUM_CASOS;
		DatosBufete.abogados.forEach(a->mem += ("\n"+a.toString()));
		String2.toConsole(mem);
	}	
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/bufete1.txt");
		toConsole();
	}
	
}

