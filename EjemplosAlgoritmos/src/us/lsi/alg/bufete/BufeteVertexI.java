package us.lsi.alg.bufete;
import us.lsi.common.List2;


import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;


public record BufeteVertexI(Integer index,List<Integer> cargas) implements BufeteVertex {
	
	public static BufeteVertex of(Integer i, List<Integer> cargas) {
		return new BufeteVertexI(i,cargas);
	}

	public static BufeteVertex initialVertex() {
		return new BufeteVertexI(0,List2.nCopies(0,DatosBufete.NUM_ABOGADOS));
	}
	
	@Override
	public Boolean goal() {
		return this.index() == DatosBufete.NUM_CASOS;
	}
	
	@Override
	public Boolean goalHasSolution() {
		return true;
	}

	public static BufeteVertex copy(BufeteVertexI v) {
		return BufeteVertexI.of(v.index(), v.cargas());
	}
	
	@Override
	public Boolean isValid() {
		return true;
	}
	
	@Override
	public Integer npMax() {
		return IntStream.range(0,DatosBufete.NUM_ABOGADOS)
		.boxed()
		.max(Comparator.comparing(i->this.cargas.get(i)))
		.get();
	}
	
	@Override
	public Integer npMin() {
		return IntStream.range(0,DatosBufete.NUM_ABOGADOS)
				.boxed()
				.min(Comparator.comparing(i->this.cargas.get(i)))
				.get();
	}
	
	@Override
	public Integer maxCarga() {
		return this.cargas().get(this.npMax());
	}
	
	@Override
	public List<Integer> cargasDespues(Integer a){
		Integer d = DatosBufete.horas(a,this.index) + this.cargas().get(a);
        return List2.setElement(this.cargas(), a, d);
	}
	
	@Override
	public Integer greedyAction() {
		return IntStream.range(0,DatosBufete.NUM_ABOGADOS)
				.boxed()
				.min(Comparator.comparing(a->cargasDespues(a).get(a)))
				.get();
	}
	
	public  BufeteEdge greadyEdge() {
		return this.edge(this.greedyAction());
	}

	@Override
	public List<Integer> actions() {
		if(this.index() == DatosBufete.NUM_CASOS-1) return List.of(this.greedyAction());
		return List2.rangeList(0,DatosBufete.NUM_ABOGADOS);
	}

	@Override
	public BufeteVertex neighbor(Integer a) {
		return BufeteVertexI.of(index+1, cargasDespues(a));
	}

	@Override
	public BufeteEdge edge(Integer a) {
		BufeteVertex siguiente = this.neighbor(a);
		return BufeteEdge.of(this, siguiente, a);
	}

}

