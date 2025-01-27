package us.lsi.clase.monotonas;

import java.util.Locale;

public class Monotonas {
	
	/**
	 * @param x Numero real entre 0 y 1
	 * @param eps Error
	 * @return e^x-1
	 */
	public static Double exponential(Double x, Double eps){
		Double r = 0.;
		Integer i = 0;
		Double t = 1.;
		while(t>eps) {	
			i = i+1;
			t = t*x/i;
			r = r + t;
		}
		return r;
	}
	
	public static void test12() {
		Locale.setDefault(Locale.of("en", "us"));
		System.out.printf("%.4f,%.4f\n",Math.exp(0.567),1+exponential(0.567,0.0001));
	}

}
