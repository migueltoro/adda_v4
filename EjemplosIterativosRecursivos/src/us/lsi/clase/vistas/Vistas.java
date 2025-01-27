package us.lsi.clase.vistas;

import java.util.Iterator;
import java.util.List;

import us.lsi.common.Matrix;
import us.lsi.common.String2;
import us.lsi.common.View1;
import us.lsi.common.View2E;
import us.lsi.common.Ranges.IntRange;
import us.lsi.common.Ranges.LongRange;
import us.lsi.iterables.Iterables;

public class Vistas {

	public static Integer sum(List<Integer>ls) {
		if(ls.isEmpty()) return 0;
		IntRange r = IntRange.of(0, ls.size());		
		View1<IntRange,Integer> w = r.view1();	
		Integer sum = 0;
		while(r.size()>1) {					
			sum = sum +w.e();
			r = w.r();
			w = r.view1();
		}
		return sum;
	}
	
	public static Long sqrtLong(Long a) {
		LongRange r = LongRange.of(0L,a+1);
		Long e = 1L;
		Long ec = 1L;
		while(r.size()>2 && ec != a) {	
			View2E<LongRange,Long> w = r.view2Overlapping();
			e = w.e();
			ec = e*e;
			if(a < ec) r = w.left();
			else r = w.right();			
		}
		return e*e == a? e : r.a();
	}
	
	public static Long sqrtLong_r(Long a) {
		LongRange r = LongRange.of(0L,a+1);
		Long e = 1L;
		Long ec = 1L;
		while(r.size()>2 && ec != a) {	
			View2E<LongRange,Long> w = r.view2Overlapping();
			e = w.e();
			ec = e*e;
			if(a < ec) r = w.left();
			else r = w.right();			
		}
		return e*e == a? e : r.a();
	}
	
	public static <E extends Comparable<? super E>> int indexOf(List<E>ls, Integer elem) {
		IntRange r = IntRange.of(0, ls.size());		
		View2E<IntRange,Integer> w = r.view2();			
		E e = ls.get(w.e());
		while(r.size()>2 && e != elem) {			
			if(e.compareTo(e) <0) r = w.left();
			else r = w.right();
			w = r.view2();
			e = ls.get(w.e());
		}
		return e == elem? w.e(): -1;
	}
	
	public static int masCercano(List<Integer>ls, Integer elem) {
		if(ls.isEmpty()) return -1;
		if(ls.size()==1) return ls.get(0);
		IntRange r = IntRange.of(0, ls.size());		
		View2E<IntRange,Integer> w = r.view2Overlapping();			
		Integer i= w.e();
		while(r.size()>2 && ls.get(i) != elem) {	
			if(elem < ls.get(i)) r = w.left();
			else r = w.right();
			w = r.view2Overlapping();
			i = w.e();
		}
		return ls.get(i) == elem? i: masCercanoBase(ls,elem, r);
	}
	
	public static Integer masCercanoBase(List<Integer>ls, Integer elem, IntRange r) {
		Integer a = ls.get(r.a());
		Integer b = ls.get(r.a()+1);
		if(Math.abs(elem-a) < Math.abs(elem-b)) return a;
		else return b;
	}
	
	public static int masCercano2(List<Integer>ls, Integer elem) {
		if(ls.isEmpty()) return -1;
		if(ls.size()==1) return ls.get(0);
		IntRange r = IntRange.of(0, ls.size());					
		return masCercano2(ls,elem,r);
	}
	
	public static Integer masCercano2(List<Integer>ls, Integer elem, IntRange r) {				
		Integer s;		
		if(r.size()<=2) {
			s = masCercanoBase(ls,elem, r);
		}  else {
			View2E<IntRange,Integer> w = r.view2Overlapping();			
			Integer i = w.e();
			if(ls.get(i) == elem) s = ls.get(i);
			else if(elem < ls.get(i)) s = masCercano2(ls,elem,w.left());
			else s = masCercano2(ls,elem,w.right());;
		}
		return s;
	}
	
	public static Integer sumIntegerOFile(String file) {
		Iterator<String> it = Iterables.file(file).iterator();
		if(!it.hasNext()) return 0;	
		Integer sum = 0;
		while(it.hasNext()) {
			View1<Iterator<String>,String> w = Iterables.view(it);
			Integer e = Integer.parseInt(w.e());
			it = w.r();
			sum = sum + e;			
		}
		return sum;
	}
	
	public static void matrix() {
		Integer[][] a = {{1,2,3,5,6},{3,4,5,-2,3},{5,6,7,5,6},{5,6,7,5,6},{5,6,7,5,6}};
		Matrix<Integer> m7 = Matrix.of(a);
		m7.print("m7");
		Matrix<Integer> m8 = m7.view(2);
		m8.print("m8");
		System.out.println(String.format("%d,%d",m7.nf(),m7.nc()));
		System.out.println(String.format("%d,%d",m8.nf(),m8.nc()));
		System.out.println(String.format("%s,%s,%s,%s",
				m8.get(0,0),
				m8.get(0,m8.nc()-1),
				m8.get(m8.nf()-1,0),
				m8.get(m8.nf()-1,m8.nc()-1)));
	}
	
	public static void main(String[] args) {
		System.out.println(sum(List.of(1,3,7,9,31,54,91,102)));
		System.out.println(sqrtLong(101L));
		System.out.println(indexOf(List.of(1,3,7,9,31,54,91,102),92));
		System.out.println(masCercano(List.of(1,3,7,9,31,54,91,102),103));
		System.out.println(masCercano2(List.of(1,3,7,9,31,54,91,102),90));
		System.out.println(sumIntegerOFile("ficheros/numeros.txt"));
		matrix();
		Integer[] m =   {1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1,1, 0, 1, 0, 1};
		Matrix<Integer> mt = Matrix.of(5,5,m);
		String2.toConsole("%s",mt);
		String2.toConsole("_________________");
		String2.toConsole("%s",mt.view(1,1,3,3));
		String2.toConsole("_________________");
		String2.toConsole("%s",mt.view(1,1,3,3).view(1,1,2,2));
		String2.toConsole("_________________");
		String2.toConsole("%s",mt.view(1,1,3,3).view(1,1,2,2).get(0,1));
	}

}

