package us.lsi.geometria;

public record Semiplano2D(Recta2D r, Punto2D s) {

	public static Semiplano2D of(Recta2D r, Punto2D s) {
		return new Semiplano2D(r,s);
	}
	
	public Boolean contains(Punto2D p) {
		return null;
	}
	
	public Boolean contains(Poligono2D a) {
		return null;
	}
	
	public Poligono2D intersecta(Poligono2D a) {
		return null;
	}

		
}
