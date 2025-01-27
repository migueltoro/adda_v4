package us.lsi.p3.ej_3;

import java.util.List;
import java.util.stream.Collectors;


import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosAlumnos {
	public static record Alumno(String nombre, List<Integer> afinidades) {
		public static Alumno create(String s) {
			String[] v = s.split(":");
			return new Alumno(v[0].trim(), List2.parse(v[1].trim().split(","), Integer::parseInt));
		}
		
		public Integer getAfinidad(int index) {
			return afinidades.get(index);
		}	
		
		@Override
		public String toString() {		
			return String.format("%s: %s", nombre, afinidades);
		}		
	}
	
	private static List<Alumno> alumnos; 
	
	public static void iniDatos(String fichero) {
		alumnos = Files2.streamFromFile(fichero).map(Alumno::create).toList();

		toConsole();
	}
	
	public static Integer getNumAlumnos() {
		return alumnos.size();
	}
	public static Integer getNumGrupos() {
		return alumnos.get(0).afinidades().size();
	}
	public static Integer getTamGrupo() {
		return getNumAlumnos()/getNumGrupos();
	}
	public static Integer getAfinidad(Integer i, Integer j) {
		return alumnos.get(i).getAfinidad(j);
	}
	
	public static Alumno getAlumno(int index) {
		return alumnos.get(index);
	}
	
	public static List<Alumno> getAlumnos(){
		return alumnos;
	}
	

	public static void toConsole() {
		String s = alumnos.stream().map(Alumno::toString)
		.collect(Collectors.joining("\n", String.format("N� de grupos: %d\n", getNumGrupos()), "\n"));
		String2.toConsole("%s%s", s, String2.linea());
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros/p3/ejemplo3_3.txt");		
	}
	
}

