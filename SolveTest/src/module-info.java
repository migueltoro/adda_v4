module solve_test {
	
	exports us.lsi.reinas_test;
	exports us.lsi.model_test;
	exports us.lsi.solve_test;
	exports us.lsi.mochila_test;
	exports us.lsi.gurobi_test;
	
	requires transitive datos_compartidos;
	requires transitive org.antlr.antlr4.runtime;
	requires transitive partecomun;
	requires transitive solve;
}