package us.lsi.ag.agstopping;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.FixedElapsedTime;
import org.apache.commons.math3.genetics.FixedGenerationCount;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.StoppingCondition;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.common.List2;

/**
 * 
 * <p> Acumula una lista, en la propiedad 
 * <code> AlgoritmoAG.bestChromosomes </code> que guarda los mejores <code> numBestChromosomes </code> 
 * distintos de cada generaci�n.</p>
 * 
 * 
 * <p> Implementa una condici�n de parada que se cumple cuando se encuentran <code> numBestChromosomes </code> cromosomas
 * que cumplan un  <code> predicate </code> o el n�mero de generaciones supera
 * <code> AlgoritmoAG.NUM_GENERATIONS</code>  </p>
 * 
 * <p> La propiedad <code> predicate </code> debe ser inicializada desde el problema concreto a resolver. La propiedad numeroDeGeneraciones
 * guarda el n�mero de generaciones </p>
 * 
 * @author Miguel Toro
 *
 */
public class SolutionsNumber implements StoppingCondition {

	
	private static Predicate<Chromosome> predicate =null;
	private Integer minSol;
	private Integer maxGen;
	private Long maxTime;
	private Double minFit;
	private FixedElapsedTime fixedElapsedTime;
	private FixedGenerationCount fixedGenerationNumber;
	private Integer nbc = 0;
	private Double minFitness = 0.;
	public SolutionsNumber(Long maxTime,Integer maxGen,Integer minSol, Double minFit) {
		super();
		this.minSol = minSol;
		this.maxGen = maxGen;
		this.maxTime = maxTime;
		this.minFit = minFit;
		AlgoritmoAG.bestChromosomes = List2.empty();
		this.fixedGenerationNumber = 
				new FixedGenerationCount(this.maxGen);
		this.fixedElapsedTime = 
				new FixedElapsedTime(this.maxTime);
		SolutionsNumber.predicate = 
				(Chromosome x) -> x.fitness() >= this.minFit; 
	}

	@Override
	public boolean isSatisfied(Population population) {
		List<Chromosome> r = List2.empty();
		for(Chromosome cr:population) {
			r.add(cr);
		}
		List<Chromosome> ls = r.stream()
		  .sorted(Comparator.<Chromosome> reverseOrder())
		  .distinct()
		  .limit(this.minSol)
		  .collect(Collectors.toList());
		AlgoritmoAG.bestChromosomes.addAll(ls);
		Boolean r1 = false, r2 = false, r3= false;
		r1 = this.fixedElapsedTime.isSatisfied(population);
		r2 = r1 || this.fixedGenerationNumber.isSatisfied(population);
		r3 = r2 || ls.stream().allMatch((Chromosome x) -> predicate.test(x));
		if(r3) {
			this.nbc = ls.size();
			this.minFitness = ls.get(ls.size()-1).fitness();
		}
		return r3;
	}

	@Override
	public String toString() {
		return String.format("Se ha consumido el tiempo de %d nanosegundos, o %f segundos, se ha iterado sobre %d generaciones y se han "
				+ "encontrado %d soluciones con un fitnes igual o superior a %.2f",
				AlgoritmoAG.time(),
				AlgoritmoAG.time()/1000000000., 
				this.fixedGenerationNumber.getNumGenerations(),
				this.nbc,this.minFitness);
	}
	
	

}
