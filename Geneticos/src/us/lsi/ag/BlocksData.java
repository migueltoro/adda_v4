package us.lsi.ag;

import java.util.List;

import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public interface BlocksData<S> extends ChromosomeData<List<Integer>,S> {
	
	/**
	 * @return Limites de los bloques en los que se descompone el cromosoma. 
	 * Un bloque está definido por dos valore consecutivos
	 */
	List<Integer> blocksLimits();
	
	/**
	 * @return Valores iniciales del cromosoma. 
	 * Los valores del cromosoma serán permutaciones de los valores dentro de cada bloque
	 */
	List<Integer> initialValues();
	
	default ChromosomeType type() {
		return ChromosomeType.Blocks;
	}

}
