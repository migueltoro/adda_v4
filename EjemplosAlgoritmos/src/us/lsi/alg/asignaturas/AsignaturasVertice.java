package us.lsi.alg.asignaturas;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record AsignaturasVertice(Integer index,List<Integer>diasAsignatura,Integer lastAsignatura) implements VirtualVertex<AsignaturasVertice,AsignaturasEdge,Integer> {

	public static AsignaturasVertice inicial() {
		return new AsignaturasVertice(0,List2.nCopies(0,DatosAsignaturas.NA),null);
	}

	public static AsignaturasVertice of(Integer index,List<Integer> diasAsignatura,Integer lastAsignatura) {
		return new AsignaturasVertice(index,diasAsignatura,lastAsignatura);
	}
	
	public static AsignaturasVertice copy(AsignaturasVertice v) {
		return new AsignaturasVertice(v.index(),v.diasAsignatura(),v.lastAsignatura());
	}

	@Override
	public Boolean isValid() {
		return this.index>=0 &&this.index<=DatosAsignaturas.ND;
	}
	
	@Override
	public Boolean goalHasSolution() {
		return this.diasAsignatura.stream().allMatch(da->da>=1 && da<=4);
	}
	
	@Override
	public Boolean goal() {
		return this.index==DatosAsignaturas.ND;
	}
	
	@Override
	public Integer greedyAction() {
		Random r = new Random();
		List<Integer> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
	}
	
	public Optional<Integer> mejor() {
		return IntStream.range(0,DatosAsignaturas.NA).boxed()
				.filter(a->!a.equals(this.lastAsignatura()))
				.filter(a->this.diasAsignatura.get(a) < 4)
				.max(Comparator.comparing(a->DatosAsignaturas.getMejora(this.diasAsignatura().get(a)+1,a)));
	}

	@Override
	public List<Integer> actions() {
		List<Integer> actions = List2.empty();
		if(this.index==DatosAsignaturas.ND-1) {
			Optional<Integer> mejor = this.mejor();
			actions= mejor.isPresent()?List2.of(mejor.get()):actions;	
		} else {
			actions = IntStream.range(0,DatosAsignaturas.NA)
					.boxed()
					.filter(a->!a.equals(this.lastAsignatura()))
					.filter(a->this.diasAsignatura.get(a) < 4)
					.toList();
			actions = actions.stream()
					.sorted(Comparator.comparing(a->-DatosAsignaturas.getMejora(this.diasAsignatura().get(a)+1,a)))
					.toList();
		}	
		return actions;
	}

	@Override
	public AsignaturasVertice neighbor(Integer a) {
		return of(this.index+1,List2.setElement(this.diasAsignatura,a,this.diasAsignatura.get(a)+1),a);
	}

	@Override
	public AsignaturasEdge edge(Integer a) {
		return AsignaturasEdge.of(this, this.neighbor(a),a);
	}

	public Integer getPeso() {
		return IntStream.range(0, DatosAsignaturas.NA)
				.map(i->DatosAsignaturas.getMejora(this.diasAsignatura.get(i),i))
				.sum();
	}

	public static Boolean goal(AsignaturasVertice c) {
		return c.index == 10;
	}
	
	public String string() {
		return String.format("(%d,%s,%s)",this.getPeso(),this.index(),this.diasAsignatura());
	}
	
}

