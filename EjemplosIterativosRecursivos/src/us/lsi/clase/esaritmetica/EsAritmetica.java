package us.lsi.clase.esaritmetica;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import us.lsi.streams.Stream2;

public class EsAritmetica {
	
	public static Boolean esAritmetica1(List<Integer> ls) {
		Integer n = ls.size();
		if(n<=2) return true;
		Integer r = ls.get(1)-ls.get(0);
		return IntStream.range(0,n-1)
	 		.allMatch(i->(ls.get(i+1)-ls.get(i)) == r);
	}

	public static Boolean esAritmetica2(List<Integer> ls) {
		Integer n = ls.size();
		if(n <=2) return true;
		Integer i = 0;
		Boolean b = true;
		Integer r = ls.get(1)-ls.get(0);
		while(n-i>1  && b){
		   Integer e1 = ls.get(i), e2 = ls.get(i+1);
		   i++;
		   b = e2-e1 == r;
		}
		return b;
	}
	
	public static Boolean esAritmetica3(List<Integer> ls) {
		Integer n = ls.size();
		if(n <=2) return true;
		Integer r = ls.get(1)-ls.get(0);
		return esAritmetica3(0,ls,r,n);
	}
	private static Boolean esAritmetica3(Integer i, List<Integer> ls, Integer r, Integer n) {
		Boolean b = true;
		if(n-i>1) {
			b = ls.get(i+1)-ls.get(i) == r && esAritmetica3(i+1,ls,r,n);
		}
		return b;
	}
	
	public static Boolean esAritmetica4(Iterable<Integer> iterable) {
			Iterator<Integer> it = iterable.iterator();
			Integer last,e;
			if(it.hasNext()) e = it.next(); else return true;
			Boolean b = true;
			Integer r =null;
			while(it.hasNext()  && b){
				last = e;
				e = it.next();
				if(r == null) r = e-last;
				b = e-last == r;	
			}
			return b;
	}
	
	public static Boolean esAritmetica5(Iterable<Integer> iterable) {
		Iterator<Integer> it = iterable.iterator();
		Integer a1, a2;
		if(it.hasNext()) 	a1 = it.next(); else return true;
		if(it.hasNext()) 	a2 = it.next(); else return true;
		Integer r = a2-a1;
	    return Stream2.consecutivePairs(Stream2.of(()->it))
	    	.allMatch(p->p.second()-p.first()==r);		
	}
	
	public static Boolean esAritmetica6(Stream<Integer> st) {	
		Stream<Integer> stc = Stream2.consecutivePairs(st).map(p->p.second()-p.first());
	    return Stream2.allEquals(stc);		
	}
	
	public static void test3() {
//		List<Integer> ls = List.of(4,6,8,11,12);
		List<Integer> ls = List.of(4,6);
		System.out.println(esAritmetica1(ls));
		System.out.println(esAritmetica2(ls));
		System.out.println(esAritmetica3(ls));
		System.out.println(esAritmetica4(ls));
		System.out.println(esAritmetica5(ls));
		System.out.println(esAritmetica6(ls.stream()));
	}
	


}
