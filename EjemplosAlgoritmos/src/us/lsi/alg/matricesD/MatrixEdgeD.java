package us.lsi.alg.matricesD;

import java.util.List;

import us.lsi.alg.matrices.DatosMatrices;
import us.lsi.hypergraphsD.HyperEdgeD;

public record MatrixEdgeD(MatrixVertexD source,List<MatrixVertexD> targets,Integer action) 
           implements HyperEdgeD<MatrixVertexD,MatrixEdgeD,Integer,String> {
	
	public static MatrixEdgeD of(MatrixVertexD source, List<MatrixVertexD> targets, Integer action) {
		return new MatrixEdgeD(source, targets, action);
	}
	
	public MatrixEdgeD me() {
		return this;
	}

	@Override
	public Double weight(List<Double> solutions) {		
		Double weight = solutions.get(0)+solutions.get(1);
		weight += DatosMatrices.nf(source.i())*DatosMatrices.nf(action())*DatosMatrices.nc(source.j()-1);
		return weight;
	}

	@Override
	public String solution(List<String> solutions) {
		return String.format("(%s) * (%s)",solutions.get(0),solutions.get(1));
	}

	@Override
	public String toString() {
		return String.format("%s",this.action());
	}
}
