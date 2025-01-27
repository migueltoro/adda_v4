package us.lsi.curvefitting;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.filetools.FileTools;
import us.lsi.streams.Stream2;

public class DataFile {

	public static WeightedObservedPoint parse(String line) {
		String[] p = line.split(",");
		return new WeightedObservedPoint(1.,Double.parseDouble(p[0]),Double.parseDouble(p[1]));
	}

	public static List<WeightedObservedPoint> points(String file){
		Stream<String> s = Stream2.file(file);
		List<WeightedObservedPoint> points = s.map(ln->parse(ln)).toList();
		return points;
	}
	
	public static Boolean predicate(List<WeightedObservedPoint> ls, Integer x, Double err){
		if(x<=2 || ls.size()-x <=2) return false;
		else {
			Double[] p = {ls.get(x-2).getY(),ls.get(x-1).getY(), ls.get(x+1).getY(),ls.get(x+2).getY()};
			Double m = Arrays.stream(p).mapToDouble(y->y).average().getAsDouble();
			if(Math.abs(ls.get(x).getY()-m)/m > err) return false;
			else return true;
		}	
	}
	
	public static List<WeightedObservedPoint> smoothPoints(String file, Double err){
		List<WeightedObservedPoint> points = DataFile.points(file);
		List<WeightedObservedPoint> r = IntStream.range(0, points.size())
				.filter(x->predicate(points,x,err))
				.mapToObj(i->points.get(i))
				.toList();
		return r;
	}
	
	public static void saveData(String file, Map<Integer, Double> tiempos){
		Locale.setDefault(Locale.of("en", "US"));
		List<Integer> keys = tiempos.keySet().stream().sorted().toList();
		String txt = keys.stream()
				.map(x->String.format("%d,%f",x,tiempos.get(x)))
				.collect(Collectors.joining("\n"));
		FileTools.write(file, txt);
	}
}
