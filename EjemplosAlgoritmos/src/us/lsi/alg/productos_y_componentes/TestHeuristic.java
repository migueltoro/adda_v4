package us.lsi.alg.productos_y_componentes;

import java.util.Locale;

public class TestHeuristic {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.of("en", "US"));		
		for (Integer i = 0; i < 1; i++) {
			DatosProductos.iniDatos("ficheros/productos_y_componentes/productoscomp"+ i +".txt");
			VertexProductos start = VertexProductos.initial();
			
			System.out.println(start);
			System.out.println(DatosProductos.getTotalProd());
			System.out.println(DatosProductos.getTotalElab());
			System.out.println(start.actions());
		
			System.out.println("GP = "+ProductosHeuristic.greedyValue(start,null,null));
			System.out.println("He = "+ProductosHeuristic.heuristic(start,null,null));
		}
	}

}
