package us.lsi.graphs.examples;


import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.String2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

/**
 * Resulve un problema de coloreado de grafos
 * 
 * @author Miguel Toro
 *
 */
public class ColoreadoDeGrafos {

	public static void main(String[] args) {
		
		Graph<Ciudad,Carretera> graph =  
				GraphsReader.newGraph("ficheros/andalucia.txt",
						Ciudad::ofFormat, 
						Carretera::ofFormat,
						Graphs2::simpleWeightedGraph,
						Carretera::km);
		
		VertexColoringAlgorithm<Ciudad> vca = new GreedyColoring<>(graph);
		VertexColoringAlgorithm.Coloring<Ciudad> vc = vca.getColoring();
		Map<Ciudad,Integer> colorDeCiudad = vc.getColors();
		Integer nc = vc.getNumberColors();
		String2.toConsole("%s,%s",nc,colorDeCiudad);
		
		GraphColors.toDot(graph,"ficheros/coloresAndalucia.gv",
				x->x.nombre(),
				x->x.nombre(),
				x->GraphColors.color(colorDeCiudad.get(x)),
				e->GraphColors.style(Style.solid));

	}

}
