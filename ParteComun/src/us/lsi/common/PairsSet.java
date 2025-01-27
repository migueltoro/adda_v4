package us.lsi.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import us.lsi.streams.Collectors2;

public class PairsSet<A, B> extends HashSet<Pair<A, B>> {
	
	public static <A, B> PairsSet<A, B> empty() {
		return new PairsSet<A, B>();
	}
	
	public static <A, B> PairsSet<A, B> of(Collection<? extends Pair<A, B>> c) {
		return new PairsSet<A, B>(c);
	}
	
	public static <A, B> PairsSet<A, B> of(Stream<Pair<A, B>> st) {
		PairsSet<A, B> r = PairsSet.<A, B>empty();
		st.forEach(e->r.add(e));
		return r;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private PairsSet() {
		super();
	}

	private PairsSet(Collection<? extends Pair<A, B>> c) {
		super(c);
	}

	public Map<A,List<B>> asMap1() {
		return this.stream().collect(Collectors2.groupingList(e->e.first(),e->e.second()));
	}
	
	public Map<B,List<A>> asMap2() {
		return this.stream().collect(Collectors2.groupingList(e->e.second(),e->e.first()));
	}
	
	public Map<A,Set<B>> asMapSet1() {
		return this.stream().collect(Collectors2.groupingSet(e->e.first(),e->e.second()));
	}
	
	public Map<B,Set<A>> asMapSet2() {
		return this.stream().collect(Collectors2.groupingSet(e->e.second(),e->e.first()));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
