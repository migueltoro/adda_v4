package us.lsi.alg.floyd.manual;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.GraphWalk;

import us.lsi.alg.floyd.DatosFloyd;
import us.lsi.alg.floyd.FloydVertex;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.SimpleEdge;

public class FloydPD2 {
	
	public static record Sp(Boolean action,Double weight) implements Comparable<Sp> {
		
		public static Sp of(Boolean a, Double weight) {
			return new Sp(a, weight);
		}
		
		@Override
		public int compareTo(Sp sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	private Map<FloydVertex,Sp> memory;
	public FloydVertex startVertex;
	
	public static FloydPD2 of(FloydVertex startVertex) {
		return new FloydPD2(startVertex);
	}
	
	private FloydPD2(FloydVertex startVertex) {
		this.startVertex = startVertex;
	}
	
	public Map<FloydVertex,Sp> search(){
		this.memory = new HashMap<>();
		search(this.startVertex,memory);
		return memory;
	}

	private Sp edgeSp(FloydVertex actual, Boolean a, Integer nbn, List<Double> nbWeights) {
		return nbWeights.size() == nbn ? Sp.of(a,actual.edge(a).weight(nbWeights)): null;
	}
	
	private Sp edgeSp(FloydVertex actual, Boolean a, List<FloydVertex> neighbors) {
		return neighbors.stream()
				.map(v -> search(v, memory))
				.takeWhile(s -> s != null)
				.map(s -> s.weight().doubleValue())
				.collect(Collectors.collectingAndThen(Collectors.toList(),
						ls->edgeSp(actual,a,neighbors.size(),ls)));
	}

	private Sp search(FloydVertex actual, Map<FloydVertex, Sp> memory) {
		Sp r = null;
		if (memory.containsKey(actual)) {
			r = memory.get(actual);
		} else if (actual.isBaseCase()) {
			Double w = actual.baseCaseWeight();
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
	
	
	public GraphWalk<Integer,SimpleEdge<Integer>> solucion(FloydVertex p, Map<FloydVertex,Sp> memory) {
		GraphWalk<Integer,SimpleEdge<Integer>> r;
		Sp sp = memory.get(p);
		if(sp.action() == null) {
			r = p.baseCaseSolution();
		}
		else {
			List<GraphWalk<Integer, SimpleEdge<Integer>>> b = 
					p.neighbors(sp.action()).stream().map(v->solucion(v,memory)).toList();
			r = p.solution(b);
		}
		return r;
	}
	
	public static Ciudad ciudad(Graph<Ciudad,Carretera> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosFloyd.datos();
		
		System.out.println(DatosFloyd.graph);
		System.out.println(DatosFloyd.graphI);
		
		Integer origen = DatosFloyd.graphI.index(v->v.nombre().equals("Sevilla"));
		Integer destino = DatosFloyd.graphI.index(v->v.nombre().equals("Almeria"));
		
		FloydVertex.graph = DatosFloyd.graphI;
		FloydVertex.n = DatosFloyd.graphI.vertexSet().size();
		
		FloydVertex start = FloydVertex.initial(origen,destino);
		
		FloydPD2 a = FloydPD2.of(start);
		
		Map<FloydVertex, Sp> r = a.search();
		
		
		System.out.println(a.solucion(start, r).getVertexList().stream()
				.map(i->DatosFloyd.graphI.vertex(i))
				.toList());
	}

}

