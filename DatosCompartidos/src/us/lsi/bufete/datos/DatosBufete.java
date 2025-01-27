package us.lsi.bufete.datos;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosBufete {
	
	public static int NUM_CASOS, NUM_ABOGADOS;
	private static List<Abogado> abogados; 
	
	public static void iniDatos(String fichero) {
		abogados = Files2.streamFromFile(fichero)
		.map(s->Abogado.parse(s)).collect(Collectors.toList());
		
		NUM_ABOGADOS = abogados.size();
		NUM_CASOS = abogados.get(0).getHoras().size();		
	}
	
	public static List<Abogado> getAbogados() {
		return abogados;
	}

	public static Abogado getAbogado(int index) {
		return abogados.get(index);
	}
	
	public static Integer getHoras(int i, int j) {
		return abogados.get(i).getHoras(j);
	}
	
	public static void toConsole() {
		System.out.println("Nº de casos: "+NUM_CASOS);
		abogados.forEach(System.out::println);		
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/PI6Ej2DatosEntrada1.txt");
		toConsole();
	}
	
}

