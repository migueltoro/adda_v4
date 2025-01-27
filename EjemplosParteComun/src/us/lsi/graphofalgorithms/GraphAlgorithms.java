package us.lsi.graphofalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.colors.GraphColors;


public class GraphAlgorithms {
	
	
	public static <V> SimpleDirectedGraph<V,DefaultEdge> graph(Map<V,List<V>> m){
		SimpleDirectedGraph<V,DefaultEdge> g = new SimpleDirectedGraph<V,DefaultEdge>(DefaultEdge.class);
		for(V k:m.keySet()) {
			if(!g.containsVertex(k)) g.addVertex(k);
			for(V v:m.get(k)) {
				if(!g.containsVertex(v)) g.addVertex(v);
				g.addEdge(k, v);
			}
		}
		return g;
	}
	
	public static record Ds(Integer i, Integer j, Integer r) {
		public static Ds of(Integer i, Integer j,Integer r) {
			return new Ds(i,j,r);
		}
		public String toString( ) {
			return String.format("i=%d,j=%d,r=%d",this.i,this.j,this.r);
		}
	}
	
	public static record Dsb(Integer i, Integer j,Integer r) {
		public static Dsb of(Integer i, Integer j,Integer r) {
			return new Dsb(i,j,r);
		}
		public String toString( ) {
			return String.format("i=%d,b=%d,r=%d",this.i,this.j,this.r);
		}
	}
	
	public static record Dsc(Integer i,Integer r) {
		public static Dsc of(Integer i,Integer r) {
			return new Dsc(i,r);
		}
		public String toString( ) {
			return String.format("i=%d,r=%d",this.i,this.r);
		}
	}
	
	public static Map<Ds,List<Ds>> problems = new HashMap<>();
	public static Map<Dsb,List<Dsb>> problemsb = new HashMap<>();
	public static Map<Dsc,List<Dsc>> problemsc = new HashMap<>();
	
	public static Integer suma1(List<Integer> ls) {
		Integer n = ls.size();
		problems = new HashMap<>();
		return suma1(0,n, ls);
	}

	private static Integer suma1(Integer i, Integer j, List<Integer> ls) {
		Integer r;
		Integer k = (i+j)/2;
		if(j-i == 0) {
			r = 0;
			if(!problems.containsKey(Ds.of(i, j, r))) problems.put(Ds.of(i,j,r), new ArrayList<>());
		} else if(j-i == 1) {
			r = ls.get(i);
			if(!problems.containsKey(Ds.of(i, j,r))) problems.put(Ds.of(i, j,r), new ArrayList<>());
		}
		else {
			Integer r1 = suma1(i,k,ls);
			Integer r2 = suma1(k,j,ls);
			r = r1+r2;
			if(!problems.containsKey(Ds.of(i, j,r))) {
				problems.put(Ds.of(i,j,r), Arrays.asList(Ds.of(i,k,r1),Ds.of(k,j,r2)));
			}
		}
		return r;
	}
	
	public static Integer suma2(List<Integer> ls) {
		Integer n = ls.size();
		problems = new HashMap<>();
		return suma2(0,n,ls);
	}

	private static Integer suma2(Integer i, Integer j, List<Integer> ls) {
		Integer r;
		if(j-i == 0) {
			r = 0;
			if(!problems.containsKey(Ds.of(i, j,r))) problems.put(Ds.of(i, j,r), new ArrayList<>());
		} else if(j-i == 1) {
			r = ls.get(i);
			if(!problems.containsKey(Ds.of(i, j,r))) problems.put(Ds.of(i,j,r), new ArrayList<>());
		}
		else {
			Integer r1 = suma2(i+1,j-1,ls);
			r = r1 + ls.get(i)+ls.get(j-1);
			if(!problems.containsKey(Ds.of(i,j,r))) {
				problems.put(Ds.of(i, j, r), Arrays.asList(Ds.of(i+1,j-1, r1)));
			}
		}
		return r;
	}
	
	public static Integer suma3(List<Integer> ls) {
		problemsb = new HashMap<>();
		return suma3(0,0,ls);
	}

	private static Integer suma3(Integer i, Integer b, List<Integer> ls) {
		Integer r = b;
		Integer n = ls.size();
		if(n-i>0) {
			r = suma3(i+1,b+ls.get(i),ls);
			if(!problemsb.containsKey(Dsb.of(i,b,r))) {
				problemsb.put(Dsb.of(i, b, r), Arrays.asList(Dsb.of(i+1,b+ls.get(i), r)));
			}
		}
		return r;
	}
	
	public static Integer suma4(List<Integer> ls) {
		problemsc = new HashMap<>();
		return suma4(0,ls);
	}

	private static Integer suma4(Integer i, List<Integer> ls) {
		Integer r = 0;
		Integer n = ls.size();
		if(n-i>0) {
			Integer r1 = suma4(i+1,ls);
			r = ls.get(i) +r1;
			if(!problemsc.containsKey(Dsc.of(i,r))) {
				problemsc.put(Dsc.of(i,r), Arrays.asList(Dsc.of(i+1, r1)));
			}
		} else {
			problemsc.put(Dsc.of(n,0), Arrays.asList());
		}
		return r;
	}



	public static void main(String[] args) {
		System.out.println("1 = "+suma1(Arrays.asList(0,4,-1,67,89,55,99,100,101)));
		System.out.println(problems);
		GraphColors.toDot(GraphAlgorithms.graph(problems),"ficheros/problemas1.gv");
		System.out.println("2 = "+suma2(Arrays.asList(0,4,-1,67,89,55,99,100,101)));
		System.out.println(problems);
		GraphColors.toDot(GraphAlgorithms.graph(problems),"ficheros/problemas2.gv");
		System.out.println("3 = "+suma3(Arrays.asList(0,4,-1,67,89,55,99,100,101)));
		System.out.println(problemsb);
		GraphColors.toDot(GraphAlgorithms.graph(problemsb),"ficheros/problemas3.gv");
		System.out.println("4 = "+suma4(Arrays.asList(0,4,-1,67,89,55,99,100,101)));
		System.out.println(problemsc);
		GraphColors.toDot(GraphAlgorithms.graph(problemsc),"ficheros/problemas4.gv");
	}

}
