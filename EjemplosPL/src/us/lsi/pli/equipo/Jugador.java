package us.lsi.pli.equipo;

import java.util.List;

import us.lsi.common.List2;

public class Jugador {

	public static Jugador create(String s) {
		String[] tokens = s.split(":");
		return new Jugador(tokens[0].trim(),
				List2.parse(tokens[1].trim().split(","), Double::parseDouble));
	}
	
	private final String nombre;
	private final List<Double> rendimiento;
	
	private Jugador(String nombre, List<Double> ls) {
		this.nombre = nombre;
		rendimiento = ls;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Double> getRendimiento() {
		return rendimiento;
	}		
	
	public Double getRendimiento(int posicion) {
		return rendimiento.get(posicion);
	}	
	
	@Override
	public String toString() {		
		return nombre;
	}
}
