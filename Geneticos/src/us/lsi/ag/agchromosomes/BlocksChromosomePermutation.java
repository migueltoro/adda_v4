package us.lsi.ag.agchromosomes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;

import us.lsi.ag.BlocksData;
import us.lsi.ag.Chromosome;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class BlocksChromosomePermutation extends RandomKey<Integer> 
          implements BlocksData<Object>, Chromosome<List<Integer>> {
	
	public static BlocksData<Object> data;
	public static int DIMENSION;
	
	public static <S> void iniValues(BlocksData<Object> data){
		BlocksChromosomePermutation.data = data; 
		BlocksChromosomePermutation.DIMENSION = BlocksChromosomePermutation.data.size();
	}
	
	public static BlocksChromosomePermutation getInitialChromosome() {
		List<Double> ls = RandomKey.randomPermutation(BlocksChromosomePermutation.DIMENSION);
		BlocksChromosomePermutation cr = BlocksChromosomePermutation.of(ls);
		return cr;
	}
	
	public static BlocksChromosomePermutation of(List<Double> representation) throws InvalidRepresentationException {
		return new BlocksChromosomePermutation(representation);
	}

	public static <S> BlocksChromosomePermutation of(Double[] representation) throws InvalidRepresentationException {
		return new BlocksChromosomePermutation(representation);
	}

	public BlocksChromosomePermutation(Double[] representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	public BlocksChromosomePermutation(List<Double> representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}
	
	@Override
	public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> ls) {
		return new BlocksChromosomePermutation(ls);
	}
	
	private static record Dt(Double first,Integer second) {}
	
	@Override
	public List<Integer> decode() {
		List<Double> r = super.getRepresentation();
		List<Integer> s = new ArrayList<>();
		List<Integer> p = this.blocksLimits();
		Integer pn = p.size();
		for(int i=0; i<pn-1;i++) {
			Integer tb = p.get(i+1)-p.get(i);
			List<Double> rp = r.subList(p.get(i),p.get(i+1));
			List<Integer> values = this.initialValues().subList(p.get(i),p.get(i+1));
			List<Dt> dts = IntStream.range(0,tb)
					.boxed()
					.map(x->new Dt(rp.get(x),values.get(x)))
					.collect(Collectors.toList());
			Collections.sort(dts,Comparator.comparing(d->d.first));	
			List<Integer> sortValues = dts.stream().map(d->d.second()).collect(Collectors.toList());
			s.addAll(sortValues);			
		}
		return s;
	}	
	
	private double ft;
	
	private double calculateFt(){
		return BlocksChromosomePermutation.data.fitnessFunction(this.decode());
	}


	@Override
	public double fitness() {
		return ft;
	}

	@Override
	public Double fitnessFunction(List<Integer> cr) {
		return BlocksChromosomePermutation.data.fitnessFunction(cr);
	}

	@Override
	public Object solucion(List<Integer> cr) {
		return BlocksChromosomePermutation.data.solucion(cr);
	}

	@Override
	public Integer size() {
		return BlocksChromosomePermutation.data.size();
	}

	@Override
	public ChromosomeType type() {
		return BlocksChromosomePermutation.data.type();
	}


	@Override
	public List<Integer> blocksLimits() {
		return BlocksChromosomePermutation.data.blocksLimits();
	}

	@Override
	public List<Integer> initialValues() {
		return BlocksChromosomePermutation.data.initialValues();
	}

}
