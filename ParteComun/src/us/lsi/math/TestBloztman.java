package us.lsi.math;


public class TestBloztman {

	
	public static void main(String[] args) {
		double r = 0.5;
		double delta = 0.1;
		double t = 10.;
		for(int i = 0; i<100;i++){
			t = t*(1-delta);
			System.out.println(Math2.boltzmann(r, t)+","+Math2.aceptaBoltzmann(r, t));
		}
	}

}
