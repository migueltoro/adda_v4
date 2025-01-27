package us.lsi.ag.agchromosomes;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;

import us.lsi.ag.Chromosome;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;


/**
 * @author Miguel Toro
 * 
 * 
 * <p> Una implementación del tipo ValuesInRangeCromosome&lt;Integer&gt;. Toma como información la definición de un problema que implementa el interfaz 
 * ValuesInRangeProblemAG. </p>
 * 
 * <p> Asumimos que el número de variables es n. La lista decodificada está formada por una lista de  
 * enteros de tamaño n cuyos elementos para cada i son 
 * valores en en rango [getMin(i),getMax(i)]. </p>
 * 
 * <p> La implementación usa un cromosoma RandomKey del tamaño n.  </p>
 * 
 * <p> Es un cromosoma adecuado para codificar problemas de subconjuntos de multiconjuntos</p>
 *
 */
public class RangeChromosome extends RandomKey<Integer>  
             implements ValuesInRangeData<Integer,Object>, Chromosome<List<Integer>> {
	
	public static ValuesInRangeData<Integer,Object> data;
	
	/**
	 * Dimensión del cromosoma igual a size()
	 */
	
	public static int DIMENSION;
	

	public static void iniValues(ValuesInRangeData<Integer,Object> data){
		RangeChromosome.data = data; 
		RangeChromosome.DIMENSION = RangeChromosome.data.size();
	}
	
	public RangeChromosome(Double[] representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	public RangeChromosome(List<Double> representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	@Override
	public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> ls) {
		return new RangeChromosome(ls);
	}
	
	private Integer convert(Double e, Integer i) {
//		System.out.printf("%.2f,%d,%d,%d,%d\n",e,i,RangeChromosome.DIMENSION,this.getMin(i),this.getMax(i));
		return (int) (this.min(i) + (this.max(i)-this.min(i))*e);
	}
	
	public List<Integer> decode() {
		List<Double> ls = super.getRepresentation();
		return IntStream.range(0,ls.size()).boxed().map(i->this.convert(ls.get(i),i)).toList();
	}
	
	public static RangeChromosome getInitialChromosome() {
		List<Double> ls = RandomKey.randomPermutation(RangeChromosome.DIMENSION);
		return new RangeChromosome(ls);
	}

	@Override
	public double fitness() {
		return ft;
	}
	
	private double ft;
	
	protected double calculateFt(){
		return RangeChromosome.data.fitnessFunction(this.decode());
	}

	@Override
	public Integer max(Integer i) {
		return RangeChromosome.data.max(i);
	}

	@Override
	public Integer min(Integer i) {
		return RangeChromosome.data.min(i);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeFactory.ChromosomeType.Range;
	}

	@Override
	public Integer size() {
		return RangeChromosome.data.size();
	}

	@Override
	public Double fitnessFunction(List<Integer> dc) {
		return RangeChromosome.data.fitnessFunction(dc);
	}

	@Override
	public Object solucion(List<Integer> dc) {
		return RangeChromosome.data.solucion(dc);
	}

	
}

