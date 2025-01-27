package us.lsi.alg.bufete;

import java.util.List;

import us.lsi.common.List2;

public record Abogado(String nombre, List<Integer> horas) {
	
	public static Abogado create(String s) {
		String[] tokens = s.split(":");
		return new Abogado(tokens[0].trim(),
				List2.parse(tokens[1].trim().split(","), Integer::parseInt));
	}
	
	public Integer horasCaso(int index) {
		return horas().get(index);
	}	
	

}
