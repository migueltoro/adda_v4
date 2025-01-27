package us.lsi.alg.floyd.manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.GraphWalk;

import us.lsi.alg.floyd.DatosFloyd;
import us.lsi.alg.floyd.FloydEdge;
import us.lsi.alg.floyd.FloydVertex;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.SimpleEdge;

public class FloydPD {
	
	public static record Sp(Boolean action,Double weight) implements Comparable<Sp> {
		
		public static Sp of(Boolean a, Double weight) {
			return new Sp(a, weight);
		}
		
		@Override
		public int compareTo(Sp sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	
	public static FloydVertex startVertex;
	
	public static FloydPD of(FloydVertex startVertex) {
		return new FloydPD(startVertex);
	}
	
	private FloydPD(FloydVertex startVertex) {
		FloydPD.startVertex = startVertex;
	}
	
	public Map<FloydVertex,Sp> search(){
		Map<FloydVertex,Sp> memory = new HashMap<>();
		search(FloydPD.startVertex,memory);
		return memory;
	}

	public Sp search(FloydVertex actual, Map<FloydVertex, Sp> memory) {
		Sp r = null;
		if (memory.containsKey(actual)) {
			r = memory.get(actual);
		} else if (actual.isBaseCase()) {
			Double w = actual.baseCaseWeight();
			if (w != null)
				r = Sp.of(null, w);
			memory.put(actual, r);
		} else {
			List<Sp> sps = new ArrayList<>();			
			for (Boolean a : actual.actions()) {
				List<Sp> spsa = new ArrayList<>();
				FloydEdge e = actual.edge(a);
				for (FloydVertex v : actual.neighbors(a)) {
					Sp spa = search(v, memory);
					if (spa == null) {
						spsa = null;
						break;
					}
					spsa.add(spa);
				}
				Sp sp = null;
				if (spsa!=null && !spsa.isEmpty()) {
					sp = Sp.of(a, e.weight(spsa.stream().map(s->s.weight()).toList()));
				}
				sps.add(sp);
			}
			r = sps.stream().filter(s -> s != null).min(Comparator.naturalOrder()).orElse(null);
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
		
		FloydPD a = FloydPD.of(start);
		
		Map<FloydVertex, Sp> r = a.search();
		
		
		System.out.println(a.solucion(start, r).getVertexList().stream()
				.map(i->DatosFloyd.graphI.vertex(i))
				.toList());
	}

}
