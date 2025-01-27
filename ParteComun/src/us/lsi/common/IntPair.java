package us.lsi.common;

public record IntPair(Integer first,Integer second) {

	
	public static IntPair of(Integer a, Integer b) {
		return new IntPair(a, b);
	}
	
	public static IntPair of(Pair<Integer,Integer> p) {
		return new IntPair(p.first(), p.second());
	}
	
	public static IntPair parse(String s) {
		String[] partes = s.split("[(),]");
		return new IntPair(Integer.parseInt(partes[0].trim()), Integer.parseInt(partes[1].trim()));
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d)",this.first(),this.second());
	}

	public IntPair add(IntPair p) {
		return IntPair.of(this.first()+p.first(), this.second()+p.second());
	}

	public IntPair minus(IntPair p) {
		return IntPair.of(this.first()-p.first(), this.second()-p.second());
	}
	
	public IntPair multiply(Integer e) {
		return IntPair.of(e*this.first(), e*this.second());
	}
	
	public Double module() {
		return Math.sqrt(this.first()*this.first()+this.second()*this.second());
	}
	
	public Integer manhattan(IntPair p) {
		IntPair r = this.minus(p);
		return Math.abs(r.first())+Math.abs(r.second());
	}
	
	public Integer size() {
		return this.second()-this.first();
	}
	
	public Integer center() {
		return (this.second()+this.first())/2;
	}
	
	public View1<IntPair,Integer> view1() {
		return View1.of(this.first(),IntPair.of(this.first()+1,this.second()));
	}
	
	public View2E<IntPair,Integer> view2e() {
		Integer k = (this.second()+this.first())/2;
		return View2E.of(k,IntPair.of(this.first(),k),IntPair.of(k,this.second()));
	}
	
	public View2E<IntPair,Integer> view2eOverlap() {
		Integer k = (this.second()+this.first())/2;
		return View2E.of(k,IntPair.of(this.first(),k+1),IntPair.of(k,this.second()));
	}
}
