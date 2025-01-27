package us.lsi.graphics;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.PowerLog;

public class CanvasTest {
	
	public static void test1() {
		Random r = new Random();
		Canvas c = Canvas.of("Ajuste","a*n^b*(ln n)^c + d");
		List<WeightedObservedPoint> points = DataFile.points("ficheros/datos_polinomiallog.txt");
		List<Double> lx = points.stream().map(x->x.getX()).toList();
		double[] p = {1.00,2.50,2.00,10.00};
		List<Double> ajuste = lx.stream().map(x->PowerLog.of().value(x,p)).toList();
		List<Double> datos = points.stream().map(x->x.getY()+300000*r.nextDouble()-150000).map(x->x>0?x:0).toList();	
		c.drawData("Datos", Color.RED, lx, datos, true);
		c.drawData("Ajuste", Color.BLUE, lx, ajuste, false);
	}
	
	public static void test2() {
		double[] p = {1.00,2.50,2.00,10.00};
		CanvasPlot.show("ficheros/datos_polinomiallog.txt",x->PowerLog.of().value(x,p), "a*n^b*(ln n)^c + d");
	}

	public static void main(String[] args) {
		Canvas.of(null, null);
	}

}
