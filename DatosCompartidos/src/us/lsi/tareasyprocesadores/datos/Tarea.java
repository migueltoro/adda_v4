package us.lsi.tareasyprocesadores.datos;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;



public class Tarea {

	public static List<Tarea> tareas;
	public static Integer n;
	
	public static void leeTareas(String fichero){
		nId =0;
		tareas = Files2.streamFromFile(fichero)
				.map(s ->Tarea.create(s))
				.sorted(Comparator.<Tarea,Double>comparing(Tarea::getDuracion).reversed())
				.collect(Collectors.toList());
		n = tareas.size();
	}
	
	public static Tarea create(String s) {
		return new Tarea(s);
	}

	public static Tarea create(Double duracion) {
		return new Tarea(duracion);
	}
	private static Integer nId;
	private Double duracion;
	private Integer id;
	private Tarea(Double duracion) {
		super();
		this.duracion = duracion;
		this.id = nId;
		nId++;
	}
	private Tarea(String s) {
		super();
		this.duracion = Double.parseDouble(s.trim());
		this.id = nId;
		nId++;
	}
	public Double getDuracion() {
		return duracion;
	}
	
	public static Double getDuracion(int i) {
//		if(i >= Tarea.n) return 0.;
		return Tarea.tareas.get(i).getDuracion();
	}
	
	public Integer getId() {
		return id;
	}
	
	public static Tarea getTarea(int i) {
		return Tarea.tareas.get(i);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tarea))
			return false;
		Tarea other = (Tarea) obj;
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "("+id + "," + duracion + ")";
	}
	
	

}
