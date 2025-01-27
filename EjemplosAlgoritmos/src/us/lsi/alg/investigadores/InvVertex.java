package us.lsi.alg.investigadores;

import java.util.List;
import us.lsi.common.IntPair;
import us.lsi.graphs.virtual.VirtualVertex;

public interface InvVertex extends VirtualVertex<InvVertex, InvEdge, Integer>{

	/**
	 * index: indice que recorre los pares (i,j) desde 0 hasta n*m
	 * i = this.index()%DatosInvestigadores.n;
	 * j = this.index()/DatosInvestigadores.n;
	 * k = DatosInvestigadores.getEspecialidadDeInvestigador(this.i());
	 * ldIr: diasInvestigadorRestantes: dias restantes de cada trabajador, ldIr.get(i) los restantes del trabajador i
	 * tA: El trabajo estaAcabado[j] is true si el trabajo j está acabado
	 * ldEr: diasEspecialidadRestantes[j,k] dias que faltan dedicar al trabajo j de la especialidad k
	 *
	 */
	
	public static InvVertex first() {
		return InvVertexI.of(0, DatosInv.ldid, DatosInv.ldEn);
	}
	
	public static InvVertex of(Integer index, List<Integer> ldIr, List<List<Integer>> ldEr) {
	   return new InvVertexI(index, ldIr, ldEr);
	}
	/**
	index: indice que recorre los pares (i,j) desde 0 hasta n*m
	*/
	Integer index();
	
	/**
	i() = this.index()%DatosInvestigadores.n;
	*/
	Integer i();
	
	/**
	j() = this.index()/DatosInvestigadores.n;
	*/
	Integer j();
	
	/**
	k() = DatosInvestigadores.getEspecialidadDeInvestigador(this.i());
	*/
	Integer k();
	
	/**
	pjk() = IntPair.of(this.j(),k());
	*/
	IntPair pjk();

	/**
	pij() = IntPair.of(this.i(),j());
	*/
	IntPair pij();
	/**
	diasInvestigadorRestantes: dias restantes de cada trabajador, 
	ldIr.get(i) los restantes del trabajador i
	*/
	List<Integer> ldIr();
	/**
	diasEspecialidadRestantes[j,k] dias que faltan dedicar al trabajo j de la especialidad k
	*/
	List<List<Integer>> ldEr();
	/**
	esp(i) dias de la especialidad i
	*/
	Integer esp(Integer i);

	/**
	 * @return Lista de dias disponibles restantes de cada una de las especialidades
	 */
	List<Integer> dEr();

	/**
	 * @param j El trabajo j
	 * @return Si al trabajo j se le han empezado a asignar investigadores
	 */
	Boolean esTrabajoIniciado(Integer j);

	/**
	 * @param j El trabajo j
	 * @return Si el trabajo j esta acabado de investigadores
	 */
	Boolean esTrabajoAcabado(Integer j);

	/**
	 * @param j El trabajo j
	 * @return Si el trabajo j se puede terminar dadas las horas de investigadores restantes por especialidades
	 */
	Boolean esTrabajoTerminable(Integer j);
	/**
	 * @param j El trabajo j
	 * @return Si el trabajo está acabado, iniciado o sin iniciar
	 */
	DatosInv.VertexType tipo(Integer j);
	/**
	 * @param j El trabajo j
	 * @return La mayor acción posible
	 */
	Integer mayorAccionPosible();
	/**
	 * @return Valor de la función objetivo
	 */
	Integer fo();

}