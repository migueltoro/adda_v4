package us.lsi.ag.agoperators;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.BinaryChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.RandomKey;

import us.lsi.ag.agchromosomes.PermutationSubListChromosome;

import us.lsi.common.Preconditions;

/**
 * <p>
 * Un operador de cruce adecuado para cromosomas de tipo mixto y que reutiliza
 * los operadores de cruce proporcionados en <a href=
 * "http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/package-summary.html"
 * target="_blank"> Apache Genetics </a>
 * </p>
 * 
 * @author Miguel Toro
 *
 */
public class SubListCrossoverPolicy implements CrossoverPolicy {

	protected CrossoverPolicy operatorBin;
	protected CrossoverPolicy operatorKey;

	public SubListCrossoverPolicy(CrossoverPolicy operatorBin, CrossoverPolicy operatorKey) {
		super();
		this.operatorBin = operatorBin;
		this.operatorKey = operatorKey;
	}

	@Override
	public ChromosomePair crossover(Chromosome chr0, Chromosome chr1)
			throws MathIllegalArgumentException {
		if (!(chr0 instanceof PermutationSubListChromosome))
			throw new IllegalArgumentException();
		PermutationSubListChromosome c0 = (PermutationSubListChromosome) chr0;
		if (!(chr1 instanceof PermutationSubListChromosome))
			throw new IllegalArgumentException();
		PermutationSubListChromosome c1 = (PermutationSubListChromosome) chr1;
		ChromosomePair binary = operatorBin.crossover(c0.getBinary(),c1.getBinary());
		Preconditions.checkArgument(binary.getFirst() instanceof BinaryChromosome);
		Preconditions.checkArgument(binary.getSecond() instanceof BinaryChromosome);
		ChromosomePair randomKey = operatorKey.crossover(c0.getRandomKey(),c1.getRandomKey());
		Preconditions.checkArgument(randomKey.getFirst() instanceof RandomKey);
		Preconditions.checkArgument(randomKey.getSecond() instanceof RandomKey);
		ChromosomePair r = new ChromosomePair(new PermutationSubListChromosome(binary.getFirst(),
				randomKey.getFirst()), new PermutationSubListChromosome(binary.getSecond(),
				randomKey.getSecond()));
		return r;
	}
}
