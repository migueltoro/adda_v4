package us.lsi.geometria;


public record Recta2D(Punto2D punto, Vector2D vector)  {
	
	public static Recta2D of(Punto2D punto, Vector2D vector) {
		return new Recta2D(punto, vector);
	}
	
	public static Recta2D of(Punto2D p1, Punto2D p2) {
		return new Recta2D(p1, p2.minus(p1));
	}
	
	@Override
	public String toString() {
		return String.format("(%.2f,%.2f)",this.punto, this.vector).toString();
	}

}
