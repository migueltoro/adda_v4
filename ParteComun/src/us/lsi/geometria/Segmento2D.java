package us.lsi.geometria;

public record Segmento2D(Punto2D p1, Punto2D p2) implements ObjetoGeometrico2D {
	
	public static Segmento2D of(Punto2D p1, Punto2D p2) {
		return new Segmento2D(p1, p2);
	}

	public Vector2D vector() {
		return Vector2D.of(this.p1, this.p2);
	}

	public Double longitud() {
		return p1.distanciaA(p2);
	}

	@Override
	public Segmento2D rota(Punto2D p, Double angulo) {
		return Segmento2D.of(this.p1.rota(p, angulo), this.p2.rota(p, angulo));
	}

	@Override
	public Segmento2D traslada(Vector2D v) {
		return Segmento2D.of(this.p1.traslada(v), this.p2.traslada(v));
	}

	@Override
	public Segmento2D homotecia(Punto2D p, Double factor) {
		return Segmento2D.of(this.p1.homotecia(p, factor), this.p2.homotecia(p, factor));
	}

	@Override
	public Segmento2D proyectaSobre(Recta2D r) {
		return Segmento2D.of(this.p1.proyectaSobre(r), this.p2.proyectaSobre(r));
	}

	@Override
	public Segmento2D simetrico(Recta2D r) {
		return Segmento2D.of(this.p1.simetrico(r), this.p2.simetrico(r));
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)", this.p1, this.p2);
	}

}
