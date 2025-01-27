package us.lsi.alg.equipo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DatosEquipo {
	//numero de jugadores
	public static int N;
	//numero de posiciones
	public static int M;
	private static List<Jugador> jugadores; 
	
	public static void iniDatos(String fichero) {
		iniDatos(fichero, 0);
	}
	
	public static void iniDatos(String fichero, int zonas) {
		jugadores = Files2.streamFromFile(fichero)
		.map(s->Jugador.create(s)).collect(Collectors.toList());
		
		N = jugadores.size();
		M = zonas>0? zonas: jugadores.get(0).getRendimiento().size();
	}

	public static Jugador getJugador(int index) {
		return jugadores.get(index);
	}
	
	/**
	 * @return Número de jugadores
	 */
	public static Integer getN() {
		return N;
	}

	/**
	 * @return Número de posiciones
	 */
	public static Integer getM() {
		return M;
	}

	/**
	 * @param i Jugador i
	 * @param j Posición j
	 * @return El rendimiento del jugador i en la posicón j
	 */
	public static Double getR(Integer i, Integer j) {
		return jugadores.get(i).getRendimiento(j);
	}

	public static int mejorPuesto(Integer index, List<Integer> posiciones) {
		return IntStream.range(0, M)
		.filter(i->posiciones.get(i)>0).boxed()				
		.max(Comparator.comparing(i->getR(index,i))).get();
	}
	
	private static String mem;
	public static void toConsole() {
		mem = "Nº de jugadores: "+N;
		jugadores.forEach(a->mem += ("\n"+a.toString()));
		String2.toConsole(mem);
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/DatosEntrada1.txt");
		toConsole();
	}


	
}
