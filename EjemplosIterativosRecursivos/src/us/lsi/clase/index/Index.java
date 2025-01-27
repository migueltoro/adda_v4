package us.lsi.clase.index;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import us.lsi.common.Comparator2;
import us.lsi.common.Enumerate;
import us.lsi.common.List2;
import us.lsi.streams.Stream2;

public class Index {
	
	//Resuelto con la generalización sufija y agregado indexable no ordenado
	
	public static <E> Integer index0(List<E> ls, E elem) {
		Optional<Integer> r = IntStream.range(0,ls.size()).boxed()
				.filter(i->ls.get(i).equals(elem))
				.findFirst();
		return r.isPresent()?r.get():-1;
	}

	public static <E> Integer index1(List<E> ls, E elem) {
		Integer i = 0;
		Integer n = ls.size();
		Integer b = -1;
		while(i < n && b == -1){
		   if(elem.equals(ls.get(i))) b = i;
		   i = i+1;
		}
		return b;
	}
	
	public static <E> Integer index3(List<E> ls, E elem) {
		return index3(0,ls,elem);
	}
	private static <E> Integer index3(Integer i, List<E> ls, E elem) {
		Integer b = -1;
		Integer n = ls.size();
		if(n-i> 0){
			if (ls.get(i).equals(elem)) b = i;
		    else b = index3(i+1,ls,elem);
		}
		return b;
	}
	
	//Resuelto con la generalización sufija y agregado indexable ordenado
	
		public static <E> Integer index0Ord(List<E> ls, E elem, Comparator<E> cmp) {
			Optional<Integer> r = IntStream.range(0,ls.size()).boxed()
					.filter(i->(ls.get(i).equals(elem) || cmp.compare(ls.get(i),elem)>0))
					.findFirst();
			return r.isPresent() && r.get().equals(elem)?r.get():-1;
		}

		public static <E> Integer index1Ord(List<E> ls, E elem, Comparator<E> cmp) {
			Integer i = 0;
			Integer n = ls.size();
			Integer b = -1;
			Boolean c = false;
			while(i < n && b == -1 && !c){
			   if(elem.equals(ls.get(i))) b = i;
			   if(cmp.compare(ls.get(i),elem) > 0) c = true;
			   i = i+1;
			}
			return b;
		}
		
		public static <E> Integer index3Ord(List<E> ls, E elem, Comparator<E> cmp) {
			return index3Ord(0,ls,elem,cmp);
		}
		private static <E> Integer index3Ord(Integer i, List<E> ls, E elem, Comparator<E> cmp) {
			Integer b = -1;
			Integer n = ls.size();
			if(n-i> 0){
				if (ls.get(i).equals(elem)) b = i;
				else if (cmp.compare(ls.get(i),elem) > 0) b = -1;
			    else b = index3(i+1,ls,elem);
			}
			return b;
		}
	
	//Resuelto con generalización central y agregado indexable ordenado
	
	public static <E> Integer index4(List<E> ls, E elem, Comparator<E> cmp) {
		return index4(0,ls.size(),ls,elem,cmp);
	}
	private static <E> Integer index4(Integer i, Integer j, List<E> ls,E elem, Comparator<E> cmp) {
		Integer r = -1;
		if(j-i==1) {
			r = cmp.compare(elem,ls.get(i)) == 0 ? i:-1;
		} else if(j-i>1){
		   Integer k = (j+i)/2;
		   if (cmp.compare(elem,ls.get(k)) == 0) r = k;
		   else if (cmp.compare(elem,ls.get(k)) < 0) r = index4(i,k,ls,elem,cmp);
		   else r = index4(k+1,j,ls,elem,cmp);
		}
		return r;
	}
	

	public static <E> Integer index5(List<E> ls,E elem,Comparator<E> cmp) {
		Integer b = -1;
		Integer i = 0;
		Integer j = ls.size();	
		while(j-i>1 && b==-1){
		   Integer k = (j+i)/2;
		   if (cmp.compare(ls.get(k),elem) == 0) b = k;
		   else if (cmp.compare(ls.get(k),elem) < 0) i = k;
		   else j = k;		   
		}
		if(j-i==1) b = cmp.compare(elem,ls.get(i)) == 0 ? i:-1;
		return b;
	}
	
	public static record  P2(Integer i, Integer j) {
		public static P2 of(Integer i, Integer j) {
			return new P2(i,j);
		}
	}

	public static record P3(Integer i, Integer j) {
		public static P3 of(Integer i, Integer j) {
			return new P3(i,j);
		}
		public Integer k() {
			return (i+j)/2;
		}
	}

