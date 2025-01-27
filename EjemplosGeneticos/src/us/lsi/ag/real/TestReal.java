package us.lsi.ag.real;

import us.lsi.ag.Chromosome;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

import java.util.List;
import java.util.Locale;

public class TestReal {

	public static void main(String[] args){
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 200;
		
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
		StoppingConditionFactory.NUM_GENERATIONS = 100;
		
		ValuesInRangeData<Double,List<Double>> p = new DatosReal();
		AlgoritmoAG<List<Double>,List<Double>> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		
		Locale.setDefault(new Locale("en", "US"));
		Chromosome<List<Double>> cr = ap.getBestChromosome();
		System.out.println("================================");
		System.out.println(ap.bestSolution()+","+(cr.fitness()));
		System.out.println("================================");
		
	}	
	
}
