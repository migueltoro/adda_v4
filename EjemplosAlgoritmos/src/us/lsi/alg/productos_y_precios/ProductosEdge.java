package us.lsi.alg.productos_y_precios;


import us.lsi.graphs.virtual.SimpleEdgeAction;

public record ProductosEdge(ProductosVertex source, ProductosVertex target, Integer action, Double weight)
           implements SimpleEdgeAction<ProductosVertex, Integer> {
	
	public static ProductosEdge of(ProductosVertex origen, ProductosVertex destino, Integer action) {
		Double coste = DatosProductos.producto(origen.indice()).precio()*action;
		return new ProductosEdge(origen, destino, action, coste);
	}

}
