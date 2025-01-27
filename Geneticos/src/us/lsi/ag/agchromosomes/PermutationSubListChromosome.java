package us.lsi.ag.agchromosomes;

import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.BinaryChromosome;

//import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;

import us.lsi.ag.Chromosome;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Preconditions;
import us.lsi.common.List2;



/**
 * 
 * 
 * 
 * 
 * @author Miguel Toro
 * 
 * 
* <p> Una implementación del tipo IndexChromosome. Toma como información la definición de un problema que implementa el interfaz IndexProblemAG.
 * A partir de esa información construye una secuencia normal. 
 * Asumimos que el número de objetos es <code>n </code>y el tamaño de la secuencia normal <code>r</code>. 
 * La lista decodificada es una permutación de un subconjunto de la secuencia normal.</p>
 *  
 * <p> La lista decodificada está formada, por tanto, 
 *  por una lista de  tamaño menor o igual a <code>r</code>, cuyos valores son 
 * índices en el rango <code> [0,n-1]</code>, y cada índice <code>i</code> se  repite un número de veces igual o menor 
 * al dado por la multiplicidad máxima del objeto <code> i </code>
 * definida en el problema. </p>
 * 
 * <p> La implementación usa un cromosoma de clave aleatoria de tamaño <code> r </code> y otro binario del 
 * mismo tamaño.
 * Es un cromosoma adecuado para codificar problemas de subcojuntos de permutaciones.</p>
 */
