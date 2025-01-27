package us.lsi.clase.listarotada;

import java.util.List;
import java.util.stream.Stream;

import us.lsi.common.Preconditions;

public class ListaRotada {
	
//	Dado una lista ordenada y rotada k veces, diseñar un algoritmo Θ(log n) 
//	que encuentre el elemento mayor de la lista, suponiendo que no conocemos el valor de k. 
	
	public static Integer mayor_r(List<Integer> ls) {
		Preconditions.checkArgument(!ls.isEmpty());
		return mayor_r(ls,0,ls.size());
	}
	
	private static Integer mayor_r(List<Integer> ls, int i, int j) {
		Integer r;
		if(j-i == 1)
			r = ls.get(i);
		else {
			int k =(j+i)/2;
			if(ls.get(i) >= ls.get(k))
				r = mayor_r(ls, i, k);
			else
				r = mayor_r(ls, k, j);
		}
		return r;
	}
	
	public static Integer mayor_i(List<Integer> ls) {
		Preconditions.checkArgument(!ls.isEmpty());
		int i=0;
		int j=ls.size();
		while(j-i > 1) {
			int k =(j+i)/2;
			if(ls.get(i) >= ls.get(k)) j = k;
			else i = k;
		}
		return ls.get(i);
	}
	
	public static record MM(Integer i, Integer j, List<Integer> ls) {
		public static MM of(Integer i, Integer j, List<Integer> ls) { return new MM(i,j,ls);}
		public MM next() {
			int i = this.i();
			int j = this.j();
			List<Integer> ls = this.ls();
			int k =(j+i)/2;
			if(ls.get(i) >= ls.get(k)) j = k;
			else i = k;
			return MM.of(i, j, ls);
		}
		public Boolean guard() {
			int i = this.i();
			int j = this.j();
			return j-i == 1;
		}
		
		Integer value() {
			return this.ls().get(this.i());
		}
	}
	
	public static Integer mayor_f(List<Integer> ls) {
		Preconditions.checkArgument(!ls.isEmpty());
		MM v = Stream.iterate(MM.of(0,ls.size(),ls),m->m.next()).filter(m->m.guard()).findFirst().get();
		return v.value();
	}


}
