package us.lsi.clase.digits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Digitos {
	
	public static List<Integer> digitos(Integer n, Integer b){
		List<Integer> r;
		if(n>b) {
			r = digitos(n/b,b);
			Integer d = n%b;
		    r.add(d);
		} else {
			r = new ArrayList<>();
			r.add(n);		}
		return r;	
	}
	
	public static List<Integer> digitosI(Integer n, Integer b){
		List<Integer> r = new ArrayList<>(); 
		while(n>b) {
			Integer d = n%b;
		    r.add(0,d);
			n = n/b;
		}
		r.add(0,n);
		return r;	
	}

	public static Integer numeroDeCeros(Integer n, Integer a){
		Integer b = 0;
		while(n>0) {
		     Integer d = n%a;
		     if(d == 0){
		    	 b = b+1;
		     }
		     n = n/a;
		} 
		return b;	
	}
	
	
	public static Long entero(List<Integer> digitos,Integer a){
		Long b = 0L;
		Integer i = 0;
		Integer n = digitos.size();
		while(n-i > 0){
			Integer d = digitos.get(i);
			b = b * a + d;
			i = i+1;
		} 
		return b;
	}

	public static Integer inverso(Integer n, Integer a){
		Integer b = 0;
		while(n > 0){
			int d =n%a;
			b = b * a + d;
			n = n/a;
		}
		return b;
	}
	
	public static void test1() throws IOException {
		Integer n = 2345789;
		Integer a = 10;
		List<Integer> ls = digitos(n,a);
		System.out.println("Digitos = "+ls);
		ls = digitosI(n,a);
		System.out.println("Digitos I = "+ls);
		System.out.println("Ceros = "+numeroDeCeros(n,a));
		System.out.println("Entero = "+n+","+entero(ls,a));
		System.out.println("Entero = "+n+","+inverso(n,a));
	}

}
