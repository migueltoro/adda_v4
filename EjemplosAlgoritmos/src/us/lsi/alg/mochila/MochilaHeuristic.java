package us.lsi.alg.mochila;


import java.util.function.Predicate;
import org.jgrapht.GraphPath;
import us.lsi.graphs.alg.Greedy;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.mochila.datos.DatosMochila;

public class MochilaHeuristic {
	
	public static Double heuristic1(MochilaVertex v1, Predicate<MochilaVertex> goal, MochilaVertex v2) {
		return hu(Md.of(v1.index(),(double)v1.capacidadRestante()),v->v.index()==MochilaVertexI.n|| v.cr()==0.);
	}
	
	public static Double heuristic2(MochilaVertex v1, Predicate<MochilaVertex> goal, MochilaVertex v2) {
		return 1000.*(MochilaVertexI.n-v1.index());
	}

	public static record Md(Integer index, Double cr) {
		public static Md of(Integer index, Double cr) {
			return new Md(index,cr);
		}
		public Double heuristicAction() {
			return Math.min(cr()/DatosMochila.getPeso(index()),DatosMochila.getNumMaxDeUnidades(index()));
		}
		public Md next() {
			Double a = heuristicAction();
			return new Md(index()+1, cr() - a * DatosMochila.getPeso(index()));
		}
		public Double weight() {
			if(this.index >= DatosMochila.n) return 0.;
			return heuristicAction()*DatosMochila.getValor(index());
		}
	}

	public static Double hu(Md v1, Predicate<Md> goal) {	
		Greedy<Md> r = Greedy.of(v1,v->v.next(),goal);
		return r.stream().mapToDouble(v->v.weight()).sum();
	}
	
	public static SolucionMochila solucionVoraz(MochilaVertex v, EGraph<MochilaVertex, MochilaEdge> graph) {
		GraphPath<MochilaVertex, MochilaEdge> r = 
				GreedyOnGraph.of(graph,MochilaVertex::greedyEdge).path();
		return SolucionMochila.of(r);
	}
}
