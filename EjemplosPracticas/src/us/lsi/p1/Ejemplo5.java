package us.lsi.p1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Ejemplo5 {
	/*
	PI1 - Ejemplo 5

	Analizar los tiempos de ejecución de las versiones recursivas sin y con memoria, 
	iterativa y haciendo uso de la potencia de matrices para el cálculo de los 
	números de Fibonacci. 
	
	Comparar según los resultados sean de tipo Double o BigInteger.
	*/
	
	
	public static BigInteger fibR(Integer n) {
		BigInteger r = null;
		if (n==0) {
			r = BigInteger.ZERO;
		} else if (n==1) {
			r = BigInteger.ONE;
		} else {
			r = fibR(n-1).add(fibR(n-2));
		}
		return r;
	}
	
	public static BigInteger fibRM(Integer n) {
		return fibRM(n, new HashMap<Integer, BigInteger>());
	}
	
	private static BigInteger fibRM(Integer n, Map<Integer, BigInteger> m) {
		BigInteger r = null;
		if (m.containsKey(n))
			r = m.get(n);
		else if (n==0) {
			r = BigInteger.ZERO;
			m.put(n,r);
		} else if (n==1) {
			r = BigInteger.ONE;
			m.put(n,r);
		} else {
			r = fibRM(n-1,m).add(fibRM(n-2,m));
			m.put(n,r);
		}
		return r;
	}
	
	public static BigInteger fibIter(Integer n) {
		BigInteger r = null;
		if (n==0) {
			r = BigInteger.ZERO;
		} else if (n==1) {
			r = BigInteger.ONE;
		} else {
			BigInteger f1 = BigInteger.ONE;
			BigInteger f2 = BigInteger.ZERO;
			for (int i=2; i<=n; i++) {
				r = f1.add(f2);
				f2 = f1;
				f1 = r;
			}
		}
		return r;
	}
	
	
	/**
	 * @pre n &gt; = 0
	 * @param n Un Entero
	 * @return El n�mero n de Fibonacci calculado reduciendo el problema a una potenciaci�n de matrices
	 * y en una versi�n iterativa
	 */
	public static BigInteger fibonacciPotMat(Integer n){
        return MatrixFibBigInteger.unitPotencia(n).a(); // suponiendo fib(0)=0, fib(1)=1
	}
  
	
	public static Double fibR_Double(Integer n) {
		Double r = null;
		if (n==0.) {
			r = 0.;
		} else if (n==1.) {
			r = 1.;
		} else {
			r = fibR_Double(n-1) + (fibR_Double(n-2));
		}
		return r;
	}
	
	public static Double fibRM_Double(Integer n) {
		return fibRM_Double(n, new HashMap<Integer, Double>());
	}
	
	private static Double fibRM_Double(Integer n, Map<Integer, Double> m) {
		Double r = null;
		if (m.containsKey(n))
			r = m.get(n);
		else if (n==0.) {
			r = 0.;
			m.put(n,r);
		} else if (n==1.) {
			r = 1.;
			m.put(n,r);
		} else {
			r = fibRM_Double(n-1,m) + (fibRM_Double(n-2,m));
			m.put(n,r);
		}
		return r;
	}
	
	public static Double fibIter_Double(Integer n) {
		Double r = null;
		if (n==0.) {
			r = 0.;
		} else if (n==1.) {
			r = 1.;
		} else {
			Double f1 = 1.;
			Double f2 = 0.;
			for (int i=2; i<=n; i++) {
				r = f1 + f2;
				f2 = f1;
				f1 = r;
			}
		}
		return r;
	}
	
	
	/**
	 * @pre n &gt; = 0
	 * @param n Un Entero
	 * @return El n�mero n de Fibonacci calculado reduciendo el problema a una potenciaci�n de matrices
	 * y en una versi�n iterativa
	 */
	public static Double fibonacciPotMat_Double(Integer n){
        return MatrixFibDouble.unitPotencia(n).a(); // suponiendo fib(0)=0, fib(1)=1

	}
	
	
}

