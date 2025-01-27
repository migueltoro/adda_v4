package us.lsi.math;


public class TestPolinomio {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double[] c = {1.,1.,2.};
		Polinomio p = Polinomio.create(c);		
//		System.out.println(p);
//		p.setCoeficientes(2, 0.);
//		System.out.println(p);
		Double[] c1 = {-1.,1.,3.};
		Polinomio p1 = Polinomio.create(c1);
//		System.out.println(p1);
//		p.sum(p1);
		System.out.println(p+"        "+p1);	
		p.setCoefficient(7,1.);
		System.out.println(p.getDegree()+"        "+p1);	
		System.out.println(p.multiply(p1));
		System.out.println(p.multiply(p1).derivative());
		System.out.println(p.multiply(p1).derivative().getValue(1.));
	}

}
