package us.lsi.p3.ej_2;


import java.util.List;
import java.util.Locale;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;


public class TestSubconjuntosAGBinary {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		BinSubconjuntoAG p = new BinSubconjuntoAG("ficheros/p3/ejemplo2_2.txt");
		
		AlgoritmoAG<List<Integer>,SolucionSubconjunto> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
	}
}
