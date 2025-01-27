package us.lsi.alg.monedas;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatosMonedas {

	public static Integer n;
	public static List<Moneda> monedas;

	public static void datosIniciales(String fichero) {
		Comparator<Moneda> cmp = Comparator.<Moneda, Double>comparing(m -> m.pesoUnitario()).reversed();
		DatosMonedas.monedas = Files2.streamFromFile(fichero).map(ln -> Moneda.parse(ln)).sorted(cmp)
				.collect(Collectors.toList());
		DatosMonedas.n = DatosMonedas.monedas.size();
		DatosMonedas.n=DatosMonedas.monedas.size();
	}

	public static Double getPesoUnitario(Integer i) {
		return DatosMonedas.monedas.get(i).pesoUnitario();
	}

	public static Moneda get(Integer i) {
		return DatosMonedas.monedas.get(i);
	}

	public static Integer valor(Integer i) {
		return DatosMonedas.monedas.get(i).valor();
	}

	public static Integer peso(Integer i) {
		return DatosMonedas.monedas.get(i).peso();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
