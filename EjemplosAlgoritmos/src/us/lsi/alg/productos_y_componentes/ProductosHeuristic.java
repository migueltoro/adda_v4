package us.lsi.alg.productos_y_componentes;


import java.util.function.Predicate;

import us.lsi.alg.productos_y_componentes.DatosProductos.Producto;


public class ProductosHeuristic {
	
	public static Double heuristic(VertexProductos v1, Predicate<VertexProductos > goal,VertexProductos  v2) {
		Integer tpR = v1.tpR();
		Integer teR = v1.teR();
		Double r = 0.;
		for(Integer index = v1.index(); index < DatosProductos.getNumProductos(); index++) {
			Producto p = DatosProductos.getProducto(index);
			Integer a = DatosProductos.maxActionInt(p,tpR,teR);
			r = r + (a+1)*p.precio();
			tpR = tpR - a*p.tp();
			teR = teR - a*p.te();	
		}		
		return r;	
	}

	public static Integer greedyValue(VertexProductos v1, Predicate<VertexProductos> goal,VertexProductos  v2) {
		Integer tpR = v1.tpR();
		Integer teR = v1.teR();
		Integer r = 0;
		for(Integer index = v1.index(); index < DatosProductos.getNumProductos(); index++) {
			Producto p = DatosProductos.getProducto(index);
			Integer a = DatosProductos.maxActionInt(p,tpR,teR);
			r = r + a*p.precio().intValue();
			tpR = tpR - a*p.tp();
			teR = teR - a*p.te();	
		}		
		return r;		
	}
	
}
