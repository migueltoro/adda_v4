package us.lsi.alg.cursos.manual;

import java.util.List;
import java.util.Locale;
import us.lsi.alg.cursos.CursosVertexI;
import us.lsi.alg.cursos.DatosCursos;
import us.lsi.alg.cursos.SolucionCursos;



public class BTCursos {
	
	public static BTCursos of() {
		return new BTCursos();
	}
	
	private CursosVertexI start;
	private StateCursos estado;
	private SolucionCursos solucion;
	public Double minValue;
	private Long time;
	
	private BTCursos() {
		super();
	}

	public void bt(Double minValue, SolucionCursos s) {
		this.time = System.nanoTime();
		this.solucion = s;
		this.minValue = minValue;
		this.start = CursosVertexI.first();
		this.estado = StateCursos.of(this.start);
		btm();
		this.time = System.nanoTime() - this.time;
	}
	
	public Long time() {
		return time;
	}
	
	public SolucionCursos solucion() {
		return solucion;
	}

	private void btm() {
		if(this.estado.vertice().index() == DatosCursos.n && 
				this.estado.vertice().remaining().isEmpty() &&
				this.estado.vertice().centers().size() <= DatosCursos.maxCentros) {
			Double value = estado.valorAcumulado();
			if(this.minValue == null || value < this.minValue) {
				this.minValue = value;
				this.solucion = SolucionCursos.of(this.estado.acciones());
			}
		} else {
			List<Integer> alternativas = this.estado.vertice().actions();
			for(Integer a:alternativas) {	
				Double wp = this.estado.valorAcumulado()+GreedyCursos.cota(this.estado.vertice(),a);
				if(this.minValue != null && wp >= this.minValue) continue;
				this.estado.forward(a);
				btm();  
				this.estado.back(a);
			}
		}
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosCursos.iniDatos("ficheros/cursos/cursos3.txt");
		CursosVertexI v1 = CursosVertexI.first();
		SolucionCursos sv = GreedyCursos.solucionVoraz(v1);
		System.out.println(sv);
		BTCursos a = BTCursos.of();
		a.bt(null,null);
		System.out.println(a.time() + "  === \n"+a.solucion());
		a.bt(sv.precio(),sv);
		System.out.println(a.time() + "  === \n"+a.solucion());
	}

}
