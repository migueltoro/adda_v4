package us.lsi.ag.agchromosomes;

import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;

import us.lsi.ag.Chromosome;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;

public class PermutationSubList2Chromosome extends RandomKey<Integer>
		implements SeqNormalData<Object>, Chromosome<List<Integer>> {

	public static List<Integer> normalSequence = null;
	public static SeqNormalData<Object> data;

	/**
	 * Dimensión del cromosoma
	 */

	public static int DIMENSION;

	public static void iniValues(SeqNormalData<Object> data) {
		PermutationSubList2Chromosome.data = data;
		PermutationSubList2Chromosome.normalSequence = PermutationSubList2Chromosome.data.normalSequence();
		PermutationSubList2Chromosome.normalSequence.add(-1);
		PermutationSubList2Chromosome.DIMENSION = PermutationSubList2Chromosome.normalSequence.size();
	}

	public PermutationSubList2Chromosome(List<Double> representation) throws InvalidRepresentationException {
		super(representation);
//		this.ft = this.calculateFt();
	}

	public PermutationSubList2Chromosome(Double[] representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	@Override
	public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> ls) {
		return new PermutationSubList2Chromosome(ls);
	}

	@Override
	public List<Integer> decode() {
		Preconditions.checkArgument(PermutationSubList2Chromosome.normalSequence != null);
		Integer size = (int)(List2.last(super.getRepresentation())*(DIMENSION));
//		System.out.println("Size="+size);
		List<Integer> r = super.decode(PermutationSubList2Chromosome.normalSequence).stream().filter(e->e!=-1).toList();
		return r.subList(0, size);
	}

	public static PermutationSubList2Chromosome getInitialChromosome() {
		List<Double> ls = RandomKey.randomPermutation(PermutationSubList2Chromosome.DIMENSION);
		return new PermutationSubList2Chromosome(ls);
	}

	@Override
	public double fitness() {
		return ft;
	}

	private double ft;

	private double calculateFt() {
		return PermutationSubList2Chromosome.data.fitnessFunction(this.decode());
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeFactory.ChromosomeType.Permutation;
	}

	@Override
	public Integer size() {
		return PermutationSubList2Chromosome.data.size();
	}

	@Override
	public Double fitnessFunction(List<Integer> dc) {
		return PermutationChromosome.data.fitnessFunction(dc);
	}

	@Override
	public Object solucion(List<Integer> dc) {
		return PermutationSubList2Chromosome.data.solucion(dc);
	}

	@Override
	public Integer itemsNumber() {
		return PermutationSubList2Chromosome.data.itemsNumber();
	}

}
