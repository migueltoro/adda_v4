package us.lsi.alg.typ;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.SimpleEdgeAction;


public record TyPVertexI(Integer index, List<Double> cargas) 
      implements  TyPVertex{

	
	public static TyPVertexI first() {
		return new TyPVertexI(0,List2.nCopies(0.,DatosTyP.m));
	}
	
	public static TyPVertex last() {
		return new TyPVertexI(DatosTyP.n,List2.nCopies(0.,DatosTyP.m));
	}
	
	public static TyPVertexI of(Integer index, List<Double> cargas) {
		List<Double> cargasC = List.copyOf(cargas);
		return new TyPVertexI(index,cargasC);
	}
	
	@Override
	public Boolean goal() {
		return this.index()==DatosTyP.n;
	}
	
	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	@Override
	public Integer npMax() {
		return IntStream.range(0,DatosTyP.m)
		.boxed()
		.max(Comparator.comparing(i->this.cargas.get(i)))
		.get();
	}
	
	@Override
	public Integer npMin() {
		return IntStream.range(0,DatosTyP.m)
				.boxed()
				.min(Comparator.comparing(i->this.cargas.get(i)))
				.get();
	}

	@Override
	public Double maxCarga() {
		return this.cargas().get(this.npMax());
	}

	@Override
	public Boolean isValid() {
		return true;
	}

	@Override
	public List<Integer> actions() {
		return IntStream.range(0, DatosTyP.m).boxed().toList();
	}
	
	@Override
	public List<Double> cargasDespues(Integer a){
		Double d = DatosTyP.duracion(this.index) + this.cargas().get(a);
        return List2.setElement(this.cargas(), a, d);
	}
	
	@Override
	public Integer greedyAction() {
		return IntStream.range(0,DatosTyP.m)
				.boxed()
				.min(Comparator.comparing(a->cargasDespues(a).get(a)))
				.get();
	}
	
	public  SimpleEdgeAction<TyPVertex, Integer> greadyEdge() {
		return this.edge(this.greedyAction());
	}

	@Override
	public TyPVertexI neighbor(Integer a) {
		return TyPVertexI.of(index+1, cargasDespues(a));
	}
	
	@Override
	public SimpleEdgeAction<TyPVertex, Integer> edge(Integer a) {
		return SimpleEdgeAction.of(this,this.neighbor(a), a, 1.);
	}
	
	public static TyPVertex copy(TyPVertexI vertex) {
		return new TyPVertexI(vertex.index,vertex.cargas);
	}

}
