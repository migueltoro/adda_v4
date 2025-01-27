package us.lsi.pli.reparto;

import java.util.List;
public class SolucionReparto implements Comparable<SolucionReparto>{

	public static SolucionReparto of(List<Integer> ls, Double dist, Double ben) {
		return new SolucionReparto(ls, dist, ben);	
	}
	
	private List<Integer> path;
	private Double kms, beneficio;

	private SolucionReparto(List<Integer> ls, Double dist, Double ben) {
		path = ls;
		kms = dist;
		beneficio = ben;
	}
	
	@Override
	public String toString() {
		return String.format("Kms: %.1f\nBeneficio: %.1f\nPath de la solucion: %s", kms, beneficio, path);
	}

	@Override
	public int compareTo(SolucionReparto sol) {
		return sol.beneficio.compareTo(beneficio);
	}
	
}
