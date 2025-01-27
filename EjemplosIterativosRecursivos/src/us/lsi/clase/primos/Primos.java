package us.lsi.clase.primos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import us.lsi.common.Pair;
import us.lsi.iterables.Iterables;
import us.lsi.math.Math2;
import us.lsi.streams.Stream2;

public class Primos {
	
	public static List<Pair<Long,Long>> primosPar(Long m, Long n, Integer k) {
		Stream<Long> r = Stream.iterate(Math2.siguientePrimo(m - 1), x -> x < n, x -> Math2.siguientePrimo(x));
		List<Pair<Long,Long>> r2 = 
				Stream2.consecutivePairs(r)
				.filter(t -> t.second() - t.first() == k)
				.toList();
		return r2;
	}


	public static List<Pair<Long,Long>> primosPar2(Long m, Long n, Integer k) {
		List<Pair<Long,Long>> r = new ArrayList<>();
		Long e1 = null;
		Long e2 = Math2.siguientePrimo(m - 1);		
		while(e2<n) {
			Pair<Long,Long> p = Pair.of(e1, e2);
			e1 = e2;
			e2 = Math2.siguientePrimo(e2);
			if(p.first() != null && p.second() - p.first() == k) {
				r.add(p);
			}			
		}
		return r;
	}
	
	public static Integer sumaPrimos1(String file) {
		Iterator<String> fileIt = Iterables.file(file).iterator();
		Integer suma = 0;
		while(fileIt.hasNext()){
			String linea = fileIt.next();
			Iterator<String> lineaIt = Iterables.split(linea,"[ ,]").iterator();
			while(lineaIt.hasNext()){
	     		String p = lineaIt.next();
				Integer e = Integer.parseInt(p);
				if(Math2.esPrimo(e)){
	 				suma = suma + e;
				}
	 		}
		}
	 	return suma;
	}

	
	public static Integer sumaPrimos2(String file) {
		Iterable<String> fileIt = Iterables.file(file);
		Integer suma = 0;
		for(String linea: fileIt) {		
			for(String e: Iterables.split(linea, "[ ,]")) {
				Integer en = Integer.parseInt(e);
				if (Math2.esPrimo(en)) {
					suma = suma + en;
				}
			}
		}
		return suma;
	}

	public static Integer sumaPrimos3(String file) {
		Iterable<String> fileIt = Iterables.file(file);
		Iterable<String> ff = Iterables.flatMap(fileIt,linea->Iterables.split(linea,"[ ,]"));
		return sumaPrimos3(ff.iterator(),0);
	}
	private static Integer sumaPrimos3(Iterator<String> ff, Integer b) {
		if(ff.hasNext()) {
			String p = ff.next();
			Integer en = Integer.parseInt(p);
			if (Math2.esPrimo(en)) {
				b = b + en;
			}
			b = sumaPrimos3(ff,b);
		}
		return b;
	}

	
	public static void test2() {
		System.out.println(primosPar(30L,100L,2));
		System.out.println(primosPar2(30L,100L,2));
	}

}
