package us.lsi.ag.anuncios;



import java.util.List;
import java.util.Locale;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.anuncios.datos.DatosAnuncios;


public class TestAnunciosAG {
	

	public static void main(String[] args){
				
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		StoppingConditionFactory.NUM_GENERATIONS = 400;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
		
		DatosAnuncios.tiempoTotal = 30;
		System.out.println("ficheros/anuncios.txt");
		SeqNormalData<SolucionAnuncios> p = new DatosAnunciosAG("ficheros/anuncios.txt");		
		AlgoritmoAG<List<Integer>,SolucionAnuncios> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		Locale.setDefault(new Locale("en", "US"));
		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");		
	}	

}
