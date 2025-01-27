package us.lsi.p2;

import java.util.function.Predicate;

import org.jgrapht.Graph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjemplo3 {

	public static void main(String[] args) {
		testsEjemplo3("Andalucia");
		testsEjemplo3("Castilla La Mancha");
		
	}
	
	public static void testsEjemplo3(String file) {
		Graph<Ciudad, Carretera> g = GraphsReader
					.newGraph("ficheros/p2/" + file + ".txt", //fichero de datos
							Ciudad::ofFormat, //factoria para construir los vertices
							Carretera::ofFormat, //factoria para crear las aristas
							Graphs2::simpleGraph); //creador del grafo
		
		
		
		
		//Para mostrar el grafo original
		GraphColors.toDot(g,"ficheros_generados/p2/ejemplo3/" + file + ".gv",
				v->v.nombre(), //que etiqueta mostrar en vertices y aristas
				e->e.nombre(),
				v->GraphColors.color(Color.black), //color o estilo de vertices y aristas
				e->GraphColors.color(Color.black));
		
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
		// a) PrimerPredicado: Ciudades cuyo nombre contiene la letra “e”, y carreteras con menos de 200 km de distancia 
		Predicate<Ciudad> pv1 = c -> c.nombre().contains("e");
		Predicate<Carretera> pa1 = ca -> ca.km() < 200;
		
		Ejemplo3.crearVista(file, g,pv1,pa1," Primer predicado");
		
		
		/* b) SegundoPredicado: Ciudades que poseen menos de 500.000 habitantes, y carreteras cuya ciudad origen o 
		   destino tiene un nombre de más de 5 caracteres y poseen más de 100 km de distancia */
		Predicate<Ciudad> pv2 = c -> c.habitantes() < 500000;
		Predicate<Carretera> pa2 = ca -> ca.km() > 100 &&		
			(g.getEdgeSource(ca).nombre().length() > 5 || g.getEdgeTarget(ca).nombre().length() > 5);
		
		Ejemplo3.crearVista(file, g,pv2,pa2," Segundo predicado");
	}

}
