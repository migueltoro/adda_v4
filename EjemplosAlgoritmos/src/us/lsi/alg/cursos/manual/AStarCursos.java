package us.lsi.alg.cursos.manual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jheaps.AddressableHeap.Handle;
import org.jheaps.tree.FibonacciHeap;

import us.lsi.alg.cursos.CursosHeuristic;
import us.lsi.alg.cursos.CursosVertex;
import us.lsi.alg.cursos.CursosVertexI;
import us.lsi.alg.cursos.DatosCursos;
import us.lsi.alg.cursos.SolucionCursos;

public class AStarCursos {

	public static record AStarDataCursos(CursosVertex vertex, Integer a, CursosVertex lastVertex,
			Double distanceToOrigin) {
		public static AStarDataCursos of(CursosVertex vertex, Integer a, CursosVertex lastVertex,
			Double distanceToOrigin) {
			return new AStarDataCursos(vertex, a, lastVertex,distanceToOrigin);
		}	
	}
	
	public static AStarCursos of() {
		return new AStarCursos();
	}
	
	public Long time() {
		return time;
	}
	
	private Integer minValue;
	private SolucionCursos solucion;
	private Map<CursosVertex,Handle<Double,AStarDataCursos>> tree;
	private FibonacciHeap<Double,AStarDataCursos> heap; 
	private Boolean goal;
	private Comparator<Double> cmp;
	private Long time;
	
	private AStarCursos() {
		super();
	}
	
	private List<Integer> acciones(CursosVertex v) {
		List<Integer> ls = new ArrayList<>();
		Integer a = this.tree.get(v).getValue().a();
		while (a != null) {
			ls.add(a);
			v = this.tree.get(v).getValue().lastVertex();
			a = this.tree.get(v).getValue().a();
		}
		Collections.reverse(ls);
		return ls;
	}

	public SolucionCursos search(CursosVertex start, Integer minValue, SolucionCursos s) {
		this.time = System.nanoTime();
		this.minValue = minValue;
		this.solucion = s;
		Double distanceToEnd = CursosHeuristic.heuristic(start,x->x.goal(),null);
		AStarDataCursos a = AStarDataCursos.of(start, null,null,0.);
		this.cmp = Comparator.naturalOrder();
		this.heap = new FibonacciHeap<>(cmp);
		Handle<Double, AStarDataCursos> h = this.heap.insert(distanceToEnd,a);
		this.tree = new HashMap<>();
		this.tree.put(start,h);
		this.goal = false;
		List<Integer> r = search();
		if(r==null) return this.solucion;
		this.time = System.nanoTime() - this.time;
		return SolucionCursos.of(r);
	}

	public List<Integer> search() {		
		CursosVertex vertexActual = null;
		while (!heap.isEmpty() && !goal) {
			Handle<Double, AStarDataCursos> ha = heap.deleteMin();
			AStarDataCursos dataActual = ha.getValue();
			vertexActual = dataActual.vertex();
			if(this.minValue != null &&  CursosHeuristic.heuristic(vertexActual,v->v.goal(),null) >= this.minValue) continue;
			for (Integer a : vertexActual.actions()) {
				CursosVertex v = vertexActual.neighbor(a);
				Double newDistance = dataActual.distanceToOrigin()+a*DatosCursos.getCoste(vertexActual.index());
				Double newDistanceToEnd = newDistance + CursosHeuristic.heuristic(v,x->x.goal(),null);				
				if (!tree.containsKey(v)) {	
					AStarDataCursos ac = AStarDataCursos.of(v, a, vertexActual, newDistance);
					Handle<Double, AStarDataCursos> nh = heap.insert(newDistanceToEnd,ac);
					tree.put(v,nh);	
				}else if (cmp.compare(newDistance, tree.get(v).getValue().distanceToOrigin()) <0 ) {
					AStarDataCursos ac = AStarDataCursos.of(v, a, vertexActual, newDistance);
					Handle<Double, AStarDataCursos> hv = tree.get(v);
					hv.setValue(ac);
					hv.decreaseKey(newDistanceToEnd);
				}
			}
			this.goal = vertexActual.index() == DatosCursos.n && 
					vertexActual.remaining().isEmpty() &&
					vertexActual.centers().size() <= DatosCursos.maxCentros;
		}
		if(!this.goal) return null;
		return acciones(vertexActual);
	}
	
	public SolucionCursos solucion(List<Integer> acciones) {
		return SolucionCursos.of(acciones);
	}
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosCursos.iniDatos("ficheros/cursos/cursos3.txt");
		CursosVertexI v1 = CursosVertexI.first();
		SolucionCursos sv = GreedyCursos.solucionVoraz(v1);
		System.out.println(sv);
		AStarCursos a = AStarCursos.of();
		SolucionCursos s = a.search(v1,null,null);
		System.out.println(a.time() + "  === "+s);
		s = a.search(v1,sv.precio().intValue(),s);
		System.out.println(a.time() + "  === "+s);
	}


}
