package us.lsi.curvefitting;

import java.util.List;
import java.util.Locale;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

public class TestExponential {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "us"));
		Exponential ep = Exponential.of();
		List<WeightedObservedPoint> points = DataFile.points("ficheros/datos_exponential.txt");		
		ep.fit(points);
		System.out.println(String.format("Solutions = %s",ep.getExpression()));
	}

}
