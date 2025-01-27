package us.lsi.afinidad.datos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Cliente {
	
	public static Cliente create(String cl) {
		return new Cliente(cl);
	}
	public static Cliente create(String nombre, int franjaHoraria,
			Set<String> trabajadoresAfines) {
		return new Cliente(nombre, franjaHoraria, trabajadoresAfines);
	}
	public String nombre;
	public int franjaHoraria;
	Set<String> nombresDeTrabajadoresAfines;
	public Set<Integer> trabajadoresAfines;
	
	private Cliente(String nombre, int franjaHoraria, Set<String> trabajadoresAfines) {
		super();
		this.nombre = nombre;
		this.franjaHoraria = franjaHoraria;
		this.nombresDeTrabajadoresAfines = trabajadoresAfines;
		this.trabajadoresAfines = new HashSet<>();
	}
	private Cliente(String cl) {
		//System.out.println(l);
		String[] data= cl.split(",");
		if(data.length!=3) throw new IllegalArgumentException("Incorrecto formato para un Cliente "+cl+". Debería ser nombre,franjaHoraria,trabajadoresAfines.");
		
		this.nombre= data[0];
		this.franjaHoraria= Integer.parseInt(data[1]);
		this.nombresDeTrabajadoresAfines = Arrays.asList(data[2].split(";")).stream().collect(Collectors.toSet());
		this.trabajadoresAfines = new HashSet<>();
	}
	
	public void calculaTrabajadoresAfines(){
		this.nombresDeTrabajadoresAfines.stream()
			.forEach(x->this.trabajadoresAfines.add(DatosAfinidad.trabajadores.indexOf(x)));
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", franjaHoraria=" + franjaHoraria + ", trabajadoresAfines="
				+ trabajadoresAfines + "]";
	}	
}

