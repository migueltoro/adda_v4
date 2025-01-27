package us.lsi.p3.ej_3;

import java.util.List;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class PermutationAlumnosAG2 implements SeqNormalData<SolucionAlumnos>{

	public PermutationAlumnosAG2(String fichero) {
		DatosAlumnos.iniDatos(fichero);
	}


	@Override
	public Double fitnessFunction(List<Integer> ls) {
		Double afinidad_total = 0.;
		Integer errores = 0;
		for (Integer i=0;i<ls.size();i++) {
			Integer afinidad = DatosAlumnos.getAfinidad(i, ls.get(i));
			if (afinidad > 0)
				afinidad_total += afinidad;
			else
				errores++;
		}

		return afinidad_total - 10000 * AuxiliaryAg.distanceToEqZero(1.*errores);
	}

	@Override
	public SolucionAlumnos solucion(List<Integer> ls) {
		SolucionAlumnos res = SolucionAlumnos.empty();
		for(int i=0; i<ls.size(); i++) {
			res.add(i, ls.get(i));
		}
		return res;
	}

	@Override
	public Integer itemsNumber() {
		return DatosAlumnos.getNumGrupos();
	}
	
	@Override
	public Integer maxMultiplicity(int index) { 
		return DatosAlumnos.getTamGrupo(); 
	}


	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

}

