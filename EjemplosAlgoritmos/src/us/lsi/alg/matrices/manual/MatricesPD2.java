package us.lsi.alg.matrices.manual;



import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.alg.matrices.DatosMatrices;
import us.lsi.alg.matrices.MatrixVertex;
import us.lsi.hypergraphsD.Data;


public class MatricesPD2 {
	
	public static record Sp(Integer a,Integer weight) implements Comparable<Sp> {
		
		public static Sp of(Integer a, Integer weight) {
			return new Sp(a, weight);
		}
		
		@Override
		public int compareTo(Sp sp) {
			return this.weight.compareTo(sp.weight);
		}
	}	

	public static MatricesPD2 of(MatrixVertex startVertex) {
		return new MatricesPD2(startVertex);
	}
	
	private MatrixVertex startVertex;
	private Map<MatrixVertex,Sp> memory;
	
	private MatricesPD2(MatrixVertex startVertex) {
		this.startVertex = startVertex;
	}
	
	public Map<MatrixVertex, Sp> search(){
		memory = new HashMap<>();
		search(this.startVertex,memory);
		return memory;
	}
	
	private Sp edgeSp(MatrixVertex actual, Integer a, Integer nbn, List<Double> nbWeights) {
		return nbWeights.size() == nbn ? Sp.of(a,actual.edge(a).weight(nbWeights).intValue()): null;
	}
	
	private Sp edgeSp(MatrixVertex actual, Integer a, List<MatrixVertex> neighbors) {
		return neighbors.stream()
				.map(v -> search(v, memory))
				.takeWhile(s -> s != null)
				.map(s -> s.weight().doubleValue())
				.collect(Collectors.collectingAndThen(Collectors.toList(),
						ls->edgeSp(actual,a,neighbors.size(),ls)));
	}

	private Sp search(MatrixVertex actual, Map<MatrixVertex, Sp> memory) {
		Sp r = null;
		if (memory.containsKey(actual)) {
			r = memory.get(actual);
		} else if (actual.isBaseCase()) {
			Integer w = actual.baseCaseWeight().intValue();
			if (w != null) r = Sp.of(null, w);
			memory.put(actual, r);
		} else {
			r = actual.actions().stream()
					.map(a -> this.edgeSp(actual, a, actual.neighbors(a)))
					.filter(sp -> sp != null)
					.min(Comparator.naturalOrder()).orElse(null);
			memory.put(actual, r);
		}
		return r;
	}
	

	public static String solucion(MatrixVertex v, Map<MatrixVertex,Sp> memory) {
		Sp s = memory.get(v);
		if(s.a() == null) 
			return String.format("(%d,%d)",v.i(),v.j());
		else {
			List<MatrixVertex> vc = v.neighbors(s.a());
			return String.format("(%s,%s)",solucion(vc.get(0),memory),solucion(vc.get(1),memory));
		}
	
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMatrices.leeFichero("ficheros/matrices/matrices_3.txt",Data.DpType.Min);		
		MatrixVertex start = MatrixVertex.of(0,DatosMatrices.n);		
		MatricesPD2 a = MatricesPD2.of(start);		
		Map<MatrixVertex, Sp> r = a.search();
		System.out.println(MatricesPD2.solucion(start, r));
	}

	
}

