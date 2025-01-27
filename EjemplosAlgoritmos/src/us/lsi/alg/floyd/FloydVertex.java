package us.lsi.alg.floyd;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.GraphWalk;

import us.lsi.common.List2;
import us.lsi.graphs.SimpleEdge;
import us.lsi.hypergraphs.VirtualHyperVertex;


public record FloydVertex(Integer i,Integer j,Integer k) 
	implements VirtualHyperVertex<FloydVertex,FloydEdge,Boolean,GraphWalk<Integer,SimpleEdge<Integer>>>{

	
	public static FloydVertex initial(Integer i,Integer j) {	
		return new FloydVertex(i,j,0);
	}
	
	public static FloydVertex of(Integer i,Integer j,Integer k) {	
		return new FloydVertex(i,j,k);
	}

	public static Graph<Integer,SimpleEdge<Integer>> graph;
	public static Integer n;
	
	
	public FloydVertex neigbord(Integer i, Integer j, Integer k){
		return new FloydVertex(i,j,k);
	}
	
	@Override
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
	public List<FloydVertex> neighbors(Boolean a) {
		List<FloydVertex> r=null;
		if(!a) r = List.of(this.neigbord(i,j,k+1)); 
		else r = List.of(this.neigbord(i, k, k+1),this.neigbord(k, j, k+1)); 
		return r;
	}
	
	@Override
	public FloydEdge edge(Boolean a) {
		return FloydEdge.of(this,this.neighbors(a), a);
	}
	
	@Override
	public Boolean isBaseCase() {
		return  k == n; //FloydVertex.graph.containsEdge(this.i,this.j) ;
	}
	
	@Override
	public Double baseCaseWeight() {
		Double r = null;
		if(i==j) r = 0.;
		else if(k ==n && FloydVertex.graph.containsEdge(this.i, this.j))
			r = FloydVertex.graph.getEdge(i, j).weight();
		else if(k ==n && !FloydVertex.graph.containsEdge(this.i, this.j)) 
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
	public GraphWalk<Integer,SimpleEdge<Integer>> baseCaseSolution() {
		Integer origen = i;
		Integer destino = j;
		List<Integer> ls = List2.of(origen,destino);
		return new GraphWalk<Integer,SimpleEdge<Integer>>(FloydVertex.graph,ls,this.baseCaseWeight());
	}

	@Override
	public GraphWalk<Integer,SimpleEdge<Integer>> solution(List<GraphWalk<Integer,SimpleEdge<Integer>>> solutions) {
		if(solutions.size()==1){
			return solutions.get(0);
		} else {
			GraphWalk<Integer,SimpleEdge<Integer>> gp1 = solutions.get(0);
			GraphWalk<Integer,SimpleEdge<Integer>> gp2 = solutions.get(1);
			return gp1.concat(gp2,g->g.getWeight()+gp2.getWeight());
		}
	}

}
