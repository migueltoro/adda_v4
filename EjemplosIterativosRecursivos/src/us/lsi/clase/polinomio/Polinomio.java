package us.lsi.clase.polinomio;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import us.lsi.common.List2;
import us.lsi.streams.Stream2;

public class Polinomio {
	
	// Coeficientes de menor a mayor
	
		public static Double polF(List<Double> c, Double v){
			return Stream2.zip(c.stream(),Stream.iterate(1.,e->e*v),(e1,e2)->e1*e2)
					.mapToDouble(x->x).sum();
		}
		
		// Coeficientes de menor a mayor
		
		public static Double polP(List<Double> c, Double v) {
			Double b = 0.;
			Integer i = 0;
			Double p = 1.;
			Integer n = c.size();
			while (i < n) {
				Double cf = p * c.get(i);
				i = i + 1;
				p = p * v;
				b = b + cf;
			}
			return b;
		}
		
		// Coeficientes de menor a mayor
		
		public static Double prodI(List<Double> c, Double v){
			Double b = 1.;
			Integer i = 0;
			Double p = 1.;
			Integer n = c.size();
			while(i<n) {
				Double cf = p*c.get(i);
				i = i+1;
				p = p*v;
				b = b * cf;
			} 
			return b;	
		}

		// Coeficientes de mayor a menor
		
		public static Double polHI(List<Double> d, Double v){
			Double b = 0.;
			Integer i = 0;
			Integer r = d.size();
			while(i<r) {
				Double dd = d.get(i);
			    i = i +1;
			    b = b*v + dd;
			} 
			return b;	
		}

		// Coeficientes de menor a mayor
		
		public static Double polHD(List<Double> c, Double v){
			return polHD(c,v,0,c.size());
		}
		public static Double polHD(List<Double> c,Double v,Integer i,Integer r){
			Double b = 0.;
			if(i<r) {
				b = polHD(c,v,i+1,r);
				Double d = c.get(i);
				b = b*v + d;
			} 
			return b;	
		}
		
		public static void test9() {
			Locale.setDefault(Locale.of("en", "us"));
			List<Double> c = List2.of(0.,0.,0.,0.,1.);		
			Double v1 = polHD(c,2.);
			Double v2 = polF(c,2.);
			Double v3 = polP(c,2.);
			List<Double> d = List2.reverse(c);
			Double v4 = polHI(d,2.);	
			
			System.out.printf("2: = %.2f,%.2f,%.2f,%.2f\n",v1,v2,v3,v4);
			List<Double> c2 = List2.of(1.,1.,1.);		
			Double v5 = prodI(c2,2.);
			System.out.printf("3: = %.2f\n",v5);
		}

}
