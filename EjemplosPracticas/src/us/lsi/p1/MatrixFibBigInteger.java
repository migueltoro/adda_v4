package us.lsi.p1;

import java.math.BigInteger;


public record MatrixFibBigInteger (BigInteger a, BigInteger b) {

	
	/**
	 * Clase de utilidades para las matrices de Fibonacci, de la forma
	 *   |a+b a|
	 *   | a  b|
	 * Nos referiremos a esas matrices a partir de la tupla (a, b)
	 */

	public static MatrixFibBigInteger of(BigInteger a, BigInteger b) {
			return new MatrixFibBigInteger(a, b);
		}
		
	/**
	 * @return (a, b)*(a,b)
	 */
	public MatrixFibBigInteger square() {
		BigInteger a2 = a.pow(2);
		BigInteger b2 = b.pow(2);
		BigInteger ab2 = BigInteger.TWO.multiply(a.multiply(b));
		return of(a2.add(ab2), a2.add(b2));
	}
	
		
	/**
	 * @return (1, 0)*(a, b)
	 */
	public MatrixFibBigInteger next() {
		return of(a.add(b), a);
	}
		
	/**
	 * @return (1, 0)
	 */
	public static MatrixFibBigInteger unit() {
		return of(BigInteger.ONE, BigInteger.ZERO);
	}
	
	/**
	 * @return (0, 1)
	 */
	public static MatrixFibBigInteger identity() {
		return of(BigInteger.ZERO, BigInteger.ONE);
	}
	
	/**
	 * @param n
	 * @return potencia((1, 0), n)
	 */
	public static MatrixFibBigInteger unitPotencia(Integer n) {
		MatrixFibBigInteger res = null;
		if (n == 1) res = unit();
		else if (n%2 == 0) res = unitPotencia(n/2).square();
		else res = unitPotencia(n/2).square().next();
		return res;
	}

		
	/**
	 * @param n
	 * @return potencia((a, b), n)
	 */
	public MatrixFibBigInteger potencia(Integer n) {
		MatrixFibBigInteger res = null;
		if (n == 1) res = of(a, b);
		else if (n%2 == 0) res = potencia(n/2).square();
		else res = potencia(n/2).square().multiply(this);
		return res;
	}
		
	/**
	 * @param mf
	 * @return (a, b)*mf
	 */
	public MatrixFibBigInteger multiply(MatrixFibBigInteger mf) {
		BigInteger aa = a.multiply(mf.a());
		BigInteger ab = a.multiply(mf.b());
		BigInteger ba = b.multiply(mf.a());
		BigInteger bb = b.multiply(mf.b());
		return of(aa.add(ab).add(ba), aa.add(bb));
	}
}
	



