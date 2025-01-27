/**
 * 
 */
/**
 * @author migueltoro
 *
 */

module solve {
	exports us.lsi.solve;
	exports us.lsi.gurobi;
	exports us.lsi.model;

	requires transitive datos_compartidos;
	requires transitive gurobi;
	requires transitive org.antlr.antlr4.runtime;
	requires transitive partecomun;
}