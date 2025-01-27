package us.lsi.ag.agchromosomes;

import java.util.List;

import org.apache.commons.math3.genetics.BinaryMutation;
//import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.CycleCrossover;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.NPointCrossover;
import org.apache.commons.math3.genetics.OnePointCrossover;
import org.apache.commons.math3.genetics.OrderedCrossover;
import org.apache.commons.math3.genetics.RandomKeyMutation;
import org.apache.commons.math3.genetics.SelectionPolicy;
import org.apache.commons.math3.genetics.TournamentSelection;
import org.apache.commons.math3.genetics.UniformCrossover;

import us.lsi.ag.BlocksData;
import us.lsi.ag.Chromosome;
import us.lsi.ag.ChromosomeData;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.ValuesInSetData;
import us.lsi.ag.agoperators.SubListCrossoverPolicy;
import us.lsi.ag.agoperators.SubListMutationPolicy;
import us.lsi.common.Preconditions;


/**
 * @author Miguel Toro
 * 
 * <p> Una factor�a de cromosomas de los distintos tipos implementados. </p>
 *
 */
public class ChromosomeFactory {
	
	/**
	 * Los diferentes tipos de cromosmomas implementados
	 *
	 */
	public enum ChromosomeType {Binary,Range,Real,InSet,SubList,Permutation,PermutationSubList,PermutationSubList2,Blocks,Expression}
	
//	public static ChromosomeType tipo;

	/**
	 * @param tipo El tipo de cromosoma
	 * @return Un cromosoma aleatorio del tipo indicado
	 */
	@SuppressWarnings("unchecked")
	public static <E> Chromosome<E> randomChromosome(ChromosomeType tipo){
		Chromosome<E> chromosome = null;
		switch(tipo){
		case Binary: chromosome = (Chromosome<E>) BinaryChromosome.getInitialChromosome(); break;
		case SubList: chromosome = (Chromosome<E>) SubListChromosome.getInitialChromosome(); break;
		case Range: chromosome = (Chromosome<E>) RangeChromosome.getInitialChromosome(); break;
		case Permutation: chromosome = (Chromosome<E>) PermutationChromosome.getInitialChromosome(); break;
		case PermutationSubList: chromosome = (Chromosome<E>) PermutationSubListChromosome.getInitialChromosome(); break;
		case PermutationSubList2: chromosome = (Chromosome<E>) PermutationSubList2Chromosome.getInitialChromosome(); break;
		case Real: chromosome = (Chromosome<E>) DoubleChromosome.getInitialChromosome(); break;
		case InSet: chromosome = (Chromosome<E>) ValuesInSetChromosomeC.getInitialChromosome(); break;
		case Blocks: chromosome = (Chromosome<E>) BlocksChromosomePermutation.getInitialChromosome(); break;
		}
		return chromosome;
	}
	
	/**
	 * <p>Distintos tipo de operadores de cruce </p>
	 *
	 * <ul>
	 * <li> <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/OnePointCrossover.html" target="_blank"> OnePointCrossover </a>
	 * <li> <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/NPointCrossover.html" target="_blank"> NPointCrossover </a>
	 * <li> <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/CycleCrossover.html" target="_blank"> CycleCrossover </a>
	 * <li> <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/OrderedCrossover.html" target="_blank"> OrderedCrossover </a>
	 * <li> <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/genetics/UniformCrossover.html" target="_blank"> UniformCrossover </a>
	 * </ul> 
	 * 
	 */
	
	public enum CrossoverType{Cycle,NPoint,OnePoint,Ordered,Uniform};
	
	/**
	 * Tipo del operador de cruce
	 */
	public static CrossoverType crossoverType = CrossoverType.OnePoint;
	
	/**
	 * N�mero de puntos usados en la partici�n si se usa un operador de cruce de tipo NPointCrossover
	 */
	public static int NPOINTCROSSOVER = 3;
	/**
	 * La ratio si se usa el operador de cruce de tipo UniformCrossover
	 */
	public static double RATIO_UNIFORMCROSSOVER = 0.7;
	
