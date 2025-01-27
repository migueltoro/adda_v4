package us.lsi.math;


public class TestRacional {

	/**
	 * @param args Argumentos
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Racional r = Racional.create("-8/4");
		int n = r.getNumerador();
		int d = r.getDenominador();
		System.out.println(n+","+d);
	}

}
