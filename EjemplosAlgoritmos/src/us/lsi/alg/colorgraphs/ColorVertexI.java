package us.lsi.alg.colorgraphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.IntegerSet;
import us.lsi.common.List2;
import us.lsi.streams.Collectors2;


public record ColorVertexI(Integer index, Map<Integer,Integer> cav) 
           implements  ColorVertex{

	public static ColorVertexI of(Integer index, Map<Integer, Integer> cav) {
		return new ColorVertexI(index, cav);
	}
	
	public static ColorVertexI first() {
		return new ColorVertexI(0,new HashMap<>());
	}
	
	public static ColorVertex last() {
		return new ColorVertexI(DatosColor.n,new HashMap<>());
	}
	
	@Override
	public Boolean goal() {
		return this.index() == DatosColor.n;
	}
	
	@Override
	public Boolean goalHasSolution() {
		return true;
	}
	
	@Override
	public IntegerSet ca(){
		return this.cav().values().stream().distinct().collect(Collectors2.toIntegerSet());
	}
	
	@Override
	public Integer nc() {
		return this.ca().size();
	}
	
	@Override
	public IntegerSet cv(){
		IntegerSet r;
		if (index < DatosColor.n) {
			r = AuxiliaryColor.coloresDeVecinos(index(), DatosColor.graph, cav());
		} else {
			r = IntegerSet.empty();
		}
		return r;
	}

	@Override
	public Boolean isValid() {
		return this.index()>=0 && this.index() <= DatosColor.n;
	}


	@Override
	public List<Integer> actions() {		
		List<Integer> r = List2.difference(DatosColor.colors,this.cv());
		return r;
	}
	
	public Integer greedyAction() {
		List<Integer> r = List2.difference(this.ca(),this.cv());
		if(r.isEmpty()) r = List2.difference(DatosColor.colors,this.cv());
		return List2.randomUnitary(r).get(0);
	}
	
	public ColorEdge greedyEdge() {
		return this.edge(this.greedyAction());
	}


	@Override
	public ColorVertexI neighbor(Integer a) {
		Map<Integer,Integer> m = new HashMap<>(this.cav());
		m.put(this.index(),a);
		return ColorVertexI.of(this.index()+1,m);
	}


	@Override
	public ColorEdge edge(Integer a) {
		return ColorEdge.of(this,this.neighbor(a), a);
	}

	
}
