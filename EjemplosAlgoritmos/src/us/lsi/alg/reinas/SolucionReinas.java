package us.lsi.alg.reinas;

import java.util.Objects;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public class SolucionReinas implements Comparable<SolucionReinas> {
	
	public static SolucionReinas of(GraphPath<ReinasVertex,SimpleEdgeAction<ReinasVertex,Integer>> path) {	
		return SolucionReinas.of(List2.last(path.getVertexList()));
	}
	
	public static SolucionReinas of(ReinasVertex lastVertex) {		
		SolucionReinas r = new SolucionReinas(lastVertex);
		if(r.errores >0) r = null;
		return r;
	}

//	private GraphPath<ReinasVertex, ReinasEdge> path;
	private ReinasVertex lastVertex;
	private Integer error1;
	private Integer error2;
	private Integer error3;
	private Integer errores;

	private SolucionReinas(ReinasVertex lastVertex) {
		super();
		this.lastVertex  = lastVertex;
		this.error1 = (ReinasVertexI.n - this.lastVertex.fo().stream().collect(Collectors.toSet()).size());
		this.error2 = ReinasVertexI.n - this.lastVertex.dpo().size();
		this.error3 = ReinasVertexI.n - this.lastVertex.dso().size();
		this.errores = this.error1+this.error2+this.error3;
	}

	@Override
	public String toString() {
		return String.format("\nErrores %d\n%s",this.errores,this.lastVertex.fo());
	}

	@Override
	public int compareTo(SolucionReinas other) {
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastVertex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolucionReinas other = (SolucionReinas) obj;
		return Objects.equals(lastVertex, other.lastVertex);
	}
	
	
	
}
