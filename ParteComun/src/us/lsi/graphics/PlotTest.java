package us.lsi.graphics;

import java.io.IOException;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import us.lsi.curvefitting.PowerLog;

public class PlotTest {

	public static void main(String[] args) throws IOException, PythonExecutionException {
		double[] p = {1.00,2.50,2.00,10.00};
		MatPlotLib.show("ficheros/datos_polinomiallog.txt",x->PowerLog.of().value(x,p), "a*n^b*(ln n)^c + d");
	}

}
