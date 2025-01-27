package us.lsi.iterables;

import java.util.Iterator;
import java.util.List;

public class Test4 {
	
	public static record Tm(Integer a, Integer b) {
		public static Tm of(Integer a, Integer b){
			return new Tm(a,b);
		}	
		public boolean hn() {
			return this.b() >0;
		}
		public Tm nx() {
			return Tm.of(this.b(), this.a() % this.b());
		}
		public Integer rt() {
			return this.a();
		}	
	}
	
	public static class Itm implements Iterator<Tm>, Iterable<Tm> {
		private Tm t;
		public Itm(Integer a, Integer b) {
			this.t = Tm.of(a,b);
		}
		@Override
		public Iterator<Tm> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Tm next() {
			this.t = Tm.of(t.b(), this.t.a() % t.b());
			return t;
		}
	}

	public static void main(String[] args) {
		List<Integer> ls = List.of(45,67,89,90,567,45,67,89,90,567,1);
		System.out.println(Iterables.toListRight(ls));
		System.out.println(Iterables.toSet(ls));
		System.out.println(Iterables.groupingList(ls,e->e%3));
		System.out.println(Iterables.groupingListRight(ls,e->e%3));
		System.out.println(Iterables.counting(ls,e->e%3));
		System.out.println(Iterables.findLast(ls));
		System.out.println(Iterables.findFirst(ls));
		System.out.println(Iterables.joining(Iterables.map(ls,e->e.toString()),",","{","}"));
		System.out.println(Iterables.all(ls,e->e>2));
		System.out.println(Iterables.none(ls,e->e<1));
		System.out.println(Iterables.sequentialAlg(new Itm(123456,24566),t->t.b()>0,t->t.a()).get());
		System.out.println(Iterables.sequentialAlg(Tm.of(123456,24566),t->t.hn(),t->t.nx(),t->t.rt()).get());
		Iterable<Integer> it = Iterables.explicit(0,e->e<234,e->e+1);
		System.out.println(Iterables.reduce(it,(e1,e2)->e1+e2));
		it = Iterables.explicit(0,e->e<234,e->e+1);
		System.out.println(Iterables.reduceRight(it,(e1,e2)->e1+e2));
	}

}
