package us.lsi.alg.investigadores;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.alg.investigadores.DatosInv.VertexType;
import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.String2;

/**
 * index: indice que recorre los pares (i,j) desde 0 hasta n*m
 * i = this.index()%DatosInvestigadores.n;
 * j = this.index()/DatosInvestigadores.n;
 * k = DatosInvestigadores.getEspecialidadDeInvestigador(this.i());
 * p = IntPair.of(this.j(),k);
 * ldIr: diasInvestigadorRestantes: dias restantes de cada trabajador, ldIr.get(i) los restantes del trabajador i
 * tA: El trabajo estaAcabado[j] is true si el trabajo j está acabado
 * ldEr: diasEspecialidadRestantes[j,k] dias que faltan dedicar al trabajo j de la especialidad k
 *
 */
public record InvVertexI(Integer index, List<Integer> ldIr, List<List<Integer>> ldEr) 
    implements InvVertex {

	public static InvVertex first() {
		return InvVertexI.of(0, DatosInv.ldid, DatosInv.ldEn);
	}
	
	public static InvVertex of(Integer index, List<Integer> ldIr, List<List<Integer>> ldEr) {
	   return new InvVertexI(index, ldIr, ldEr);
	}

	@Override
	public Boolean goal() {
		return this.index() == DatosInv.na;
	}	
	
	@Override
	public Boolean goalHasSolution() {
		return this.index() == DatosInv.na && IntStream.range(0, DatosInv.m).boxed()
				.allMatch(t -> this.esTrabajoAcabado(t) || !this.esTrabajoIniciado(t));
	}
	
	public Boolean isValid() {
		return IntStream.range(0, DatosInv.n).boxed()
				.allMatch(j->this.tipo(j).equals(DatosInv.VertexType.Acabado) || 
						this.tipo(j).equals(DatosInv.VertexType.SinIniciar));
	}
	
	@Override
	public Integer i() {
		return this.index()%DatosInv.n;
	}
	
	public Integer j() {
		return this.index()/DatosInv.n;
	}
	
	public Integer k() {
		return DatosInv.investigadores.get(this.i()).especialidad();
	}
	
	@Override
	public IntPair pjk() {
		return IntPair.of(this.j(),this.k());
	}
	
	@Override
	public IntPair pij() {
		return IntPair.of(this.i(),this.j());
	}
	
	@Override
	public Integer esp(Integer i) {
		return DatosInv.investigadores.get(i).especialidad();
	}
	
	/**
	 * @return Lista de dias disponibles restantes de cada una de las especialidades
	 */
	@Override
	public List<Integer> dEr() {
		List<Integer> r = List2.nCopies(0, DatosInv.r);
		for(int i = 0; i<DatosInv.n;i++) {
			Integer s = r.get(this.esp(i))+this.ldIr().get(i);
			r.set(this.esp(i),s);
		}
		return r;
	}
	
	/**
	 * @param j El trabajo j
	 * @return Si al trabajo j se le han enpezado a asignar investigadores
	 */
	@Override
	public Boolean esTrabajoIniciado(Integer j) {
		return DatosInv.esMenor(this.ldEr().get(j),DatosInv.ldEn.get(j));
	}
	/**
	 * @param j El trabajo j
	 * @return Si el trabajo j esta completo de investigadores
	 */
	@Override
	public Boolean esTrabajoAcabado(Integer j) {
		return DatosInv.esCero(this.ldEr().get(j));
	}
	/**
	 * @param j El trabajo j
	 * @return Si el trabajo j se puede terminar dados las horas de investigadores restantes por especialidades
	 */
	@Override
	public Boolean esTrabajoTerminable(Integer j) {
		return DatosInv.esMenorOIgual(this.ldEr().get(j), this.dEr());
	}
	
	@Override
	public DatosInv.VertexType tipo(Integer j) {
		if(this.esTrabajoAcabado(j)) return DatosInv.VertexType.Acabado;
		else if(this.esTrabajoIniciado(j))  return DatosInv.VertexType.Iniciado;
		else return DatosInv.VertexType.SinIniciar;
	}
	
	public List<VertexType> tipos() {
		return IntStream.range(0,DatosInv.m).boxed().map(i->this.tipo(i)).toList();
	}
	
	@Override
	public Integer mayorAccionPosible() {
//		System.out.println(String.format("index = %d, i = %d, j = %d",index,this.i(),this.j()));
		if(this.index() == DatosInv.na) return 0;
		return Math.min(this.ldIr().get(this.i()),this.ldEr().get(this.j()).get(this.k()));
	}
	
	@Override
	public Integer fo() {
		return IntStream.range(0,DatosInv.m).boxed()
				.filter(t->this.esTrabajoAcabado(t))
				.mapToInt(t->DatosInv.trabajos.get(t).calidad())
				.sum();
	}

	@Override
	public List<Integer> actions() {
		if(this.goal()) return List.of();
		List<Integer> alternativas = IntStream.rangeClosed(0, this.mayorAccionPosible()).boxed()
				.filter(a->this.ldIr().get(this.i()) - a >= 0)
				.filter(a->this.ldEr().get(this.j()).get(this.k())-a >= 0)
				.collect(Collectors.toList());
		return List2.reverse(alternativas);
	}


	@Override
	public InvVertex neighbor(Integer a) {
		List<Integer> diasInvestigadorRestantes = List2.setElement(this.ldIr(), this.i(), this.ldIr().get(this.i()) - a);
		Integer der = this.ldEr().get(this.j()).get(this.k())-a;
		
		List<List<Integer>> diasEspecialidadRestantes = List2.copy2(this.ldEr);
		diasEspecialidadRestantes.get(this.j()).set(this.k(),der);

		return InvVertex.of(index + 1, diasInvestigadorRestantes, diasEspecialidadRestantes);
	}

	@Override
	public InvEdge edge(Integer a) {
		return InvEdge.of(this, neighbor(a), a);
	}
	
	
	public String toString() {
		String st = String.format("fo = %d, index = %d, i = %d, j = %d, k = %d",this.fo(),this.index(),this.i(),this.j(),this.k());
		String sinv = "Dias Restantes De Investigador = "+this.ldIr();
		String sesp = "Dias Restantes De Especialidad = "+this.dEr();
		String ste = "Dias Restantes De Especialidad en Trabajo = "+IntStream.range(0, DatosInv.m).boxed()
				.map(i->this.ldEr().get(i)).toList();
		
		String tp = "Tipos " + this.tipos();
		String tm = "Terminables " + IntStream.range(0, DatosInv.m).boxed()
				.map(i->this.esTrabajoTerminable(i)).toList();
		String line = String2.line("=",50);
		return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",line,st,sinv,sesp,ste,tp,tm,line);
	}

	@Override
	public Integer greedyAction() {
		return this.esTrabajoTerminable(this.j())?this.mayorAccionPosible():0;
	}

	
}
