package us.lsi.geometria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Preconditions;


public record Poligono2D(List<Punto2D> vertices) implements ObjetoGeometrico2D {

	public static Poligono2D empty() {
		return new Poligono2D(new ArrayList<>());
	}

	public static Poligono2D triangulo(Punto2D p1, Punto2D p2, Punto2D p3) {
		return new Poligono2D(List.of(p1, p2, p3));
	}

	public static Poligono2D trianguloEquilatero(Punto2D p1, Vector2D lado) {
		var p2 = p1.add(lado);
		var p3 = p1.add(lado.rota(Math.PI / 3));
		return new Poligono2D(List.of(p1,p2,p3));
	}

	public static Poligono2D cuadrado(Punto2D p, Vector2D lado) {
		var p2 = p.add(lado);
		var p3 = p.add(lado).add(lado.ortogonal());
		var p4 = p.add(lado.ortogonal());
		return new Poligono2D(List.of(p,p2,p3,p4));
	}

	public static Poligono2D rectangulo(Punto2D p, Vector2D base, Double altura) {
		 var p2 = p.add(base);
		 var p3 = p.add(base).add(base.ortogonal().multiply(altura));
		 var p4 = p.add(base.ortogonal().multiply(altura));
		return new Poligono2D(List.of(p,p2,p3,p4));
	}

	public static Poligono2D ofPuntos(Punto2D... lp) {
		return new Poligono2D(Arrays.asList(lp));
	}

	public static Poligono2D ofPuntos(List<Punto2D> lp) {
		return new Poligono2D(lp);
	}

	public static Poligono2D rectanguloHorizontal(Punto2D p, Double base, Double altura) {
		var p2 = p.add(Vector2D.of(base, 0.));
		var p3 = p.add(Vector2D.of(base, altura));
		var p4 = p.add(Vector2D.of(0., altura));
		return new Poligono2D(List.of(p,p2,p3,p4));
	}

	public static Poligono2D rectanguloHorizontal(Double xMin, Double xMax, Double yMin, Double yMax) {
		var p1 = Punto2D.of(xMin, yMin);
		var p2 = Punto2D.of(xMax, yMin);
		var p3 = Punto2D.of(xMax, yMax);
		var p4 = Punto2D.of(xMin, yMax);
		return new Poligono2D(List.of(p1,p2,p3,p4));
	}

	public Double area() {
		Integer n = this.numeroDeVertices();
		Double area = IntStream.range(1, n - 1)
				.mapToDouble(i -> this.diagonal(0, i).multiplicaVectorial(this.lado(i))).sum();
		return area / 2;
	}
	
	public Double perimetro() {
		Integer n = this.numeroDeVertices();
		Double ln = IntStream.range(0, n)
				.mapToDouble(i -> this.lado(i).modulo()).sum();
		return ln;
	}

	public int numeroDeVertices() {
		return vertices.size();
	}

	
	public Punto2D vertice(Integer i) {
		Integer n = this.numeroDeVertices();
		Preconditions.checkElementIndex(i, n);
		return vertices.get(i);
	}

	public Vector2D lado(Integer i) {
		Integer n = this.numeroDeVertices();
		Preconditions.checkElementIndex(i, n);
		return Vector2D.of(this.vertices.get(i), this.vertices.get((i + 1) % n));
	}

	public Vector2D diagonal(Integer i, Integer j) {
		Integer n = this.numeroDeVertices();
		Preconditions.checkElementIndex(i, n);
		Preconditions.checkElementIndex(j, n);
		return Vector2D.of(this.vertices.get(i), this.vertices.get(j));
	}

	public Poligono2D rota(Punto2D p, Double angulo) {
		return Poligono2D.ofPuntos(this.vertices.stream().map(x -> x.rota(p, angulo)).collect(Collectors.toList()));
	}

	public Poligono2D traslada(Vector2D v) {
		return Poligono2D.ofPuntos(this.vertices.stream().map(p -> p.traslada(v)).collect(Collectors.toList()));
	}

	@Override
	public Poligono2D homotecia(Punto2D p, Double factor) {
		return Poligono2D
				.ofPuntos(this.vertices.stream().map(x -> x.homotecia(p, factor)).collect(Collectors.toList()));
	}

	@Override
	public Poligono2D proyectaSobre(Recta2D r) {
		return Poligono2D.ofPuntos(this.vertices.stream().map(x -> x.proyectaSobre(r)).collect(Collectors.toList()));
	}

	@Override
	public Poligono2D simetrico(Recta2D r) {
		return Poligono2D.ofPuntos(this.vertices.stream().map(x -> x.simetrico(r)).collect(Collectors.toList()));
	}

	public void show(Poligono2D s) {

	}

	@Override
	public String toString() {
		return this.vertices.stream().map(p -> p.toString()).collect(Collectors.joining(",", "(", ")"));
	}
}
