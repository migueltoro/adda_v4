package us.lsi.graphs.manual.recorridos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import us.lsi.graphs.manual.VirtualGraph;

public abstract class Recorrido<V, E> {
	
	public static record Data<V,E>(Optional<V> father, Double weight) {
		public static <V, E> Data<V, E> of(Optional<V> father, Double weight) {
			return new Data<>(father, weight);
		}
    }
	
	protected VirtualGraph<V,E> graph;
	protected Map<V,Data<V,E>> tree;
	protected List<V> path;
	
	protected Recorrido(VirtualGraph<V, E> grafo) {
		this.graph = grafo;
		this.tree = new HashMap<>();
		this.path = new ArrayList<>();
	}

	public VirtualGraph<V, E> graph() {
		return graph;
	}

	public Map<V, Data<V, E>> tree() {
		return tree;
	}

	public List<V> path() {
		return path;
	}
	
	public List<V> pathToOrigin(V source) {
        List<V> ls = new ArrayList<>();
        V v = source;
        ls.add(v);
        Data<V, E> next = this.tree.get(v);
        while (next.father().isPresent()) {
            v = next.father().get();
            ls.add(v);
            next = this.tree.get(v);
        }
        return ls;
    }
	
	public List<V> pathFromOrigin(V source) {
        List<V> ls = new ArrayList<>();
        V v = source;
        ls.add(v);
        Data<V, E> next = this.tree.get(v);
        while (next.father().isPresent()) {
            v = next.father().get();
            ls.add(0, v);
            next = this.tree.get(v);
        }
        return ls;
    }
	
	public Double pathWeight(V source) {
        return this.tree.get(source).weight();
    }
	
	public V origin(V source) {
        V v = source;
        Data<V, E> next = this.tree.get(v);
        while (next.father().isPresent()) {
            v = next.father().get();
            next = this.tree.get(v);
        }
        return v;
    }
	
	public List<E> pathEdges(V source) {
        List<V> path = this.pathFromOrigin(source);
        List<E> ls = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            ls.add(this.graph.edge(path.get(i), path.get(i + 1)));
        }
        return ls;
    }
	
    abstract public void traverse(V source);
    
	public void traverseAll() {
        for (V v : this.graph.vertexSet()) {
            if (!this.tree.containsKey(v)) {
                this.traverse(v);
            }
        }
    }
	
	public Map<V,Set<V>> groups() {
        Set<V> all_elements = this.graph.vertexSet();
        Map<V,Set<V>> r = new HashMap<>();
        for (V v : all_elements) {
            V org = this.origin(v);
            if (r.containsKey(org)) {
                r.get(org).add(v);
            } else {
            	Set<V> st = new HashSet<>();
            	st.add(v);
                r.put(org, st);
            }
        }
        return r;
    }
	
	public List<Set<V>> groupsList() {
        return new ArrayList<>(this.groups().values());
    }
	
	public boolean pathExist(V source, V target) {
        return this.origin(source).equals(this.origin(target));
    }
       
}
