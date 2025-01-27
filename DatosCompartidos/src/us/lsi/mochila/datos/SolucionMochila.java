package us.lsi.mochila.datos;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> Esta clase implementa el tipo SolucionMochila. </p>
 * <p> Las propiedades de estos objetos son: </p>
 * <ul>
 * <li> Objetos en la mochila (básica, de tipo Multiset&lt;ObjetoMochila&gt;)
 * <li> Valor (derivada)
 * <li> Peso (derivada)
 * </ul> 
 * 
 * 
 * 
 * @author Miguel Toro
 *
 */
public class SolucionMochila implements Comparable<SolucionMochila>{
	
	public static SolucionMochila empty() {
		return new SolucionMochila();
	}

	private Map<ObjetoMochila,Integer> m;	

	private SolucionMochila() {
		super();
		this.m = new HashMap<ObjetoMochila,Integer>();
	}
	
	private SolucionMochila(Map<ObjetoMochila,Integer> m) {
		super();
		this.m = new HashMap<>(m);
	}
		
	public void add(ObjetoMochila ob, int nu) {
		Integer n = nu;
		if(m.containsKey(ob)) n = nu+m.get(ob);			
		this.m.put(ob, n);	
	}
	
	public void remove(ObjetoMochila ob, int nu) {
		this.m.put(ob, Math.max(m.get(ob)-nu,0));	
	}
	
	public SolucionMochila copy() {
		return new SolucionMochila(m);
	}
	
	public Integer count(ObjetoMochila ob){
		return m.get(ob);
	}
	
	public Set<ObjetoMochila> elements(){
		return m.keySet();
	}
	
	public Integer getValor() {	
		return m.keySet()
				.stream()
				.mapToInt(x->m.get(x)*x.valor())
				.sum();
	}
	
	public Integer getPeso() {
		return m.keySet()
				.stream()
				.mapToInt(x->m.get(x)*x.peso())
				.sum();
	}
	
	public String getObjetos() {
		return DatosMochila.getObjetos()
				.stream()
			    .map(x->String.format("%s=%d",x,m.get(x)==null?0:m.get(x)))
			    .collect(Collectors.joining(", ","{","}"));
	}
	
	public String toString() {
		return String.format("valor = %d, peso = %d,\n%s", 
				getValor(),getPeso(),getObjetos()); 
	}

	@Override
	public int compareTo(SolucionMochila sm) {
		return this.getValor().compareTo(sm.getValor());
	}	
}
