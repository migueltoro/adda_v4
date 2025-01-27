package us.lsi.alg.contenedores;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record VertexContenedoresI(Integer index, List<Integer> capRest)
    implements VertexContenedores {

	public static VertexContenedoresI of(Integer index,List<Integer> capRest) {
       return new VertexContenedoresI(index,capRest);	
	}
	
	public static VertexContenedoresI initial() {
		List<Integer> capRest = IntStream.range(0, DatosContenedores.getNumContenedores()+1).boxed()
			.map(j->DatosContenedores.getTamContenedor(j)).toList();
		return VertexContenedoresI.of(0,capRest);
	}

	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	public Boolean goal() {
		return this.index() == DatosContenedores.getNumElementos();
	}	
	
	@Override
	public Set<Integer>contenedoresCompletos(){
		return IntStream.range(0,DatosContenedores.getNumContenedores()).boxed()
			.filter(j->capRest.get(j).equals(0))
			.collect(Collectors.toSet());
	}
	
	@Override
	public Integer greedyAction() {
		Function<Integer,Integer> f = a->this.capRest().get(a)-DatosContenedores.getTamElemento(this.index());
		List<Integer> ls  = this.actions();
		return ls.stream()
				.min(Comparator.comparing(a->f.apply(a)))
				.get();
	}
	
	@Override
	public List<Integer> actions() {
		if(this.index().equals(DatosContenedores.getNumElementos())) return List.of();
        List<Integer> r = IntStream.range(0,DatosContenedores.getNumContenedores()+1).boxed()
        		.filter(j->!contenedoresCompletos().contains(j))
        		.filter(j->DatosContenedores.getPuedeUbicarse(this.index(), j))
        		.filter(j->DatosContenedores.getTamElemento(this.index()) <= this.capRest().get(j))
        		.toList();
        return r;
	}

	@Override
	public VertexContenedoresI neighbor(Integer a) {
		List<Integer> capRest2 = List2.copy(capRest);
		capRest2.set(a,capRest2.get(a) - DatosContenedores.getTamElemento(index));
		VertexContenedoresI r = VertexContenedoresI.of(index+1,capRest2);
		return r;
	}

	@Override
	public EdgeContenedores edge(Integer a) {
		return EdgeContenedores.of(this,this.neighbor(a),a);
	}
	
	public String toGraph() {
		return String.format("%d,%s",this.index(),this.capRest());
	}

}
