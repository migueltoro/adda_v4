package us.lsi.alg.productos_y_precios;

import java.util.Comparator;
import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.common.IntegerSet;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.graphs.virtual.VirtualVertex;

public record ProductosVertex(Integer indice,IntegerSet funcionalidades_restantes) 
		implements VirtualVertex<ProductosVertex, ProductosEdge, Integer> {
	
	// Factor�as vac�as

	public static ProductosVertex initial() {
		return ProductosVertex.of(0,DatosProductos.funcionesDeseadas());
	}
	
	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	@Override
	public Boolean goal() {
		return this.indice == DatosProductos.NUM_PRODUCTOS;
	}

	public static ProductosVertex of(Integer indice, IntegerSet funcionalidades_restantes) {
		return new ProductosVertex(indice, funcionalidades_restantes);
	}

	public static ProductosVertex copy(ProductosVertex v_input) {
		return ProductosVertex.of(v_input.indice, v_input.funcionalidades_restantes);
	}

	// M�todos para el grafo

	

	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= DatosProductos.productos().size();
	}
	
	@Override
	public Integer greedyAction() {
		return actions().stream().max(Comparator.naturalOrder()).get();
	}

	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = null;
		if (this.indice.equals(DatosProductos.NUM_PRODUCTOS))
			alternativas = List2.of();
		if (this.indice.equals(DatosProductos.NUM_PRODUCTOS - 1)) {
			if (this.funcionalidades_restantes.isEmpty())
				alternativas = List2.of(0);
			else if (!this.funcionalidades_restantes.isEmpty()
					&& DatosProductos.funciones(this.indice).containsAll(this.funcionalidades_restantes))
				alternativas = List2.of(1);
			else
				alternativas = List2.of();
//			System.out.println(String.format("%s == %s",this, alternativas));
		} else if (this.indice < DatosProductos.NUM_PRODUCTOS - 1) {
			if (this.funcionalidades_restantes.isEmpty())
				alternativas = List2.of(0);
			else if (this.funcionalidades_restantes.intersection(DatosProductos.funciones(this.indice)).isEmpty())
				alternativas = List2.of(0);
			else
				alternativas = List2.of(1, 0);
		}
		Preconditions.checkNotNull(alternativas, String.format("%s", this.toGraph()));
		return alternativas;
	}

	@Override
	public ProductosVertex neighbor(Integer a) {
		ProductosVertex r = null;
		if(a == 0) r = ProductosVertex.of(this.indice+1,this.funcionalidades_restantes);
		else if (a == 1) 
			r = ProductosVertex.of(indice + 1,
		          this.funcionalidades_restantes.difference(DatosProductos.funciones(this.indice)));
		return r;
	}

	@Override
	public ProductosEdge edge(Integer a) {
		return ProductosEdge.of(this, this.neighbor(a), a);
	}

	// M�todos auxiliares

	public String toString() {
		String nombre = this.indice<DatosProductos.NUM_PRODUCTOS?
				DatosProductos.producto(this.indice).nombre():"_";
		return String.format("(Indice: %d, %s, Asignaci�n pendiente: %s)", 
				this.indice,nombre,funcionalidades_restantes);
	}
	
	public String toGraph() {
		String nombre = this.indice < DatosProductos.NUM_PRODUCTOS? 
				DatosProductos.producto(this.indice).nombre() : "_";
		return String.format("(%s,%s)",nombre,this.funcionalidades_restantes);
	}

	

	public static SolucionProductos getSolucion(GraphPath<ProductosVertex, ProductosEdge> path) {
		return ProductosVertex.getSolucion(path.getEdgeList());
	}

	public static SolucionProductos getSolucion(List<ProductosEdge> ls) {
		List<Integer> alternativas = List2.empty();
		for (ProductosEdge edge : ls) {
			alternativas.add(edge.action());
		}

		return SolucionProductos.of(alternativas);
	}


}


