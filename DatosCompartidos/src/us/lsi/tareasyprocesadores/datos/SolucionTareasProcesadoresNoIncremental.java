package us.lsi.tareasyprocesadores.datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.List2;
import us.lsi.streams.Stream2;

public class SolucionTareasProcesadoresNoIncremental implements SolucionTareasProcesadores  {


	static Integer numeroDeProcesadores;
	private List<List<Tarea>> tareasEnProcesador;

	SolucionTareasProcesadoresNoIncremental(List<List<Tarea>> tareasEnProcesador) {
		this.tareasEnProcesador = tareasEnProcesador;
	}
	
	SolucionTareasProcesadoresNoIncremental(Integer np) {
		super();
		numeroDeProcesadores = np;
		this.tareasEnProcesador = IntStream.range(0,np)
				.boxed()
				.map(x->new ArrayList<Tarea>())
				.collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#getObjetivo()
	 */
	@Override
	public Double getObjetivo() {
		return getTiempoDelMasCargado();
	}

	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#getTareasEnProcesador()
	 */
	@Override
	public List<List<Tarea>> getTareasEnProcesador() {
		return tareasEnProcesador;
	}

	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#getTareasDeProcesador(int)
	 */
	@Override
	public List<Tarea> getTareasDeProcesador(int i) {
		return tareasEnProcesador.get(i);
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#getCargaProcesador(int)
	 */
	@Override
	public Double getCargaProcesador(int i){
		return this.tareasEnProcesador.get(i)
				.stream()
				.mapToDouble(t->t.getDuracion())
				.sum();
	}
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#getCargaProcesadores()
	 */
	@Override
	public List<Double> getCargaProcesadores() {
		return IntStream.range(0, numeroDeProcesadores)
				 .mapToDouble(x->getCargaProcesador(x))
				 .boxed()
				 .collect(Collectors.toList());		
	}
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#addTareaAProcesador(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void addTareaAProcesador(Integer p, Integer t) {
		this.tareasEnProcesador.get(p).add(Tarea.tareas.get(t));
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#removeTareaAProcesador(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void removeTareaAProcesador(Integer p, Integer t) {
		this.tareasEnProcesador.get(p).remove(Tarea.tareas.get(t));
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#getTiempoDelMasCargado()
	 */
	@Override
	public Double getTiempoDelMasCargado() {
		return IntStream.range(0,numeroDeProcesadores)
				.mapToDouble(x->getCargaProcesador(x))
				.max()
				.getAsDouble();
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#copy()
	 */
	@Override
	public SolucionTareasProcesadores copy() {
		return new SolucionTareasProcesadoresNoIncremental(this.tareasEnProcesador.stream()
				   .map(x->List2.ofCollection(x))
				   .collect(Collectors.toList()));
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#nuevoObjetivo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Double nuevoObjetivo(Integer p, Integer t) {
		List<Double> ls = new ArrayList<>(this.getCargaProcesadores());
		ls.set(p,ls.get(p)+Tarea.tareas.get(t).getDuracion());
		return ls.stream().max(Comparator.naturalOrder()).get();
	}
	
	@Override
	public String toString() {
		var s = Stream2.enumerate(this.getTareasEnProcesador().stream());
		return s.map(
				x -> "    (" + x.counter() + "=" + x.value().toString() + "," + this.getCargaProcesador(x.counter()) + ")")
				.collect(Collectors.joining("\n", "Solucion, Objetivo = " + getObjetivo() + " {\n", "\n}\n"));
	}

}
