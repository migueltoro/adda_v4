package us.lsi.flowgraph.examples;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class ProjectsData {
	
	private static Integer n;
	private static Integer m;
	private static Map<Integer,Integer> beneficios;
	private static Map<Integer,Integer> costes;
	private static Map<Integer,List<Integer>> necesita;
	
	public static void data(String file) {
		List<String> lineas = Files2.linesFromFile(file);
		Integer k1 = lineas.indexOf("#tools");
		Integer k2 = lineas.indexOf("#dependencies");
		List<String> proyectos = lineas.subList(1,k1);
		List<String> tools = lineas.subList(k1+1,k2);
		List<String> dependencies = lineas.subList(k2+1,lineas.size());
		ProjectsData.n = proyectos.size();
		ProjectsData.m = tools.size();
		ProjectsData.beneficios = proyectos.stream().map(ln->ln.split(" "))
				.collect(Collectors.toMap(e->Integer.parseInt(e[0]), e->Integer.parseInt(e[1])));
		ProjectsData.costes = tools.stream().map(ln->ln.split(" "))
				.collect(Collectors.toMap(e->Integer.parseInt(e[0]), e->Integer.parseInt(e[1])));
		ProjectsData.necesita = dependencies.stream().map(ln->ln.split(" "))
				.collect(Collectors.toMap(e->Integer.parseInt(e[0]), 
						e->Arrays.stream(e).skip(1).map(x->Integer.parseInt(x)).collect(Collectors.toList())));
		System.out.println(beneficios);
		System.out.println(costes);
		System.out.println(necesita);
	}
	
	
	
	
	public static Integer getN() {
		return ProjectsData.n;
	}
	
	public static Integer getM() {
		return ProjectsData.m;
	}
	
	public static Integer beneficio(Integer i) {
		return ProjectsData.beneficios.get(i);
	}
	
	public static Integer coste(Integer j) {
		return ProjectsData.costes.get(j);
	}
	
	public static Boolean necesita(Integer i, Integer j) {
		return ProjectsData.necesita.get(i).contains(j);
	}
	
	public static void proyects() throws IOException {
		data("data/projects.txt");
		Locale.setDefault(Locale.of("en", "US"));
		AuxGrammar.generate(ProjectsData.class,"models/projects.lsi","ficheros/proyects.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/proyects.lp");
		System.out.println(solution.toString((s,d)-> d>0.));
	}
	
	public static void main(String[] args) throws IOException  {	
		proyects();
	}

}
