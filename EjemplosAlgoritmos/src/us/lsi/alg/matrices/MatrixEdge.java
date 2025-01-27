package us.lsi.alg.matrices;


import java.util.List;
import us.lsi.hypergraphs.SimpleHyperEdge;

public record MatrixEdge(MatrixVertex source,Integer action) 
           implements SimpleHyperEdge<MatrixVertex,MatrixEdge,Integer> {
	
	public static MatrixEdge of(MatrixVertex source, Integer action) {
		return new MatrixEdge(source, action);
	}

	public List<MatrixVertex> targets() {
		return this.source.neighbors(action);
	}

	@Override
	public Double weight(List<Double> solutions) {		
		Double weight = solutions.get(0)+solutions.get(1);
		weight += DatosMatrices.nf(source.i())*DatosMatrices.nf(action())*DatosMatrices.nc(source.j()-1);
		return weight;
	}
}
