package us.lsi.p2;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjemplo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testsEjemplo4("Andalucia", "Sevilla", "Almeria");
		
	}
	
	public static void testsEjemplo4(String file, String origen, String destino) {
		
		SimpleWeightedGraph<Ciudad,Carretera> g =  
				GraphsReader.newGraph("ficheros/p2/" + file + ".txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);

		System.out.println("Archivo de entrada " + file + ".txt \n" + "Datos de entrada: " + g);

		
		System.out.println("Apartado A):");
		Ejemplo4.apartadoA(g, file, origen, destino);
		
		System.out.println("Apartado B):");
		Ejemplo4.apartadoB(g, file);
		
	}

}
