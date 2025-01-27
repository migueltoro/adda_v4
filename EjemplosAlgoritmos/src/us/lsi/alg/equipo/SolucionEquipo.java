package us.lsi.alg.equipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SolucionEquipo implements Comparable<SolucionEquipo>{
	
	public static SolucionEquipo create(List<Integer> ls) {
		return new SolucionEquipo(ls);
	}
	

	private Double rnd_eqp;
	private SortedMap<Integer,List<String>> plantel;
	
	
	private SolucionEquipo(List<Integer> ls) {
		double num = 0;
		rnd_eqp = 0.;
		plantel = new TreeMap<>();
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i)>=0) {
				num++;
				rnd_eqp += DatosEquipo.getR(i, ls.get(i));
				List<String> seccion = plantel.get(ls.get(i));
				if(seccion!=null) {
					seccion.add(DatosEquipo.getJugador(i).getNombre());
				} else {
					seccion = new ArrayList<>();
					seccion.add(DatosEquipo.getJugador(i).getNombre());
					plantel.put(ls.get(i), seccion);
				}
			}
		}
		rnd_eqp /= num;
	}

	
	@Override
	public String toString() {
		String msg = "Alineación obtenida:\n";
		for (Map.Entry<Integer,List<String>> g: plantel.entrySet()) {
			msg += "Sección "+(g.getKey()+1)+": "+g.getValue()+"\n";
		}
		return msg+String.format("Rendimiento medio: %.2f", rnd_eqp);
	}

	@Override
	public int compareTo(SolucionEquipo other) {
		return this.rnd_eqp.compareTo(other.rnd_eqp);
	}


}
