
module ejemplos_de_grafos {
	exports us.lsi.flowgraph.examples;
	exports us.lsi.graphs.examples;
    
	requires transitive datos_compartidos;
	requires transitive grafos;
	requires transitive org.jgrapht.core;
	requires transitive org.jgrapht.io;
	requires transitive partecomun;
	requires transitive solve;
}