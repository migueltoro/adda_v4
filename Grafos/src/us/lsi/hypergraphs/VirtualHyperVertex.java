package us.lsi.hypergraphs;

import java.util.List;

public interface VirtualHyperVertex<V,E,A,S> {
	
	public List<A> actions();
	
	public Boolean isBaseCase();
	
	public Double baseCaseWeight();
	
	public Boolean isValid();
	
	public S baseCaseSolution();

	public S solution(List<S> solutions);
	
	/**
	 * @param a Una acci&oacute;n
	 * @return El vecino del v&eacute;rtice siguiendo esa acci&oacute;n
	 * @pre La acci&oacute;n a debe ser aplicable
	 * @post El v&eacute;rtice retornada debe ser distinto al original y v&aacute;lido
	 */
	public List<V> neighbors(A a);
	
	/**
	 * Este m&eacute;todo debe ser sobrescrito en la clase que refine el tipo
	 * @param a Acci&oacute;n
	 * @return La arista que lleva al vecino siguiendo esta acci&oacute;n
	 */
	public E edge(A a); 
	
	/**
	 * Este m&eacute;todo podr&iacute;a ser sobrescrito en la clase que refine al tipo
	 * @return El conjunto de los vecinos
	 */
	default public List<List<V>> getNeighborListOf() {
		return this.actions()
					.stream()
					.map(a->this.neighbors(a))
					.toList();
	}
	/**
	 * Este m&eacute;todo podr&iacute;a ser sobrescrito en la clase que refine al tipo
	 * @return El conjunto de las aristas hacia los vecinos
	 */
	default public List<E> edgesOf() {
		return this.actions()
					.stream()
					.map(a->this.edge(a))
					.toList();				
	}

}
