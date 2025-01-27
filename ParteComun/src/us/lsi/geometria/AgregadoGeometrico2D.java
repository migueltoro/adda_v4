package us.lsi.geometria;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AgregadoGeometrico2D implements ObjetoGeometrico2D {

	public static AgregadoGeometrico2D empty() {
		return new AgregadoGeometrico2D();
	}
	
	public static AgregadoGeometrico2D of(ObjetoGeometrico2D... objetos) {
		return new AgregadoGeometrico2D(Arrays.stream(objetos).collect(Collectors.toSet()));
	}
	
	public static AgregadoGeometrico2D of(Set<ObjetoGeometrico2D> objetos) {
		return new AgregadoGeometrico2D(objetos);
	}

	private Set<ObjetoGeometrico2D> objetos;
	
	private AgregadoGeometrico2D() {
		super();
		this.objetos = new HashSet<>();
	}	

	private AgregadoGeometrico2D(Set<ObjetoGeometrico2D> objetos) {
		super();
		this.objetos = objetos;
	}	
	
	public int size() {
		return objetos.size();
	}

	public boolean isEmpty() {
		return objetos.isEmpty();
	}

	public boolean contains(Object o) {
		return objetos.contains(o);
	}

	public boolean add(ObjetoGeometrico2D e) {
		return objetos.add(e);
	}

	public boolean remove(Object o) {
		return objetos.remove(o);
	}

	public boolean addAll(Collection<? extends ObjetoGeometrico2D> c) {
		return objetos.addAll(c);
	}

	@Override
	public AgregadoGeometrico2D rota(Punto2D p, Double angulo) {
		return new AgregadoGeometrico2D(this.objetos.stream().map(x->x.rota(p,angulo)).collect(Collectors.toSet()));
	}

	@Override
	public AgregadoGeometrico2D traslada(Vector2D v) {
		return new AgregadoGeometrico2D(objetos.stream().map(x->x.traslada(v)).collect(Collectors.toSet()));
	}
	
	@Override
	public AgregadoGeometrico2D homotecia(Punto2D p, Double factor) {
		return AgregadoGeometrico2D.of(this.objetos.stream().map(x->x.homotecia(p, factor)).collect(Collectors.toSet()));
	}
	
	@Override
	public AgregadoGeometrico2D proyectaSobre(Recta2D r) {
		return AgregadoGeometrico2D.of(this.objetos.stream().map(x->x.proyectaSobre(r)).collect(Collectors.toSet()));
	}

	@Override
	public AgregadoGeometrico2D simetrico(Recta2D r) {
		return AgregadoGeometrico2D.of(this.objetos.stream().map(x->x.simetrico(r)).collect(Collectors.toSet()));
	}

}