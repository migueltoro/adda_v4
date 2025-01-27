package us.lsi.tareasyprocesadores.datos;

import java.util.List;

public interface SolucionTareasProcesadores {

	public static SolucionTareasProcesadores create(Integer np) {
		return new SolucionTareasProcesadoresNoIncremental(np);
	}
	
	Double getObjetivo();

	List<List<Tarea>> getTareasEnProcesador();

	List<Tarea> getTareasDeProcesador(int i);

	Double getCargaProcesador(int i);

	List<Double> getCargaProcesadores();

	void addTareaAProcesador(Integer p, Integer t);

	void removeTareaAProcesador(Integer p, Integer t);

	Double getTiempoDelMasCargado();

	SolucionTareasProcesadores copy();

	Double nuevoObjetivo(Integer p, Integer t);

}