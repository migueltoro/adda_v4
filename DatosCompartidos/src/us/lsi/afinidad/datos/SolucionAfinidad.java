package us.lsi.afinidad.datos;

import java.util.HashMap;
import java.util.Map;

public class SolucionAfinidad {

	public static SolucionAfinidad create(Map<String, String> sol,
			Integer afinidadAcum) {
		return new SolucionAfinidad(sol, afinidadAcum);
	}


	private Map<String, String> sol;
	private Integer afinidadAcum;
	
	
	private SolucionAfinidad(Map<String, String> sol, Integer afinidadAcum) {
		super();
		this.sol = new HashMap<>(sol);;
		this.afinidadAcum = afinidadAcum;
	}


	public Map<String, String> getAsignacion() {
		return sol;
	}


	public Integer getAfinidad() {
		return afinidadAcum;
	}

	
	public Double getObjetivo() {
		return (double) this.afinidadAcum;
	}	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((afinidadAcum == null) ? 0 : afinidadAcum.hashCode());
		result = prime * result + ((sol == null) ? 0 : sol.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SolucionAfinidad))
			return false;
		SolucionAfinidad other = (SolucionAfinidad) obj;
		if (afinidadAcum == null) {
			if (other.afinidadAcum != null)
				return false;
		} else if (!afinidadAcum.equals(other.afinidadAcum))
			return false;
		if (sol == null) {
			if (other.sol != null)
				return false;
		} else if (!sol.equals(other.sol))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Afinidad = "+afinidadAcum +", Asignacion =" + sol;
	}	

}
