package us.lsi.alg.secuencias;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import us.lsi.graphs.virtual.VirtualVertex;

public record SeqVertex(Integer	index,String s) implements VirtualVertex<SeqVertex,SeqEdge,SeqAction>{
	
	public static List<SeqAction> actions = Arrays.asList(SeqAction.values());
	
	
	public static SeqVertex of(Integer index, String s) {
		return new SeqVertex(index, s);
	}
	
	public static SeqVertex first() {
		return new SeqVertex(0, SeqVertex.s1);
	}
	
	public static SeqVertex last() {
		return new SeqVertex(SeqVertex.n2, SeqVertex.s2);
	}
	
	public static void data(String s1, String s2) {
		SeqVertex.s1 = s1;
		SeqVertex.s2 = s2;
		SeqVertex.n2 = s2.length();
	}
	
	public Integer n() {
		return this.s().length();
	}
	
	public static String s1; //compartida
	public static String s2; // compartida
	public static Integer n2; // Integer, tama�o de s2, derivada
	

	@Override
	public Boolean isValid() {
		return true;
	}

	@Override
	public List<SeqAction> actions() {
		List<SeqAction> r;
		if(this.n()- this.index() == 0 && SeqVertex.n2 - this.index() > 0)
			r = List.of(SeqAction.a); 
		else if(this.n()- this.index() > 0 && SeqVertex.n2 - this.index() == 0) 
			r = List.of(SeqAction.e);
		else if(this.n()- this.index() > 0  && SeqVertex.n2 - this.index() > 0  &&
					this.s().charAt(this.index()) != SeqVertex.s2.charAt(this.index()))
				r = List.of(SeqAction.c,SeqAction.e);
		else if(this.n()- this.index() > 0  && SeqVertex.n2 - this.index() > 0  &&
				this.s().charAt(this.index()) == SeqVertex.s2.charAt(this.index()))
				r = List.of(SeqAction.m);
		else 
			r = List.of();
		return r;	
	}
	
	private static String sustitute(String s1, int index, String s2) {
		String pf = s1.substring(0,index);
		String sf = s1.substring(index+1,s1.length());
		return pf+s2.charAt(index)+sf;
	}
	
	private static String eliminate(String s1, int index) {
		String pf = s1.substring(0,index);
		String sf = s1.substring(index+1,s1.length());
		return pf+sf;
	}

	@Override
	public SeqVertex neighbor(SeqAction a) {
		return switch(a) {
		case a -> SeqVertex.of(this.index()+1,this.s()+SeqVertex.s2.charAt(this.index()));
		case c -> SeqVertex.of(this.index()+1,
				SeqVertex.sustitute(this.s(),this.index(),""+SeqVertex.s2));
		case e -> SeqVertex.of(this.index(),SeqVertex.eliminate(this.s(),this.index()));
		case m -> SeqVertex.of(this.index()+1,this.s());
		};
	}

	@Override
	public SeqEdge edge(SeqAction a) {
		return SeqEdge.of(this, this.neighbor(a), a);
	}	

	@Override
	public String toString() {
		return String.format("(%d,%s,%s)",this.index(),this.s(),SeqVertex.s2);
	}

	@Override
	public Boolean goal() {
		return this.equals(last());
	}

	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	@Override
	public SeqAction greedyAction() {
		Random r = new Random();
		List<SeqAction> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
	}
	
}
