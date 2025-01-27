package us.lsi.clases.problemasdelistas;

import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.Multiset;
import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.math.*;
import us.lsi.streams.Stream2;



public class ProblemasDeListas {
	
	public static <E> E masDeLaMitad(List<E> ls) {
		Integer n = ls.size();
		Multiset<E> ms = Stream2.toMultiSet(ls.stream());
		return ms.elementSet().stream().map(e->Pair.of(e,ms.count(e)))
				.filter(p->p.second()>=n/2)
				.findFirst()
				.map(p->p.first())
				.orElse(null);
	}
	
	public static <E> E masDeLaMitad2(List<E> ls,Comparator<? super E> cmp) {
		IntPair p = masDeLaMitad2R(ls,cmp);
		return p==null?null:ls.get(p.first());
	}
	
	private static <E> IntPair masDeLaMitad2R(List<E> ls,Comparator<? super E> cmp) {
		Integer n = ls.size();
		IntPair r;
		E pivote = escogePivote(ls,0,n);
		IntPair p = banderaHolandesa(ls,pivote,cmp);
		if(p.size() >= n/2) r = p;
		else if(p.first() >=n/2) r = masDeLaMitad2R(ls.subList(0,p.first()),cmp);
		else if(n-p.second() >=n/2) r = masDeLaMitad2R(ls.subList(p.second(),n),cmp);
		else r = null;
		return r;
	}
	
	public static <E extends Comparable<? super E>> int binarySearch(List<E> lista, E key){
		Comparator<E> ord = Comparator.naturalOrder();
		return bSearch(lista,0,lista.size(),key,ord);	
	}
	
	public static <E> int binarySearch(List<E> lista, E key, Comparator<? super E> cmp){
		return bSearch(lista,0,lista.size(),key,cmp);	
	}
	
	private static <E> int bSearch(List<E> lista, int i,int j, E key, Comparator<? super E> ord){
		Preconditions.checkArgument(j>=i);
		int r;
		int k;
		if(j-i == 0){
			r = -1;
		}else{
			k = (i+j)/2;
			int r1 = ord.compare(key,lista.get(k));
			if(r1==0){
				r = k;
			}else if(r1<0){
				r = bSearch(lista,i,k,key,ord);
			}else{
				r = bSearch(lista,k+1,j,key,ord);
			}
		}
		return r;
	}
	
	public static <E extends Comparable<? super E>> void sort(List<E> lista){
		Comparator<? super E> ord = Comparator.naturalOrder();
		quickSort(lista,0,lista.size(),ord);	
	}
	
	public static <E> void sort(List<E> lista, Comparator<? super E> cmp){
		quickSort(lista,0,lista.size(),cmp);	
	}
	
	public static <E> IntPair banderaHolandesa(List<E> ls, E pivote, Comparator<? super E> cmp){
		Integer n = ls.size();
		return banderaHolandesa(ls,pivote,0,n,cmp);
	}
	
	public static <E> IntPair banderaHolandesa(List<E> ls, E pivote, Integer i, Integer j,  Comparator<? super E> cmp){
		Integer a=i, b=i, c=j;
		while (c-b>0) {
		    E elem =  ls.get(b);
		    if (cmp.compare(elem, pivote)<0) {
		    	List2.intercambia(ls,a,b);
				a++;
				b++;
		    } else if (cmp.compare(elem, pivote)>0) {
		    	List2.intercambia(ls,b,c-1);
				c--;	
		    } else {
		    	b++;
		    }
		}
		return IntPair.of(a, b);
	}
	
	
	
	public static record Rbh<E>(Integer a, Integer b, Integer c, List<E> ls, E pivote, Comparator<? super E> cmp) {
		public static <E> Rbh<E> of(Integer a, Integer b, Integer c, List<E> ls, E pivote, Comparator<? super E> cmp){
			return new Rbh<>(a, b, c, ls, pivote, cmp);
		}
		public static <E> Rbh<E> of(List<E> ls, E pivote, Comparator<? super E> cmp){
			return new Rbh<>(0, 0, ls.size(), ls, pivote, cmp);
		}
		public Rbh<E> next(){
			Integer a1 = a(),b1 = b(),c1 = c();
			E elem =  ls.get(b1);
		    if (cmp.compare(elem, pivote)<0) {
		    	List2.intercambia(ls,a1,b1);
				a1++;
				b1++;
		    } else if (cmp.compare(elem, pivote)>0) {
		    	List2.intercambia(ls,b1,c1-1);
				c1--;	
		    } else {
		    	b1++;
		    }
		    return Rbh.of(a1, b1, c1, ls, pivote, cmp);
		}
	}
	
