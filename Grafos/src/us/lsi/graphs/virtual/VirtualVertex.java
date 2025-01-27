package us.lsi.graphs.virtual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface VirtualVertex<V extends VirtualVertex<V, E, A>, E extends SimpleEdgeAction<V, A>, A> {

	/**
	 * Devuelve una lista de acciones disponibles desde este vértice.
	 *
	 * @return una lista de acciones
	 */
	List<A> actions();

	/**
	 * Devuelve el vértice vecino alcanzado al realizar la acción dada.
	 *
	 * @param a la acción a realizar
	 * @return el vértice vecino
	 */
	V neighbor(A a);

	/**
	 * Devuelve la arista asociada con la acción dada.
	 *
	 * @param a la acción a realizar
	 * @return la arista asociada con la acción
	 */
	E edge(A a);

	/**
	 * Verifica si el objetivo ha sido alcanzado.
	 *
	 * @return true si el objetivo ha sido alcanzado, de lo contrario false
	 */
	public default Boolean goal() {
		return true;
	}

	/**
	 * Verifica si el objetivo tiene una solución.
	 *
	 * @return true si el objetivo tiene una solución, de lo contrario false
	 */
	public default Boolean goalHasSolution() {
		return true;
	}

	/**
	 * Devuelve la acción más prometedora.
	 *
	 * @return la acción más prometedora
	 */
	public default A greedyAction() {
		return this.actions().stream().findFirst().get();
	}

	/**
	 * Devuelve el vecino alcanzado por la acción más prometedora.
	 *
	 * @return el vecino alcanzado por la acción más prometedora
	 */
	public default V greedyNeighbor() {
		return this.neighbor(this.greedyAction());
	}

	/**
	 * Devuelve la arista asociada con la acción más prometedora.
	 *
	 * @return la arista asociada con la acción más prometedora
	 */
	public default E greedyEdge() {
		return this.edge(this.greedyAction());
	}

	/**
	 * Devuelve la arista que conecta con el vértice dado.
	 *
	 * @param v el vértice con el que se quiere encontrar la arista
	 * @return la arista que conecta con el vértice dado
	 */
	public default E getEdgeToVertex(V v) {
		return this.edgesOf().stream().filter(e -> e.source().equals(v) || e.target().equals(v)).findFirst().get();
	}

	/**
	 * Devuelve el conjunto de vértices vecinos.
	 *
	 * @return el conjunto de vértices vecinos
	 */
	public default Set<V> getNeighborListOf() {
		return actions().stream().map(a -> this.neighbor(a)).collect(Collectors.toSet());
	}

	/**
	 * Devuelve el conjunto de aristas asociadas con las acciones disponibles desde
	 * este vértice.
	 *
	 * @return el conjunto de aristas asociadas con las acciones disponibles
	 */
	public default Set<E> edgesOf() {
		return actions().stream().map(a -> this.edge(a)).collect(Collectors.toSet());
	}

	/**
	 * Devuelve una lista de aristas asociadas con las acciones disponibles desde
	 * este vértice.
	 *
	 * @return una lista de aristas asociadas con las acciones
	 */
	public default List<E> edgesListOf() {
		return actions().stream().map(a -> this.edge(a)).collect(Collectors.toList());
	}

	/**
	 * Verifica si el vértice dado es un vecino.
	 *
	 * @param v el vértice a verificar
	 * @return true si el vértice dado es un vecino, de lo contrario false
	 */
	public default Boolean isNeighbor(V v) {
		return this.getNeighborListOf().contains(v);
	}

	/**
	 * Verifica si el vértice es válido.
	 *
	 * @return true si el vértice es válido, de lo contrario false
	 */
	public default Boolean isValid() {
		return true;
	}

}
