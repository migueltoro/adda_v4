package us.lsi.ag.agchromosomes;


import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;

import us.lsi.ag.Chromosome;
import us.lsi.ag.ValuesInSetData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class ValuesInSetChromosomeC extends RandomKey<Integer> 
       implements ValuesInSetData<Object>,Chromosome<List<Integer>> {
	
		
	public static ValuesInSetData<Object> data;
	
	/**
	 * Dimensión del cromosoma igual a size()
	 */
	
	public static int DIMENSION;
	

	public static void iniValues(ValuesInSetData<Object> data){
		ValuesInSetChromosomeC.data =  data; 
		ValuesInSetChromosomeC.DIMENSION = ValuesInSetChromosomeC.data.size();
	}
	
	public ValuesInSetChromosomeC(Double[] representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	public ValuesInSetChromosomeC(List<Double> representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	@Override
	public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> ls) {
		return new ValuesInSetChromosomeC(ls);
	}
	
	private Integer convert(Double e, Integer i) {
		Integer index = (int) (this.values(i).size()*e);
		return this.values(i).get(index);
	}

	
	public List<Integer> decode() {
		List<Double> ls = super.getRepresentation();
		return IntStream.range(0,ls.size()).boxed().map(i->this.convert(ls.get(i),i)).toList();
	}
	
	public static ValuesInSetChromosomeC getInitialChromosome() {
		List<Double> ls =  RandomKey.randomPermutation(ValuesInSetChromosomeC.DIMENSION);
		return new ValuesInSetChromosomeC(ls);
	}

	@Override
	public double fitness() {
		return ft;
	}
	
	private double ft;
	
	protected double calculateFt(){
		return ValuesInSetChromosomeC.data.fitnessFunction(this.decode());
	}

	public Integer getObjectsNumber() {
		return ValuesInSetChromosomeC.data.size();
	}

	
	public List<Integer> values(int i){
		return ValuesInSetChromosomeC.data.values(i);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeFactory.ChromosomeType.InSet;
	}

	@Override
	public Integer size() {
		return ValuesInSetChromosomeC.data.size();
	}

	@Override
	public List<Integer> values(Integer i) {
		return ValuesInSetChromosomeC.data.values(i);
	}

	@Override
	public Double fitnessFunction(List<Integer> dc) {
		return ValuesInSetChromosomeC.data.fitnessFunction(dc);
	}

	@Override
	public Object solucion(List<Integer> dc) {
		return ValuesInSetChromosomeC.data.solucion(dc);
	}
}
