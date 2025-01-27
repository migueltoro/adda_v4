package us.lsi.curvefitting;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;

public class Polynomial implements Fit{
	
	public static Polynomial of(Integer grado) {
		return new Polynomial(grado);
	}

	private Integer grado;
	private PolynomialCurveFitter2 fitter = null;		
	private Evaluation evaluation;
	protected String expression = null;
	protected Function<Double, Double> function = null;
	
	private Polynomial(Integer grado) {
		super();
		this.grado = grado;
		this.fitter = PolynomialCurveFitter2.create(grado);
	}

	public double[] fit(List<WeightedObservedPoint> points) {
		double[] r = this.fitter.fit(points);
		this.evaluation = this.fitter.getProblem(points).evaluate(RealVectors.toRealVector(r));
		this.expression = IntStream.range(0, this.grado+1).boxed()
				.map(i->String.format(i>0?"%.2f * x^%d":"%.2f",r[i],i))
				.collect(Collectors.joining("+"));
		this.function = x -> this.value(x, r);
		return r;
	}
	
	public Evaluation getEvaluation() {
		return evaluation;
	}
	
	public String getExpression() {
		return expression;
	}

	public Integer getGrado() {
		return grado;
	}
	
	public Function<Double, Double> getFunction() {
		return function;
	}

	public double value(double n, double... p) {
		return new PolynomialFunction(p).value(n);
	}

	@Override
	public double[] gradient(double n, double... p) {
		return null;
	}

	@Override
	public void print(double n, double... p) {
		
	}
	
}
