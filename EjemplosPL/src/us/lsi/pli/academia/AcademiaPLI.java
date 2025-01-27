package us.lsi.pli.academia;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import us.lsi.common.Files2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class AcademiaPLI {

	
	public record Alumno(Integer codigo, String nombre, List<Integer> afinidades) {
		
		public static Alumno of(String s) {
			String[] contenido = s.split(": ");
			String nombre = contenido[0];
			Integer codigo = Integer.parseInt(nombre.split("_")[1]);
			String[] numeros = contenido[1].split(",");
			List<Integer> afinidades = new ArrayList<>();
			for (String numero : numeros) {
				afinidades.add(Integer.parseInt(numero));
			}	
			return new Alumno(codigo,nombre, afinidades);
		}
	}
		
	public class DatosAlumno {

		public static List<Alumno> alumnos;

		public static void iniDatos(String fichero) {
			DatosAlumno.alumnos = Files2.streamFromFile(fichero).map(s -> Alumno.of(s)).toList();
		}

		public static Integer afinidad(Integer i, Integer j) {
			return DatosAlumno.alumnos.get(i).afinidades.get(j);
		}

		public static Integer getNumeroGrupos() { 
			return DatosAlumno.alumnos.get(0).afinidades.size();
		}

		public static Integer getNumeroAlumnos() {
			return DatosAlumno.alumnos.size();
		}

	}

	public static void academia() throws IOException {		
		DatosAlumno.iniDatos("data/academia.txt");
		System.out.println("Afinidad (0, 0): "+DatosAlumno.afinidad(0,0));
		System.out.println("Número de alumnos: "+DatosAlumno.getNumeroAlumnos());
		System.out.println("Número de Grupos: "+DatosAlumno.getNumeroGrupos());
	   
		AuxGrammar.generate(DatosAlumno.class, "modelos/academia.lsi", "ficheros/academia.lp");
		
		Locale.setDefault(Locale.of("en", "US"));
		GurobiSolution solution = GurobiLp.gurobi("ficheros/academia.lp");
		System.out.println(solution.toString((s, d) -> d > 0.));
	}
	
	public static void academia2 () throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DatosAlumno.iniDatos("data/academia.txt");
		Class<DatosAlumno> c = DatosAlumno.class;
		Method m = c.getMethod("getNumeroAlumnos", new Class<?>[0]);
		System.out.println(m.invoke(null, new Object[0]));
		Class<?>[] pt = {Integer.class,Integer.class};
		Method m2 = c.getMethod("afinidad",pt);
		System.out.println(m2.invoke(null,Arrays.asList(0,0).toArray()));
	}
	
	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException   {
		academia();
	}

	
}

