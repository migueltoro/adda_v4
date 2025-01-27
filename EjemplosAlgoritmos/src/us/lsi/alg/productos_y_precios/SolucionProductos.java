package us.lsi.alg.productos_y_precios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

public record SolucionProductos(Double precioTotal,Set<Producto> productos,Set<String> funciones) implements Comparable<SolucionProductos>{
	
	public static SolucionProductos of(GraphPath<ProductosVertex, ProductosEdge> p) {
		List<Integer> ls = p.getEdgeList().stream().map(x -> x.action()).collect(Collectors.toList());
		return SolucionProductos.of(ls);
	}
	
	public static SolucionProductos of(List<Integer> ls) {
		List<Integer> lsC = new ArrayList<>(ls);
		Double precioTotal = 0.;
		Set<Producto> productos = new HashSet<>();
		Set<String> funciones = new HashSet<>();
		for(int i=0; i<lsC.size(); i++) {
			if(ls.get(i)==1) {
				Producto p = DatosProductos.producto(i);
				productos.add(p);
				funciones.addAll(DatosProductos.nombres(p.funciones()));
				precioTotal += p.precio();
			}			
		}
		return new SolucionProductos(precioTotal,productos,funciones);
	}
	
	
	public static SolucionProductos of(Double precioTotal,Set<Producto> productos, Set<String> funciones) {
		return new SolucionProductos(precioTotal,productos,funciones);
	}

	@Override
	public String toString() {
		return String.format("Funcionalidades a cubrir: %s\nComposicion del lote seleccionado:\n\t%s"
				+ "\nFuncionalidades del lote seleccionado:\n\t%s\nPrecio total del lote seleccionado: %.2f euros", 			
				DatosProductos.funcionesDS,
				this.productos().stream().map(e->e.toString()).collect(Collectors.joining("\n\t")),
		        this.funciones(),
		        precioTotal);
	}

	@Override
	public int compareTo(SolucionProductos other) {
		return this.precioTotal.compareTo(other.precioTotal);
	}	

}

