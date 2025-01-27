package us.lsi.alg.candidatos;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import us.lsi.common.IntegerSet;
import us.lsi.common.List2;

public record VertexCandidatosI(Integer index,IntegerSet seleccion,Integer pRest) 
	implements  VertexCandidatos {

	public static VertexCandidatos of(Integer index, IntegerSet seleccion,Integer pRest) {
		return new VertexCandidatosI(index,seleccion,pRest);
	}
	
	public static VertexCandidatos initial() {
		return VertexCandidatosI.of(0,IntegerSet.empty(),DatosCandidatos.getPresupuestoMax());
	}

	@Override
	public Boolean goal() {
		return this.index() == DatosCandidatos.getNumCandidatos();
	}
	
	@Override
	public Boolean goalHasSolution() {
		return this.cualidadesCubiertas().size() == DatosCandidatos.getNumCualidades();
	}
	
	@Override
	public Boolean greedyAction() {
		Random r = new Random();
		List<Boolean> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
	}
	
	@Override
	public Set<Integer> cualidadesCubiertas(){
		return this.seleccion().stream().flatMap(i->DatosCandidatos.getCualidadesSet(i).stream())
				.collect(Collectors.toSet());
	}
	
	@Override
	public List<Boolean> actions() {
		if(index == DatosCandidatos.getNumCandidatos()) return List.of();
		List<Boolean> r = List2.of(false);
		Boolean c = DatosCandidatos.getSueldoMin(index) <= this.pRest() &&
			this.seleccion.stream().noneMatch(i->DatosCandidatos.getSonIncompatibles(this.index(),i));				
		if(c) r.add(true);
		return r;		
	}

	@Override
	public VertexCandidatos neighbor(Boolean a) {
		IntegerSet seleccion2 = IntegerSet.copy(seleccion);
		Integer pRest2 = pRest;
		if (a) {
		   seleccion2.add(index);
		   pRest2 = (int)(pRest2 - DatosCandidatos.getSueldoMin(index));
		} 
 		return VertexCandidatosI.of(index+1,seleccion2,pRest2);
	}

	@Override
	public EdgeCandidatos edge(Boolean a) {
		return EdgeCandidatos.of(this,this.neighbor(a),a);
	}

	@Override
	public String toString() {
		Locale.setDefault(Locale.of("en", "US"));
		return String.format("%d,%d,%.0f",index,this.cualidadesCubiertas().size(),
				CandidatosHeuristic.heuristic(this,v->v.goal(), null));
	}
	
	
}
