package us.lsi.ag;

/**
 * @author Miguel Toro
 *
 * @param <E> El tipo de los valores del cromosoma
 * 
 * <p> El tipo de un cromosoma </p>
 */
public interface Chromosome<E>  {
	/**
	 * @return Un valor de tipo E asociado al cromosoma
	 */
	E decode();	
	/**
	 * @return El valor de fitness del cromosoma
	 */
	double fitness();
	
}