	public static <T> Integer index6(List<T> ls, T elem, Comparator<T> cmp) {
		P3 p = P3.of(0, ls.size());
		while ((p.j - p.i) > 0 && !Comparator2.isEQ(elem, ls.get(p.k()), cmp)) {
			if (Comparator2.isLT(elem, ls.get(p.k()), cmp))
				p = P3.of(p.i(), p.k());
			else
				p = P3.of(p.k() + 1, p.j);
		}
		return (p.j() - p.i() > 0 && Comparator2.isEQ(elem, ls.get(p.k()), cmp)) ? p.k() : -1;
	}
	
	//Resuelto con generalización central y agregado indexable no ordenado
	
	
	public static <E> Integer index4NoOrd(List<E> ls, E elem, Comparator<E> cmp) {
		return index4NoOrd(0,ls.size(),ls,elem,cmp);
	}
	private static <E> Integer index4NoOrd(Integer i, Integer j, List<E> ls,E elem, Comparator<E> cmp) {
		Integer r = -1;
		if(j-i==1) {
			r = cmp.compare(elem,ls.get(i)) == 0 ? i:-1;
		} else if(j-i>1){
		   Integer k = (j+i)/2;
		   if (cmp.compare(elem,ls.get(k)) == 0) r = k;
		   else if (cmp.compare(elem,ls.get(k)) < 0) r = index4(i,k,ls,elem,cmp);
		   else r = index4(k+1,j,ls,elem,cmp);
		}
		return r;
	}
	

	public static <E> Integer index5NoOrd(List<E> ls,E elem,Comparator<E> cmp) {
		Integer b = -1;
		Integer i = 0;
		Integer j = ls.size();	
		while(j-i>1 && b==-1){
		   Integer k = (j+i)/2;
		   if (cmp.compare(ls.get(k),elem) == 0) b = k;
		   else if (cmp.compare(ls.get(k),elem) < 0) i = k;
		   else j = k;		   
		}
		if(j-i==1) b = cmp.compare(elem,ls.get(i)) == 0 ? i:-1;
		return b;
	}
	
	public static record  P2NoOrd(Integer i, Integer j) {
		public static P2NoOrd of(Integer i, Integer j) {
			return new P2NoOrd(i,j);
		}
	}

	public static record P3NoOrd(Integer i, Integer j) {
		public static P3NoOrd of(Integer i, Integer j) {
			return new P3NoOrd(i,j);
		}
		public Integer k() {
			return (i+j)/2;
		}
	}

	public static <T> Integer index6NoOrd(List<T> ls, T elem, Comparator<T> cmp) {
		P3 p = P3.of(0, ls.size());
		while ((p.j - p.i) > 0 && !Comparator2.isEQ(elem, ls.get(p.k()), cmp)) {
			if (Comparator2.isLT(elem, ls.get(p.k()), cmp))
				p = P3.of(p.i(), p.k());
			else
				p = P3.of(p.k() + 1, p.j);
		}
		return (p.j() - p.i() > 0 && Comparator2.isEQ(elem, ls.get(p.k()), cmp)) ? p.k() : -1;
	}
	
	//Resuelto con generalización central y agregado indexable no ordenado y reducción tamaño de 2
	
	public static <E> Integer index10(List<E> ls, E elem) {
		Integer n = ls.size();
		return index10(0,n,ls,elem);
	}
	
	private static <E> Integer index10(Integer i, Integer j, List<E> ls, E elem) {
		Integer r;
		if(j-i == 0) r = -1;
		else if (j-i == 1 && ls.get(i).equals(elem)) r =i;
		else if (j-i == 1 && !ls.get(i).equals(elem)) r = -1;
		else if (j-i == 2 && ls.get(i).equals(elem)) r = i;
		else if (j-i == 2 && ls.get(j-1).equals(elem)) r = j-1;
		else if (j-i == 2 && !ls.get(i).equals(elem) && !ls.get(j-1).equals(elem)) r = -1; 
		else if (j-i > 2 && ls.get(i).equals(elem)) r = i;
		else if (j-i > 2 && ls.get(j-1).equals(elem)) r = j-1;
		else r = index10(i+1, j-1, ls, elem);
		return r;
	}
	
	public static <E> Integer index10i(List<E> ls, E elem) {
		Integer n = ls.size();
		Integer i = 0;
		Integer j = n;
		Integer r = -1;
		while (j - i > 2 && r == -1) {
			if (ls.get(i).equals(elem))
				r = i;
			else if (ls.get(j - 1).equals(elem))
				r = j - 1;
			i = i + 1;
			j = j - 1;
		}
		if (r == -1) {
			if (j - i == 0)
				r = -1;
			else if (j - i == 1 && ls.get(i).equals(elem))
				r = i;
			else if (j - i == 2 && ls.get(i).equals(elem))
				r = i;
			else if (j - i == 2 && ls.get(j - 1).equals(elem))
				r = j - 1;
		}
		return r;
	}
	
	
	public static record IND<E>(Integer i, Integer j, Integer r, List<E> ls, E elem) {
		
