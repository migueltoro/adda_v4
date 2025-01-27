package us.lsi.p1;


public record MatrixFibDouble (Double a, Double b) {

	
	/**
	 * Clase de utilidades para las matrices de Fibonacci, de la forma
	 *   |a+b a|
	 *   | a  b|
	 * Nos referiremos a esas matrices a partir de la tupla (a, b)
	 */

	public static MatrixFibDouble of(Double a, Double b) {
			return new MatrixFibDouble(a, b);
		}
		
	/**
	 * @return (a, b)*(a,b)
	 */
	public MatrixFibDouble square() {
		Double a2 = a*a;
		Double b2 = b*b;
		Double ab2 = 2.*a*b;
		return of(a2+ab2, a2+b2);
	}
	
		
	/**
	 * @return (1, 0)*(a, b)
	 */
	public MatrixFibDouble next() {
		return of(a+b, a);
	}
		
	/**
	 * @return (1, 0)
	 */
	public static MatrixFibDouble unit() {
		return of(1., 0.);
	}
	
	/**
	 * @return (0, 1)
	 */
	public static MatrixFibDouble identity() {
		return of(0., 1.);
	}
	
	/**
	 * @param n
	 * @return potencia((1, 0), n)
	 */
	public static MatrixFibDouble unitPotencia(Integer n) {
		MatrixFibDouble res = null;
		if (n == 1) res = unit();
		else if (n%2 == 0) res = unitPotencia(n/2).square();
		else res = unitPotencia(n/2).square().next();
		return res;
	}

		
	/**
	 * @param n
	 * @return potencia((a, b), n)
	 */
	public MatrixFibDouble potencia(Integer n) {
		MatrixFibDouble res = null;
		if (n == 1) res = of(a, b);
		else if (n%2 == 0) res = potencia(n/2).square();
		else res = potencia(n/2).square().multiply(this);
		return res;
	}
		
	/**
	 * @param mf
	 * @return (a, b)*mf
	 */
	public MatrixFibDouble multiply(MatrixFibDouble mf) {
		Double aa = a*mf.a();
		Double ab = a*mf.b();
		Double ba = b*mf.a();
		Double bb = b*mf.b();
		return of(aa+ab+ba, aa+bb);
	}
}
	



