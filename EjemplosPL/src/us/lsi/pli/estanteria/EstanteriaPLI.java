package us.lsi.pli.estanteria;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class EstanteriaPLI {

	public static class Libro {
		
		public static Libro of(String linea) {
			String[] partes = linea.split(",");
			return new Libro(Double.parseDouble(partes[0]),Double.parseDouble(partes[1]));
		}
		public static Libro of(Double altura, Double anchura) {
			return new Libro(altura,anchura);
		}
		public static List<Libro> libros;
		public final Double altura;
		public final Double anchura;
		public Libro(Double altura, Double anchura) {
			super();
			this.altura = altura;
			this.anchura = anchura;
		}
		public static Double altura(Integer i) {
			return Libro.libros.get(i).altura;
		}
		public static Double anchura(Integer i) {
			return Libro.libros.get(i).anchura;
		}
		@Override
		public String toString() {
			return String.format("(%.2f,%.2f)",altura,anchura);
		}		
	}
	
	public static class Estante {
		
		public static Estante of(String linea) {
			return new Estante(Double.parseDouble(linea));
		}
		public static Estante of(Double altura) {
			return new Estante(altura);
		}	
		public static List<Estante> estantes;
		public final Double altura;
		public static Double anchura;
		public Estante(Double altura) {
			super();
			this.altura = altura;
		}	
		public static Double altura(Integer i) {
			return Estante.estantes.get(i).altura;
		}
		@Override
		public String toString() {
			return String.format("(%.2f)",altura);
		}	
	}
	
	public static void datos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		Integer p1 = lineas.indexOf("#");
		Integer p2 = p1+1+lineas.subList(p1+1,lineas.size()).indexOf("#");
		Libro.libros = lineas.subList(0,p1).stream().map(linea->Libro.of(linea)).collect(Collectors.toList());
		Estante.estantes = lineas.subList(p1+1,p2).stream().map(linea->Estante.of(linea)).collect(Collectors.toList());
		Estante.anchura = Double.parseDouble(lineas.get(p2+1));
	}
	
	public static Integer getN() {
		return Libro.libros.size();
	}
	
	public static Integer getM() {
		return Estante.estantes.size();
	}
	
	public static Double alturaLibro(Integer i) {
		return Libro.altura(i);
	}
	
	public static Double alturaEstante(Integer i) {
		return Estante.altura(i);
	}
	
	public static Double anchuraLibro(Integer i) {
		return Libro.anchura(i);
	}
	
	public static Double anchuraEstante() {
		return Estante.anchura;
	}
	
	
	public static void estanteria_model() throws IOException {
		datos("data/estanteria.txt");
		System.out.println(Libro.libros);
		System.out.println(Estante.estantes);
		System.out.println(Estante.anchura);
		System.out.println(Libro.libros.size());
		System.out.println(Estante.estantes.size());
		AuxGrammar.generate(EstanteriaPLI.class,"modelos/estanteria.lsi","ficheros/estanteria.lp");
		Locale.setDefault(Locale.of("en", "US"));
		GurobiSolution solution = GurobiLp.gurobi("ficheros/estanteria.lp");
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	public static void main(String[] args) throws IOException {
		estanteria_model();
	}

}
