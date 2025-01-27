package us.lsi.ag.agchromosomes;

import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

import us.lsi.ag.BinaryData;
import us.lsi.ag.Chromosome;
import us.lsi.ag.ChromosomeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

/**
 * @author Miguel Toro
 * 
 * <p> 
 * Un cromosoma cuya valor decodificado es una lista de ceros y unos del tamaño especificado en el problema.
 * La implementación es una adaptación de la clase {@link org.apache.commons.math3.genetics.Chromosome Chromosome} de Apache. </p>
 *
 */
public class BinaryChromosome<S> extends org.apache.commons.math3.genetics.BinaryChromosome implements
                   BinaryData<S>, Chromosome<List<Integer>> {
	
	/**
	 * Dimensión del cromosoma
	 */
	
	protected static Integer DIMENSION;
	protected static ChromosomeData<List<Integer>,Object>  data;
	
	@SuppressWarnings("unchecked")
	public static <S> ChromosomeData<List<Integer>,S> data() {
		return (ChromosomeData<List<Integer>,S>) data;
	}
	
	public static void iniValues(ChromosomeData<List<Integer>,Object> data){
		BinaryChromosome.data = data;
		BinaryChromosome.DIMENSION = data.size();
	}

	public BinaryChromosome(List<Integer> representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	public BinaryChromosome(Integer[] representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	@Override
	public List<Integer> decode() {
		return getRepresentation();
	}

	@Override
	public double fitness() {
		return ft;
	}
	
	private Double ft = null;
	
	private double calculateFt(){
		return this.fitnessFunction(this.decode());
	}

	@Override
	public AbstractListChromosome<Integer> newFixedLengthChromosome(List<Integer> ar) {
		return new BinaryChromosome<>(ar);
	}
	
	public static <S> BinaryChromosome<S> getInitialChromosome() {
		List<Integer> ls = BinaryChromosome.randomBinaryRepresentation(BinaryChromosome.DIMENSION);
		return new BinaryChromosome<>(ls);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeFactory.ChromosomeType.Binary;
	}

	@Override
	public Integer size() {
		return BinaryChromosome.data.size();
	}

	@Override
	public Double fitnessFunction(List<Integer> dc) {
		return BinaryChromosome.data.fitnessFunction(dc);
	}

	@Override
	public S solucion(List<Integer> dc) {
		return BinaryChromosome.<S>data().solucion(dc);
	}

}
