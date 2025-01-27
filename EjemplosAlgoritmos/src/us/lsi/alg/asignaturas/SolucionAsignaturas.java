package us.lsi.alg.asignaturas;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;

import us.lsi.common.IntPair;

public record SolucionAsignaturas(Integer mejora, Map<Integer,List<Integer>> asignacion) implements Comparable<SolucionAsignaturas> {
	
	public  static SolucionAsignaturas of(GraphPath<AsignaturasVertice, AsignaturasEdge> s){
		return SolucionAsignaturas.of(s.getEdgeList().stream().map(e ->e.action()).toList());
	}
	
	
	public  static SolucionAsignaturas of(List<Integer>ls){
		Map<Integer,List<Integer>>asignacion = IntStream.range(0,ls.size()).boxed()
				.map(d->IntPair.of(d,ls.get(d)))
				.collect(Collectors.groupingBy(p->p.second(),Collectors.mapping(p->p.first(),Collectors.toList())));
		Integer mejora = asignacion.keySet().stream()
				.mapToInt(a->DatosAsignaturas.getMejora(asignacion.get(a).size(), a)).sum();
		return new SolucionAsignaturas(mejora,asignacion);
	}
	
	
	@Override
	public int compareTo(SolucionAsignaturas other) {
		return this.mejora.compareTo(other.mejora);
	}

}
