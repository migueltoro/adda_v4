package us.lsi.curvefitting;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.IntegerSet;
import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.streams.Collectors2;

class PowerLogFixingValues  extends PowerLog {
	
	public static PowerLogFixingValues of(List<Pair<Integer,Double>> fixedParams) {
		Preconditions.checkArgument(fixedParams.size() < 4, String.format("Solo se pueden fijar menos de cuatro parámetros"));
		return new PowerLogFixingValues(fixedParams);
	}
	
	protected List<Pair<Integer,Double>> fixedParamsValues = null;
	protected IntegerSet fixedParams = null;
	protected IntegerSet freeParams = null;
	protected Integer numFreeParameters;
	protected double[] parameters = null;
	protected double[] initialParameters = null;
	protected Function<Double, Double> function = null;

	protected PowerLogFixingValues(List<Pair<Integer,Double>> fixedParamsvalues) {
		super();
		this.fixedParamsValues = fixedParamsvalues;
		this.fixedParams = fixedParamsvalues.stream().map(p->p.first()).collect(Collectors2.toIntegerSet());
		this.freeParams = IntegerSet.of(0,1,2,3).difference(fixedParams);
		this.numFreeParameters = this.freeParams.size();
		this.initialParameters = IntStream.range(0,this.numFreeParameters).mapToDouble(i->1.).toArray();
		super.fitter = SimpleCurveFitter2.create(this,this.initialParameters);
		this.parameters = new double[] {0.,0.,0.,0.};
		this.fixedParamsValues.stream().forEach(pp->this.parameters[pp.first()] = pp.second());
	}
	
	public double[] values(double... p) {
		double[] pp = new double[4];
		int j = 0;
		for(int i = 0;i<4;i++) {
			if(this.freeParams.contains(i)) {
				pp[i] = p[j];
				j++;
			} else {
				pp[i] = this.parameters[i];
			}
			
		}
		return pp;
	}
	
	@Override
	public double value(double n, double... p) {
		return super.value(n, values(p));
	}
	
	@Override
	public double[] gradient(double n, double... p) {
		double[] g2 = super.gradient(n, values(p));
		return this.freeParams.stream().mapToDouble(i->g2[i]).toArray();
	}
	
	public double[] fit(List<WeightedObservedPoint> points) {
		double[] r = this.fitter.fit(points);
		this.evaluation = this.fitter.getProblem(points).evaluate(RealVectors.toRealVector(r));
		double[] r2 = this.values(r);
		super.expression = String.format("%.2f * n^%.2f * (ln n)^%.2f + %.2f", r2[0], r2[1], r2[2], r2[3]);
		super.function = x->super.value(x, r2);
		
		return r;
	}
	
	@Override
	public void print(double n, double... p) {
		super.print(n,values(p));
	}
	
	public String print(double[] p) {
		System.out.println(p.length);
		return IntStream.range(0, p.length).boxed().map(i->""+p[i]).collect(Collectors.joining(",", "{", "}"));
	}
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "us"));
		List<WeightedObservedPoint> points = DataFile.points("ficheros/datos_polinomiallog.txt");
		PowerLogFixingValues p = PowerLogFixingValues.of(List.of());		
		p.fit(points);
		System.out.println(String.format("Solutions = %s",p.getExpression()));
		System.out.println(String.format("Solutions = %s",p.getEvaluation().getRMS()));	
		System.out.println(String.format("Solutions = %s",p.getEvaluation().getCost()));	
	}
	
}