		public static <E> IND<E> of(Integer i, Integer j, Integer r, List<E> ls, E elem) {
			return new IND<>(i, j, r, ls, elem);
		}
		
		public static <E> IND<E> of(List<E> ls, E elem) {
			return new IND<>(0, ls.size(), -1, ls, elem);
		}
		

		public Boolean g() {
			return j - i > 2 && r == -1;
		}

		public IND<E> nxt() {
			Integer r = -1;;
			if(ls.get(i).equals(elem)) r = i;
			else if(ls.get(j-1).equals(elem)) r = j-1;
			return IND.of(i + 1, j - 1, r, ls, elem);
		}
	}
	
	public static <E> Integer index10r(List<E> ls, E elem) {
		Stream<IND<E>> st = Stream.iterate(IND.of(ls, elem),e->e.nxt())
				.filter(e->!e.g());
		IND<E> pw = st.findFirst().get();
		Integer i = pw.i();
		Integer j = pw.j();
		Integer r = -1;
		if (j-i == 0) r = -1;
		else if(j-i == 1 && ls.get(i).equals(elem)) r = i;
		else if(j-i == 2 && ls.get(i).equals(elem)) r = i;
		else if(j-i == 2 && ls.get(j-1).equals(elem)) r = j-1;
		return r;
				
	}
	
	//Resuelto con la generalización sufija y agregado no indexable y no ordenado
	
	public static <E> Integer index2(Iterable<E> it, E e) {
		Stream<Enumerate<E>> s = Stream2.enumerate(Stream2.of(it));
		Optional<Enumerate<E>> entry = s.filter(p->p.value().equals(e))
				.findFirst();
		return entry.isPresent()?entry.get().counter():-1;
	}
	
	public static <T> Integer index8(Iterable<T> it, T e) {
		Integer i = 0;
		Integer b = -1;
		Iterator<T> itt = it.iterator();
		while(itt.hasNext() && b == -1){
		   T a = itt.next();
		   if(a.equals(e)) b = i;
		   i = i + 1;
		}
		return b;
	}
	
	public static <E> Integer index9(Iterable<E> it, E elem, Comparator<E> cmp) {
		Integer i = 0;
		Integer b = -1;
		Iterator<E> itt = it.iterator();
		E a = null;
		while(itt.hasNext() && b == -1 && ((a==null) || cmp.compare(a,elem)<=0)){
		   a = itt.next();
		   if(a.equals(elem)) b = i;
		   i = i +1;
		}
		return b;
	}
	
	//Resuelto con la generalización sufija y agregado no indexable y ordenado
	
	public static <E> Integer index2Ord(Iterable<E> it, E e) {
		Stream<Enumerate<E>> s = Stream2.enumerate(Stream2.of(it));
		Optional<Enumerate<E>> entry = s.filter(p->p.value().equals(e))
				.findFirst();
		return entry.isPresent()?entry.get().counter():-1;
	}
	
	public static <T> Integer index8Ord(Iterable<T> it, T e) {
		Integer i = 0;
		Integer b = -1;
		Iterator<T> itt = it.iterator();
		while(itt.hasNext() && b == -1){
		   T a = itt.next();
		   if(a.equals(e)) b = i;
		   i = i + 1;
		}
		return b;
	}
	
	public static <E> Integer index9Ord(Iterable<E> it, E elem, Comparator<E> cmp) {
		Integer i = 0;
		Integer b = -1;
		Iterator<E> itt = it.iterator();
		E a = null;
		while(itt.hasNext() && b == -1 && ((a==null) || cmp.compare(a,elem)<=0)){
		   a = itt.next();
		   if(a.equals(elem)) b = i;
		   i = i +1;
		}
		return b;
	}
	
	public static void test6() throws IOException {
		Integer e = 13;
		List<Integer> ls1 = List2.of(-1,3,5,5,7,9,13,15,15,17,19);
		System.out.println(index1(ls1,e));
		System.out.println(index10(ls1,e));
		System.out.println(index4(ls1,e,Comparator.naturalOrder()));
		System.out.println(index5(ls1,e,Comparator.naturalOrder()));
		System.out.println(index9(ls1,e,Comparator.naturalOrder()));
		Iterator<Integer> it1 = Files.lines(Path.of("ficheros/numeros.txt")).map(x->Integer.parseInt(x)).iterator();
		Iterator<Integer> it2 = Files.lines(Path.of("ficheros/numeros.txt")).map(x->Integer.parseInt(x)).iterator();
		Iterator<Integer> it3 = Files.lines(Path.of("ficheros/numeros.txt")).map(x->Integer.parseInt(x)).iterator();
		System.out.println(String.format("1: %d, %d, %d",index2(()->it1,e), index8(()->it2,e), index9(()->it3,e,Comparator.naturalOrder())));
	}


	public static void main(String[] args) throws IOException {
		test6();
	}
}
