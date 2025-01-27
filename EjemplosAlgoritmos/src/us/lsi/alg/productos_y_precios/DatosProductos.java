package us.lsi.alg.productos_y_precios;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.IntegerSet;
import us.lsi.common.String2;
import us.lsi.streams.Collectors2;

public class DatosProductos {
	
	public static Integer NUM_PRODUCTOS, NUM_FUNCIONES;
	public static List<String> todasFunciones;
	public static Set<String> funcionesDS;
	public static IntegerSet funcionesD;
	
	private static List<Producto> productos;
	private static Map<String, Integer> map_fObjetivo;

	public static void iniDatos(String fichero) {
		List<String> ls = Files2.linesFromFile(fichero);

		DatosProductos.funcionesDS = Arrays.stream(ls.remove(0).split(":")[1].split(",")).map(s -> s.trim())
				.collect(Collectors.toSet());
		
		List<ProductoL> productosL = ls.stream()
				.map(s -> ProductoL.parse(s))
				.collect(Collectors.toList());
		
		DatosProductos.todasFunciones = productosL.stream()
				.flatMap(p->p.funciones().stream())
				.distinct().toList();
		
		DatosProductos.funcionesD = DatosProductos.indices(DatosProductos.funcionesDS);
		
		DatosProductos.productos = productosL.stream()
				.map(pl->Producto.of(pl))
				.sorted(Comparator.naturalOrder())
				.toList();
		
		DatosProductos.NUM_PRODUCTOS = DatosProductos.productos.size();
		DatosProductos.NUM_FUNCIONES = DatosProductos.todasFunciones.size();
				
		DatosProductos.map_fObjetivo = new HashMap<>();
		Integer id = 0;
		for(String f: funcionesDS) {
			map_fObjetivo.put(f, id++);
		}	
	}
	
	public static IntegerSet indices(Set<String> ss) {
		return ss.stream().map(s->DatosProductos.todasFunciones.indexOf(s))
				.collect(Collectors2.toIntegerSet());
	}
	
	public static Set<String> nombres(IntegerSet ss) {
		return ss.stream().map(i->DatosProductos.todasFunciones.get(i))
				.collect(Collectors.toSet());
	}
	
	public static List<Producto> productos() {
		return DatosProductos.productos;
	}

	public static Producto producto(Integer index) {
		return DatosProductos.productos.get(index);
	}	
	
	public static IntegerSet funcionesDeseadas() {
		return DatosProductos.funcionesD;
	}
	
	public static IntegerSet funciones(Integer index) {
		return DatosProductos.productos.get(index).funciones();
	}

	public static Map<String, Integer> fObj() {
		return DatosProductos.map_fObjetivo;
	}		
	
	private static String mem;
	public static void toConsole() {
		String2.toConsole("Funcionalidades a cubrir: "+funcionesDS);
		mem = "Numero de productos: "+NUM_PRODUCTOS;
		productos.forEach(a->mem += ("\n"+a.toString()));
		String2.toConsole(mem);
	}	
	
	// Test de la lectura del fichero
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		iniDatos("ficheros/productos_y_precios/productos2.txt");
		toConsole();
		String2.toConsole(DatosProductos.producto(0).toString());
		String2.toConsole(DatosProductos.NUM_PRODUCTOS.toString());
	}	
}

