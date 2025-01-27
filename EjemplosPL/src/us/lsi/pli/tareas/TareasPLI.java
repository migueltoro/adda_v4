package us.lsi.pli.tareas;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import us.lsi.streams.Stream2;

public class TareasPLI {
	
	public static class Tarea {
		
		public static List<Tarea> tareas;
		public static Integer n;
		
		public static List<Tarea> datos(String fichero){
			return Files2.streamFromFile(fichero)
					.map(linea->Tarea.of(linea))
					.sorted(Comparator.comparing(t->t.start))
					.collect(Collectors.toList());
		}
		
		public static Tarea of(String linea) {
			String[] partes = linea.split(",");
			String[] sp = partes[0].split(":");
			String[] dp = partes[1].split(":");
			Integer start = Integer.parseInt(sp[0])*60+Integer.parseInt(sp[1]);
			Integer duration = Integer.parseInt(dp[0])*60+Integer.parseInt(dp[1]);
			Integer ganancia = Integer.parseInt(partes[2]);
			return new Tarea(start,duration,ganancia);
		}
		
		public final Integer start;
		public final Integer duration;
		public final Integer ganancia;
		
		public Tarea(Integer start, Integer duration, Integer ganancia) {
			super();
			this.start = start;
			this.duration = duration;
			this.ganancia = ganancia;
		}
		
		public static Integer solape(Integer i, Integer j) {
			return Math.max(Tarea.tareas.get(i).start+Tarea.tareas.get(i).duration-Tarea.tareas.get(j).start, 0);
		}

		@Override
		public String toString() {
			return String.format("(%d,%d,%d,%d)",start,start+duration,duration,ganancia);
		}		
	}
	
	public static Integer ganancia(Integer i) {
		return Tarea.tareas.get(i).ganancia;
	}
	
	public static Boolean solape(Integer i, Integer j) {
		return Tarea.solape(i, j) > 0;
	}
	
	public static Integer getN() {
		return Tarea.n;
	}
	
	

	public static void tareas_model() throws IOException {
		Tarea.tareas = Tarea.datos("data/tareas.txt");
		String ss = Stream2.enumerate(Tarea.tareas.stream())
				.map(e -> String.format("%d == %s", e.counter(), e.value()))
				.collect(Collectors.joining("\n"));
		Tarea.n = Tarea.tareas.size();
		AuxGrammar.generate(TareasPLI.class,"modelos/tareas.lsi","ficheros/tareas.lp");
		Locale.setDefault(Locale.of("en", "US"));
		GurobiSolution solution = GurobiLp.gurobi("ficheros/tareas.lp");
		System.out.println(solution.toString((s, d) -> d > 0.));
		System.out.println(ss);
	}

	public static void main(String[] args) throws IOException {
		tareas_model();
	}

}
