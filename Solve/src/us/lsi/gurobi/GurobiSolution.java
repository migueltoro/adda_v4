package us.lsi.gurobi;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class GurobiSolution {

	public static GurobiSolution of(Double objVal, Map<String, Double> values) {
		return new GurobiSolution(objVal, values);
	}

	public Double objVal;
	public Map<String,Double> values;
	
	private GurobiSolution(Double objVal, Map<String, Double> values) {
		super();
		this.objVal = objVal;
		this.values = values;
	}
	
	public String toString(BiPredicate<String,Double> pd) {
		return String.format("\n\n\nEl valor objetivo es %.2f\nLos valores de la variables\n%s",this.objVal,
				this.values.entrySet()
				.stream()
				.filter(e->pd.test(e.getKey(),e.getValue()))
				.sorted(Comparator.comparing(e->e.getKey()))
				.map(e->String.format("%s == %d",e.getKey(),e.getValue().intValue()))
				.collect(Collectors.joining("\n")));
	}
	
	private String allValues() {
		return this.values.entrySet()
				.stream()
				.map(e->String.format("%s == %.1f",e.getKey(),e.getValue()))
				.collect(Collectors.joining("\n"));
	}

	@Override
	public String toString() {
		return String.format("\n\n\nEl valor objetivo es %.2f\nLos valores de la variables\n%s",
				this.objVal,this.allValues());
	}
	
}
