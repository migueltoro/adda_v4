package us.lsi.grafos.datos;

public record Ciudad(String nombre, Integer habitantes)  {

	public static Ciudad ofFormat(String[] formato) {
		String nombre = formato[0];
		Integer habitantes = Integer.parseInt(formato[1]);
		return new Ciudad(nombre,habitantes);
	}
	
	public static Ciudad of(String nombre, Integer habitantes) {
		return new Ciudad(nombre,habitantes);
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
