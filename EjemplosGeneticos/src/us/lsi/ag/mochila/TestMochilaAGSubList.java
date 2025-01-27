package us.lsi.ag.mochila;

import java.util.List;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.mochila.datos.DatosMochila;
import us.lsi.mochila.datos.SolucionMochila;

public class TestMochilaAGSubList {
	
public static void main(String[] args) {
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 20000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 623;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.SolutionsNumber;
		
		DatosMochila.capacidadInicial = 78;
		DatosMochilaSubList p = new DatosMochilaSubList("ficheros/objetosmochila.txt");
		
		AlgoritmoAG<List<Integer>,SolucionMochila> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		System.out.println(DatosMochila.getObjetos());
		System.out.println("================================");
		List<Integer> dc = ap.getBestChromosome().decode();
		System.out.println(dc);
		System.out.println(ap.bestSolution());
		System.out.println("================================");
		System.out.println(ap.stoppingCondition());
	}


}
