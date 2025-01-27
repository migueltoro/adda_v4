package us.lsi.alg.productos_y_precios;

import java.util.Set;
import us.lsi.common.Set2;


public record ProductoL(String nombre,Double precio,Set<String> funciones) {

	public static ProductoL parse(String s) {
		String[] partes = s.split(":");
		String[] tokens = partes[0].split("[()]");
		String nombre = tokens[0].trim();
		Double precio = Double.parseDouble(tokens[1].split(" ")[0].trim());
		Set<String> funciones = Set2.parse(partes[1].trim().split(","),tk->tk.trim());
		return new ProductoL(nombre,precio,funciones);				
	}

}


