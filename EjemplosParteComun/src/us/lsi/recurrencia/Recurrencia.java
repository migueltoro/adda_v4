package us.lsi.recurrencia;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.fraction.BigFraction;

import us.lsi.common.Arrays2;
import us.lsi.common.Matrix;
import us.lsi.common.String2;

public class Recurrencia {
	
	/**
	 * C�lculo de la funci�n f definida por:
	 * 
	 * f(n) = d1 si n=1
	 * f(n) = d0 si n=0
	 * f(n) = a*f(n-1)+b*f(n-2) si n &ge; 0
	 * 
	 * @param n Parametro de entrada
	 * @param a Parametro de entrada
	 * @param b Parametro de entrada
	 * @param d1 Parametro de entrada
	 * @param d0 Parametro de entrada
	 * @return El valor de f(n,a,b,d1,d0) calculado de forma recursiva sin memoria
	 */
	public static <E extends FieldElement<E>> E recsm(Integer n,
			E a, E b, E d1, E d0){
		E r;
		if(n==0) {
			r = d0;
		} else if(n==1) {
			r = d1;
		} else {
			r = a.multiply(recsm(n-1,a,b,d1,d0)).add(b.multiply(recsm(n-2,a,b,d1,d0)));
		}
		return r;
	}
	
	/**
	 * C�lculo de la funcion f definida por:
	 * 
	 * f(n) = d1 si n=1
	 * f(n) = d0 si n=0
	 * f(n) = a*f(n-1)+b*f(n-2) si n &gt; 1
	 * 
	 * @param n Parametro de entrada
	 * @param a Parametro de entrada
	 * @param b Parametro de entrada
	 * @param d1 Parametro de entrada
	 * @param d0 Parametro de entrada
	 * @return El valor de f(n,a,b,d1,d0) calculado de forma recursiva con memoria
	 */
	public static <E extends FieldElement<E>> E recCM(Integer n,E a, E b, E d1,E d0){
		Map<Integer,E> m = new HashMap<>();
		return recCM(m,n,a,b,d1,d0);
	}
	
	public static <E extends FieldElement<E>> E recCM(Map<Integer,E> m, Integer n,E a, E b, E d1,E d0){
		E r;
		if(m.containsKey(n)) {
			r = m.get(n);
		} else if(n==0) {
			r = d0;
			m.put(n,r);
		} else if(n==1) {
			r = d1;
			m.put(n,r);
		} else {
			r = a.multiply(recCM(m,n-1,a,b,d1,d0)).add(b.multiply(recCM(m,n-2,a,b,d1,d0)));
			m.put(n,r);
		}
		return r;
	}
	
	public static <E extends FieldElement<E>> E recLin(Integer n,E a, E b, E d1,E d0){
		Map<Integer,E> m = new HashMap<>();
		for(int i = 0;i<=n;i++) {
			E r;
			if(i==0) r = d0;
			else if(i==1) r = d1;
			else r = a.multiply(m.get(i-1)).add(b.multiply(m.get(i-2)));
			m.put(i, r);
			m.remove(i-3);
		}
		return m.get(n);
	}
	
	public static <E extends FieldElement<E>> E recMatrix(Integer n, E a, E b, E d1,E d0){
		Field<E> field = a.getField();
		Matrix<E> base = Matrix.of(2, 2, Arrays2.newArray(a,b,field.getOne(),field.getZero()));
		Matrix<E> colum = Matrix.of(2, 1, Arrays2.newArray(d1,d0));
		Matrix<E> m = Matrix.pow(base, n);
		Matrix<E> r = Matrix.multiply(m,colum);
		return r.get(1, 0);
	}
	
	public static void test1() {
		BigFraction a = new BigFraction(2);
		BigFraction b= new BigFraction(1);
		BigFraction d1= new BigFraction(1);
		BigFraction d0 = new BigFraction(1);
		Long t0 = System.nanoTime();
		BigFraction r1 = recLin(10000,a,b,d1,d0);
		Long t1 = System.nanoTime();
//		BigFraction r2 = recCM(10000,a,b,d1,d0);
		Long t2 = System.nanoTime();
		BigFraction r3 = recMatrix(10000,a,b,d1,d0);
		Long t3 = System.nanoTime();
		String2.toConsole("%d == %s\n%d == %s\n%d == %s",t1-t0,r1,t2-t1,r1,t3-t2,r3);
	}

	public static void main(String[] args) {
		test1();
	}
	
}
