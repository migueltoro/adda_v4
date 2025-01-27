package us.lsi.p4.ej_2.manual;


import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import us.lsi.common.Set2;
import us.lsi.p4.ej_2.DatosSubconjuntos;

public record SubconjuntosProblem(Integer index, Set<Integer> remaining) {
	
	public static SubconjuntosProblem initial() {
		return of(0, Set2.copy(DatosSubconjuntos.universo()));
	}

	public static SubconjuntosProblem of(Integer i, Set<Integer> rest) {
		return new SubconjuntosProblem(i, rest);
	}
	
	public List<Integer> actions() {
		List<Integer> r;
		if(index == DatosSubconjuntos.NUM_SC) r = List.of();
		else if(this.index == DatosSubconjuntos.NUM_SC-1) {
			if(remaining.isEmpty()) r = List.of(0);
			else if(DatosSubconjuntos.conjunto(index).containsAll(this.remaining)) r = List.of(1);
			else r = List.of();
		} else {
			Set<Integer> rest = Set2.difference(remaining, DatosSubconjuntos.conjunto(index));
			if(rest.equals(remaining)) r = List.of(0);
			else r = List.of(1,0);
		}
		return r;
	}
	
	public SubconjuntosProblem neighbor(Integer a) {
		Set<Integer> rest = a==0? Set2.copy(remaining):
			Set2.difference(remaining, DatosSubconjuntos.conjunto(index));
		return of(index+1, rest);
	}
	
	public Double heuristic() {
		return heuristic1();
	//	return heuristic0();
	}
	
	public Double heuristic0() {
		return 0.;
	}

	public Double heuristic1() {
		if (remaining.isEmpty())  return 0.;
		else return IntStream.range(index,DatosSubconjuntos.NUM_SC)
				.mapToDouble(i->DatosSubconjuntos.peso(i))
				.min()
				.getAsDouble();
	}

	
	
}
