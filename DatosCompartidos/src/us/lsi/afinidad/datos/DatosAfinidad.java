package us.lsi.afinidad.datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;



public class DatosAfinidad {
	
	public static List<String> trabajadores;
	public static List<Cliente> clientes;	
	
	private DatosAfinidad() {
			
	}	
	/**
	 * Define un problemaAfinidad de ejemplo con 8 clientes y 3 trabajadores
	 * @return Un problema de ejemplo
	 */
	public static DatosAfinidad createEjemplo(){
		clientes= List2.of(
				Cliente.create("Juan", 10, Set2.of("Amparo", "Rosa")),
				Cliente.create("Maria", 10, Set2.of("Rosa")),
				Cliente.create("Sara", 11, Set2.of("Amparo", "Rosa")),
				Cliente.create("Andres", 11, Set2.of("Marco", "Rosa")),
				Cliente.create("Antonio", 11,Set2.of("Marco")),
				Cliente.create("Sonia", 12, Set2.of("Marco")),
				Cliente.create("Marta", 12, Set2.of("Marco")),
				Cliente.create("Ivan", 12, Set2.of("Amparo"))			
				);
		Set<String> trab= new HashSet<>();	
		clientes.stream().forEach(x-> trab.addAll(x.nombresDeTrabajadoresAfines));
		trabajadores = new ArrayList<>(trab);//Elimina repetidos
		clientes.stream().forEach(x->x.calculaTrabajadoresAfines());
		return  new DatosAfinidad();
	}
	
	/**
	 * Define un ProblemaAfinidad cogiendo los datos de un fichero de texto.
	 * 
	 * Cada línea del fichero de texto tendrá la estructura:
	 * nombreCliente, franjaHoraria, trabajadororesAfines
	 * 
	 * Por su lado, trabajadoresAfines serán los nombres de los trabajadores separados por ;
	 * 
	 * @param file El archivo para leer los datos
	 * @return El problema creado
	 */
	public static DatosAfinidad create(String file){
		Set<String> trab= new HashSet<>();			
		clientes=new ArrayList<>();
		
		Files2.streamFromFile(file)
			.map(x-> x.replace(" ","")) //quitar espacios en blanco
			.peek(x -> clientes.add(Cliente.create(x))) //crear clientes
			.forEach(x-> trab.addAll(Arrays.asList(x.split(",")[2].split(";")))); //crear trabajadores
			
		trabajadores = new ArrayList<>(trab);//Elimina repetidos		
		clientes.stream().forEach(x->x.calculaTrabajadoresAfines());
		return new DatosAfinidad();
	}
}

