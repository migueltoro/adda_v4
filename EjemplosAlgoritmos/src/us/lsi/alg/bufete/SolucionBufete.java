package us.lsi.alg.bufete;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.common.IntPair;
import us.lsi.common.List2;

public record SolucionBufete(Map<Integer,List<Integer>> asignacion,List<Integer> cargas, Integer maxCarga,
		Integer npMin,Integer npMax) implements Comparable<SolucionBufete>{
		
		public static SolucionBufete of(Map<Integer, List<Integer>> asignacion, List<Integer> cargas, 
				Integer maxCarga, Integer npMin, Integer npMax) {
			return new SolucionBufete(asignacion,cargas,maxCarga,npMin,npMax);
		}
		
		public static SolucionBufete of(GraphPath<BufeteVertex,BufeteEdge> path){	
			Map<Integer,List<Integer>> asignacion = path.getEdgeList().stream()
					.map(e->IntPair.of(e.action(),e.source().index()))
					.collect(Collectors.groupingBy(p->p.first(),
							Collectors.mapping(p->p.second(), Collectors.toList())));		
			BufeteVertex v = List2.last(path.getVertexList());
			return SolucionBufete.of(asignacion,v.cargas(), v.maxCarga(), v.npMin(), v.npMax());
		}
		
		public String toString2() {
			return String.format("(%d,%d,%d,%s)",maxCarga,npMin,npMax,cargas);
		}
		
		@Override
		public int compareTo(SolucionBufete other) {
			return this.maxCarga().compareTo(other.maxCarga());
		}
		
}

