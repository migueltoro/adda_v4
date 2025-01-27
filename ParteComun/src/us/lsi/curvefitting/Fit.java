package us.lsi.curvefitting;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem.Evaluation;

public interface Fit {

	double[] gradient(double n, double... p);

	double value(double n, double... p);

	double[] fit(List<WeightedObservedPoint> points);

	Evaluation getEvaluation();

	String getExpression();

	Function<Double, Double> getFunction();

	void print(double n, double... p);

}