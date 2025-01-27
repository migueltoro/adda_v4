package us.lsi.alg.productos_y_precios;

import us.lsi.common.IntegerSet;


public record Producto(String nombre,Double precio,IntegerSet funciones) 
		implements Comparable<Producto>{

	public static Producto of(ProductoL p) {	
		return new Producto(p.nombre(),p.precio(),DatosProductos.indices(p.funciones()));				
	}
	
	public Boolean excluded() {
		return this.funciones().intersection(DatosProductos.funcionesD).isEmpty();
	}
	
	@Override
	public String toString() {		
		return String.format("%s (%5s euros) => %s, %.2f", nombre, precio,funciones, precioPorFuncionalidad());
	}
	
	public Double precioPorFuncionalidad() {
		Integer n = this.funciones.intersection(DatosProductos.funcionesD).size();
		return this.precio()/n;
	}

	@Override
	public int compareTo(Producto p) {
		return this.precioPorFuncionalidad().compareTo(p.precioPorFuncionalidad());
	}
}

