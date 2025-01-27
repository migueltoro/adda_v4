package us.lsi.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import us.lsi.common.List2;
import us.lsi.common.Multiset;



public class TestMath {

	/**
	 * @param args Argumentos
	 */
	public static void main(String[] args) {
		System.out.println(Math2.escala(1000, 1024, 3));
		System.out.println(Math2.numeroDeBits(7));
		System.out.println(Math2.code(7));
		System.out.println(Math2.decode(Arrays.asList(1, 1, 1)));
		System.out.println(Math2.decodes(Arrays.asList(1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1),4,3));
		System.out.println(Math2.pow(2,10));
		Long m = Math2.pow(2,7);
		Multiset<Long> m1 = Multiset.empty();
		for(int i=0;i<m;i++){
			m1.add((i*12/m));
		}
		System.out.println(m1);
		List<Integer> r = new ArrayList<>();
		List<Integer> max = List2.of(4, 4);
		for(int i=0;i<12;i++){
			r.add(Math2.getEnteroAleatorio(0, 2));
		}
		System.out.println(Math2.decodesInScala(r,4,3,max));
		for(int i=0;i<20;i++){
			System.out.print(Math2.pow(2,i)+",");
		}
	}

}
