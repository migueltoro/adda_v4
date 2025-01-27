package us.lsi.alg.matrices;

public class MatrixInf {
	
	public static MatrixInf of(Integer nf, Integer nc) {
		return new MatrixInf(nf, nc);
	}
	
	public Integer nf;
	public Integer nc;

	private MatrixInf(Integer nf, Integer nc) {
		super();
		this.nf = nf;
		this.nc = nc;
	}

	@Override
	public String toString() {
		return "[" + nf + "," + nc + "]";
	}

}
