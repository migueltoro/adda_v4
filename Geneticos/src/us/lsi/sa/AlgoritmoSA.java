package us.lsi.sa;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.random.JDKRandomGenerator;
import us.lsi.math.Math2;



	/**
	 * <p> Implementación del Algoritmo de Simulated Annealing. Un problema que se quiera resolver por este algortimo
	 * debe implementar el interface ProblemaSA &lt; E,S,A &gt; </p>
	 * 
	 * <p> Para usar esta técnica hay que considerar un conjunto de estado y unas alternativas para pasar de unos a otros.
	 * El estado que minimice el objetivo debe ser alcanzable desde el estado inicial a través de una secuencia de 
	 * alternativas. </p>
	 * 
	 * <p>La documentación puede encontarse en el: <a href="../../../document/Tema16.pdf" target="_blank">Tema16</a></p>
	 * 
	 * <p>Un resumen de la a documentación puede encontarse en el: <a href="../../../document/SimulatedAnn.html" target="_blank">Tema16</a></p>
	 * 
	 * @author Miguel Toro
	 *
	 */
public class AlgoritmoSA {

	/**
	 *
	 * @param estado El estado del cromosoma
	 * @return AlgoritmoSA
	 */

	public static AlgoritmoSA of(StateSa estado) {
			return new AlgoritmoSA(estado);
	}

	/**
	 * Conjunto de soluciones encontradas
	 */
	public Set<StateSa> soluciones;
	/**
	 * Mejor solución encontrada
	 */
	public StateSa mejorSolucionEncontrada = null;
	/**
	 * Número de intentos. En cada intento se parte del estado incial y se llevan a
	 * cabo un número de iteraciones por intento. En cada iteración se llevan a cabo
	 * un número de iteraciones sin disminuir la temperatura.
	 */
	public static Integer numeroDeIntentos = 10;
	/**
	 * El número iteraciones por intento. Los designaremos por n.
	 */
	public static Integer numeroDeIteracionesPorIntento = 300;
	/**
	 * El número iteraciones a la misma temperatura. Lo designaremos por m.
	 */
	public static Integer numeroDeIteracionesALaMismaTemperatura = 10;
	/**
	 * La temperatura fijada inicialmente. Lo designaremos por t0.
	 */
	public static double temperaturaInicial = 1000;
	
	public static double alfa = 0.99;

	private double temperatura;
	private StateSa estado;
	private StateSa nextEstado;

	
	private AlgoritmoSA(StateSa estado) {
		this.estado = estado;
		this.mejorSolucionEncontrada = estado;
		this.soluciones = new HashSet<>();
		JDKRandomGenerator random = new JDKRandomGenerator();
		random.setSeed((int) System.currentTimeMillis());
		Math2.rnd = random;
	}
	
	public Double averageIncrement(int n) {
		Double s = 0.;
		Integer r = 0;
		Double f = this.estado.fitness();
		for(int i=0; i<n;i++) {
			StateSa e = this.estado.random();
			if(e.fitness() > f) {
				r++;
				s = e.fitness()-f;
			}
		}
		return s/r;
	}

	/**
	 * Ejecución del algoritmo
	 */
	public void ejecuta() {
		this.mejorSolucionEncontrada = this.estado.random();
		for (Integer n = 0; n < numeroDeIntentos; n++) {
			this.temperatura = temperaturaInicial;
			this.estado = this.estado.random();
			for (int numeroDeIteraciones = 0;
				     numeroDeIteraciones < numeroDeIteracionesPorIntento; numeroDeIteraciones++) {
				for (int s = 0; s < numeroDeIteracionesALaMismaTemperatura; s++) {
					this.nextEstado = this.estado.mutate();
					double incr = nextEstado.fitness() - estado.fitness();
					if (aceptaCambio(incr)) {
						estado = nextEstado;
						actualizaMejorValor();
					}
				}
				this.temperatura = nexTemperatura(numeroDeIteraciones);
			}
			soluciones.add(this.estado);
		}
	}

	private void actualizaMejorValor() {
		if (estado.fitness() < mejorSolucionEncontrada.fitness()) {
			mejorSolucionEncontrada = estado;
		}
	}

	private double nexTemperatura(int numeroDeIteraciones) {
		return alfa * temperatura;
	}

	private boolean aceptaCambio(double incr) {
		return Math2.aceptaBoltzmann(incr, temperatura);
	}

}
