package us.lsi.ag.reinas;

import java.util.List;
import java.util.Set;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.reinas.datos.Reina;


public class DatosReinasAG implements SeqNormalData<List<Reina>> {

	public static int numeroDeReinas = 8;
	
	public static DatosReinasAG create() {
		return new DatosReinasAG();
	}

	private DatosReinasAG() {
	}

	@Override
	public List<Reina> solucion(List<Integer> ls) {
		List<Reina> r = List2.empty();
		for (int i = 0; i < ls.size(); i++) {
			r.add(Reina.create(i, ls.get(i)));
		}
		return r;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		Set<Integer> dp = Set2.empty();
		Set<Integer> ds = Set2.empty();
		for (int i = 0; i < ls.size(); i++) {
			dp.add(ls.get(i)-i);
			ds.add(ls.get(i)+i);
		}
		return -100*AuxiliaryAg.distanceToEqZero(2.*DatosReinasAG.numeroDeReinas-dp.size()-ds.size());
	}

	@Override
	public Integer itemsNumber() {
		return  numeroDeReinas;
	}
	
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}
	
//	@Override
//	public List<Integer> normalSequence() {
//		return IntStream.range(0,numeroDeReinas).boxed().toList();
//	}
//	
//	@Override
//	public Integer size() {
//		return numeroDeReinas;
//	}
}
