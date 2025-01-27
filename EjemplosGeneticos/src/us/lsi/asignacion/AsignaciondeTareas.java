package us.lsi.asignacion;


import java.util.List;

import us.lsi.common.Files2;

/**
 * @author Miguel Toro
 * 
 *         Resuelve le problema de la asignación de tareas
 *
 */
public class AsignaciondeTareas {

	private Integer n;
	private Integer m;
	private Double[][] costes;

	public static AsignaciondeTareas create(String f) {
		return new AsignaciondeTareas(f);
	}

	public Integer getN() {
		return n;
	}

	public Integer getM() {
		return m;
	}

	public Double getCoste(int i, int j) {
		return costes[i][j];
	}

	private AsignaciondeTareas(String f) {
		super();
		this.leeFichero(f);
	}

	private void leeFichero(String f) {
		List<String> lineas = Files2.linesFromFile(f);
		this.n = Integer.parseInt(lineas.get(0));
		this.m = Integer.parseInt(lineas.get(1));
		this.costes = new Double[n][m];
		String[] dat;
		Integer i, j;
		for (int k = 2; k < lineas.size(); k++) {
			dat = lineas.get(k).split(",");
			i = Integer.parseInt(dat[0].trim());
			j = Integer.parseInt(dat[1].trim());
			costes[i][j] = Double.parseDouble(dat[2].trim());
		}
	}

	
}
