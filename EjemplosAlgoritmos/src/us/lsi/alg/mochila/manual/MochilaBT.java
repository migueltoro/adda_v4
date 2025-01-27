package us.lsi.alg.mochila.manual;


import java.util.List;
import java.util.Locale;

import us.lsi.alg.mochila.MochilaVertexI;
import us.lsi.alg.mochila.SolucionMochila;
import us.lsi.mochila.datos.DatosMochila;

public class MochilaBT {
	
	public static MochilaBT of() {
		return new MochilaBT();
	}
	
	private MochilaVertexI start;
	private StateMochila estado;
	private SolucionMochila solucion;
	public Integer maxValue;
	private Long time;
	
	private MochilaBT() {
		super();
	}

	public void btm(Integer capacidadInicial, Integer maxValue, SolucionMochila s) {
		this.time = System.nanoTime();
		this.solucion = s;
		this.maxValue = maxValue;
		this.start = MochilaVertexI.of(0,capacidadInicial);
		this.estado = StateMochila.of(start);
		btm();
		this.time = System.nanoTime() - this.time;
	}
	
	public Long time() {
		return time;
	}
	
	public SolucionMochila solucion() {
		return solucion;
	}

	public void btm() {
		if(this.estado.vertice().index() == DatosMochila.n) {
			Integer value = estado.valorAcumulado();
			if(this.maxValue == null || value > this.maxValue) {
				this.maxValue = value;
				this.solucion = SolucionMochila.of(this.estado.acciones());
			}
		} else {
			List<Integer> alternativas = this.estado.vertice().actions();
			for(Integer a:alternativas) {	
				Double weight = this.estado.valorAcumulado()+Heuristica.cota(this.estado.vertice(),a);
				if(this.maxValue != null && weight <= this.maxValue) continue;
				this.estado.forward(a);
				btm();  
				this.estado.back(a);
			}
		}
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMochila.iniDatos("ficheros/mochila/objetosMochila.txt");
		DatosMochila.capacidadInicial = 78;
		MochilaVertexI v1 = MochilaVertexI.of(0, DatosMochila.capacidadInicial);
		SolucionMochila s = Heuristica.solucionVoraz(v1);
		MochilaBT bt = MochilaBT.of();
		bt.btm(DatosMochila.capacidadInicial,null,null);	
		System.out.println("1 = "+bt.time());
		bt.btm(DatosMochila.capacidadInicial,s.valor(),s);
		System.out.println("2 = "+bt.time());
		System.out.println(bt.solucion());
	}

	


}
