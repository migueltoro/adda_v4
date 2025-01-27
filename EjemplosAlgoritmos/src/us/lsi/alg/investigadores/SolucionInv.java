package us.lsi.alg.investigadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.common.IntPair;

public class SolucionInv {

	private Map<IntPair,Integer> diasInvAsignados;
	private Integer fo;
	
	public static SolucionInv of(Map<IntPair, Integer> diasInvAsignados, Integer fo) {
		return new SolucionInv(diasInvAsignados, fo);
	}
	
	public static SolucionInv of(InvVertex start,List<Integer> alternativas) {
		Map<IntPair,Integer> diasInvAsignados = new HashMap<>();
		InvVertex v = start;
		for(Integer a:alternativas) {
			diasInvAsignados.put(v.pij(),a);
			v = v.neighbor(a);
		}
		if(!v.goalHasSolution())
			System.out.println(diasInvAsignados);
//		Preconditions.checkArgument(InvVertex.goalHasSolution().test(v),"La solucion no es valida");
		
		return SolucionInv.of(diasInvAsignados, v.fo());
	}
	
	public static InvVertex lastVertex(InvVertex start,List<Integer> alternativas) {
		Map<IntPair,Integer> diasInvAsignados = new HashMap<>();
		InvVertex v = start;
		for(Integer a:alternativas) {
			diasInvAsignados.put(v.pij(),a);
			v = v.neighbor(a);
		}
		if(!v.goalHasSolution())
			System.out.println(diasInvAsignados);
//		Preconditions.checkArgument(InvVertex.goalHasSolution().test(v),"La solucion no es valida");
		
		return v;
	}
	
	public static SolucionInv of(GraphPath<InvVertex,InvEdge> path) {
		Map<IntPair,Integer> diasInvAsignados = new HashMap<>();
		List<InvEdge> edgeList = path.getEdgeList();
		edgeList.stream().forEach(e->diasInvAsignados.put(e.source().pij(),e.action()));
		Integer fo = path.getEndVertex().fo();
		if(!path.getEndVertex().goalHasSolution())
			System.out.println(diasInvAsignados);
//		Preconditions.checkArgument(InvVertex.goalHasSolution().test(path.getEndVertex()),"La solucion no es valida");
		return of(diasInvAsignados, fo);
	}
	
	private SolucionInv(Map<IntPair, Integer> diasInvAsignados, Integer fo) {
		super();
		this.diasInvAsignados = diasInvAsignados;
		this.fo = fo;
	}
	
	public Integer fo() {
		return fo;
	}

	public Integer diasDeInvEnTrabajo(Integer i, Integer j) {
		IntPair p = IntPair.of(i,j);
		return this.diasInvAsignados.getOrDefault(p,0);
	}
	
	public String toString() {
		String r = this.diasInvAsignados.keySet().stream()
				.filter(p->this.diasInvAsignados.get(p) > 0)
				.map(p -> String.format("(%d,%d) -> %d", p.first(), p.second(), this.diasInvAsignados.get(p)))
				.collect(Collectors.joining("\n","Investigador, Trabajo  -> dias\n",""));
		return String.format("Fo = %d\n%s", this.fo, r);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
