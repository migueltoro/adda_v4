package us.lsi.alg.matrices.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.alg.matrices.DatosMatrices;
import us.lsi.alg.matrices.MatrixEdge;
import us.lsi.alg.matrices.MatrixVertex;
import us.lsi.hypergraphsD.Data;


public class MatricesPD {
	
	public static record Sp(Integer a,Integer weight) implements Comparable<Sp> {
		
		public static Sp of(Integer a, Integer weight) {
			return new Sp(a, weight);
		}
		
		@Override
		public int compareTo(Sp sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	

	public static MatricesPD of(MatrixVertex startVertex) {
		return new MatricesPD(startVertex);
	}
	
	private MatrixVertex startVertex;
	
	private MatricesPD(MatrixVertex startVertex) {
		this.startVertex = startVertex;
	}
	
	public Map<MatrixVertex, Sp> search(){
		Map<MatrixVertex,Sp> memory = new HashMap<>();
		search(this.startVertex,memory);
		return memory;
	}

	private Sp search(MatrixVertex actual, Map<MatrixVertex, Sp> memory) {
		Sp r = null;
		if (memory.containsKey(actual)) {
			r = memory.get(actual);
		} else if (actual.isBaseCase()) {
			Integer w = actual.baseCaseWeight().intValue();
			if (w != null)
				r = Sp.of(null, w);
			memory.put(actual, r);
		} else {
			List<Sp> sps = new ArrayList<>();
			for (Integer a : actual.actions()) {
				List<Sp> spsa = new ArrayList<>();
				MatrixEdge e = actual.edge(a);
				for (MatrixVertex v : actual.neighbors(a)) {
					Sp nba = search(v, memory);
					if (nba == null) {
						spsa = null;
						break;
					}
					spsa.add(nba);
				}
				Sp sp = null;
				if (spsa != null && !spsa.isEmpty()) {
					List<Double> lw = spsa.stream().map(s->s.weight().doubleValue()).toList();
					Integer weight = e.weight(lw).intValue();
					sp = Sp.of(a, weight);
				}
				sps.add(sp);
			}
			r = sps.stream().filter(s -> s != null).min(Comparator.naturalOrder()).orElse(null);
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
		
		DatosMatrices.leeFichero("ficheros/matrices/matrices.txt",Data.DpType.Min);
		
		MatrixVertex start = MatrixVertex.of(0,DatosMatrices.n);
		
		MatricesPD a = MatricesPD.of(start);
		
		Map<MatrixVertex, Sp> r = a.search();
		
		System.out.println(MatricesPD.solucion(start, r));
	}

	
}
