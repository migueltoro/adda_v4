package us.lsi.alg.monedas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record MonedasVertexI(Integer index,Integer valorRestante) 
	implements  MonedasVertex{

	public static MonedasVertexI of(Integer index, Integer valorRestante) {
		return new MonedasVertexI(index, valorRestante);
	}

	public static MonedasVertexI first(Integer valorInicial) {
		return new MonedasVertexI(0,valorInicial);
	}
	
	public static MonedasVertexI last() {
		return new MonedasVertexI(DatosMonedas.n,0);
	}
	
	@Override
	public Boolean goal() {
		return this.index() == DatosMonedas.n;
	}
	
	@Override
	public Boolean goalHasSolution() {
		return this.valorRestante() == 0;
	}
	
	public MonedasVertex copy() {
		return MonedasVertexI.of(this.index(), this.valorRestante());
	}
	
	public Integer size() {
		return DatosMonedas.n - this.index();
	}
	
	@Override
	public Boolean isValid() {
		return this.index() >=0 && this.index() <= DatosMonedas.n && this.valorRestante() >=0;
	}

	@Override
	public Integer greedyAction() {
		return this.valorRestante()/DatosMonedas.valor(this.index());
	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> r;
		if(this.index() == DatosMonedas.n) r = List.of();
		else if(this.index() == DatosMonedas.n-1 && this.valorRestante%DatosMonedas.valor(this.index) == 0) {
			r = List.of(this.greedyAction());
		} else if(this.index() == DatosMonedas.n-1 && this.valorRestante%DatosMonedas.valor(this.index) != 0) {
		    r = List.of();
		} else {
			Integer nue = this.valorRestante()/DatosMonedas.valor(this.index);
			r = IntStream.range(0,nue+1).boxed().collect(Collectors.toList());
			Collections.reverse(r);
		}
		return r;
	}

	@Override
	public MonedasVertex neighbor(Integer a) {
		MonedasVertexI r;
		if(this.valorRestante() == 0) r = MonedasVertexI.last();
		else r = MonedasVertexI.of(this.index()+1,this.valorRestante()-a*DatosMonedas.valor(this.index()));
		return r;
	}

	@Override
	public MonedasEdge edge(Integer a) {
		return MonedasEdge.of(this,this.neighbor(a),a);
	}
	
}