	public static <E> IntPair banderaHolandesaF(List<E> ls, E pivote, Comparator<? super E> cmp){
		Rbh<E> s = Stream.iterate(Rbh.of(ls, pivote, cmp),Rbh::next)
				.filter(r->r.b()-r.c()==0)
				.findFirst()
				.get();
		return 	IntPair.of(s.a(),s.b());
	}

	public static <T> Integer reordenaSobrePivote(List<T> lista, T pivote, Integer i, Integer j,  Comparator<? super T> cmp){
		int a, b; 
		a = i;  
		b = j; 
		while (b-a>0) {
			T elem =  lista.get(a);
			 if (cmp.compare(elem, pivote)<0){
				 a++; 
			 } else {
				 List2.intercambia(lista, a,b-1);
				 b--;
			 }
		}
		return a;
	}
	
	public static Integer umbral = 4;
	
	public static <E> void quickSort(List<E> lista, Comparator<? super E> ord){
		quickSort(lista, 0, lista.size(), ord);
	}
	
	private static <E> void quickSort(List<E> lista, int i, int j, Comparator<? super E> ord){
		Preconditions.checkArgument(j>=i);
		if(j-i <= umbral){
			ordenaBase(lista, i, j, ord);
		}else{
			E pivote = escogePivote(lista, i, j);
			IntPair p = banderaHolandesa(lista, pivote, i, j, ord);
			quickSort(lista,i,p.first(),ord);
			quickSort(lista,p.second(),j,ord);			
		}
	}
	
	public static <T> void ordenaBase(List<T> lista, Integer inf, Integer sup, Comparator<? super T> ord) {		
		for (int i = inf; i < sup; i++) {
		      for(int j = i+1; j < sup; j++){
		    	  if(ord.compare(lista.get(i),lista.get(j))>0){
		    		  List2.intercambia(lista, i, j);
		    	  }
		      }
		}
	}
	
	public static <E extends Comparable<? super E>> void mergeSort(List<E> lista){
		mergeSort(lista,1);	
	}
	
	public static <E> void mergeSort(List<E> lista, Comparator<? super E> ord){
		mergeSort(lista,ord,1);	
	}

	public static <E extends Comparable<? super E>> void mergeSort(List<E> lista, Integer umbral){
		Comparator<? super E> ord = Comparator.naturalOrder();
		List<E> ls = List2.ofCollection(lista);
		mgSort(lista,0,lista.size(),ord,ls,umbral);	
	}
	
	public static <E> void mergeSort(List<E> lista, Comparator<? super E> ord, Integer umbral){
		List<E> ls = List2.ofCollection(lista);
		mgSort(lista,0,lista.size(),ord,ls,umbral);	
	}
	
	private static <E> void mgSort(List<E> lista, int i, int j, Comparator<? super E> ord, List<E> ls, Integer umbral){
		if(j-i <= umbral){
			ordenaBase(lista, i, j, ord);
		}
		else {
			int k = (j+i)/2;
			mgSort(lista,i,k,ord,ls,umbral);
			mgSort(lista,k,j,ord,ls,umbral);
			mezcla(lista,i,k,lista,k,j,ls,i,j,ord);
			copia(lista,i,j,ls);
		}
	}
	
	private static <E> void mezcla(List<E> l1, int i1, int j1, List<E> l2, int i2, int j2,List<E> l3, int i3, int j3, Comparator<? super E> ord){
		int k1= i1;
		int k2= i2;
		int k3= i3;
		while(k3<j3){
			if(k1<j1 && k2<j2){
				if(ord.compare(l1.get(k1), l2.get(k2))<=0){
					l3.set(k3, l1.get(k1));
					k1++;
					k3++;
				}else{
					l3.set(k3, l2.get(k2));
					k2++;
					k3++;
				}
			}else if(k2==j2){
				l3.set(k3, l1.get(k1));
				k1++;
				k3++;
			}else{
				l3.set(k3, l2.get(k2));
				k2++;
				k3++;
			}
		}
	}
	
