package us.lsi.sa;

//import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.MutationPolicy;

import us.lsi.ag.Chromosome;
import us.lsi.ag.ChromosomeData;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class StateSaChromosome implements StateSa  {

	public static  StateSaChromosome of(Chromosome<?> chromosome) {	
		return new StateSaChromosome(chromosome);
	}
	
	public static <E,S> StateSaChromosome random(ChromosomeData<E,S> data, ChromosomeType tipo) {	
		ChromosomeFactory.iniValues(data,tipo);
		MutationPolicy mutationPolicy = ChromosomeFactory.getMutationPolicy(tipo);
		AlgoritmoAG.mutationPolicy = mutationPolicy;
		AlgoritmoAG.tipo = data.type();
		Chromosome<?> chr = ChromosomeFactory.randomChromosome(tipo);
		return new StateSaChromosome(chr);
	}
	
	public final Chromosome<?> chromosome;
	
	private StateSaChromosome(Chromosome<?> chromosome) {
		super();
		this.chromosome = chromosome;	
	}
	
	@Override
	public double fitness() {
		return -this.chromosome.fitness();
	}
	@Override
	public StateSa mutate() {
		return StateSaChromosome.of( 
				(Chromosome<?>) AlgoritmoAG.mutationPolicy.mutate((org.apache.commons.math3.genetics.Chromosome) this.chromosome));
	}

	@Override
	public StateSa random() {
		return StateSaChromosome.of(ChromosomeFactory.randomChromosome(AlgoritmoAG.tipo));
	}

	@Override
	public StateSa copy() {
		return StateSaChromosome.of(this.chromosome);
	}
	
	public Object decode() {
		return this.chromosome.decode();
	}
	
}
