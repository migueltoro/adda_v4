package us.lsi.alg.mochila;


import java.util.Collections;
import java.util.List;
import java.util.stream.*;

import org.jgrapht.GraphPath;

import us.lsi.mochila.datos.DatosMochila;

public record MochilaVertexI(Integer index, Integer capacidadRestante)
          implements MochilaVertex {

	public static MochilaVertexI initialVertex() {
		return of(0, capacidadInicial);
	}
	
	public static MochilaVertex copy(MochilaVertexI m) {
		return of(m.index, m.capacidadRestante);
	}
	
	public static MochilaVertexI of(int index, Integer capacidadRestante) {
		return new MochilaVertexI(index, capacidadRestante);
	}
	
	public static MochilaVertex lastVertex() {
		return new MochilaVertexI(n, 0);
	}
	
	@Override
	public Boolean goal() {
		return this.index == MochilaVertexI.n;
	}
	
	@Override
	public Boolean goalHasSolution() {
		return true;
	}

	public static Integer n = DatosMochila.numeroDeObjetos;
	public static Integer capacidadInicial;

	public Integer capacidadInicial() {
		return capacidadInicial;
	}
	
	public static SolucionMochila getSolucion(GraphPath<MochilaVertex, MochilaEdge> path){
		return SolucionMochila.of(path);
	}

	@Override
	public Boolean isValid() {
		return index>=0 && index<=DatosMochila.getObjetos().size();
	}
	
	@Override
	public Integer greedyAction() {
		return Math.min(this.capacidadRestante/DatosMochila.getPeso(index),DatosMochila.getNumMaxDeUnidades(index));
	}
	
	public static Double heuristicAction(Integer index, Double cr) {
		return Math.min(cr/DatosMochila.getPeso(index),DatosMochila.getNumMaxDeUnidades(index));
	}

	@Override
	public List<Integer> actions() {
		if(this.index == n) return List.of();
		Integer nu = greedyAction().intValue();
		if(this.index == n-1) return List.of(nu);
		List<Integer> alternativas = IntStream.rangeClosed(0,nu)
				.boxed()
				.collect(Collectors.toList());
		Collections.reverse(alternativas);
		return alternativas;
	}
	
	@Override
	public MochilaVertexI neighbor(Integer a) {
		MochilaVertexI r;
		Integer cr = capacidadRestante - a * DatosMochila.getPeso(index);
		if (this.index == MochilaVertexI.n - 1 || this.capacidadRestante == 0.) 
			r = MochilaVertexI.of(MochilaVertexI.n, cr);
		else r = MochilaVertexI.of(index + 1, cr);
		return r;
	}

	@Override
	public MochilaEdge edge(Integer a) {
		MochilaVertex v = this.neighbor(a);
		return MochilaEdge.of(this,v,a);
	}
	
}

