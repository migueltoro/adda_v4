package us.lsi.p4.ej_2.manual;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.p4.ej_2.DatosSubconjuntos;
import us.lsi.p4.ej_2.SolucionSubconjuntos;

public class SubconjuntosState {

	SubconjuntosProblem actual;
	Double acumulado;
	List<Integer> acciones;
	List<SubconjuntosProblem> anteriores;
	
	private SubconjuntosState(SubconjuntosProblem p, Double a, 
		List<Integer> ls1, List<SubconjuntosProblem> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}

	public static SubconjuntosState initial() {
		SubconjuntosProblem pi = SubconjuntosProblem.initial();
		return of(pi, 0., List2.empty(), List2.empty());
	}

	public static SubconjuntosState of(SubconjuntosProblem prob, Double acum, List<Integer> lsa,
			List<SubconjuntosProblem> lsp) {
		return new SubconjuntosState(prob, acum, lsa, lsp);
	}

	public void forward(Integer a) {		
		acumulado += a * DatosSubconjuntos.peso(actual.index());
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}

	public void back() {
		int last = acciones.size() - 1;
		var prob_ant = anteriores.get(last);
		
		acumulado -= acciones.get(last) * DatosSubconjuntos.peso(prob_ant.index());
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	}

	public List<Integer> alternativas() {
		return actual.actions();
	}

	public Double cota(Integer a) {
		Double weight = a > 0 ? DatosSubconjuntos.peso(actual.index()) : 0.;
		return acumulado + weight + actual.neighbor(a).heuristic();
	}

	public Boolean esSolucion() {
		return actual.remaining().isEmpty();
	}

	public boolean esTerminal() {
		return actual.index() == DatosSubconjuntos.NUM_SC;
	}
	

	public SolucionSubconjuntos getSolucion() {
		return SolucionSubconjuntos.of(acciones);
	}

}
