package us.lsi.ag.agoperators;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.BinaryChromosome;
import org.apache.commons.math3.genetics.BinaryMutation;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.RandomKey;
import org.apache.commons.math3.genetics.RandomKeyMutation;

import us.lsi.ag.agchromosomes.PermutationSubListChromosome;

import us.lsi.common.Preconditions;

/**
 * <p> Un operador de mutación adecaudo para cromosomas de tipo mixto y que reutiliza los operadores mutación proporcionados
 * en <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/package-summary.html" 
 * target="_blank"> Apache Genetics </a>
 * </p>
 *   
 * @author Miguel Toro
 *
 */

public class SubListMutationPolicy implements MutationPolicy {

	protected static MutationPolicy binaryOperator = new BinaryMutation();
	protected static MutationPolicy randomKeyOperator  = new RandomKeyMutation();
	
	public SubListMutationPolicy() {
		super();
	}
	
	@Override
	public Chromosome mutate(Chromosome chr0) throws MathIllegalArgumentException {
		if (!(chr0 instanceof PermutationSubListChromosome))
			throw new IllegalArgumentException();;
		PermutationSubListChromosome c0 = (PermutationSubListChromosome) chr0;		
		BinaryChromosome binary = c0.getBinary();
		RandomKey<Integer> randomKey = c0.getRandomKey();
		Preconditions.checkArgument(binary != null);		
		Preconditions.checkArgument(randomKey != null);	
		Chromosome c1 = binaryOperator.mutate(binary);
		Preconditions.checkArgument(c1 instanceof BinaryChromosome);
		Chromosome c2 = randomKeyOperator.mutate(randomKey);		
		Preconditions.checkArgument(c2 instanceof RandomKey);
		return new PermutationSubListChromosome(c1,c2);
	}	
	
}
