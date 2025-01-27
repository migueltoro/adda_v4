package us.lsi.ag;

import java.util.List;

public interface ValuesInRangeData<E,S> extends ChromosomeData<List<E>,S> {

	/**
	 * @pre 0 &le; i &lt; getVariableNumber()
	 * @param i Un entero 
	 * @return El máximo valor, sin incluir, del rango de valores de la variable i
	 */
	E max(Integer i);
	/**
	 * @pre 0 &le; i &lt; 
	 * @param i Un entero getVariableNumber()
	 * @return El mínimo valor del rango de valores de la variable i
	 */
	E min(Integer i);

}
