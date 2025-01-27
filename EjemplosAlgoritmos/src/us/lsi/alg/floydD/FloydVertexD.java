package us.lsi.alg.floydD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.graph.GraphWalk;

import us.lsi.alg.floyd.FloydVertex;
import us.lsi.common.List2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.alg.PD.Sp;
import us.lsi.graphs.views.IntegerVertexGraphView;
import us.lsi.hypergraphsD.HyperVertexD;

public record FloydVertexD(Integer i,Integer j,Integer k) 
		implements HyperVertexD<FloydVertexD,FloydEdgeD,Boolean,GraphWalk<Integer,SimpleEdge<Integer>>>{

	public static Map<FloydVertexD,Sp<FloydEdgeD>> memory = new HashMap<>();
	
	public static FloydVertexD initial(Integer i,Integer j) {	
		return new FloydVertexD(i,j,0);
	}
	
	public static FloydVertexD of(Integer i,Integer j,Integer k) {	
		return new FloydVertexD(i,j,k);
	}

	public static IntegerVertexGraphView<Ciudad,Carretera> graph;
	public static Integer n;
	
	public Boolean isValid() {
		return 0 <= i && i < n && 0 <= j && j < n && 0<= k && j <= n;
	}
	@Override
	public List<Boolean> actions() {
		if(this.isBaseCase()) return List.of();
		else if(i==k || k==j) return List.of(false);
		else return List.of(false,true);
	}
	
	@Override
	public List<FloydVertexD> neighbors(Boolean a) {
		List<FloydVertexD> r=null;
		if(!a) r = List.of(FloydVertexD.of(i,j,k+1)); 
		else r = List.of(FloydVertexD.of(i, k, k+1),FloydVertexD.of(k, j, k+1)); 
		return r;
	}
	
	@Override
	public FloydEdgeD edge(Boolean a) {
		return FloydEdgeD.of(this,this.neighbors(a), a);
	}
	
	@Override
	public Boolean isBaseCase() {
		return  k == n || i == j; //FloydVertex.graph.containsEdge(this.i,this.j) ;
	}
	
	@Override
	public Double baseCaseWeight() {
		Double r = null;
		if(i==j) r = 0.;
		else if(k ==n && FloydVertexD.graph.containsEdge(this.i, this.j))
			r = FloydVertexD.graph.getEdge(i, j).weight();
		else if(k ==n && !FloydVertexD.graph.containsEdge(this.i, this.j))
			r = null;
		return r;
	}
	
	public Integer getSource() {
		return this.i;
	}
	
	public Integer getTarget() {
		return j;
	}

	@Override
	public GraphWalk<Integer, SimpleEdge<Integer>> baseCaseSolution() {
		GraphWalk<Integer, SimpleEdge<Integer>> gp = null;
		if(this.i.equals(this.j)) 
			gp = new GraphWalk<>(FloydVertexD.graph,List2.of(i),0.);
		else if(k ==n && FloydVertexD.graph.containsEdge(this.i, this.j)){
			Double w = FloydVertexD.graph.getEdge(i, j).weight();
			List<Integer> ls = List2.of(i,j);
			gp = new GraphWalk<>(FloydVertexD.graph,ls,w);
		} else if(k ==n && !FloydVertex.graph.containsEdge(this.i, this.j)) {
			gp = null;
		}
		return gp;
	}

	@Override
	public FloydVertexD me() {
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d,%d)",i(),j(),k());
	}
	
}
