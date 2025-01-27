package us.lsi.alg.matricesD;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.alg.matrices.DatosMatrices;
import us.lsi.hypergraphsD.HyperVertexD;
	
public record MatrixVertexD(Integer i,Integer j) 
		implements HyperVertexD<MatrixVertexD,MatrixEdgeD,Integer,String>{

		
		public static MatrixVertexD of(Integer i, Integer j) {
			return new MatrixVertexD(i, j);
		}
		
		public static MatrixVertexD initial() {
			return new MatrixVertexD(0, DatosMatrices.n);
		}

		@Override
		public MatrixVertexD me() {
			return this;
		}

		public Boolean isValid() {
			return i>=0 && i<=DatosMatrices.n && j>=i && j<=DatosMatrices.n;
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
			default -> null;
			};
		}
		
		@Override
		public String baseCaseSolution() {
			String r;
			if(j-i == 1) r = DatosMatrices.matrices.get(i).toString();
			else r = String.format("(%s * %s)",DatosMatrices.matrices.get(i),DatosMatrices.matrices.get(i+1));
			return r;
		}

		@Override
		public List<MatrixVertexD> neighbors(Integer a) {
			return Arrays.asList(MatrixVertexD.of(i, a),MatrixVertexD.of(a, j));
		}

		@Override
		public MatrixEdgeD edge(Integer a) {
			return MatrixEdgeD.of(this, this.neighbors(a),a);
		}

		@Override
		public String toString() {
			return String.format("(%d,%d)",i,j);
		}
	
}
