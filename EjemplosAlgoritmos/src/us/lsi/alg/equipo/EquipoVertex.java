package us.lsi.alg.equipo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.common.IntegerSet;
import us.lsi.graphs.virtual.VirtualVertex;

public record EquipoVertex(Integer index,IntegerSet players) implements VirtualVertex<EquipoVertex, EquipoEdge, Integer> {
	
	public static EquipoVertex copy(EquipoVertex v) {
		return new EquipoVertex(v.index(), v.players());
	}

	public static EquipoVertex first() {
		return of(0, IntegerSet.range(0,DatosEquipo.N));
	}

	public static EquipoVertex of(Integer index, IntegerSet players) {
		return new EquipoVertex(index, players);
	}

	public EquipoVertex(Integer index, IntegerSet players) {
		this.index = index;
		this.players = players;
	}
	
	public static SolucionEquipo getSolucion(GraphPath<EquipoVertex, EquipoEdge> path) {
		return EquipoVertex.getSolucion(path.getEdgeList());
	}

	public static SolucionEquipo getSolucion(List<EquipoEdge> aristas) {
		List<Integer> ls = aristas.stream()
				.map(e->e.action())
				.collect(Collectors.toList());
		
		Integer[] v = new Integer[DatosEquipo.N];
		Arrays.fill(v, -1);
		for(int i=0; i<ls.size(); i++) {
			v[ls.get(i)]=i;
		}
		return SolucionEquipo.create(Arrays.asList(v));
	}
	
	@Override
	public Boolean goal() {
		return this.index == DatosEquipo.M;
	}

	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	@Override
	public Integer greedyAction() {
		Random r = new Random();
		List<Integer> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
	}
	
	public Optional<Integer> mejorEnPosicion(Integer p) {
		Optional<Integer> mejor = players.stream()
				.filter(i -> DatosEquipo.getR(i,p) > 7)
				.max(Comparator.comparing(i -> DatosEquipo.getR(i,p)));
		return mejor;
	}
	
	public Optional<Integer> random(Integer p) {
		Optional<Integer> mejor = players.stream()
				.filter(i -> DatosEquipo.getR(i,p) > 7)
				.findAny();
		return mejor;
	}
	
	public Optional<Integer> randomSin(Integer p) {
		Optional<Integer> mejor = players.stream()
				.findAny();
		return mejor;
	}
	
	public Optional<Integer> mejorEnPosicionSin(Integer p) {
		Optional<Integer> mejor = players.stream()
				.max(Comparator.comparing(i -> DatosEquipo.getR(i,p)));
		return mejor;
	}

	@Override
	public Boolean isValid() {
		return index >= 0 && index <= DatosEquipo.M;
	}

	@Override
	public List<Integer> actions() {
		if (index == DatosEquipo.M) {
			return List.of();
		} else if (index == DatosEquipo.M - 1) {
			Optional<Integer> mejor = mejorEnPosicion(index);
			return mejor.isPresent() ? List.of(mejor.get()) : List.of();
		} else {
			return players.stream()
					.filter(i->DatosEquipo.getR(i, index) > 7)
					.collect(Collectors.toList());
		}
	}

	@Override
	public EquipoVertex neighbor(Integer a) {
		return EquipoVertex.of(index + 1,players.removeF(a));
	}

	@Override
	public EquipoEdge edge(Integer a) {
		return EquipoEdge.of(this, neighbor(a), a);
	}

	public String toString() {
		return String.format("Indice: %d; Jugadores: %s", index, players);
	}

}