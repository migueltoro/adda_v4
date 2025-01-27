package us.lsi.curvefitting;

import java.util.List;

import us.lsi.common.Pair;

public class PowerLogFixingConsValues extends PowerLogFixingValues {
	
	public static PowerLogFixingConsValues of(List<Pair<Integer, Double>> fixedParamsvalues, Integer cp, Double minPv,
			Double maxPv, Double k) {
		return new PowerLogFixingConsValues(fixedParamsvalues, cp, minPv, maxPv, k);
	}

	private Integer cp;
	private Double minPv;
	private Double maxPv;
	private Double k;
	
	
	private PowerLogFixingConsValues(List<Pair<Integer, Double>> fixedParamsvalues, Integer cp, Double minPv,
			Double maxPv, Double k) {
		super(fixedParamsvalues);
		this.cp = cp;
		this.minPv = minPv;
		this.maxPv = maxPv;
		this.k = k;
	}
	
	@Override
	public double value(double n, double... p) {
		double r = super.value(n, p);
		double x = p[cp];
		r = r+((x>=minPv && x<=maxPv)?0.:k*(x*x-(minPv+maxPv)*x+minPv*maxPv));
		return r;
	}
	
	@Override
	public double[] gradient(double n, double... p) {
		double[] g2 = super.gradient(n, p);
		double x = p[cp];
		g2[cp] = g2[cp]+k*((x>=minPv && x<=maxPv)?0.:(2*x-(minPv+maxPv)));
		return g2;		
	}
	
}
