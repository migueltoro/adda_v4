package us.lsi.alg.productos_y_componentes;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;

public class DatosProductos {
	
	public static record Producto(String nombre, Double precio, Map<String,Integer> componentes, Integer max,
			Integer tp, Integer te) {
		public static Producto create(String s) {
			String[] v0 = s.split("->");
			String[] v1 = v0[1].trim().split(";");
			Double p = Double.parseDouble(v1[0].split("=")[1].trim());
			
			Map<String,Integer> cmp = Map2.empty();
			String[] v2 = v1[1].split("=")[1].trim().split(",");
			for(String e: v2) {
				String[] v3 = e.trim().split("[:()]");
				cmp.put(v3[1].trim(), Integer.parseInt(v3[2].trim()));
			}
			Integer c = Integer.parseInt(v1[2].split("=")[1].trim());
			Integer tp = 0;
			for (String nm: cmp.keySet()) {
				Componente cm = DatosProductos.componentes.get(nm);
				tp += cmp.get(nm)*cm.tp();
			}
			Integer te = 0;
			for (String nm: cmp.keySet()) {
				Componente cm = DatosProductos.componentes.get(nm);
				te += cmp.get(nm)*cm.te();
			}
			return new Producto(v0[0].trim(),p,cmp, c,tp,te);
		}		
		@Override
		public String toString() {		
			return String.format("%s;%.2f;%s;%d",nombre,precio,componentes,max);
		}		
	}

	public static record Componente(String nombre, Integer tp, Integer te) {
		public static Componente create(String s) {
			String[] v0 = s.split(":");
			String[] v1 = v0[1].split(";");
			String a = v1[0].split("=")[1].trim();
			String b = v1[1].split("=")[1].trim();
			return new Componente(v0[0].trim(), Integer.parseInt(a), Integer.parseInt(b));
		}	
		@Override
		public String toString() {		
			return nombre+": "+tp+"; "+te;
		}		
	}	
	
	public static Integer Tp, Te;
	public static Map<String,Componente> componentes;
	public static List<Producto> productos;
	
	public static void iniDatos(String fichero) {		
		componentes = Map2.empty();
		productos = List2.empty();
		
		List<String> ls = Files2.linesFromFile(fichero);
		
		Tp = Integer.parseInt(ls.remove(0).split("=")[1].trim());
		Te = Integer.parseInt(ls.remove(0).split("=")[1].trim());
		for(String s: ls) {
			if(s.startsWith("//"))
				continue;
			else if(s.startsWith("C")) {
				Componente c = Componente.create(s);
				componentes.put(c.nombre(), c);
			}else if(s.startsWith("P"))
				productos.add(Producto.create(s));
		}
		Comparator<Producto> cmp = 
				Comparator.comparing(p->beneficioInt(p,DatosProductos.Tp, DatosProductos.Te));
		Collections.sort(DatosProductos.productos,cmp.reversed());
		toConsole();
	}
	
	public static Integer getNumProductos() {
		return productos.size();
	}
	public static Integer getNumComponentes() {
		return componentes.size();
	}
	public static Double getPrecio(Integer i) {
		return productos.get(i).precio();
	}
	public static Integer getMaxUnidades(Integer i) {
		return productos.get(i).max();
	}
	public static Integer getTotalProd() {
		return Tp;
	}
	public static Integer getTotalElab() {
		return Te;
	}	
	public static Producto getProducto(Integer i) {
		return productos.get(i);
	}
	
	public static Integer maxActionInt(Producto p,Integer tpR,Integer teR) {
		return List.of(p.max(),
				tpR/p.tp(),
				teR/p.te()).stream()
				.min(Comparator.naturalOrder())
				.get();		
	}
	
	public static Integer beneficioInt(Producto p,Integer tpR, Integer teR) {
		return (int)(maxActionInt(p,tpR,teR)*p.precio());	
	}
	
	public static Double maxActionDouble(Producto p, Double tpR, Double teR) {
		return List.of((double)p.max(),
				tpR/p.tp(),
				teR/p.te()).stream()
				.min(Comparator.naturalOrder())
				.get();		
	}
	
	public static Double beneficioDouble(Producto p,Double tpR, Double teR) {
		return maxActionDouble(p,tpR,teR)*p.precio();	
	}
	
	public static void toConsole() {
		String2.toConsole(IntStream.range(0, DatosProductos.getNumProductos()).boxed()
				.map(i -> productos.get(i) + "; "
						+ DatosProductos.beneficioInt(DatosProductos.getProducto(i), DatosProductos.Tp, DatosProductos.Te))
				.toList(), "Productos");
		String2.toConsole(componentes.values(), "Componentes");
		String2.toConsole("Tp=%d\nTe=%d\n%s", Tp, Te, String2.linea());
	}
	
	// Test de la lectura del fichero
	public static void main(String[] args) throws IOException {
		iniDatos("ficheros/PI5Ej3DatosEntrada1.txt");
	}
	
}
