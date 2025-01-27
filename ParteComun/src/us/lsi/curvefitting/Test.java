package us.lsi.curvefitting;

import java.util.List;


import org.apache.commons.math3.fitting.WeightedObservedPoint;
import us.lsi.graphics.MatPlotLib;

public class Test {

	public static void main(String[] args) {
		List<WeightedObservedPoint> data = DataFile.points("ficheros/datos_polinomial.txt");
		Polynomial pl = Polynomial.of(3);
		pl.fit(data);
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show("ficheros/datos_polinomial.txt", pl.getFunction(), pl.getExpression());
	}

}
