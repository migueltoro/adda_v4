package us.lsi.bufete;

import java.util.List;
import java.util.Locale;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.bufete.datos.SolucionBufete;

public class TestBufete {
	
	private static void test(String fichero) {		
		DatosBufeteAG prob = DatosBufeteAG.create("ficheros/"+fichero);
		AlgoritmoAG<List<Integer>, SolucionBufete> alg = AlgoritmoAG.of(prob);
		alg.ejecuta();
		System.out.println(alg.bestSolution());		
	}	

	public static void main(String[] args) {
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 200;
		
		StoppingConditionFactory.NUM_GENERATIONS = 15000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = -4;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
		Locale.setDefault(Locale.of("en", "US"));
		TestBufete.test("bufete.txt");
//		test("PI6Ej2DatosEntrada2");
//		test("PI6Ej2DatosEntrada3");	
//		System.out.println(AlgoritmoAG.)
	}

}
