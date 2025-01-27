package us.lsi.camino;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;
import us.lsi.grafos.datos.Ciudad;

public class CaminoData implements SeqNormalData<List<Ciudad>>{
	
	@Override
	public ChromosomeType type() {
		return ChromosomeType.PermutationSubList;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		if(value.isEmpty()) return -100000.;
		value = List2.addFirst(value,CaminoDatos.or);
		value = List2.addLast(value,CaminoDatos.de);
		calcula(value);
		if(CaminoDatos.sh) System.out.println(String.format("goal = %.2f, pGoal = %.2f, eVertice = %.2f, eArista = %.2f",
				this.goal,this.pGoal,this.eVertice,this.eArista));
		return -this.goal-pGoal-1000*eVertice-1000*eArista;
	}
	
	@Override
	public Integer itemsNumber() {
		return null;
	}
	
	public List<Integer> normalSequence() {
		List<Integer> r = new ArrayList<>(IntStream.range(0,CaminoDatos.n).boxed().toList());
		r.remove(CaminoDatos.or);
		r.remove(CaminoDatos.de);
		return r;
	}
	
	public Double goal;
	public Double pGoal;
	public Double eVertice;
	public Double eArista;
	
	private void calcula(List<Integer> value) {
		goal = 0.;
		pGoal = 0.;
		eVertice = 0.;
		eArista = 0.;
		for (Integer i = 0; i < value.size() - 1; i++) {
			if (CaminoDatos.graph.containsEdge(value.get(i), value.get(i + 1)))
				goal += CaminoDatos.graph.getEdgeWeight(value.get(i), value.get(i + 1));
			else
				pGoal += 1000;
			if (CaminoDatos.graph.containsEdge(value.get(i), value.get(i+1)) 
					&& CaminoDatos.pe.test(CaminoDatos.graph.getEdgeWeight(value.get(i), value.get(i+1)))); {
				eArista = eArista +1;
			}
			if (i>0 && CaminoDatos.pv.test(CaminoDatos.graph.vertex(value.get(i)))) {
				eVertice = eVertice +1 ;
			}
		}
		eArista = eArista > 1? 0.:eArista*eArista;
		eVertice = eVertice > 1? 0.:eVertice*eVertice;
	}
	
	

	@Override
	public List<Ciudad> solucion(List<Integer> value) {
		
		value = List2.addFirst(value,CaminoDatos.or);
		value = List2.addLast(value,CaminoDatos.de);
			
		Double kms = 0.;
		List<Ciudad> res = new ArrayList<>();
		
		for (Integer i = 0; i < value.size(); i++) {
			res.add(CaminoDatos.graph.getVertex(value.get(i)));
		}
		
		for (Integer i = 0; i < value.size()-1; i++) {
			if (CaminoDatos.graph.containsEdge(value.get(i), value.get(i+1))) {
				kms += CaminoDatos.graph.getEdgeWeight(value.get(i), value.get(i+1));
			}
		}
		
		System.out.println("Kms: " + kms);
		return res;
	}

	

}
