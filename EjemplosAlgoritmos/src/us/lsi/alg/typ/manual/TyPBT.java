package us.lsi.alg.typ.manual;

import java.util.List;
import java.util.Locale;

import us.lsi.alg.typ.DatosTyP;
import us.lsi.alg.typ.SolucionTyP;
import us.lsi.alg.typ.TyPVertex;

public class TyPBT {
	
	
	public static TyPBT of() {
		return new TyPBT();
	}
	
	private StateTyP estado;
	private Integer minValue;
	private SolucionTyP solucion;
	private Long time;

	private TyPBT() {
		super();
	}

	public Long time() {
		return time;
	}
	
	public void bt(TyPVertex start, Integer minValue, SolucionTyP s) {
		this.time = System.nanoTime();
		this.estado = StateTyP.of(start);
		this.minValue = minValue;
		this.solucion = s;
		bt();
		this.time = System.nanoTime() - this.time;
	}
	
	private void bt() {
		if(this.estado.vertice.index() == DatosTyP.n) {
			Integer value = estado.valorAcumulado;
			if(this.minValue == null || value < this.minValue) {
				this.minValue = value;
				this.solucion = this.estado.solucion();
			}
		} else {
			List<Integer> alternativas = this.estado.vertice().actions();
			for(Integer a:alternativas) {	
				Integer cota = Heuristica.cota(this.estado.vertice,a);
				if(this.minValue != null && cota >= this.minValue) continue;
				this.estado.forward(a);
				bt();  
				this.estado.back(a);
			}
		}
	}
	
	public SolucionTyP solucion() {
		return this.solucion;
	}


	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosTyP.datos("ficheros/tareas.txt",5);
		TyPVertex v1 = TyPVertex.first();
		SolucionTyP s = Heuristica.solucionVoraz(v1);
		System.out.println(s);
		TyPBT a = TyPBT.of();
		a.bt(v1,null,null);
		System.out.println("1 = "+a.time());
		System.out.println(a.solucion());
		a.bt(v1,s.getMaxCarga().intValue(),s);
		System.out.println("2 = "+a.time());
		System.out.println(a.solucion());
	}

}
