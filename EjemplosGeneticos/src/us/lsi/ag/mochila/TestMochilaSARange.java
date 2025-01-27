package us.lsi.ag.mochila;



import java.util.List;
import java.util.Locale;

import us.lsi.ag.ChromosomeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.String2;
import us.lsi.mochila.datos.DatosMochila;
import us.lsi.mochila.datos.SolucionMochila;
import us.lsi.sa.AlgoritmoSA;
import us.lsi.sa.StateSaChromosome;

public class TestMochilaSARange {
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoSA.alfa = 0.9999;
		AlgoritmoSA.numeroDeIteracionesPorIntento = 100000;
		AlgoritmoSA.numeroDeIntentos = 30;
		AlgoritmoSA.numeroDeIteracionesALaMismaTemperatura = 1;
		AlgoritmoSA.temperaturaInicial = 627651.60;
		
		DatosMochila.capacidadInicial = 78;
		
		ChromosomeData<List<Integer>, SolucionMochila> p = new DatosMochilaAGRange("ficheros/objetosmochila.txt");
		StateSaChromosome c = StateSaChromosome.random(p,ChromosomeType.Range);
		AlgoritmoSA a = AlgoritmoSA.of(c);
		String2.toConsole("%.2f",a.averageIncrement(20));
		a.ejecuta();		
		StateSaChromosome s = (StateSaChromosome) a.mejorSolucionEncontrada;
		List<Integer> d = (List<Integer>) s.decode();
		System.out.println(p.solucion(d));
	}

}
