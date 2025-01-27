
module ejemplos_pl {
	exports us.lsi.pli.vertexcover;
	exports us.lsi.pli.asignacion;
	exports us.lsi.pli.equipo;
	exports us.lsi.pli.mochila;
	exports us.lsi.pli.pack;
	exports us.lsi.pli.bufete;
	exports us.lsi.pli.colorgraph;
	exports us.lsi.pli.scl;
	exports us.lsi.pli.estanteria;
	exports us.lsi.pli.academia;
	exports us.lsi.pli.reparto;
	exports us.lsi.pli.sudoku;
	exports us.lsi.pli.reinas;
	exports us.lsi.pli.tsp;
	exports us.lsi.pli.tareas;

	requires transitive datos_compartidos;
	requires transitive ejemplos_algoritmos;
	requires transitive grafos;
	requires transitive org.jgrapht.core;
	requires transitive org.jgrapht.io;
	requires transitive partecomun;
	requires transitive solve;
}