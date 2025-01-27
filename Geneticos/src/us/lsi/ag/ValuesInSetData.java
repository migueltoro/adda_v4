package us.lsi.ag;

import java.util.List;

import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;


/**
 * @author Miguel Toro
 *
 * @param <S> El tipo de solución del problema
 */
public interface ValuesInSetData<S> extends ChromosomeData<List<Integer>,S> {
	
	/**
	 * @pre 0 &le; i &lt; getVariableNumber()
	 * @param i Un entero 
	 * @return El conjunto de valores de la variable i
	 */
	List<Integer> values(Integer i);
	
	default ChromosomeType type() {
		return ChromosomeType.InSet;
	}

}
