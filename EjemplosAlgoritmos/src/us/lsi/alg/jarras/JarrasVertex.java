package us.lsi.alg.jarras;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import us.lsi.graphs.virtual.Action;
import us.lsi.graphs.virtual.VirtualVertex;

public record JarrasVertex(Integer c1,Integer c2) implements VirtualVertex<JarrasVertex,JarrasEdge,Action<JarrasVertex>> {

	public static void data(
			Integer cantidadFinalEnJarra1,
			Integer cantidadFinalEnJarra2,
			Integer cantidadInicialEnJarra1,
			Integer cantidadInicialEnJarra2,
			Integer capacidadJarra1,
			Integer capacidadJarra2) {
		JarrasVertex.cF1 = cantidadFinalEnJarra1;
		JarrasVertex.cF2 = cantidadFinalEnJarra2;
		JarrasVertex.cI1 = cantidadInicialEnJarra1;
		JarrasVertex.cI2 = cantidadInicialEnJarra2;
		JarrasVertex.cP1 = capacidadJarra1;
		JarrasVertex.cP2 = capacidadJarra2;
	}
	
	
	public static JarrasVertex of(Integer cJ1, Integer cJ2) {
		return new JarrasVertex(cJ1, cJ2);
	}
	
	public static JarrasVertex first() {
		return new JarrasVertex(JarrasVertex.cI1,JarrasVertex.cI2);
	}
	
	public static JarrasVertex last() {
		return new JarrasVertex(JarrasVertex.cF1,JarrasVertex.cF2);
	}
	
	public static Integer cF1;
	public static Integer cF2;
	public static Integer cI1;
	public static Integer cI2;
	public static Integer cP1;
	public static Integer cP2;

	@Override
	public Boolean isValid() {
		return this.c1 >=0 && this.c1 <= JarrasVertex.cP1 && 
				this.c2 >=0 && this.c2 <= JarrasVertex.cP2;
	}

	@Override
	public List<Action<JarrasVertex>> actions() {
		return JarrasAction.actions.stream().filter(a->a.isApplicable(this)).collect(Collectors.toList());
	}

	@Override
	public JarrasVertex neighbor(Action<JarrasVertex> a) {
		JarrasVertex r = a.neighbor(this);
		System.out.println(r);
		return r;
	}

	@Override
	public JarrasEdge edge(Action<JarrasVertex> a) {
		return JarrasEdge.of(this,a.neighbor(this),a);
	}


	@Override
	public Boolean goal() {
		return this.equals(JarrasVertex.last());
	}

	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	@Override
	public Action<JarrasVertex> greedyAction() {
		Random r = new Random();
		List<Action<JarrasVertex>> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
	}
	
}
