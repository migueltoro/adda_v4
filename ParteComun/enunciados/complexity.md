
# Complexity

Cálculo empírico de la complejidad de un algoritmo

Se compone de tres partes: Generación de datos, ajuste de curvas y representación gráfica.

## Generación de datos

- La clase _GenData_ genera los ficheros de tiempos de ejecución asociados a tamaños. Tiene dos métodos:

- Function\<Integer,Long\> time(Consumer\<Integer\> algorithm): devuelve una función que asocia un tamaño al tiempo tardado en ejecutar el consumer algorithm.

- Function\<Integer,Long\> time(Consumer\<Integer\> preparation, Consumer\<Integer\> algorithm): devuelve una función que asocia un tamaño al tiempo tardado en preparar los datos y posteriormente ejecutar el consumer algorithm.

- tiemposEjecucion(Function\<Integer,Long\> algorithm, String ficheroTiempos, Integer tMin, Integer tMax, Integer tInc, Integer numIter, Integer numIterWarmup): Este método genera un fichero de datos _ficheroTiempos_ con datos de tamaños que van de _tMin_ a _tMax_ con incrementos de _tInc_. Se establecen un número de iteraciones para obtener la media del tiempo de ejecución para cada tamaño. El entero _numIterWarmup_ establece, para cada tamaño, un número de ejecuciones previas a las medidas necesarias para calcular la media.

## Ajuste de curvas

Se ofrecen tres familias de curvas para ajustar a ellas un nube de puntos leídos de un fichero:

- La clase Polynomial ajusta $a_n*x^n + ... + a_0$ siendo $n$ el grado el polinomio. 
	
- La clase Exponential ajusta $a*b^n$ a una nuble de puntos
	
- La clase PowerLog ajusta $a*n^b*(ln n)^c + d$ a una nube de puntos. Esta clase tiene métodos de factoría que permiten especficar valorés concretos para alguno d elos parámetros o indicar el rango de valores permitido para otros.

Cada una de las clases anteriores implementa si son necesaios los métodos del interface:

```java
public interface Fit {
	double[] gradient(double n, double... p);
	double value(double n, double... p);
	double[] fit(List<WeightedObservedPoint> points);
	Evaluation getEvaluation();
	String getExpression();
	Function<Double, Double> getFunction();
	void print(double n, double... p);
}
```

El tipo Evaluation de Apache contiene los métodos:

```java
public interface Evaluation {
	RealMatrix getCovariances(double threshold);
	RealVector getSigma(double covarianceSingularityThreshold);
	double getRMS();
	RealMatrix getJacobian();
	double getCost();
	RealVector getResiduals();
	RealVector getPoint();
}
```

## Representación gráfica

Dispone de dos clases:

- La clase _MatPlotLib_ representa un ajuste con el método: 
	- show(String dataFile, Function\<Double, Double\> f, String title). Esta clase necesita tener Pyhton instalado
- La clase _CanvasPlot_ representa un ajuste con el método: 
	- show(String dataFile, Function\<Double, Double\> f, String title). Esta clase no necesita tener Pyhton instalado aunque los resultados son de menor calidad que con el método anterior