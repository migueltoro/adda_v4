package us.lsi.graphics;

import java.awt.Color;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.curvefitting.DataFile;

public class CanvasPlot {
	
	public static void show(String dataFile, Function<Double, Double> f, String title) {
		List<WeightedObservedPoint> points = DataFile.points(dataFile);
		List<Double> lx = points.stream().map(p -> p.getX()).toList();
		List<Double> ajuste = points.stream().map(p -> f.apply(p.getX())).toList();
		List<Double> datos = points.stream().map(p -> p.getY()).toList();
		Canvas canvas = Canvas.of("Ajuste",title);
		canvas.axes(lx,datos);
		canvas.drawData("Datos", Color.RED, lx, datos, true);
		canvas.drawData("Ajuste", Color.BLUE, lx, ajuste, false);
    	
    }
	
	public static Color[] colores = {Color.RED, Color.BLUE,Color.BLACK,Color.CYAN,Color.MAGENTA,Color.PINK, Color.ORANGE};
	
	public static void showCombined(String header, List<String> files, List<String> labels) {
		Canvas canvas = Canvas.of("Comparacion", header);
		
		for (int i = 0; i < files.size(); i++) {
			String label = labels.get(i);
			String file = files.get(i);
			List<WeightedObservedPoint> points = DataFile.points(file);
			List<Double> lx = points.stream().map(p -> p.getX()).toList();
			List<Double> datos = points.stream().map(p -> p.getY()).toList();
			if(i==0) canvas.axes(lx,datos);
			canvas.drawData(label,colores[i], lx, datos,true);
		}
	}

}
