package us.lsi.color;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.Graphs2;


public class DatosColorAG implements ValuesInRangeData<Integer,Map<Ciudad,Integer>> {

	
	private static SimpleWeightedGraph<Ciudad,Carretera> grafo; 
	private static List<Ciudad> ciudades;
	public static Integer maxNumColors;
	
	public DatosColorAG(String ficheroGrafo) { //"./ficheros/Andalucia.txt"
		grafo = cargaGrafo(ficheroGrafo);
		ciudades = grafo.vertexSet().stream().collect(Collectors.toList());
	}
	
	private static SimpleWeightedGraph<Ciudad, Carretera> cargaGrafo(String f) {
		return GraphsReader.newGraph(f, 
				Ciudad::ofFormat,
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph,
				Carretera::km);
	}

	@Override
	public Integer size() {
		return ciudades.size();
	}
	
	@Override	
    public Integer max(Integer index){
		return maxNumColors;		
	}

	@Override	
    public Integer min(Integer index){
		return 0;		
	}

	@Override
	public Map<Ciudad,Integer> solucion(List<Integer> solucion) {
		Map<Ciudad,Integer> res = IntStream.range(0, solucion.size()).boxed()
				.collect(Collectors.toMap(i->ciudades.get(i),i->solucion.get(i)));
		return res;		
	}
	
	@Override
	public Double fitnessFunction(List<Integer> solucion) {
		Map<Ciudad,Integer> m = solucion(solucion);			
  		Integer N = ciudades.size();
  		Long numAristasIlegales = grafo.edgeSet().stream()
				.filter(c -> m.get(grafo.getEdgeSource(c))== m.get(grafo.getEdgeTarget(c)))
				.count();
  		Integer numeroDeColores = m.values().stream()
  				.collect(Collectors.toSet())
  				.size();
		Double fitness = -(double) numeroDeColores -numAristasIlegales * N * N;
		
		return fitness;		
	}

	public Set<Set<Ciudad>> getComponentes(List<Integer> solucion) {
		Map<Ciudad,Integer> m = solucion(solucion);
			
		return m.entrySet().stream()
				   .collect(Collectors.groupingBy(e -> e.getValue(),Collectors.mapping(e->e.getKey(),Collectors.toSet())))
				   .values().stream()
				   .collect(Collectors.toSet());
	}
	
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
}
