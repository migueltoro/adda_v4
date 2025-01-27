package us.lsi.alg.tsp;

import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.Graph;

import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.grafos.datos.Carretera;
import us.lsi.graphs.virtual.VirtualVertex;
import us.lsi.path.GraphPath2;
import us.lsi.streams.Stream2;

public record TravelVertex(List<Ciudad> camino) 
          implements VirtualVertex<TravelVertex, TravelEdge, IntPair> {
	
	public static TravelVertex of(List<Ciudad> camino) {
		return new TravelVertex(List2.copy(camino));
	}

	public static Graph<Ciudad,Carretera> graph;

	@Override
	public Boolean isValid() {
		return true;
	}
	
	@Override
	public List<IntPair> actions() {
		Integer n = this.camino().size();
		return Stream2.allPairs(1,n-1,1,n-1)
				.filter(p->p.second() > p.first()+2)
				.collect(Collectors.toList());
	}

	@Override
	public TravelVertex neighbor(IntPair a) {
		return TravelVertex.of(AuxiliaryTsp.neighbor(this.camino,a));
	}

	@Override
	public TravelEdge edge(IntPair a) {
		TravelVertex v = this.neighbor(a);
		Preconditions.checkArgument(!this.equals(v),String.format("No puede ser igual al vecino %s, %s, %s",a,this,v));
		return TravelEdge.of(this,v,a);
	}

	@Override
	public String toString() {
		return String.format("(%.2f,%s",weight(),this.camino());
	}
	
	public Double weight() {
		return GraphPath2.ofVertices(TravelVertex.graph,this.camino()).getWeight();
	}

	@Override
	public Boolean goal() {
		return null;
	}

	@Override
	public Boolean goalHasSolution() {
		return null;
	}

	@Override
	public IntPair greedyAction() {
		return null;
	}
	
	

}
