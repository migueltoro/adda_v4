package us.lsi.curvefitting;

import java.util.List;
import java.util.Locale;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

public abstract class TestPolinomialLog {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "us"));
		List<WeightedObservedPoint> points = DataFile.points("ficheros/datos_polinomiallog.txt");
		Fit pl = PowerLog.of();		
		pl.fit(points);
		System.out.println(String.format("Solutions = %s",pl.getExpression()));
		System.out.println(pl.getFunction().apply(345.));
	}

}
