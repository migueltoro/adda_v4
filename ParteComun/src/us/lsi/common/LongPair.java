package us.lsi.common;

public record LongPair(Long first,Long second) {

	
	public static LongPair of(Long a, Long b) {
		return new LongPair(a, b);
	}
	
	public static LongPair of(Pair<Long,Long> p) {
		return new LongPair(p.first(), p.second());
	}
	
	public static LongPair parse(String s) {
		String[] partes = s.split("[(),]");
		return new LongPair(Long.parseLong(partes[0].trim()), Long.parseLong(partes[1].trim()));
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d)",this.first(),this.second());
	}

	public LongPair add(LongPair p) {
		return LongPair.of(this.first()+p.first(), this.second()+p.second());
	}

	public LongPair minus(LongPair p) {
		return LongPair.of(this.first()-p.first(), this.second()-p.second());
	}
	
	public LongPair multiply(Long e) {
		return LongPair.of(e*this.first(), e*this.second());
	}
	
	public Long sumAbs() {
		return Math.abs(this.first())+Math.abs(this.second());
	}
	
	public Double module() {
		return Math.sqrt(this.first()*this.first()+this.second()*this.second());
	}
	
	public Long manhattan(LongPair p) {
		return this.minus(p).sumAbs();
	}
	
	public Long size() {
		return this.second()-this.first();
	}
	
	public Long center() {
		return (this.second()+this.first())/2;
	}
	
	public View1<LongPair,Long> view1() {
		return View1.of(this.first(),LongPair.of(this.first()+1,this.second()));
	}
	
	public View2E<LongPair,Long> view2e() {
		Long k = (this.second()+this.first())/2;
		return View2E.of(k,LongPair.of(this.first(),k),LongPair.of(k,this.second()));
	}
	
	public View2E<LongPair,Long> view2eOverlap() {
		Long k = (this.second()+this.first())/2;
		return View2E.of(k,LongPair.of(this.first(),k+1),LongPair.of(k,this.second()));
	}
}