	private static <E> void copia(List<E> lista, int i, int j, List<E> ls){
		for(int k = i; k<j; k++){
			lista.set(k, ls.get(k));
		}
	}
	
	private static <E> E escogePivote(List<E> lista, int i, int j) {
		E pivote = lista.get(Math2.getEnteroAleatorio(i, j));
		return pivote;
	}
	
	public static <E extends Comparable<? super E>> E getKesimo(List<E> lista, int k){
		Preconditions.checkElementIndex(k, lista.size());
		Comparator<? super E> ord = Comparator.naturalOrder();
		return escogeKesimo(lista,0,lista.size(),k,ord);	
	}
	
	public static <E> E getKesimo(List<E> lista, int k, Comparator<? super E> cmp){
		Preconditions.checkElementIndex(k, lista.size());
		return escogeKesimo(lista,0,lista.size(),k,cmp);
	}
	
	private static <E> E escogeKesimo(List<E> lista, int i, int j, int k, Comparator<? super E> ord){
		Preconditions.checkArgument(j>=i);
		E r;
		if(j-i == 0){
			r = null;
		} else if(j-i == 1){
			r = lista.get(i);
		}else{
			E pivote = escogePivote(lista, i, j);
			IntPair p = banderaHolandesa(lista, pivote, i, j, ord);
			if(k < p.first()){
				r = escogeKesimo(lista,i,p.first(),k,ord);
			}else if(k >= p.second()){
				r = escogeKesimo(lista,p.second(),j,k,ord);
			}else{
				r = lista.get(k);
			}					
		}
		return r;
	}	
	
	public static SubSecuencia getSubSecuenciaMaxima(List<Double> lista){
		return subSecuenciaMaxima(lista,0,lista.size());
	}
	
	private static SubSecuencia subSecuenciaMaxima(List<Double> ls, int i, int j){
		SubSecuencia r = null;	
		if(j-i <= 1){
			r = SubSecuencia.of(i,j,ls.get(i),ls);
		}else{
			int k = (i+j)/2;
			SubSecuencia s1 = subSecuenciaMaxima(ls, i, k);
			SubSecuencia s2 = subSecuenciaMaxima(ls, k, j);
			SubSecuencia s3 = subSecuenciaMaximaCentrada(ls,i,j,k);
			r = Stream.of(s1, s2, s3).max(Comparator.naturalOrder()).get();
		}
		return r;
	}	
	
	private static SubSecuencia subSecuenciaMaximaCentrada(List<Double> ls, int i, int j, int k){
		Double suma = 0.;
		SubSecuencia smax = SubSecuencia.of(k,k,0., ls);
		for(Integer i1 = k-1;i1>=i;i1--) {
			suma = suma + ls.get(i1);  
			SubSecuencia s = SubSecuencia.of(i1,k,suma, ls);
			if(s.compareTo(smax) >0) smax = s;
		}
		suma = smax.suma();
		Integer i1 = smax.from();
		for(Integer j1 = k;j1<j;j1++) {
			suma = suma + ls.get(j1);  
			SubSecuencia s = SubSecuencia.of(i1,j1+1,suma,ls);
			if(s.compareTo(smax) >0) smax = s;
		}
		return smax;
	}
	
	public static record SubSecuencia(Integer from, Integer to, Double suma, List<Double> ls) implements Comparable<SubSecuencia>{
		
		public static SubSecuencia of(Integer from, Integer to, Double suma, List<Double> ls) {
			return new SubSecuencia(from,to,suma, ls);
		}
		
		public static SubSecuencia of(Integer from, Integer to, List<Double> ls) {
			Double suma = IntStream.range(from,to).mapToDouble(i->ls.get(i)).sum();
			return new SubSecuencia(from,to,suma, ls);
		}

		@Override
		public int compareTo(SubSecuencia s) {
			return this.suma().compareTo(s.suma());
		}

		@Override
		public String toString() {
			return String.format("(%d,%d,%s)",from,to,suma());
		}
	}

	
}
