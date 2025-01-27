package us.lsi.flowgraph;

import java.util.List;

import us.lsi.common.List2;
import us.lsi.common.Preconditions;

/**
 * Una arista simple de una Red de Fujo.
 * Cada arista de este tipo tiene asociado un coste unitario, un flujo máximo y otro mínimo 
 * 
 * @author Miguel Toro
 *
 */
public class FlowEdge {
	
	public static List<FlowEdge> edges = List2.empty();
	public final Double min;
	public final Double max;
	public final Double cost;
	public final String name;
	private final Integer id;
	
	private static Integer nId = 0;
	
	public static FlowEdge create(String[] formato) {
		FlowEdge r = new FlowEdge(formato);
		FlowEdge.edges.add(r);
		return r;
	}
	
	public static FlowEdge get(String variable) {
		Preconditions.checkArgument(variable.charAt(0) == 'e');
		String r = variable.substring(1);
		int index = Integer.parseInt(r);
		return FlowEdge.edges.get(index);
	}
	
	private Double convert(String s) {
		Double r;
		if(s.equals("inf")) {
			r = FlowVertex.maxDouble;
		}else {
			r = Double.parseDouble(s);
		}
		return r;
	}
	
	private FlowEdge(String[] formato) {
		super();
		if(formato.length == 2) {
			this.min = 0.;
			this.max = FlowVertex.maxDouble;
			this.cost = 0.;
			this.name = "";
		} else if(formato.length == 5) {
			this.min = convert(formato[2]);
			this.max = convert(formato[3]);
			this.cost = convert(formato[4]);
			this.name = "";
		} else if(formato.length == 6) {
			this.min = convert(formato[2]);
			this.max = convert(formato[3]);
			this.cost = convert(formato[4]);
			this.name = formato[5];
		} else {
			throw new IllegalArgumentException("Formato incorrecto");
		}
		this.id = nId;
		nId++;	
	}
	
	public String number(Double d) {
		return String.format("%+.1f", d);
	}
	
	@Override
	public String toString() {
		return (name.equals("")?"":" = "+name);
	}
	
	public String toStringLong() {
		return String.format("(%d,%.2f,%.2f,%2.f)",this.id,this.min,this.max,this.cost);
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
		if (!(obj instanceof FlowEdge))
			return false;
		FlowEdge other = (FlowEdge) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
