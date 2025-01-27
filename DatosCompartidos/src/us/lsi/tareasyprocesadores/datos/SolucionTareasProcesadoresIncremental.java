package us.lsi.tareasyprocesadores.datos;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolucionTareasProcesadoresIncremental extends SolucionTareasProcesadoresNoIncremental
		implements SolucionTareasProcesadores {

	List<Double> cargaProcesadores;
	
	SolucionTareasProcesadoresIncremental(Integer np) {
		super(np);
		this.cargaProcesadores = IntStream.range(0,np)
				.boxed()
				.map(x->0.)
				.collect(Collectors.toList());
	}

	SolucionTareasProcesadoresIncremental(List<List<Tarea>> tareasEnProcesador,
			List<Double> cargaProcesadores) {
		super(tareasEnProcesador);
		this.cargaProcesadores = cargaProcesadores;
	}

	@Override
	public Double getCargaProcesador(int i){
		return cargaProcesadores.get(i);
	}
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#getCargaProcesadores()
	 */
	@Override
	public List<Double> getCargaProcesadores() {
		return this.cargaProcesadores;		
	}
	
	@Override
	public void addTareaAProcesador(Integer p, Integer t) {
		super.addTareaAProcesador(p, t);
		this.cargaProcesadores.set(p,this.cargaProcesadores.get(p)+Tarea.tareas.get(t).getDuracion());
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.bt.tareasyprocesadores.SolucionTareasProcesadores#removeTareaAProcesador(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void removeTareaAProcesador(Integer p, Integer t) {
		super.removeTareaAProcesador(p, t);
		this.cargaProcesadores.set(p,this.cargaProcesadores.get(p)-Tarea.tareas.get(t).getDuracion());
	}
}