	/**
	 * @param tipo El tipo del cromosoma
	 * @param problema Las propiedades del probblema a resolver
	 * @return Un operador de cruce adecuado para un cromosma del tipo indicado
	 */
	public static CrossoverPolicy getCrossoverPolicy(ChromosomeType tipo){
		CrossoverPolicy crossOverPolicyBin = null;	
		switch(crossoverType){
		case Cycle: crossOverPolicyBin = new CycleCrossover<Integer>();break;
		case NPoint: crossOverPolicyBin = new NPointCrossover<Integer>(NPOINTCROSSOVER);break;
		case OnePoint: crossOverPolicyBin = new OnePointCrossover<Integer>();break;
		case Ordered: crossOverPolicyBin = new OrderedCrossover<Integer>(); break;
		case Uniform: crossOverPolicyBin = new UniformCrossover<Integer>(RATIO_UNIFORMCROSSOVER); break;
		}
		CrossoverPolicy crossOverPolicyKey = null;	
		switch(crossoverType){
		case Cycle: crossOverPolicyKey = new CycleCrossover<Double>();break;
		case NPoint: crossOverPolicyKey = new NPointCrossover<Double>(NPOINTCROSSOVER);break;
		case OnePoint: crossOverPolicyKey = new OnePointCrossover<Double>();break;
		case Ordered: crossOverPolicyKey = new OrderedCrossover<Double>(); break;
		case Uniform: crossOverPolicyKey = new UniformCrossover<Double>(RATIO_UNIFORMCROSSOVER); break;
		}
		CrossoverPolicy crossOverPolicy = null;	
		switch(tipo){
		case Binary: crossOverPolicy = crossOverPolicyBin; break;
		case SubList: crossOverPolicy = crossOverPolicyBin; break;
		case Range: crossOverPolicy = crossOverPolicyKey; break;
		case Permutation: crossOverPolicy = crossOverPolicyKey; break;
		case PermutationSubList: crossOverPolicy = new SubListCrossoverPolicy(crossOverPolicyBin,crossOverPolicyKey); break;
		case PermutationSubList2: crossOverPolicy = crossOverPolicyKey; break;
		case Real: crossOverPolicy = crossOverPolicyKey; break;
		case InSet: crossOverPolicy = crossOverPolicyKey; break;	
		case Blocks: crossOverPolicy = crossOverPolicyKey; break;
		case Expression: crossOverPolicy = crossOverPolicyKey; break;
		}
		Preconditions.checkState(crossOverPolicy!=null);
		return crossOverPolicy;
	}
	
	/**
	 * @param tipo El tipo del cromosoma
	 * @param problema El problema a resolver
	 * @return Un operador de mutaci�n adecuado para un cromosoma del tipo indicado
	 */
	public static MutationPolicy getMutationPolicy(ChromosomeType tipo){
		MutationPolicy mutationPolicy = null;
		switch(tipo){
		case Binary:  mutationPolicy = new BinaryMutation()	; break;
		case SubList: mutationPolicy = new BinaryMutation(); break;
		case Range: mutationPolicy = new RandomKeyMutation();	; break;
		case Permutation: mutationPolicy = new RandomKeyMutation(); break;
		case PermutationSubList: mutationPolicy = new SubListMutationPolicy(); break;
		case PermutationSubList2: mutationPolicy = new RandomKeyMutation(); break;
		case Real: mutationPolicy = new RandomKeyMutation();	; break;
		case InSet: mutationPolicy = new RandomKeyMutation(); break;
		case Blocks: mutationPolicy = new RandomKeyMutation(); break;
		case Expression: mutationPolicy = new RandomKeyMutation(); break;
		}
		Preconditions.checkState(mutationPolicy!=null);
		return mutationPolicy;
	}
	
	/**
	 * <p> Para aplicar los operadores de mutaci�n se escogen dos cromosomas. 
	 * La t�cnica implementada para escoger cada uno de los dos cromosomas se denomina selecci�n por torneo. 
	 * Se trata de organizar dos torneos. 
	 * En cada uno se elige el mejor cromosoma de entrre <code> TOURNAMENT_ARITY </code> cromosomas de la poblaci�n seleccionados al azar. 
	 * Si el tama�o de <code> TOURNAMENT_ARITY </code> es m�s grande, los cromosomas
	 *  d�biles tienen menor probabilidad de ser seleccionados.</p>
	 * 
	 * <p> N�mero de participantes en el torneo para elegir los cromosomas que participar�n en el cruce </p>
	 * <p> Un valor t�pico es 2 </p>
	 */
	
	public static int TOURNAMENT_ARITY = 2;
	
	/**
	 * @return Un operador que implementa la pol�tica de selecci�n escogida
	 */
	public static SelectionPolicy getSelectionPolicy(){	
		SelectionPolicy selectionPolicy = new TournamentSelection(TOURNAMENT_ARITY);
		Preconditions.checkState(selectionPolicy!=null);
		return selectionPolicy;
	}
	
	/**
	 * @param tipo Tipo de cromosoma
	 * @param problema El problema a resolver 
	 * @post El m�todo inicializa los par�metros relevantes de la clase que implementa el tipo indicado de cromosoma
	 */
	@SuppressWarnings("unchecked")
	public static <E,S> void iniValues(ChromosomeData<E,S> data, ChromosomeType tipo){
		switch(tipo){
		case Binary: BinaryChromosome.iniValues((ChromosomeData<List<Integer>, Object>) data);break;
		case SubList: SubListChromosome.iniValues((SeqNormalData<Object>) data);break;
		case Range: RangeChromosome.iniValues((ValuesInRangeData<Integer, Object>) data); break;
		case Permutation: PermutationChromosome.iniValues((SeqNormalData<Object>) data);break;
		case PermutationSubList: PermutationSubListChromosome.iniValues((SeqNormalData<Object>) data);break;
		case PermutationSubList2: PermutationSubList2Chromosome.iniValues((SeqNormalData<Object>) data);break;
		case Real: DoubleChromosome.iniValues((ValuesInRangeData<Double, Object>) data);break;
		case InSet: ValuesInSetChromosomeC.iniValues((ValuesInSetData<Object>) data); break;
		case Blocks: BlocksChromosomePermutation.iniValues((BlocksData<Object>) data); break;
		}
	}
}
