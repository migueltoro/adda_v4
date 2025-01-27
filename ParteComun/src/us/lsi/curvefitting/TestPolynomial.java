package us.lsi.curvefitting;

import java.util.List;
import java.util.Locale;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

public class TestPolynomial {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "us"));
		List<WeightedObservedPoint> points = DataFile.points("ficheros/datos_polinomial.txt");	
		Polynomial p = Polynomial.of(3);
		var r = p.fit(points);
		System.out.println(String.format("Solutions = %s",p.getExpression()));
		System.out.println(p.getFunction().apply(1000.));
		System.out.println(RealVectors.toRealVector(r));
	}

}
