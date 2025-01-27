package us.lsi.flowgraph;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.common.Preconditions;

/**
 * Un vértice de una Red de Fujo.
 * Un vértice de este tipo tiene asociado un coste unitario, un flujo máximo, otro mínimo 
 * y el tipo de vértice
 * 
 * @author Miguel Toro
 *
 */
public class FlowVertex {
	
	public enum TipoDeVertice{Source,Sink,Intermediate};
	
	public static List<FlowVertex> vertices = List2.empty();
	
	public static Double maxDouble = Double.MAX_VALUE;
	
	private final String id;	
	public final TipoDeVertice tipo;
	public final Double min;
	public final Double max;
	public final Double cost;
	public final String name;

	public static FlowVertex create(String[] formato) {
		FlowVertex v = new FlowVertex(formato);
		FlowVertex.vertices.add(v);
		return v;
	}
	
	
	private Double convert(String s) {
		Double r;
		if(s.equals("inf")) {
			r = maxDouble;
		}else {
			r = Double.parseDouble(s);
		}
		return r;
	}
	
	public String convert(Double s) {
		String r;
		if(s > 1000000000.0) {
			r = "inf";
		}else {
			r = String.format("%.2f",s);
		}
		return r;
	}
	
	private TipoDeVertice tipoDeVertice(String text) {
		TipoDeVertice r = null;
		text = text.trim();
		switch(text) {
		case "1":
		case "Source": r = TipoDeVertice.Source; break;
		case "2":
		case "Sink" : r = TipoDeVertice.Sink;break;
		case "0":
		case "Intermediate" : r = TipoDeVertice.Intermediate;break;
		default: Preconditions.checkArgument(false,String.format("Tipo %s no permitido",text));
		}
		return r;
	}
	
	private FlowVertex(String[] formato) {		
		this.id = formato[0];
		if (formato.length==1) {
			this.tipo = TipoDeVertice.Intermediate;
			this.min = 0.;
			this.max = maxDouble;
			this.cost = 0.;
			this.name = "";
		}else if (formato.length==2) {
			this.tipo = tipoDeVertice(formato[1]);
			this.min = 0.;
			this.max = maxDouble;
			this.cost = 0.;
			this.name = "";				
		}else if(formato.length==5) {
			this.tipo = tipoDeVertice(formato[1]);
			this.min = convert(formato[2]);
			this.max = convert(formato[3]);
			this.cost = convert(formato[4]);
			this.name = "";	
		} else if(formato.length==6){
			this.tipo = tipoDeVertice(formato[1]);
			this.min = convert(formato[2]);
			this.max = convert(formato[3]);
			this.cost = convert(formato[4]);
			this.name = formato[5];
		} else {
			throw new IllegalArgumentException("Formato incorrecto");
		}
	}
	
	public Integer getColor() {
		Integer r = null;
		switch(this.tipo) {
		case Source:r=9;break;
		case Sink:r=4;break;
		case Intermediate:r=0;
		}
		return r;
	}

	public boolean isSource() {
		return this.tipo==TipoDeVertice.Source;
	}

	public boolean isSink() {
		return this.tipo==TipoDeVertice.Sink;
	}

	public boolean isIntermediate() {
		return this.tipo==TipoDeVertice.Intermediate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlowVertex other = (FlowVertex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return name.equals("")?id:name;
	}
	
}
