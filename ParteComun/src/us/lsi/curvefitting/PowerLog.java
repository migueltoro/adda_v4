package us.lsi.curvefitting;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;

import us.lsi.common.Pair;


/**
 * @author migueltoro
 * 
 * Una curva de la forma a*n^b*(ln n)^c + d
 *
 */
public class PowerLog implements ParametricUnivariateFunction, Fit {
	
	public static Fit of(List<Pair<Integer, Double>> fixedParams) {
		return PowerLogFixingValues.of(fixedParams);
	}

	public static Fit of(List<Pair<Integer, Double>> fixedParamsvalues, Integer cp, Double minPv, Double maxPv,
			Double k) {
		return PowerLogFixingConsValues.of(fixedParamsvalues, cp, minPv, maxPv, k);
	}
	
	protected SimpleCurveFitter2 fitter = null;		
	protected Evaluation evaluation = null;;
	protected String expression = null;
	protected Function<Double, Double> function = null;
	
	public static PowerLog of() {
		return new PowerLog();
	}
	public PowerLog() {
		super();
		this.evaluation = null;
		this.fitter = SimpleCurveFitter2.create(this,new double[] { 1.,1.,1.,1.});
	}

	@Override
	public double[] gradient(double n, double... p) {
		Double a = p[0];
		Double b = p[1];
		Double c = p[2];
		Double a0 = Math.pow(n,b)*Math.pow(Math.log(n),c);
		Double b0 = a*Math.pow(n,b)*Math.pow(Math.log(n),c+1);
		Double c0 = a*Math.pow(n,b)*Math.log(Math.log(n))*Math.pow(Math.log(n),c);
		Double d0 = 1.;
		double[] r = {a0,b0,c0,d0};
		return r;
	}

	@Override
	public double value(double n, double... p) {
		Double a = p[0];
		Double b = p[1];
		Double c = p[2];
		Double d = p[3];
		return a*Math.pow(n,b)*Math.pow(Math.log(n),c) + d;
	}
	
	@Override
	public double[] fit(List<WeightedObservedPoint> points) {
		double[] r = this.fitter.fit(points);
		this.evaluation = this.fitter.getProblem(points).evaluate(RealVectors.toRealVector(r));
		this.expression = String.format("%.2f * n^%.2f * (ln n)^%.2f + %.2f", r[0], r[1], r[2], r[3]);
		this.function = x -> this.value(x, r);
		return r;
	}
	
	@Override
	public Evaluation getEvaluation() {
		return evaluation;
	}
	
	@Override
	public String getExpression() {
		return expression;
	}
	
	@Override
	public Function<Double, Double> getFunction() {
		return function;
	}
	@Override
	public void print(double n, double... p) {
		String r = String.format("Values: n = %.2f,a = %.2f,b = %.2f,c = %.2f,d = %.2f",n, p[0],p[1],p[2],p[3]);
		System.out.println(r);
		System.out.println("Value = "+this.value(n, p));
		double[] g = this.gradient(n, p);
		System.out.println("Gradiente = "+String.format("%.2f,%.2f,%.2f,%.2f",g[0],g[1],g[2],g[3]));
	}
	
	

}
