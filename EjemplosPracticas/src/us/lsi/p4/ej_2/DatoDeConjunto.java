package us.lsi.p4.ej_2;


import java.util.Set;
import us.lsi.common.Set2;

public record DatoDeConjunto(String nombre,Double peso, Set<Integer> conjunto) {
	
	public static DatoDeConjunto parse(String s) {
		String[] tokens = s.split("[=:]");
		return new DatoDeConjunto(
				tokens[0],
				Double.parseDouble(tokens[2].trim()), 
				Set2.parse(tokens[1].trim(), " ,{}",Integer::parseInt));
	}
	
	public Double pesoUnitario() {
		return this.peso()/this.conjunto().size();
	}
}

