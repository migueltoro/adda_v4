package us.lsi.jarras.datos;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.IntPair;


public class DatosJarras {
	
	public static Integer cantidadFinalEnJarra1;
	public static Integer cantidadFinalEnJarra2;
	public static Integer cantidadInicialEnJarra1;
	public static Integer cantidadInicialEnJarra2;
	public static Integer capacidadJarra1;
	public static Integer capacidadJarra2;
	public static Integer numMaxAcciones;

	private static List<Operacion> acciones = null;
	
	public static List<Operacion> getAcciones(){
		return acciones;
	}
	
	public static Operacion getAccion(int index){
		return acciones.get(index);
	}
	
	public static void iniDatos(String file){
		if(acciones==null) {
			acciones = Files2.streamFromFile(file)
				.map((String s) -> Operacion.create(s))
				.collect(Collectors.toList());
			
			DatosJarras.acciones.get(0).aplicable = (x1,x2)->x1>0 ;
			DatosJarras.acciones.get(1).aplicable = (x1,x2)->x1>0 ;
			DatosJarras.acciones.get(2).aplicable = (x1,x2)->x1>0 ;
			DatosJarras.acciones.get(3).aplicable = (x1,x2)->x1 < capacidadJarra1 ;
			DatosJarras.acciones.get(4).aplicable = (x1,x2)->x2 < capacidadJarra1 ;
			DatosJarras.acciones.get(5).aplicable = (x1,x2)->x2>0 ;
			DatosJarras.acciones.get(6).aplicable = (x1,x2)->x2>0 ;
			DatosJarras.acciones.get(7).aplicable = (x1,x2)->x2>0 ;
			
			DatosJarras.acciones.get(0).actualiza = (x1,x2)-> IntPair.of(0, x2);
			DatosJarras.acciones.get(1).actualiza = (x1,x2)-> IntPair.of(0, Math.min(x1+x2,capacidadJarra2)) ;
			DatosJarras.acciones.get(2).actualiza = (x1,x2)-> 
			IntPair.of(Math.max(0,x1+x2-capacidadJarra2), Math.min(x1+x2,capacidadJarra2));
			DatosJarras.acciones.get(3).actualiza = (x1,x2)-> IntPair.of(capacidadJarra1, x2);
			DatosJarras.acciones.get(4).actualiza = (x1,x2)-> IntPair.of(x1,capacidadJarra2);
			DatosJarras.acciones.get(5).actualiza = (x1,x2)-> IntPair.of(x1, 0);;
			DatosJarras.acciones.get(6).actualiza = (x1,x2)-> IntPair.of(Math.min(x1+x2,capacidadJarra1),0) ; 
			DatosJarras.acciones.get(7).actualiza = (x1,x2)->  
			IntPair.of(Math.min(x1+x2,capacidadJarra1), Math.max(0,x1+x2-capacidadJarra1));		
		
		}
	}
	
	public static class Operacion {
		
		private static Integer n = 0;
		private Integer id;
		private String descripcionCorta;
		private String descripcion;
		private BiPredicate<Integer,Integer> aplicable;
		private BiFunction<Integer,Integer,IntPair> actualiza;
		
		private static Operacion create(String s) {
			return new Operacion(s);
		}
		
		private Operacion(String s){
			String[] v = s.split(",");
			Integer ne = v.length;
			if(ne != 3) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
			this.id = n;
			n++;
			this.descripcionCorta = v[1];
			this.descripcion = v[2];		
		}
		
		public int getId() {
			return this.id;
		}

		public String getDescripcionCorta() {
			return descripcionCorta;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public Boolean isAplicable(Integer j1, Integer j2) {
			return aplicable.test(j1, j2);
		}
		
		public IntPair result(Integer j1, Integer j2) {
			return actualiza.apply(j1, j2);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Operacion))
				return false;
			Operacion other = (Operacion) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "(id=" + id + "," + descripcionCorta + ")";
		}
	}
	
}
