package us.lsi.ag.agstopping;

import org.apache.commons.math3.genetics.FixedElapsedTime;
import org.apache.commons.math3.genetics.FixedGenerationCount;
import org.apache.commons.math3.genetics.StoppingCondition;



public class StoppingConditionFactory {

	/**
	 * <p> Distintos tipos de condiciones de parada </p>
	 * 
	 * <ul>
	 * <li> ElapsedTime: Para cuando el tiempo transcurrido se el especificado en <code> elapsedTime </code>.
	 * <li> GenerationCount: Para cuando el n�mero de generaciones sea igual al especificado en <code> NUM_GENERATIONS </code>
	 * <li> SolutionsNumber: Para cuando en una generaci�n encuentra al menos SOLUTIONS_NUMBER de cromososmas 
	 * con <code> fitness</code>  igual o mayor <code> FITNESS </code> o <code> NUM_GENERATIONS </code> ha sido superado.
	 * </ul> 
	 *
	 */
	public enum StoppingConditionType{ElapsedTime,GenerationCount,SolutionsNumber};
	
	/**
	 * Condici�n de parada
	 */
	public static StoppingConditionType stoppingConditionType = StoppingConditionType.SolutionsNumber;
	/**
	 * N�mero de soluciones a encontrar si fijamos el criterio de parada en SolutionsNumber
	 */
	public static int SOLUTIONS_NUMBER_MIN = 1;
	/**
	 * Tiempo m�ximo transcurrido para finalizar el algoritmo si usamos la condici�n de finalizaci�n ElapsedTime.
	 */
	public static long MAX_ELAPSEDTIME = 1000000000;
	
	/**
	 * Valor m�nimo de la fitness de los cromosomas en las soluciones que vamos buscando si fijamos el criterio de parada en SolutionsNumber
	 */
	public static double FITNESS_MIN = 0.;	
	/**
	 * N�mero de generaciones m�ximo para fijar le criterio de parada
	 */
	public static int NUM_GENERATIONS = Integer.MAX_VALUE;
	
	public static StoppingCondition getStoppingCondition(){
		return switch(stoppingConditionType){
		case ElapsedTime -> new FixedElapsedTime(StoppingConditionFactory.MAX_ELAPSEDTIME);
		case GenerationCount -> new FixedGenerationCount(StoppingConditionFactory.NUM_GENERATIONS);
		case SolutionsNumber -> 
		new SolutionsNumber(StoppingConditionFactory.MAX_ELAPSEDTIME,
				StoppingConditionFactory.NUM_GENERATIONS,
				StoppingConditionFactory.SOLUTIONS_NUMBER_MIN,
				StoppingConditionFactory.FITNESS_MIN);
		};
	}	
}
