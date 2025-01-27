package us.lsi.bufete.datos;

import java.util.List;

import us.lsi.common.List2;

public class Abogado {

	public static Abogado parse(String s) {
		String[] tokens = s.split(":");
		return new Abogado(tokens[0].trim(),
				List2.parse(tokens[1].trim().split(","), Integer::parseInt));
	}
	
	private final String nombre;
	private final List<Integer> horas;
	
	private Abogado(String nombre, List<Integer> ls) {
		this.nombre = nombre;
		horas = ls;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Integer> getHoras() {
		return horas;
	}
	
	public Integer getHoras(int index) {
		return horas.get(index);
	}	
	
	@Override
	public String toString() {		
		return nombre+" -> "+horas;
//		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abogado other = (Abogado) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}

