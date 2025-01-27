package us.lsi.bufete;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.bufete.datos.Abogado;
import us.lsi.bufete.datos.DatosBufete;
import us.lsi.bufete.datos.SolucionBufete;

public class DatosBufeteAG implements ValuesInRangeData<Integer, SolucionBufete> {

	public static DatosBufeteAG create(String fichero) {
		return new DatosBufeteAG(fichero);
	}

	private DatosBufeteAG(String fichero) {
		DatosBufete.iniDatos(fichero);
		DatosBufete.toConsole();
	}	

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Integer size() {
		return DatosBufete.NUM_CASOS;
	}

	@Override
	public Integer max(Integer i) {
		return DatosBufete.NUM_ABOGADOS;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}	

	public Map<Abogado,Integer> horasAbogado;
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		this.horasAbogado = new HashMap<>();
		for(int i=0; i<this.size(); i++) {
			Abogado a = DatosBufete.getAbogado(ls.get(i));
			Integer t_a = horasAbogado.get(a);
			if(t_a==null) {
				this.horasAbogado.put(a, a.getHoras(i));
			} else {
				this.horasAbogado.put(a, t_a + a.getHoras(i));
			}
		}		
		return objetivo(horasAbogado) ;
	}

	private double objetivo(Map<Abogado, Integer> horasAbogado) {
		Integer obj = this.horasAbogado.values().stream().max(Comparator.naturalOrder()).get();
		return -obj;
	}

	@Override
	public SolucionBufete solucion(List<Integer> cr) {
		return SolucionBufete.create(cr);
	}
	
	
}

