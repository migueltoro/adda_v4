	package us.lsi.alg.productos_y_componentes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.alg.productos_y_componentes.DatosProductos.Producto;
import us.lsi.common.Map2;

public class SolucionProductos {
	
	public static SolucionProductos of(GraphPath<VertexProductos,EdgeProductos> gs) {
		List<Integer> gp_as = gs.getEdgeList().stream().map(x -> x.action())
				.collect(Collectors.toList()); // getEdgeList();
		return SolucionProductos.of(gp_as);
	}
	
	public static SolucionProductos of(List<Integer> ls) {
		return new SolucionProductos(ls);
	}	

	private Map<Producto, Integer> solucion;

	private SolucionProductos(Double vo, Map<String, Double> vbles) {
		solucion = Map2.empty();

		for(Map.Entry<String, Double> par: vbles.entrySet()) {
			if(par.getValue()>0 && par.getKey().startsWith("x")) {
				String[] tokens = par.getKey().substring(2).split("_");
				Integer e = Integer.parseInt(tokens[0].trim());
				solucion.put(DatosProductos.getProducto(e), par.getValue().intValue());
			}
		}
	}
	
	private SolucionProductos(List<Integer> ls) {
		solucion = Map2.empty();
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i)>0) {
				solucion.put(DatosProductos.getProducto(i), ls.get(i));
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "Precio Total:" + solucion.entrySet().stream()
		.mapToDouble(p -> p.getKey().precio() * p.getValue())
		.sum();
		
		s = s + "\n" + solucion.entrySet().stream()
		.map(p -> p.getKey().nombre()+": "+p.getValue()+" unidades")
		.collect(Collectors.joining("\n", "Productos Seleccionados:\n", "\n"));
		return s;
	}

}
