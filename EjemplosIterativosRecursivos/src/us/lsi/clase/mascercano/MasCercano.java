package us.lsi.clase.mascercano;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.common.Preconditions;

public class MasCercano {
	
	public static Integer masCercano(List<Integer> ls, Integer e){
		if(ls.isEmpty()) Preconditions.checkArgument(!ls.isEmpty());
		int n = ls.size();
		return masCercano(ls, e, 0, n);
	}
	private static Integer masCercano(List<Integer> ls, Integer e, Integer i,Integer j) {	
		Integer r;
		Integer k = (i+j)/2;
	 	if(j-i == 1) {
	 		r = ls.get(i);
	 	} else if(j-i == 2){
	 		r = masCercano(e,ls.get(i),ls.get(i+1));
		} else if(e == ls.get(k)) {
	 		r = ls.get(k);
	 	} else if(e < ls.get(k)){
	 		r = masCercano(ls,e,i,k+1);
	 	} else {
			r = masCercano(ls,e,k,j);
	 	}
		return r;
	}
	public static Integer masCercano(Integer e, Integer e1, Integer e2){
	 	Integer r;
		if(Math.abs(e-e1) <= Math.abs(e-e2)) r = e1;
	 	else r = e2;
	 	return r;
	}
	
	public static void test10() {
		List<Integer> l1 = List2.of(1,3,5,7,9,12);
		List<Integer> l2 = List2.of(0,2,4,10,19,21,23,45);
		System.out.println(String.format("5: %s",masCercano(l1,11)));
		System.out.println(String.format("6: %s",masCercano(l2,11)));
	}
	
	public static void main(String[] args) {
		test10();
	}

}