public class PermutationSubListChromosome extends org.apache.commons.math3.genetics.Chromosome 
          implements SeqNormalData<Object>, Chromosome<List<Integer>> {

	public static List<Integer> normalSequence;	
	public static SeqNormalData<Object> data;
	
	/**
	 * Dimensión del cromosoma
	 */
	
	protected static int DIMENSION;
	

	public static void iniValues(SeqNormalData<Object> data){
		PermutationSubListChromosome.data =  data; 
		PermutationSubListChromosome.normalSequence = PermutationSubListChromosome.data.normalSequence();
		PermutationSubListChromosome.DIMENSION = PermutationSubListChromosome.normalSequence.size();
	}
	
	private BinaryChromosome2 binary;
	private RandomKey2 randomKey;	
	
	
	
	/**
	 * @param binary Un cromosoma binario
	 * @param randomKey Un cromosoma randomKey
	 */
	public PermutationSubListChromosome(org.apache.commons.math3.genetics.Chromosome binary, 
			org.apache.commons.math3.genetics.Chromosome randomKey) {
		super();
		if(binary instanceof BinaryChromosome2){
			this.binary = (BinaryChromosome2)binary;
		}
		if(randomKey instanceof RandomKey2){
			this.randomKey = (RandomKey2) randomKey;
		}
		Preconditions.checkArgument(this.binary!=null);
		Preconditions.checkArgument(this.randomKey!=null);
		Preconditions.checkArgument(this.binary.getLength()==this.randomKey.getLength());	
		this.ft = this.calculateFt();
	}
	
	/**
	 * Un constructor adecuado para crear un objeto por defecto de este tipo
	 */
	public PermutationSubListChromosome() {
		super();
		List<Integer> ls1 = BinaryChromosome2.randomBinaryRepresentation(100);
		List<Double>  ls2 = RandomKey2.randomPermutation(100);
		BinaryChromosome2 c1 = new BinaryChromosome2(ls1);
		RandomKey2 c2 = new RandomKey2(ls2);
		this.binary =  c1;
		this.randomKey = c2;	
		this.ft = 0.;
	}
	
	@Override
	public double fitness() {
		return ft;
	}
	
	private Double ft;
	
	private double calculateFt(){
		return PermutationSubListChromosome.data.fitnessFunction(this.decode());
	}

	public Integer getObjectsNumber() {
		return PermutationSubListChromosome.data.size();
	}

	public Integer getMax(int i) {
		return PermutationSubListChromosome.data.maxMultiplicity(i);
	}
	
	@Override
	public ChromosomeType type() {
		return ChromosomeFactory.ChromosomeType.PermutationSubList;
	}

	@Override
	public Integer size() {
		return PermutationSubListChromosome.data.size();
	}
	
	@Override
	public Integer itemsNumber() {
		return PermutationSubListChromosome.data.itemsNumber();
	}

	@Override
	public Double fitnessFunction(List<Integer> cr) {
		return PermutationSubListChromosome.data.fitnessFunction(cr);
	}

	@Override
	public Object solucion(List<Integer> dc) {
		return PermutationSubListChromosome.data.solucion(dc);
	}

	
	public int compareTo(org.apache.commons.math3.genetics.Chromosome another) {
		if (!(another instanceof PermutationSubListChromosome))
			throw new IllegalArgumentException();;
		PermutationSubListChromosome other = (PermutationSubListChromosome) another;
		Double f1 = this.fitness();
		Double f2 = other.fitness();
		return f1.compareTo(f2);
	}
	
	/**
	 * @return Una lista de enteros obtenida permutando la secuencia normal (0, 1, 2, ..., r-1) filtrada para incluir 
	 * sólo los seleccionados por el cromosoma binario 
	 */
	public List<Integer> decode() {	
		List<Integer> rk = randomKey.decode(normalSequence);
		List<Integer> r = List2.empty();
		List<Integer> bn = binary.getRepresentation();
		Preconditions.checkArgument(rk.size()==bn.size());
		for(int i=0; i< rk.size();i++){
			if(bn.get(i)==1){
				r.add(rk.get(i));
			}
		}
		return r;
	}

	/**
	 * @return La dimensión del cromosoma
	 */
	public int getLength() {
		return randomKey.getLength();
	}	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PermutationSubListChromosome))
			return false;
		PermutationSubListChromosome other = (PermutationSubListChromosome) obj;
		if (binary == null) {
			if (other.binary != null)
				return false;
		} else if (!binary.equals(other.binary))
			return false;
		if (randomKey == null) {
			if (other.randomKey != null)
				return false;
		} else if (!randomKey.equals(other.randomKey))
			return false;
		return true;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((binary == null) ? 0 : binary.hashCode());
		result = prime * result
				+ ((randomKey == null) ? 0 : randomKey.hashCode());
		return result;
	}

	public String toString() {
		return this.fitness()+","+this.decode();
	}
	
	public BinaryChromosome getBinary() {
		return binary;
	}

	public RandomKey<Integer> getRandomKey() {
		return randomKey;
	}
	
	/**
	 * @param dimension La dimensión del cromosoma
	 * @pre Debe estar inicializada la propiedad factory
	 * @return Un cromosoma mixto aleatorio
	 * 
	 */
	private static PermutationSubListChromosome random(Integer dimension){
		List<Integer> ls1 = BinaryChromosome2.randomBinaryRepresentation(dimension);
		List<Double>  ls2 = RandomKey2.randomPermutation(dimension);
		BinaryChromosome2 c1 = new BinaryChromosome2(ls1);
		RandomKey2 c2 = new RandomKey2(ls2);
		return new PermutationSubListChromosome(c1, c2);
	}
	
	public static PermutationSubListChromosome getInitialChromosome() {
		return PermutationSubListChromosome.random(PermutationSubListChromosome.DIMENSION);
	}

	private static class BinaryChromosome2 extends BinaryChromosome {		

		public BinaryChromosome2(Integer[] representation)
				throws InvalidRepresentationException {
			super(representation);
		}

		public BinaryChromosome2(List<Integer> representation)
				throws InvalidRepresentationException {
			super(representation);
		}

		@Override
		public double fitness() {
			return 0;
		}

		@Override
		public AbstractListChromosome<Integer> newFixedLengthChromosome(List<Integer> ls) {
			return new BinaryChromosome2(ls);
		}
		 
		@Override
		public List<Integer> getRepresentation(){
			return super.getRepresentation();
		}	
	}
	
	private static class RandomKey2 extends  RandomKey<Integer> {
		
		
		public RandomKey2(Double[] representation) throws InvalidRepresentationException {
			super(representation);
		}

		public RandomKey2(List<Double> representation) throws InvalidRepresentationException {
			super(representation);
		}

		@Override
		public double fitness() {
			return 0;
		}

		@Override
		public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> ls) {
			return new RandomKey2(ls);
		}
		
		
	}

	
	
}
