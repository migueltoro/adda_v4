package us.lsi.alg.matrices;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.hypergraphs.VirtualHyperVertex;

public record MatrixVertex(Integer i,Integer j) 
		implements VirtualHyperVertex<MatrixVertex,MatrixEdge,Integer,String>{

	
	public static MatrixVertex of(Integer i, Integer j) {
		return new MatrixVertex(i, j);
	}
	
	public static MatrixVertex initial() {
		return new MatrixVertex(0, DatosMatrices.n);
	}

	@Override
	public Boolean isValid() {
		return 0 <= i && i < DatosMatrices.n && i < j && j<=DatosMatrices.n;
	}

	@Override
	public List<Integer> actions() {
		return IntStream.range(i+1,j).boxed().collect(Collectors.toList());
	}

	@Override
	public Boolean isBaseCase() {
		return j-i < 3;
	}

	@Override
	public Double baseCaseWeight() {
		Integer d = j-i;
		return switch(d) {
		case 0 -> 0.; 
		case 1 -> 0.; 
		case 2 -> (double) DatosMatrices.nf(i)*DatosMatrices.nc(i)*DatosMatrices.nc(j-1); 
		default ->  null;
		};
	}

	@Override
	public List<MatrixVertex> neighbors(Integer a) {
		return Arrays.asList(MatrixVertex.of(i, a),MatrixVertex.of(a, j));
	}

	@Override
	public MatrixEdge edge(Integer a) {
		return MatrixEdge.of(this, a);
	}

	@Override
	public String baseCaseSolution() {
		String r;
		if(j-i == 1) r = DatosMatrices.matrices.get(i).toString();
		else r = String.format("(%s * %s)",DatosMatrices.matrices.get(i).toString(),DatosMatrices.matrices.get(i+1).toString());
		return r;
	}

	@Override
	public String solution(List<String> solutions) {
		return String.format("(%s * %s)",solutions.get(0),solutions.get(1));
	}
	
	
	
}
