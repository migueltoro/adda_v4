package us.lsi.clase.combinatorio;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Combinatorio {
	
	public static record Nc(Long a, Long b) {
		public static Nc of(Long a, Long b) {
			return new Nc(a,b);
		}
	}
	
	public static BigInteger combinatorioR(Long n, Long k) {
		BigInteger r;
		if(k==0 || n-k ==0) r = BigInteger.ONE;
		else if(k==1 || n-k ==1) r = BigInteger.valueOf(n);
		else {
			r = combinatorioR(n-1, k).add(combinatorioR(n-1, k-1));
		}
		return r;
	}
	
	public static BigInteger combinatorioI(Long n, Long k) {
		Map<Nc,BigInteger> m = new HashMap<>();
		for(Long i=0L; i<=n; i++) {
			for(Long j=0L; j<=i;j++) {
				if(j==0 || i-j==0) m.put(Nc.of(i,0L),BigInteger.ONE);
				else if(j==1 || i-j ==1) m.put(Nc.of(i,j),BigInteger.valueOf(i));
				else {
					BigInteger r = m.get(Nc.of(i-1, j)).add(m.get(Nc.of(i-1, j-1)));
					m.put(Nc.of(i,j),r);
				}
				m.remove(Nc.of(i-2,k));
			}				
		}
		return m.get(Nc.of(n, k));
	}

	public static BigInteger binomialCoefficient(Long m, Long n) {
		if (n > m || m < 0 || n < 0) {
			throw new IllegalArgumentException("Invalid values for m and n");
		}

		BigInteger result = BigInteger.ONE;

		for (int i = 0; i < n; i++) {
			result = result.multiply(BigInteger.valueOf(m - i)).divide(BigInteger.valueOf(i + 1));
		}

		return result;
	}

	public static void main(String[] args) {
		Long a = 20L;
		Long b = 5L;
		System.out.println(combinatorioR(a,b));
		System.out.println(combinatorioI(a,b));
		System.out.println(binomialCoefficient(a,b));
	}

}
