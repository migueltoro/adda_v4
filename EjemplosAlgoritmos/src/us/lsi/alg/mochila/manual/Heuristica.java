package us.lsi.alg.mochila.manual;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphWalk;

import us.lsi.alg.mochila.MochilaEdge;
import us.lsi.alg.mochila.MochilaVertex;
import us.lsi.alg.mochila.SolucionMochila;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.mochila.datos.DatosMochila;

public class Heuristica {

	public static Integer valorVoraz(MochilaVertex v1) {
		MochilaVertex v = v1;
		Integer r = 0;
		while (!v.goal()) {
			Integer a = v.greedyAction();
			r = r + a * DatosMochila.getValor(v.index());
			v = v.neighbor(a);
		}
		return r;
	}
	
	public static GraphPath<MochilaVertex,MochilaEdge> caminoVoraz(EGraph<MochilaVertex,MochilaEdge> g, MochilaVertex v1) {
		MochilaVertex v = v1;
		Integer r = 0;
		List<MochilaVertex> vertices = new ArrayList<>(List.of(v));
		while (!v.goal()) {
			Integer a = v.greedyAction();
			r = r + a * DatosMochila.getValor(v.index());
			v = v.neighbor(a);
			vertices.add(v);
		}
		return new GraphWalk<>(g,vertices,r);
	}
	
	public static SolucionMochila solucionVoraz(MochilaVertex v1) {
		List<Integer> acciones = new ArrayList<>();
		MochilaVertex v = v1;
		while (v.capacidadRestante() > 0 && v.index() < DatosMochila.n) {
			Integer a = v.greedyAction();
			acciones.add(a);
			v = v.neighbor(a);
		}
		return SolucionMochila.of(acciones);
	}
	
	
	public static Double heuristica(MochilaVertex v1) {
		Integer index = v1.index();
		Double cr = v1.capacidadRestante().doubleValue();
		Double r = 0.;
		while (cr > 0 && index < DatosMochila.n) {
			Double a = MochilaVertex.heuristicAction(index, cr);
			r = r + a * DatosMochila.getValor(index);
			index = index + 1;
			cr = cr - a * DatosMochila.getPeso(index);
		}
		return r;
	}
	
	public static Integer heuristica2(MochilaVertex v1) {
		MochilaVertex v = v1;
		Integer r = 0;
		while (!v.goal()) {
			Integer a = v.greedyAction()+1;
			r = r + a * DatosMochila.getValor(v.index());
			Integer cr = v.capacidadRestante() - a * DatosMochila.getPeso(v.index());
			v = MochilaVertex.of(v.index() + 1, cr>=0?cr:0);
		}
		return r;
	}
	
	
	public static Double cota(MochilaVertex v1, Integer a) {
		MochilaVertex v2 = v1.neighbor(a);
		return a*DatosMochila.getValor(v1.index()).doubleValue()+heuristica(v2);
	}
	
	public static Double cota2(MochilaVertex v1, Integer a) {
		MochilaVertex v2 = v1.neighbor(a);
		return a*DatosMochila.getValor(v1.index()).doubleValue()+heuristica2(v2);
	}
	
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMochila.iniDatos("ficheros/mochila/objetosMochila.txt");
		DatosMochila.capacidadInicial = 78;
		MochilaVertex v1 = MochilaVertex.of(0, DatosMochila.capacidadInicial);
		Integer r = Heuristica.valorVoraz(v1);
		Double s1 = Heuristica.heuristica(v1);
		Integer s2 = Heuristica.heuristica2(v1);
		SolucionMochila s = Heuristica.solucionVoraz(v1);
		System.out.printf("%d,%.2f,%d\n",r,s1,s2);
		System.out.printf("%s",s);
	}

}

