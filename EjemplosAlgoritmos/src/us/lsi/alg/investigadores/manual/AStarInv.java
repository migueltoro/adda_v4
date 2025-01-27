package us.lsi.alg.investigadores.manual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.alg.investigadores.DatosInv;
import us.lsi.alg.investigadores.InvVertex;
import us.lsi.alg.investigadores.SolucionInv;
import us.lsi.graphs.manual.heaps.Heap;


public class  AStarInv {
	
	public static record AStarDataInv(InvVertex vertex, Integer a,InvVertex lastVertex,
			Integer distanceToOrigin) {
		public static AStarDataInv of(InvVertex vertex, Integer a,InvVertex lastVertex,
			Integer distanceToOrigin) {
			return new AStarDataInv(vertex, a, lastVertex,distanceToOrigin);
		}	
	}
	
	public static AStarInv of() {
		return new AStarInv();
	}
	
	private Map<InvVertex,AStarDataInv> tree;
	private Heap<InvVertex,Integer> heap; 
	private Boolean goal;
	private Comparator<Integer> cmp;
	private Integer maxValue;
	private SolucionInv solucion;
	private Long time;
	
	private AStarInv() {
		super();
	}
	
	private List<Integer> acciones(InvVertex v) {
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
	
	public Long time() {
		return time;
	}
	
	public SolucionInv solucion() {
		return solucion;
	}

	public SolucionInv search(InvVertex start,Integer maxValue, SolucionInv s) {
		this.time = System.nanoTime();
		Integer distanceToEnd = start.fo();
		AStarDataInv a = AStarDataInv.of(start,null,null,0);
		this.cmp = Comparator.reverseOrder();
		this.heap = Heap.of(cmp);
		this.heap.add(start,distanceToEnd);
		this.tree = new HashMap<>();
		this.tree.put(start,a);
		this.goal = false;
		this.maxValue = maxValue;
		this.solucion = s;
		List<Integer> r = search();		
		this.time = System.nanoTime() - this.time;
		if(r==null) return this.solucion;
		return SolucionInv.of(start,r);
	}

	private Boolean forget(InvVertex v) {
		Integer w = v.fo();
		Boolean r = this.maxValue!=null && this.cmp.compare(w,this.maxValue) >= 0;
		if(r) this.tree.remove(v);
		return r;
	}
	
	private static Predicate<InvVertex> goalHasSolution() {
		return v -> v.index() == DatosInv.na && IntStream.range(0, DatosInv.m).boxed()
				.allMatch(t -> v.esTrabajoAcabado(t) || !v.esTrabajoIniciado(t));
	}
	
	private List<Integer> search() {
		InvVertex vertexActual = null;
		while (!heap.isEmpty() && !goal) {
			vertexActual = heap.remove();
			if(forget(vertexActual)) continue;
			for (Integer a : vertexActual.actions()) {
				InvVertex v = vertexActual.neighbor(a);
				Integer newDistance = v.fo();
				Integer newDistanceToEnd = v.fo();				
				if (!tree.containsKey(v)) {	
					AStarDataInv ac = AStarDataInv.of(v, a, vertexActual, newDistance);
					heap.add(v,newDistanceToEnd);
					tree.put(v,ac);	
				}else if (cmp.compare(newDistance, tree.get(v).distanceToOrigin()) < 0 ) {
					AStarDataInv ac = AStarDataInv.of(v, a, vertexActual, newDistance);
					tree.put(v,ac);	
					heap.decrease(v,newDistanceToEnd);
				}
			}
			this.goal = goalHasSolution().test(vertexActual);
		}
		if(!this.goal) return null;
		return acciones(vertexActual);
	}
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosInv.iniDatos("ficheros/investigadores/inv3.txt");
		
		AStarInv as = AStarInv.of();
		
		SolucionInv sv = GreedyInv.solucionVoraz(InvVertex.first());
		System.out.println("1 = "+ sv);

		SolucionInv so = as.search(InvVertex.first(),null,null);
		System.out.println("2 = "+ as.time());
		System.out.println("3 = "+ so);
		so = as.search(InvVertex.first(),sv.fo(),sv);
		System.out.println("4 = "+ as.time());
		System.out.println("5 = "+  so);
//		so = as.search(InvVertex.first(),null,null);
//		System.out.println(as.time());
//		
//		System.out.println(so);

	}

}
