package us.lsi.alg.mochila.manual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.alg.mochila.MochilaVertex;
import us.lsi.alg.mochila.SolucionMochila;
import us.lsi.graphs.manual.heaps.Heap;
import us.lsi.mochila.datos.DatosMochila;

public class MochilaAStar {
	
	public static class AStarMochila {
			
			private MochilaVertex vertex;
			private Integer a;
			private MochilaVertex lastVertex;
			private Double distanceToOrigin;
			
			private AStarMochila(MochilaVertex vertex, Integer a, MochilaVertex lastVertex,
					Double distanceToOrigin) {
				super();
				this.vertex = vertex;
				this.a = a;
				this.lastVertex = lastVertex;
				this.distanceToOrigin = distanceToOrigin;
			}
		
		public static AStarMochila of(MochilaVertex vertex, Integer a, MochilaVertex lastVertex,
			Double distanceToOrigin) {
			return new AStarMochila(vertex, a, lastVertex,distanceToOrigin);
		}
		
		public void set(Integer a, MochilaVertex lastVertex, Double distanceToOrigin) {
			this.a = a;
			this.lastVertex = lastVertex;
			this.distanceToOrigin = distanceToOrigin;
		}

		public MochilaVertex vertex() {
			return vertex;
		}

		public Integer a() {
			return a;
		}

		public MochilaVertex lastVertex() {
			return lastVertex;
		}

		public Double distanceToOrigin() {
			return distanceToOrigin;
		}
	}
	
	public static MochilaAStar of() {
		return new MochilaAStar();
	}
	
	public Long time() {
		return time;
	}
	
	private Integer maxValue;
	private SolucionMochila solucion;
	private Map<MochilaVertex,AStarMochila> tree;
	private Heap<MochilaVertex,Double> heap; 
	private Boolean goal;
	private Comparator<Double> cmp;
	private Long time;
	
	private MochilaAStar() {
		super();
	}
	
	private List<Integer> acciones(MochilaVertex v) {
		List<Integer> ls = new ArrayList<>();
		Integer a = this.tree.get(v).a();
		while (a != null) {
			ls.add(a);
			v = this.tree.get(v).lastVertex();
			a = this.tree.get(v).a();
		}
		Collections.reverse(ls);
		return ls;
	}

	public SolucionMochila search(MochilaVertex start, Integer maxValue, SolucionMochila s) {
		this.time = System.nanoTime();
		this.maxValue = maxValue;
		this.solucion = s;
		Double distanceToEnd = Heuristica.heuristica(start);
		AStarMochila a = AStarMochila.of(start, null,null,0.);
		this.cmp = Comparator.reverseOrder();
		this.heap = Heap.of(cmp);
		this.heap.add(start,distanceToEnd);
		this.tree = new HashMap<>();
		this.tree.put(start,a);
		this.goal = false;
		List<Integer> r = search();
		if(r==null) return this.solucion;
		this.time = System.nanoTime() - this.time;
		return SolucionMochila.of(r);
	}

	public List<Integer> search() {	
		MochilaVertex vertexActual = null;
		while (!heap.isEmpty() && !goal) {
			vertexActual = heap.remove();
			AStarMochila dataActual = tree.get(vertexActual);
			if(this.maxValue != null &&  
					(dataActual.distanceToOrigin()+Heuristica.heuristica(vertexActual)) <= this.maxValue) continue;
			for (Integer a : vertexActual.actions()) {
				MochilaVertex v = vertexActual.neighbor(a);
				Double newDistance = dataActual.distanceToOrigin()+a*DatosMochila.getValor(vertexActual.index());
				Double newDistanceToEnd = newDistance + Heuristica.heuristica(v);				
				if (!tree.containsKey(v)) {	
					AStarMochila ac = AStarMochila.of(v, a, vertexActual, newDistance);
					heap.add(v,newDistanceToEnd);
					tree.put(v,ac);	
				}else if (cmp.compare(newDistance, tree.get(v).distanceToOrigin()) <0 ) {
					AStarMochila ac = tree.get(v);
					ac.set(a, vertexActual, newDistanceToEnd);
					heap.decrease(v,newDistanceToEnd);
				}
			}
			this.goal = vertexActual.index() == DatosMochila.n;
		}
		if(!this.goal) return null;
		return acciones(vertexActual);
	}
	
	public SolucionMochila solucion(List<Integer> acciones) {
		return SolucionMochila.of(acciones);
	}
	
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMochila.iniDatos("ficheros/mochila/objetosMochila.txt");
		DatosMochila.capacidadInicial = 78;
		MochilaVertex v1 = MochilaVertex.of(0, DatosMochila.capacidadInicial);
		SolucionMochila sv = Heuristica.solucionVoraz(v1);
		MochilaAStar a = MochilaAStar.of();
		SolucionMochila s = a.search(v1,null,null);
		System.out.println(a.time() + "  === "+s);
		s = a.search(v1,sv.valor(),s);
		System.out.println(a.time() + "  === "+s);
	}

	

}
