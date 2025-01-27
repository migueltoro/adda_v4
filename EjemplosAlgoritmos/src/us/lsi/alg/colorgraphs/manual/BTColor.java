package us.lsi.alg.colorgraphs.manual;


import java.util.List;
import java.util.Locale;

import us.lsi.alg.colorgraphs.ColorVertexI;
import us.lsi.alg.colorgraphs.DatosColor;
import us.lsi.alg.colorgraphs.SolucionColor;


public class BTColor {
	
	private StateColor estado;
	private Integer minValue = null;
	private SolucionColor solucion = null;
	private Long time;
	
	public static BTColor of() {
		return new BTColor();
	}
	
	public SolucionColor solucion() {
		return this.solucion;
	}
	
	public Long time() {
		return time;
	}

	private BTColor() {
	}
	
	public void btm(Integer minValue, SolucionColor s) {
		this.time = System.nanoTime();
		this.minValue = minValue;
		this.estado = StateColor.of(ColorVertexI.first());
		btm();
		this.time = System.nanoTime() - this.time;
	}
	
	private void btm() {
		if(this.estado.vertice().index() == DatosColor.n) {
			Integer value = estado.valorAcumulado();
			if(this.minValue == null || value < this.minValue) {
				this.minValue = value;
				this.solucion = this.estado.solucion();
			}
		} else {
			List<Integer> alternativas = this.estado.vertice().actions();
			for(Integer a:alternativas) {	
				Integer cota = this.estado.vertice().neighbor(a).nc();
				if(this.minValue != null && cota >= this.minValue) continue;
				this.estado.forward(a);
				btm();  
				this.estado.back(a);
			}
		}
	}
	
	

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		DatosColor.data(20,"ficheros/andalucia.txt");
		
		BTColor bt = BTColor.of();
		
		Integer vr = GreedyColor.valorVoraz(ColorVertexI.first());
		SolucionColor sv = GreedyColor.solucionVoraz(ColorVertexI.first());
		bt.btm(null,null);
		System.out.println("1 = "+bt.time());
		bt.btm(vr,sv);
		System.out.println("2 = "+bt.time());
		
		System.out.println("BT = "+ bt.solucion());

	}

}
