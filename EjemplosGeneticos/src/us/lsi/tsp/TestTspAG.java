package us.lsi.tsp;

import java.io.IOException;
import java.util.List;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.grafos.datos.Ciudad;

public class TestTspAG {

	public static void main(String[] args) throws IOException {
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 40000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		
		DatosTspAG p = new DatosTspAG("ficheros/andalucia.txt");
		
		AlgoritmoAG<List<Integer>, List<Ciudad>> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		System.out.println("================================");
		List<Integer> dc = ap.getBestChromosome().decode();
		System.out.println(dc);
		System.out.println(ap.getBestChromosome().fitness());
		System.out.println(ap.bestSolution());
		System.out.println("================================");
		System.out.println(ap.stoppingCondition());
	}

}
