
/**
 * @author migueltoro
 *
 */

module ejemplos_algoritmos {
	
	exports us.lsi.alg.floyd;
	exports us.lsi.alg.monedas;
	exports us.lsi.alg.typ;
	exports us.lsi.alg.reinas;
	exports us.lsi.alg.secuencias;
	exports us.lsi.alg.jarras;
	exports us.lsi.alg.pack;
	exports us.lsi.alg.tsp;
	exports us.lsi.alg.mochila;
	exports us.lsi.alg.colorgraphs;
	exports us.lsi.alg.recorridos;
	exports us.lsi.alg.sudoku;
	exports us.lsi.alg.multiconjuntos;

	requires transitive datos_compartidos;
	requires transitive grafos;
	requires transitive org.jgrapht.core;
	requires transitive org.jgrapht.io;
	requires transitive org.jheaps;
	requires transitive partecomun;
}