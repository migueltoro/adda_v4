package us.lsi.p3.ej_3;


import java.util.List;
import us.lsi.ag.RangeIntegerData;

public class InRangeAlumnosAG implements RangeIntegerData<SolucionAlumnos> {

	public InRangeAlumnosAG(String fichero) {
		DatosAlumnos.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosAlumnos.getNumAlumnos();
	}

	@Override
	public Integer max(Integer i) {
		return DatosAlumnos.getNumGrupos();
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		
		double goal = 0, error = 0;
		int[] v = new int[DatosAlumnos.getNumGrupos()];
		for(int i=0; i<ls.size(); i++) {
			v[ls.get(i)]++;
			Integer a = DatosAlumnos.getAfinidad(i, ls.get(i));
			if(a>0)
				goal += a;
			else
				error ++;
		}
		for(int e: v) {
			error += e!=DatosAlumnos.getTamGrupo()? 1: 0;
		}
		return goal -10000*error;
	}

	@Override
	public SolucionAlumnos solucion(List<Integer> ls) {
		SolucionAlumnos res = SolucionAlumnos.empty();
		for(int i=0; i<ls.size(); i++) {
			res.add(i, ls.get(i));
		}
		return res;
	}


}
