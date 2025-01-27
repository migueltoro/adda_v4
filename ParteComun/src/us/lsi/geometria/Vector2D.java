package us.lsi.geometria;

import us.lsi.common.Preconditions;

public record Vector2D(Double x,Double y) {

	public static Vector2D baseX() {
		return new Vector2D(1., 0.);
	}
	
	public static Vector2D baseY() {
		return new Vector2D(0., 1.);
	}
	
	public static Vector2D of(Double x, Double y) {
		return new Vector2D(x, y);
	}

	public static Vector2D of(Punto2D p1, Punto2D p2) {
		return p2.minus(p1);
	}
	
	public static Vector2D copy(Vector2D p) {
		return new Vector2D(p.x(), p.y());
	}
	
	public static Vector2D ofGrados(Double modulo, Double angulo){
		Preconditions.checkArgument(modulo > 0, String.format("El m�dulo debe ser mayor o igual a cero y es %.2f",modulo));
		return ofRadianes(modulo, Math.toRadians(angulo));
	}
	
	public static Vector2D ofRadianes(Double modulo, Double angulo){
		Preconditions.checkArgument(modulo >= 0, String.format("El m�dulo debe ser mayor o igual a cero y es %.2f",modulo));
		return of(modulo*Math.cos(angulo),modulo*Math.sin(angulo));		
	}
	
	public Double modulo() {
		return Math.abs(Math.hypot(x, y));
	}

	public Double angulo() {
		return Math.atan2(y, x);
	}
	
	public Double anguloEnGrados() {
		return Math.toDegrees(this.angulo());
	}

	public Double angulo(Vector2D v) {
		return Math.asin(this.multiplicaVectorial(v)/(this.modulo()*v.modulo()));
	}
	
	public Double anguloEnGrados(Vector2D v) {
		return Math.toDegrees(angulo(v));
	}
	
	public Vector2D proyectaSobre(Vector2D v){
		Vector2D u = v.unitario();
		return u.multiply(this.multiplicaEscalar(u));
	}	
	
	public Punto2D punto() {
		return Punto2D.of(this.x, this.y);
	}
	
	public Vector2D ortogonal() {
		return Vector2D.of(-this.y, this.x);
	}
	
	public Vector2D unitario() {
		return Vector2D.ofRadianes(1.,this.angulo());
	}
	
	public Vector2D opuesto() {
		return Vector2D.of(-x, -y);
	}
	
	public Vector2D add(Vector2D v) {
		return Vector2D.of(this.x+v.x,this.y+v.y);
	}
	
	public Vector2D minus(Vector2D v) {
		return Vector2D.of(this.x-v.x,this.y-v.y);
	}
	
	public Vector2D rota(Double angulo) {
		return Vector2D.ofRadianes(this.modulo(),this.angulo()+angulo);
	}
		
	public Vector2D multiply(Double factor) {
		return Vector2D.of(this.x*factor,this.y*factor);
	}
	
	public Double multiplicaVectorial(Vector2D v) {
		return this.x()*v.y()-this.y()*v.x();
	}
	
	public Double multiplicaEscalar(Vector2D v) {
		return this.x()*v.x()+this.y()*v.y();
	}
	
	
	@Override
	public String toString() {
		return String.format("(%.2f,%.2f)",this.x, this.y);
	}
	
}
