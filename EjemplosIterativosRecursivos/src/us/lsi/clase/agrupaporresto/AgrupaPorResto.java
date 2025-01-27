package us.lsi.clase.agrupaporresto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.iterables.Iterables;
import us.lsi.streams.Stream2;

public class AgrupaPorResto {
	
	public static Map<Integer,List<Integer>> agrupaPorResto1(String file,Integer n) {
		return Stream2.file(file)
				.filter(ln->!ln.isEmpty())
				.flatMap(e->Stream2.split(e,"[ ,]"))
				.map(e->Integer.parseInt(e))
				.collect(Collectors.groupingBy(e->e%n));
	}
	
	public static Map<Integer, List<Integer>> agrupaPorResto2(String file, Integer n) {
		Iterable<String> fileIt = Iterables.file(file);
		Map<Integer, List<Integer>> grupos = new HashMap<>();
		for(String linea:fileIt) {
			for(String e:Iterables.split(linea, "[ ,]")) {
				Integer en = Integer.parseInt(e);
				Integer key = en % n;
				List<Integer> ls;
				if (grupos.containsKey(key)) {
					ls = grupos.get(key);
				} else {
					ls = new ArrayList<>();
					grupos.put(key, ls);
				}
				ls.add(en);
			}
		}
		return grupos;
	}
	
	public static Map<Integer, List<Integer>> agrupaPorResto3(String file, Integer n) {
		Iterator<String> fileIt = Iterables.file(file).iterator();
		Map<Integer, List<Integer>> grupos = new HashMap<>();
		while(fileIt.hasNext()) {
			String linea = fileIt.next();
			Iterator<String> lineaIt = Iterables.split(linea, "[ ,]").iterator();
			while(lineaIt.hasNext()) {
				String e = lineaIt.next();
				Integer en = Integer.parseInt(e);
				Integer key = en % n;
				List<Integer> ls;
				if (grupos.containsKey(key)) {
					ls = grupos.get(key);
				} else {
					ls = new ArrayList<>();
					grupos.put(key, ls);
				}
				ls.add(en);
			}
		}
		return grupos;
	}
	
	public static Map<Integer, List<Integer>> agrupaPorResto4(String file, Integer n) {
		Iterator<String> fileIt = Iterables.file(file).iterator();
		String linea = fileIt.next();
		Iterator<String> lineaIt = Iterables.split(linea, "[ ,]").iterator();
		Map<Integer, List<Integer>> grupos = new HashMap<>();
		Map<Integer, List<Integer>> r = agrupaPorResto4(file, n, fileIt,lineaIt, grupos);
		return r;
	}
	
	public static Map<Integer, List<Integer>> agrupaPorResto4(String file, Integer n, Iterator<String> fileIt,
			Iterator<String> lineaIt, Map<Integer, List<Integer>> grupos) {
		Map<Integer, List<Integer>> r = grupos;
		if (fileIt.hasNext() || lineaIt.hasNext()) {
			if (lineaIt.hasNext()) {
				String e = lineaIt.next();
				Integer en = Integer.parseInt(e);
				Integer key = en % n;
				List<Integer> ls;
				if (grupos.containsKey(key)) {
					ls = grupos.get(key);
				} else {
					ls = new ArrayList<>();
					grupos.put(key, ls);
				}
				ls.add(en);
			} else {
				String linea = fileIt.next();
				lineaIt = Iterables.split(linea, "[ ,]").iterator();
			}
			r = agrupaPorResto4(file, n, fileIt, lineaIt, grupos);
		}
		return r;
	}
	
	public static void test5() throws IOException {
		var r1 = agrupaPorResto1("ficheros/numeros2.txt",5);
		var r2 = agrupaPorResto2("ficheros/numeros2.txt",5);
		var r3 = agrupaPorResto3("ficheros/numeros2.txt",5);
		var r4 = agrupaPorResto4("ficheros/numeros2.txt",5);
		System.out.println("4 "+r1);
		System.out.println("5 "+r2);
		System.out.println("6 "+r3);
		System.out.println("7 "+r4);
		
	}

}
