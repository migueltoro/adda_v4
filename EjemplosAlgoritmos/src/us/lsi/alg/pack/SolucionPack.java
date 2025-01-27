package us.lsi.alg.pack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;

import us.lsi.common.IntPair;
import us.lsi.common.List2;

public class SolucionPack implements Comparable<SolucionPack>{
	
	public static SolucionPack of(GraphPath<PackVertex, PackEdge> path) {
		return new SolucionPack(path);
	}

	public Map<Integer,List<Integer>> asignacion;

	private SolucionPack(GraphPath<PackVertex,PackEdge> path) {
		super();
		List<Integer> as = path.getEdgeList().stream().map(e->e.action()).toList();
		this.asignacion = IntStream.range(0,as.size()).boxed()
				.map(i->IntPair.of(i, as.get(i)))
				.collect(Collectors.toMap(
						e->e.second(),
						e->List2.of(e.first()),
						(List<Integer> a, List<Integer> b)->List2.concat(a,b)));
	}
	
	
	public Integer nc() {
		return this.asignacion.keySet().size();
	}
	

	@Override
	public String toString() {
		return "SolucionPack [nc ="+nc()+", carga ="+asignacion + "]";
	}

	@Override
	public int compareTo(SolucionPack other) {
		return this.nc().compareTo(other.nc());
	}
}
