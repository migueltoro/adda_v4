package us.lsi.graphics;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import us.lsi.common.Preconditions;
import us.lsi.curvefitting.DataFile;

public class MatPlotLib {
	
	public static void show(String dataFile, Function<Double, Double> f, String title) {
		Preconditions.checkNotNull(f);
		List<WeightedObservedPoint> points = DataFile.points(dataFile);
		List<Double> lx = points.stream().map(p -> p.getX()).toList();
		List<Double> ajuste = points.stream().map(p -> f.apply(p.getX())).toList();
		List<Double> datos = points.stream().map(p -> p.getY()).toList();
		Plot plt = Plot.create();

		plt.plot().add(lx, ajuste).label("ajuste").linestyle("--");

		plt.plot().add(lx, datos).label("datos").linestyle(":").linewidth(3.5);

		plt.title(title);
		plt.legend();
		plt.xlabel("tamaño");
		plt.ylabel("tiempo");
		try {
			plt.show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PythonExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void showCombined(String header, List<String> files, List<String> labels) {

		Plot plt = Plot.create();
		plt.title(header);
		for (int i = 0; i < files.size(); i++) {
			String label = labels.get(i);
			String file = files.get(i);
			List<WeightedObservedPoint> points = DataFile.points(file);
			List<Double> lx = points.stream().map(p -> p.getX()).toList();
			List<Double> datos = points.stream().map(p -> p.getY()).toList();
			plt.plot().add(lx, datos).label(label).linestyle("-");
		}

		plt.legend();
		plt.xlabel("tamano");
		plt.ylabel("tiempo");
		try {
			plt.show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PythonExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void showCombined(String header, List<String> files, List<Function<Double, Double>> functions, List<String> labels) {

		Plot plt = Plot.create();
		plt.title(header);
		for (int i = 0; i < files.size(); i++) {
			String label = labels.get(i);
			String file = files.get(i);
			Function<Double,Double> f = functions.get(i);
			List<WeightedObservedPoint> points = DataFile.points(file);
			List<Double> lx = points.stream().map(p -> p.getX()).toList();
			List<Double> ajuste = points.stream().map(p -> f.apply(p.getX())).toList();
			plt.plot().add(lx, ajuste).label(label).linestyle("-");
		}

		plt.legend();
		plt.xlabel("tamano");
		plt.ylabel("tiempo");
		try {
			plt.show();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PythonExecutionException e) {
			e.printStackTrace();
		}
	}
}
