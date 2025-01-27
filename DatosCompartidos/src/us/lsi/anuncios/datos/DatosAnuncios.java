package us.lsi.anuncios.datos;

import java.util.*;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.IntPair;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.common.Set2;
import us.lsi.streams.Stream2;



public class DatosAnuncios {

	public static List<Anuncio> todosLosAnunciosDisponibles;
	public static Integer tiempoTotal;
	public static Set<IntPair> restricciones;
	public static Set<Integer> todosLosAnuncios; 
	
	public DatosAnuncios() {
		super();
	}
	
	public static void leeYOrdenaAnuncios(String file){	
		List<String> ls = Files2.streamFromFile(file)
				.collect(Collectors.toList());
		int index = ls.indexOf("#");
		List<String> ls1 = ls.subList(0, index);
		List<String> ls2 = ls.subList(index+1, ls.size());
		todosLosAnunciosDisponibles = List2.empty();
		Anuncio a;
		for(String s : ls1){
			String[] at = Stream2.split(s, ",").<String>toArray((int x)->new String[x]);
			Preconditions.checkArgument(at.length==3);
			a = Anuncio.create(at);
			todosLosAnunciosDisponibles.add(a);
		}
		restricciones = new HashSet<>();
		for(String s : ls2){
			String[] at = Stream2.split(s, ",").<String>toArray((int e)->new String[e]);
			Preconditions.checkArgument(at.length==2);
			Integer n1 = Integer.parseInt(at[0]);
			Integer n2 = Integer.parseInt(at[1]);
			restricciones.add(IntPair.of(n1, n2));
			restricciones.add(IntPair.of(n2, n1));
		}
		Collections.sort(DatosAnuncios.todosLosAnunciosDisponibles, Comparator.<Anuncio>naturalOrder().reversed());
		todosLosAnuncios = Set2.range(0, DatosAnuncios.todosLosAnunciosDisponibles.size());
	}

	public static DatosAnuncios create() {		
		return new DatosAnuncios();
	}

	public static Anuncio getAnuncio(int i){
		return todosLosAnunciosDisponibles.get(i);
	}
	
}
