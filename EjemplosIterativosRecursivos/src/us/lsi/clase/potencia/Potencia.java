package us.lsi.clase.potencia;

import java.math.BigInteger;

public class Potencia {

	/**
	 * 
	 * @param base Base
	 * @param n    Exponente
	 * @return Valor de base^n de forma iterativa
	 */
	public static Long pot(Long base, Integer n) {
		Long r = base;
		Long u = 1L;
		while (n > 0) {
			if (n % 2 == 1) {
				u = u * r;
			}
			r = r * r;
			n = n / 2;
		}
		return u;
	}

	public static Long power(int a, int n) {
		Long result = 1L;
		while (n > 0) {
			if ((n & 1) == 1) { // If n is odd
				result *= a;
			}
			n >>= 1; // Divide n by 2
			a *= a; // Square the base
		}
		return result;
	}
	
	public static BigInteger power2(BigInteger a, int n) {
        BigInteger result = BigInteger.ONE;
        while (n > 0) {
            if ((n & 1) == 1) { // If n is odd
                result = result.multiply(a);
            }
            n >>= 1; // Divide n by 2
            a = a.multiply(a); // Square the base
        }
        return result;
    }
	
	public static void main(String[] args) {
		Long a = 7L;
		Long b = 20L;
		System.out.println(power(a.intValue(), b.intValue()));
		System.out.println(pot(a,b.intValue()));
		System.out.println(power2(BigInteger.valueOf(a), b.intValue()));
	}

}
