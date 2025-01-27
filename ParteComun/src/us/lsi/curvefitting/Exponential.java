package us.lsi.curvefitting;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;

/**
 * @author migueltoro
 *
 * Función de la forma a*b^n
 */
public class Exponential implements ParametricUnivariateFunction, Fit {
	
	private SimpleCurveFitter2 fitter = null;		
	private Evaluation evaluation;
	protected String expression = null;
	protected Function<Double, Double> function = null;
	
	public static Exponential of() {
		return new Exponential();
	}
	
	public Exponential() {
		super();
		this.evaluation = null;
		this.fitter = SimpleCurveFitter2.create(this,new double[] { 1., 1.});
	}

	@Override
	public double[] gradient(double n, double... p) {
		Double a = p[0];
		Double b = p[1];
		Double a0 = Math.pow(b,n);
		Double b0 = a*n*Math.pow(b,n-1);
		double[] r = {a0,b0};
		return r;
	}

	@Override
	public double value(double n, double... p) {
		Double a = p[0];
		Double b = p[1];
		return a*Math.pow(b,n);
	}
	
	public double[] fit(List<WeightedObservedPoint> points) {
		double[] r = this.fitter.fit(points);
		this.evaluation = this.fitter.getProblem(points).evaluate(RealVectors.toRealVector(r));
		this.expression = String.format("%.2f*%.2f^n", r[0],r[1]);
		this.function = x -> this.value(x, r);
		return r;
	}
	
	public Evaluation getEvaluation() {
		return evaluation;
	}
	
	public String getExpression() {
		return expression;
	}

	public Function<Double, Double> getFunction() {
		return function;
	}

	public void print(double n, double... p) {
		String r = String.format("Values: n = %.2f,p = %s",n,Arrays.stream(p).boxed()
				.map(x->String.format("%.2f", x)).collect(Collectors.joining(",")));
		System.out.println(r);
		System.out.println("Value = "+this.value(n, p));
		double[] g = this.gradient(n, p);
		System.out.println("Gradiente = "+String.format("%s",Arrays.stream(g).boxed()
				.map(x->String.format("%.2f", x)).collect(Collectors.joining(","))));
	}

}

