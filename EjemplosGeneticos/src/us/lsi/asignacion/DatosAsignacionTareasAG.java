package us.lsi.asignacion;

import java.util.List;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class DatosAsignacionTareasAG implements SeqNormalData<List<Integer>> {

	public static AsignaciondeTareas a = null;

	public static DatosAsignacionTareasAG create(String file) {
		a = AsignaciondeTareas.create(file);
		return new DatosAsignacionTareasAG();
	}

	private DatosAsignacionTareasAG() {
	}

	@Override
	public List<Integer> solucion(List<Integer> dc) {
		return dc;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		Double coste = 0.;
		for (int i = 0; i < ls.size(); i++) {
			coste = coste + a.getCoste(i, ls.get(i));
		}
		return -coste;
	}


	@Override
	public Integer itemsNumber() {
		return a.getN();
	}
	
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}


}
