package us.lsi.alg.investigadores.manual;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import us.lsi.alg.investigadores.DatosInv;
import us.lsi.alg.investigadores.InvVertex;
import us.lsi.alg.investigadores.SolucionInv;

public class BTInv {

	private StateInv estado;
	private Integer maxValue = null;
	private SolucionInv solucion = null;
	private Long time;
	
	public static BTInv of() {
		return new BTInv();
	}
	
	public SolucionInv solucion() {
		return this.solucion;
	}
	
	public Long time() {
		return time;
	}

	private BTInv() {
	}
	
	public void btm(Integer maxValue, SolucionInv s) {
		this.time = System.nanoTime();
		this.solucion = s;
		this.maxValue = maxValue;
		this.estado = StateInv.of(InvVertex.first());
		btm();
		this.time = System.nanoTime() - this.time;
	}
	
	private void btm() {
		if(this.estado.vertice().index() == DatosInv.na && IntStream.range(0, DatosInv.m).boxed()
				.allMatch(t -> this.estado.vertice().esTrabajoAcabado(t) || 
						!this.estado.vertice().esTrabajoIniciado(t))) {
			Integer value = estado.valorAcumulado();
			if(this.maxValue == null || value > this.maxValue) {
				this.maxValue = value;
				this.solucion = this.estado.solucion();
			}
		} else {
			List<Integer> alternativas = this.estado.vertice().actions();
			for(Integer a:alternativas) {	
				Integer cota = this.estado.vertice().neighbor(a).fo();
				if(this.maxValue != null && cota <= this.maxValue) continue;
				this.estado.forward(a);
				btm();  
				this.estado.back(a);
			}
		}
	}
	
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosInv.iniDatos("ficheros/investigadores/inv3.txt");
		
		BTInv bt = BTInv.of();
		
		Integer vr = GreedyInv.valoVoraz(InvVertex.first());
		SolucionInv sv = GreedyInv.solucionVoraz(InvVertex.first());
		System.out.println(sv);
		bt.btm(vr,sv);
		System.out.println("2 = "+bt.time());
		System.out.println("BT 2 = "+ bt.solucion());
//		bt.btm(null,null);
//		System.out.println("1 = "+bt.time());
//		System.out.println("BT 1 = "+ bt.solucion());

	}


}
