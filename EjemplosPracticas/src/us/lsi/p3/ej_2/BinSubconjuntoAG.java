package us.lsi.p3.ej_2;

import java.util.List;
import java.util.Set;
import us.lsi.ag.BinaryData;
import us.lsi.common.Set2;


public class BinSubconjuntoAG implements BinaryData<SolucionSubconjunto> {
	
	public BinSubconjuntoAG(String fichero) {
		DatosSubconjunto.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosSubconjunto.getNumSubconjuntos();
	}
	
	@Override
	public SolucionSubconjunto solucion(List<Integer> ls) {
		return SolucionSubconjunto.create(ls);
	}
			
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		
		double goal = 0.0;
		Set<Integer> se = Set2.empty();
		for(int i=0; i<size(); i++) {
			if(ls.get(i)>0) {
				goal += DatosSubconjunto.getPeso(i);
				se.addAll(DatosSubconjunto.getElementos(i));
			}
		}
		double error = DatosSubconjunto.getNumElementos()-se.size();
		return -goal -10000*error*error; 
	}

	
	
}
