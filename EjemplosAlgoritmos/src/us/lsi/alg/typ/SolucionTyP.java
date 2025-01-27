package us.lsi.alg.typ;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;

import us.lsi.alg.typ.DatosTyP.Tarea;
import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public class SolucionTyP implements Comparable<SolucionTyP> {

	public static SolucionTyP of(TyPVertex lastVertex, List<Integer> acciones) {
		Map<Integer, List<Tarea>> carga = IntStream.range(0, DatosTyP.n).boxed()
				.collect(Collectors.groupingBy(t->acciones.get(t),
						Collectors.mapping(t->DatosTyP.tarea(t), Collectors.toList())));
		return SolucionTyP.of(carga, lastVertex.maxCarga(), lastVertex.npMin(), lastVertex.npMax());
	}

	public static SolucionTyP of(Map<Integer, List<Tarea>> carga, Double maxCarga, Integer npMin, Integer npMax) {
		return new SolucionTyP(carga, maxCarga, npMin, npMax);
	}

	public static SolucionTyP of(GraphPath<TyPVertex, SimpleEdgeAction<TyPVertex, Integer>> path) {
		Map<Integer, List<Tarea>> carga = path.getEdgeList().stream()
				.map(e -> IntPair.of(e.action(), e.source().index()))
				.collect(Collectors.groupingBy(p -> p.first(),
						Collectors.mapping(p -> DatosTyP.tarea(p.second()), Collectors.toList())));

		TyPVertex v = List2.last(path.getVertexList());
		return SolucionTyP.of(carga, v.maxCarga(), v.npMin(), v.npMax());
	}

	private Map<Integer, List<Tarea>> carga;
	private Double maxCarga;
	private Integer npMin;
	private Integer npMax;

	private SolucionTyP(Map<Integer, List<Tarea>> carga, Double maxCarga, Integer npMin, Integer npMax) {
		super();
		this.carga = carga;
		this.maxCarga = maxCarga;
		this.npMin = npMin;
		this.npMax = npMax;
	}

	@Override
	public String toString() {
		return String.format("(%s,%d,%d,%s)", maxCarga, npMin, npMax, carga);
	}

	public Map<Integer, List<Tarea>> getCarga() {
		return carga;
	}

	public Double getMaxCarga() {
		return maxCarga;
	}

	public Integer getNpMin() {
		return npMin;
	}

	public Integer getNpMax() {
		return npMax;
	}

	@Override
	public int compareTo(SolucionTyP other) {
		return this.getMaxCarga().compareTo(other.getMaxCarga());
	}

}
