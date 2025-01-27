package us.lsi.alg.colorgraphs.manual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.alg.colorgraphs.ColorVertex;
import us.lsi.alg.colorgraphs.DatosColor;
import us.lsi.alg.colorgraphs.SolucionColor;
import us.lsi.graphs.manual.heaps.Heap;


public class AStarColor {
	
	public static record AStarDataColor(ColorVertex vertex, Integer a,ColorVertex lastVertex,
			Integer distanceToOrigin) {
		public static AStarDataColor of(ColorVertex vertex, Integer a,ColorVertex lastVertex,
			Integer distanceToOrigin) {
			return new AStarDataColor(vertex, a, lastVertex,distanceToOrigin);
		}	
	}
	
	public static AStarColor of() {
		return new AStarColor();
	}
	
	private Map<ColorVertex,AStarDataColor> tree;
	private Heap<ColorVertex,Integer> heap; 
	private Boolean goal;
	private Comparator<Integer> cmp;
	private Integer minValue;
	private SolucionColor solucion;
	private Long time;
	
	private AStarColor() {
		super();
	}
	
	private List<Integer> acciones(ColorVertex v) {
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
	
	public SolucionColor solucion() {
		return solucion;
	}

	public SolucionColor search(ColorVertex start,Integer minValue, SolucionColor s) {
		this.time = System.nanoTime();
		Integer distanceToEnd = start.nc();
		AStarDataColor a = AStarDataColor.of(start,null,null,0);
		this.cmp = Comparator.naturalOrder();
		this.heap = Heap.of(this.cmp);
		this.heap.add(start,distanceToEnd);
		this.tree = new HashMap<>();
		this.tree.put(start,a);
		this.goal = false;
		this.minValue = minValue;
		this.solucion = s;
		List<Integer> r = search();		
		this.time = System.nanoTime() - this.time;
		if(r==null) return this.solucion;
		return SolucionColor.of(r);
	}

	private Boolean forget(ColorVertex v) {
		Integer w = v.nc();
		Boolean r = this.minValue!=null && this.cmp.compare(w,this.minValue) >= 0;
		if(r) this.tree.remove(v);
		return r;
	}
	
	private List<Integer> search() {
		ColorVertex vertexActual = null;
		while (!heap.isEmpty() && !goal) {
			vertexActual = heap.remove();
			if(forget(vertexActual)) continue;
			for (Integer a : vertexActual.actions()) {
				ColorVertex v = vertexActual.neighbor(a);
				Integer newDistance = v.nc();
				Integer newDistanceToEnd = v.nc();				
				if (!tree.containsKey(v)) {	
					AStarDataColor ac = AStarDataColor.of(v, a, vertexActual, newDistance);
					heap.add(v,newDistanceToEnd);
					tree.put(v,ac);	
				}else if (cmp.compare(newDistance, tree.get(v).distanceToOrigin()) <0 ) {
					AStarDataColor ac = AStarDataColor.of(v, a, vertexActual, newDistance);
					tree.put(v,ac);
					heap.decrease(v,newDistanceToEnd);
				}
			}
			this.goal = vertexActual.index() == DatosColor.n;
		}
		if(!this.goal) return null;
		return acciones(vertexActual);
	}
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosColor.data(10,"ficheros/andalucia/andalucia.txt");
		
		AStarColor as = AStarColor.of();
		
		SolucionColor sv = GreedyColor.solucionVoraz(ColorVertex.first());
//		System.out.println(s);

		SolucionColor so = as.search(ColorVertex.first(),null,null);
		System.out.println(as.time());
		System.out.println(so);
		so = as.search(ColorVertex.first(),sv.nc(),sv);
		System.out.println(as.time());
		
		System.out.println(so);

	}
}
