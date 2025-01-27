package us.lsi.pli.reparto;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;

import us.lsi.common.String2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;
import us.lsi.graphs.views.IntegerVertexGraphView;

public class DatosReparto {

	public static record Cliente(Integer id, Double beneficio) {
		public static Cliente ofFormat(String[] v) {
			Cliente c = new Cliente(Integer.parseInt(v[0].trim()), Double.parseDouble(v[1].trim()));
			return c;
		}

		@Override
		public String toString() {
			return String.format("%d: (%.1f)", id, beneficio);
		}
	}

	public static record Carretera(Integer id, Integer origen, Integer destino, Double kms) {

		public static Integer cont = 0;
		public static Carretera ofFormat(String[] v) {
			Carretera c = new Carretera(cont++, Integer.parseInt(v[0].trim()), Integer.parseInt(v[1].trim()),
					Double.parseDouble(v[2].trim()));
			return c;
		}

		@Override
		public String toString() {
			return id + ": (" + origen + "," + destino + "); " + kms;
		}
	}

	public static Integer origen, destino;

	private static Graph<Cliente, Carretera> grafo;
	public static IntegerVertexGraphView<Cliente, Carretera> g2;
	public static Integer n;
	public static Integer m;

	public static void iniDatos(String fichero) {

		DatosReparto.grafo = GraphsReader.newGraph(fichero, 
				Cliente::ofFormat, 
				Carretera::ofFormat,
				Graphs2::simpleWeightedGraph,
				Carretera::kms);
		
		DatosReparto.g2 = IntegerVertexGraphView.of(grafo);
		DatosReparto.n = DatosReparto.grafo.vertexSet().size();
		DatosReparto.m = DatosReparto.grafo.edgeSet().size();
	}

	public static Integer getNumCarreteras() {
		return DatosReparto.m;
	}

	public static Integer getNumClientes() {
		return DatosReparto.n;
	}

	public static Integer getN() {
		return DatosReparto.n;
	}

	public static Double getEdgeWeight(Integer i, Integer j) {
		return DatosReparto.g2.getEdgeWeight(i, j);
	}
	
	public static Double getEdgeWeight2(Integer i, Integer j) {
		return (DatosReparto.n-i)*DatosReparto.g2.getEdgeWeight(i, j);
	}

	public static Boolean containsEdge(Integer i, Integer j) {
		return DatosReparto.g2.containsEdge(i, j);
	}
	
	public static Double getBeneficio(Integer i) {
		return DatosReparto.g2.vertex(i).beneficio();
	}
	
	public static Double getBeneficios() {
		Double s = 0.;
		for (int i = 0; i < DatosReparto.n; i++) {
			s += DatosReparto.g2.vertex(i).beneficio();
		}
		return s;
	}

	public static void toConsole() {
		Locale.setDefault(Locale.of("en", "US"));
		String2.toConsole(DatosReparto.grafo.vertexSet(), "Clientes");
		String2.toConsole(DatosReparto.grafo.edgeSet(), "Carreteras");
		String2.toConsole(DatosReparto.grafo.toString());
		String2.toConsole(DatosReparto.g2.toString());
	}
	
	public static Set<SimpleEdge<Integer>> f(String text) {
		String[] p = text.split("_");
		Integer source = Integer.parseInt(p[1]);
		Integer target = Integer.parseInt(p[2]);		
		return Set.of(DatosReparto.g2.getEdge(source, target),DatosReparto.g2.getEdge(target,source));
	}
	
	public static Set<SimpleEdge<Integer>> aristas(Map<String,Double> m) { 
		return m.entrySet().stream()
			.filter(e->e.getKey().startsWith("y") && e.getValue() > 0.)
			.map(e->e.getKey())
			.map(e->f(e))
			.flatMap(s->s.stream())
			.collect(Collectors.toSet());
	}

	
	public static void main(String[] args) throws IOException {
		DatosReparto.iniDatos("data/reparto1.txt");
		DatosReparto.toConsole();
		DatosReparto.grafo.edgeSet().stream().forEach(e->String2.toConsole(e.toString()));
		String2.toConsole("_________________");
		DatosReparto.g2.edgeSet().stream().forEach(e->String2.toConsole(e.toString()));
		System.out.println(DatosReparto.g2.containsEdge(1,0));
		System.out.println(DatosReparto.g2.containsEdge(0,1));
	}

}
