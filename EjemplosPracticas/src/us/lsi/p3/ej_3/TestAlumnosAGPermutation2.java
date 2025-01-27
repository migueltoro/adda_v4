package us.lsi.p3.ej_3;

import java.util.List;
import java.util.Locale;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestAlumnosAGPermutation2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		PermutationAlumnosAG2 p = new PermutationAlumnosAG2("ficheros/p3/ejemplo3_1.txt");
		
		AlgoritmoAG<List<Integer>,SolucionAlumnos> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
	}
}

