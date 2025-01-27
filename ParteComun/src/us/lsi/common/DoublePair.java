package us.lsi.common;

public record DoublePair(Double first,Double second) {

	
	public static DoublePair of(Double a, Double b) {
		return new DoublePair(a, b);
	}
	
	public static DoublePair of(Pair<Double,Double> p) {
		return new DoublePair(p.first(), p.second());
	}
	
	public static DoublePair parse(String s) {
		String[] partes = s.split("[(),]");
		return new DoublePair(Double.parseDouble(partes[0].trim()), Double.parseDouble(partes[1].trim()));
	}
	
	@Override
	public String toString() {
		return String.format("(%f,%f)",this.first(),this.second());
	}

	public DoublePair add(DoublePair p) {
		return DoublePair.of(this.first()+p.first(), this.second()+p.second());
	}

	public DoublePair minus(DoublePair p) {
		return DoublePair.of(this.first()-p.first(), this.second()-p.second());
	}
	
	public DoublePair multiply(Double e) {
		return DoublePair.of(e*this.first(), e*this.second());
	}
	
	public Double sumAbs() {
		return Math.abs(this.first())+Math.abs(this.second());
	}
	
	public Double module() {
		return Math.sqrt(this.first()*this.first()+this.second()*this.second());
	}
	
	public Double manhattan(DoublePair p) {
		return this.minus(p).sumAbs();
	}
	
	public Double size() {
		return this.second()-this.first();
	}
	
	public Double center() {
		return (this.second()+this.first())/2;
	}
	
	public View2E<DoublePair,Double> view2e() {
		Double k = (this.second()+this.first())/2;
		return View2E.of(k,DoublePair.of(this.first(),k),DoublePair.of(k,this.second()));
	}
}

