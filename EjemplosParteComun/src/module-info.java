/**
 * 
 */
/**
 * @author migueltoro
 *
 */

module ejemplos_parte_comun {

	exports us.lsi.java8ejemplos;
	exports us.lsi.ejemplos_stream;
	
	requires transitive datos_compartidos;
	requires transitive junit;
	requires transitive partecomun;
	requires transitive commons.math3;
	requires transitive org.jgrapht.core;
	requires transitive grafos;
	requires transitive ejemplositerativosrecursivos;
}