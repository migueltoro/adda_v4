package us.lsi.alg.colorgraphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolucionColor {
	
	public static SolucionColor of(List<Integer> acciones) {
		Map<Integer,Integer> colores = new HashMap<>();
		for(int i = 0; i < acciones.size(); i++) {
			colores.put(i,acciones.get(i));
		}
		
		return new SolucionColor(colores);
	}
	
	private Map<Integer,Integer> colores;
	private Integer nc;
	

	private SolucionColor(Map<Integer, Integer> colores) {
		super();
		this.colores = colores;
		this.nc = (int) this.colores.values().stream().distinct().count();
	}
	
	public Integer color(Integer vertex) {
		return this.colores.get(vertex);
	}
	
	public Integer nc() {
		return nc;
	}

	@Override
	public String toString() {
		String cb = "Colores distintos = " + this.nc() + "\n___________\n" ;
		return this.colores.entrySet().stream()
				.map(e->String.format("%10s -- %d",DatosColor.g2.vertex(e.getKey()),e.getValue()))
				.collect(Collectors.joining("\n",cb,""));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
